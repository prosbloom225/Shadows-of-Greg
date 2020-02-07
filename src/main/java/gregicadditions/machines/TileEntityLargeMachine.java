package gregicadditions.machines;

import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.*;
import gregtech.api.recipes.crafttweaker.ChancedEntry;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockMetalCasing.MetalCasingType;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static gregtech.api.recipes.Recipe.getMaxChancedValue;


public class TileEntityLargeMachine extends RecipeMapMultiblockController {

	// TODO - force the required abilites from the enum
	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};
    public enum MachineType {

		ORE_WASHER(RecipeMaps.ORE_WASHER_RECIPES, MetaBlocks.METAL_CASING.getState(MetalCasingType.STEEL_SOLID), Textures.SOLID_STEEL_CASING,
				4,
				4.0),
		MACERATOR(RecipeMaps.MACERATOR_RECIPES, MetaBlocks.METAL_CASING.getState(MetalCasingType.STEEL_SOLID), Textures.SOLID_STEEL_CASING,
				8,
				1.6);

		public final RecipeMap recipeMap;
		public final IBlockState casingState;
		public final ICubeRenderer casingRenderer;
		public final double speedMulti;
		public final int operationsPerTier;

		MachineType(RecipeMap recipeMap, IBlockState casingState, ICubeRenderer casingRenderer, int operationsPerTier, double speedMulti) {
			this.recipeMap = recipeMap;
			this.casingState = casingState;
			this.casingRenderer = casingRenderer;
			this.operationsPerTier = operationsPerTier;
			this.speedMulti = speedMulti;
		}
	}
	public MachineType machineType;


	public TileEntityLargeMachine(ResourceLocation metaTileEntityId, MachineType type) {
		super(metaTileEntityId, type.recipeMap);
		this.machineType = type;
		LargeMachineWorkable largeMachineWorkable = new LargeMachineWorkable(this, type.speedMulti);
		this.recipeMapWorkable = largeMachineWorkable;
        // TODO - add custom recipeworkable logic
		reinitializeStructurePattern();
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return machineType == null ? null :
				FactoryBlockPattern.start()
						.aisle("XXX", "XXX", "XXX")
						.aisle("XXX", "X#X", "XXX")
						.aisle("XXX", "XSX", "XXX")
						.setAmountAtLeast('L', 10)
						.where('S', selfPredicate())
						.where('L', statePredicate(getCasingState()))
						.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
						.where('#', isAirPredicate())
						.build();
	}


	public IBlockState getCasingState() {
		return machineType.casingState;
	}

	@Override
	protected void formStructure(PatternMatchContext context) {
		super.formStructure(context);
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart arg0) {
		// TODO Auto-generated method stub
		return machineType.casingRenderer;
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		// TODO Auto-generated method stub
		return new TileEntityLargeMachine(metaTileEntityId, machineType);
	}

	protected class LargeMachineWorkable extends MultiblockRecipeLogic {
		private final double speedMulti;

		public LargeMachineWorkable(RecipeMapMultiblockController tileEntity, double speedMulti) {
			super(tileEntity);
			this.speedMulti = speedMulti;
		}

		@Override
		protected int[] calculateOverclock(int EUt, long voltage, int duration) {
			return super.calculateOverclock(EUt, voltage, duration);
		}

		@Override
		protected int getOverclockingTier(long voltage) {
			return super.getOverclockingTier(voltage);
		}

		@Override
		protected void trySearchNewRecipe() {
			long maxVoltage = getMaxVoltage();
			Recipe currentRecipe = null;
			IItemHandlerModifiable importInventory = getInputInventory();
			IMultipleTankHandler importFluids = getInputTank();
			boolean dirty = checkRecipeInputsDirty(importInventory, importFluids);
			//inverse of logic in normal AbstractRecipeLogic
			//for MultiSmelter, we can reuse previous recipe if inputs didn't change
			//otherwise, we need to recompute it for new ingredients
			//but technically, it means we can cache multi smelter recipe, but changing inputs have more priority
			if (dirty || forceRecipeRecheck) {
				this.forceRecipeRecheck = false;
				//else, try searching new recipe for given inputs
				currentRecipe = findRecipe(maxVoltage, importInventory, importFluids);
				if (currentRecipe != null) {
					this.previousRecipe = currentRecipe;
				}
			} else if (previousRecipe != null && previousRecipe.matches(false, importInventory, importFluids)) {
				//if previous recipe still matches inputs, try to use it
				currentRecipe = previousRecipe;
			}
			if (currentRecipe != null && setupAndConsumeRecipeInputs(currentRecipe)) {
				setupRecipe(currentRecipe);
			}
		}

		/**
		 * Custom findRecipe logic for large machines
		 * @param maxVoltage
		 * @param inputs
		 * @param fluidInputs
		 * @return Combined recipe to be processed as a single recipe
		 */
		@Override
		protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
			int currentItemsEngaged = 0;
			int maxItemsLimit = machineType.operationsPerTier * getOverclockingTier(getEnergyContainer().getInputVoltage());
			List<Recipe.ChanceEntry> chanceOutputs = new ArrayList<>();
            int currDuration = 0;
            int currEuT = 0;
			ArrayList<CountableIngredient> recipeInputs = new ArrayList<>();
			ArrayList<ItemStack> recipeOutputs = new ArrayList<>();
			for (int index = 0; index < inputs.getSlots(); index++) {
				ItemStack stackInSlot = inputs.getStackInSlot(index);
				if (stackInSlot.isEmpty())
					continue;
				Recipe matchingRecipe = recipeMap.findRecipe(maxVoltage,
						Collections.singletonList(stackInSlot), Collections.emptyList(), 0);
				CountableIngredient inputIngredient = matchingRecipe == null ? null : matchingRecipe.getInputs().get(0);
				if (inputIngredient != null && (maxItemsLimit - currentItemsEngaged) >= inputIngredient.getCount()) {
					ItemStack outputStack = matchingRecipe.getOutputs().get(0).copy();
					int overclockAmount = Math.min(stackInSlot.getCount() / inputIngredient.getCount(),
							(maxItemsLimit - currentItemsEngaged) / inputIngredient.getCount());
					CountableIngredient ingredient = new CountableIngredient(inputIngredient.getIngredient(),
							inputIngredient.getCount() * overclockAmount);
					recipeInputs.add(ingredient);
					if (!outputStack.isEmpty()) {
						outputStack.setCount(outputStack.getCount() * overclockAmount);
						recipeOutputs.add(outputStack);
					}
					recipeOutputs.addAll(customChanceOutput(matchingRecipe.getChancedOutputs(), ingredient.getCount(), getMachineTierForRecipe(matchingRecipe)));
					for (int i=0; i < ingredient.getCount(); i++)
						chanceOutputs.addAll(matchingRecipe.getChancedOutputs());
					currentItemsEngaged += inputIngredient.getCount() * overclockAmount;
					// use the max duration and EuT
					currDuration = Math.max(currDuration, matchingRecipe.getDuration());
					currEuT = Math.max(currEuT, matchingRecipe.getEUt());
				}
				// check that there is output availability
				// this will still void items over the count, this just compares a slot for each recipe
				if (recipeOutputs.size() > this.getOutputInventory().getSlots())
				    return null;
				// TODO - handle if not enough fluid space for recipe
				if (currentItemsEngaged >= maxItemsLimit)
					break;
			}
			return recipeInputs.isEmpty() ? null : recipeMap.recipeBuilder()
					.inputsIngredients(recipeInputs)
					.outputs(recipeOutputs)
					.chancedOutput(recipeOutputs.get(0), 1, 1)
					.EUt(currEuT)
					.duration((int) Math.max(1.0, currDuration / speedMulti))
					.build().getResult();
		}

		/**
		 * Generates the collated chanced outputs for a given recipe.  Recipe.class doesn't collate multiple
		 * instances and drops the outputs.  This is needed to handle large recipes.  Does not take into account
		 * output inventory size.
		 * @param entries items that are rolled for chancedoutput
		 * @param count number of times recipe is being processed
		 * @param tier tier the machine is processing the recipe at
		 * @return List of ItemStack to be added to recipeOutputs
		 */
		private List<ItemStack> customChanceOutput(List<Recipe.ChanceEntry> entries, int count, int tier){
			// TODO - change output to map.  nested loops are inefficient
			List<ItemStack> ret = new ArrayList<>();
			Iterator iter = entries.iterator();
			while(iter.hasNext()) {
				Recipe.ChanceEntry chancedOutput = (Recipe.ChanceEntry)iter.next();
				int outputChance = chancedOutput.getChance() + chancedOutput.getBoostPerTier() * tier;
				if (random.nextInt(getMaxChancedValue()) <= outputChance) {
					ItemStack output = chancedOutput.getItemStack().copy();
					for (int i=0; i < count; i++)
						if (random.nextInt(getMaxChancedValue()) <= chancedOutput.getChance() + chancedOutput.getBoostPerTier() * tier)
							if (output.getCount() < output.getMaxStackSize())
								output.setCount(output.getCount()+1);
							else{
							    ret.add(output);
							    output = chancedOutput.getItemStack().copy();
							}
					ret.add(output);
				}
			}
			return ret;
		}

	}
}

