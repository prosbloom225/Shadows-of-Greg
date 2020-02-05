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
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.machines.FuelRecipeMap;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockMetalCasing.MetalCasingType;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class TileEntityLargeMachine extends RecipeMapMultiblockController {

	// TODO - force the required abilites from the enum
	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};
    public enum MachineType {

        /*
		STEAM(RecipeMaps.STEAM_TURBINE_FUELS, MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_TURBINE_CASING), Textures.SOLID_STEEL_CASING, true),
		GAS(RecipeMaps.GAS_TURBINE_FUELS, MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STAINLESS_TURBINE_CASING), Textures.CLEAN_STAINLESS_STEEL_CASING, false),
		PLASMA(RecipeMaps.PLASMA_GENERATOR_FUELS, MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TUNGSTENSTEEL_TURBINE_CASING), Textures.ROBUST_TUNGSTENSTEEL_CASING, true);
         */
		ORE_WASHER(RecipeMaps.ORE_WASHER_RECIPES, MetaBlocks.METAL_CASING.getState(MetalCasingType.STEEL_SOLID), Textures.SOLID_STEEL_CASING, 400);

		public final RecipeMap recipeMap;
		public final IBlockState casingState;
		public final ICubeRenderer casingRenderer;
		public final int speedMulti;

		MachineType(RecipeMap recipeMap, IBlockState casingState, ICubeRenderer casingRenderer, int speedMulti) {
			this.recipeMap = recipeMap;
			this.casingState = casingState;
			this.casingRenderer = casingRenderer;
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
		private final int speedMulti;

		public LargeMachineWorkable(RecipeMapMultiblockController tileEntity, int speedMulti) {
			super(tileEntity);
			this.speedMulti = speedMulti;
		}

		@Override
		protected int[] calculateOverclock(int EUt, long voltage, int duration) {
			return super.calculateOverclock(EUt, voltage, duration/ speedMulti);
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
			if(dirty || forceRecipeRecheck) {
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

		@Override
		protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
			int currentItemsEngaged = 0;
			int maxItemsLimit = 32;
			ArrayList<CountableIngredient> recipeInputs = new ArrayList<>();
			ArrayList<ItemStack> recipeOutputs = new ArrayList<>();
			for (int index = 0; index < inputs.getSlots(); index++) {
				ItemStack stackInSlot = inputs.getStackInSlot(index);
				if (stackInSlot.isEmpty())
					continue;
				// TODO - this only handles single fluid recipes.. need to refactor for chem reactor
				for (IFluidTank tank : fluidInputs.getFluidTanks()){
					Recipe matchingRecipe = recipeMap.findRecipe(maxVoltage,
							Collections.singletonList(stackInSlot), Collections.singletonList(tank.getFluid()), 0);
				CountableIngredient inputIngredient = matchingRecipe == null ? null : matchingRecipe.getInputs().get(0);
				if (inputIngredient != null && (maxItemsLimit - currentItemsEngaged) >= inputIngredient.getCount()) {
					ItemStack outputStack = matchingRecipe.getOutputs().get(0).copy();
					// TODO - handle chanced outputs
					int overclockAmount = Math.min(stackInSlot.getCount() / inputIngredient.getCount(),
							(maxItemsLimit - currentItemsEngaged) / inputIngredient.getCount());
					recipeInputs.add(new CountableIngredient(inputIngredient.getIngredient(),
							inputIngredient.getCount() * overclockAmount));
					if (!outputStack.isEmpty()) {
						outputStack.setCount(outputStack.getCount() * overclockAmount);
						recipeOutputs.add(outputStack);
					}
					currentItemsEngaged += inputIngredient.getCount() * overclockAmount;
				}
					if (currentItemsEngaged >= maxItemsLimit) break;
				}
			}
			return recipeInputs.isEmpty() ? null : recipeMap.recipeBuilder()
					.inputsIngredients(recipeInputs)
					.fluidInputs()
					.outputs(recipeOutputs)
					.EUt(8) // TODO - get proper eut
					.duration((int) Math.max(1.0, 256 * (currentItemsEngaged / (maxItemsLimit * 1.0))))
					.build().getResult();
		}
	}
}

