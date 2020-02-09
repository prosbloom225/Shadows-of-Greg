package gregicadditions.machines;

import com.google.common.collect.Sets;
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
import gregtech.api.util.ValidationResult;
import gregtech.common.blocks.BlockMetalCasing.MetalCasingType;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
				1.6),
		CENTRIFUGE(RecipeMaps.CENTRIFUGE_RECIPES, MetaBlocks.METAL_CASING.getState(MetalCasingType.STEEL_SOLID), Textures.SOLID_STEEL_CASING,
				6,
				1.25);

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
			if (dirty || forceRecipeRecheck) {
				this.forceRecipeRecheck = false;

				// else, try searching new recipe for given inputs
				if (previousRecipe != null)
					recipeMap.removeRecipe(previousRecipe);
				currentRecipe = findRecipe(maxVoltage, importInventory, importFluids);
				if (currentRecipe != null) {
					this.previousRecipe = currentRecipe;
				}
			} else if (previousRecipe != null && previousRecipe.matches(false, importInventory, importFluids)) {
				// if previous recipe still matches inputs, try to use it
				currentRecipe = previousRecipe;
			}
			if (currentRecipe != null && setupAndConsumeRecipeInputs(currentRecipe)) {
				setupRecipe(currentRecipe);
			}

		}

		@Override
		protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
			Recipe recipe = super.findRecipe(maxVoltage, inputs, fluidInputs);
			if (recipe != null) {
				int maxOperations = 8;
				List<Integer> overclocks = new ArrayList<>();
				// calculate fluid overclock
				List<FluidStack> fi = fluidInputs.getFluidTanks().stream().map(f -> f.getFluid()).collect(Collectors.toList());
				for (FluidStack stack : recipe.getFluidInputs()) {
					overclocks.add(fi.stream().filter(f -> f.getFluid() == stack.getFluid()).findAny().orElse(null).amount / stack.amount);
				}
				// calculate item overclock
				List<ItemStack> ii = new ArrayList<>();
				for (int i = 0; i < inputs.getSlots(); i++)
					if (!inputs.getStackInSlot(i).isEmpty())
						ii.add(inputs.getStackInSlot(i));
				for (CountableIngredient ingredient : recipe.getInputs()) {
					overclocks.add(ii.stream().filter(i -> i.getItem() ==
							ingredient.getIngredient().getMatchingStacks()[0].getItem()).findAny().orElse(null).getCount() / ingredient.getCount());
				}

				// total overclock
				int overclockAmount = Math.min(maxOperations, overclocks.stream().mapToInt(v -> v).min().getAsInt());


				// item
				List<CountableIngredient> iin = recipe.getInputs().stream().map(i->new CountableIngredient(i.getIngredient(),
						i.getCount()*overclockAmount)).collect(Collectors.toList());
				List<ItemStack> out = new ArrayList<>();
				for (ItemStack stack : recipe.getOutputs()) {
					stack.setCount(stack.getCount() * overclockAmount);
					out.add(stack);
				}

				// fluid
				List<FluidStack> fin = recipe.getFluidInputs().stream().map(f->new FluidStack(f.getFluid(), f.amount * overclockAmount)).collect(Collectors.toList());

				// chance outputs
				//out.addAll(customChanceOutput(recipe.getChancedOutputs(), overclockAmount,getMachineTierForRecipe(recipe)));

				Recipe newRecipe = recipeMap.recipeBuilder()
						.inputsIngredients(iin)
						.fluidInputs(fin)
						.outputs(out)
						//.chancedOutput(recipeOutputs.get(0), 1, 1)
						.EUt(recipe.getEUt())
						.duration((int) Math.max(1.0, recipe.getDuration()/ speedMulti))
						.build().getResult();
				return newRecipe;
			}
			return null;
		}


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

