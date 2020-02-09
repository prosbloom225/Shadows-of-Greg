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
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.crafttweaker.ChancedEntry;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.GTLog;
import gregtech.api.util.GTUtility;
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

import javax.annotation.Nullable;
import java.util.*;
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
		LargeMachineWorkable largeMachineWorkable = new LargeMachineWorkable(this,
				type.speedMulti,
				type.operationsPerTier);
		this.recipeMapWorkable = largeMachineWorkable;
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
		private final int operationsPerTier;

		public LargeMachineWorkable(RecipeMapMultiblockController tileEntity, double speedMulti, int operationsPerTier) {
			super(tileEntity);
			this.operationsPerTier = operationsPerTier;
			this.speedMulti = speedMulti;
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
			if (recipe == null)
				return null;
			List<Integer> overclocks = new ArrayList<>();
			// calculate fluid overclock
			List<FluidStack> fi = fluidInputs.getFluidTanks().stream().map(f -> f.getFluid()).collect(Collectors.toList());
			for (FluidStack stack : recipe.getFluidInputs()) {
				overclocks.add(fi.stream().filter(f -> f.getFluid() == stack.getFluid()).findAny().orElse(null).amount / stack.amount);
			}
			// calculate item overclock
			List<ItemStack> ii = new ArrayList<>();
			for (int i = 0; i < inputs.getSlots(); i++) {
				if (!inputs.getStackInSlot(i).isEmpty())
					ii.add(inputs.getStackInSlot(i));
			}
			for (CountableIngredient ingredient : recipe.getInputs()) {
				overclocks.add(ii.stream().filter(i -> i.getItem() ==
						ingredient.getIngredient().getMatchingStacks()[0].getItem()).findAny().orElse(null).getCount() / ingredient.getCount());
			}

			// total overclock
			int overclockAmount = Math.min(operationsPerTier * GTUtility.getTierByVoltage(maxVoltage),
					overclocks.stream().mapToInt(v -> v).min().getAsInt());

			List<CountableIngredient> newRecipeInputs = new ArrayList<>();
			List<FluidStack> newFluidInputs = new ArrayList<>();
			List<ItemStack> outputI = new ArrayList<>();
			List<FluidStack> outputF = new ArrayList<>();
			this.multiplyInputsAndOutputs(newRecipeInputs, newFluidInputs, outputI, outputF, recipe, overclockAmount);

			RecipeBuilder<?> newRecipe = recipeMap.recipeBuilder()
					.inputsIngredients(newRecipeInputs)
					.fluidInputs(newFluidInputs)
					.outputs(outputI)
					.fluidOutputs(outputF)
					.EUt(recipe.getEUt())
					.duration((int)Math.max(1.0, recipe.getDuration()/speedMulti));
			copyChancedItemOutputs(newRecipe, recipe, overclockAmount);
			return (Recipe) newRecipe.build().getResult();
		}

		protected void multiplyInputsAndOutputs(List<CountableIngredient> newRecipeInputs, List<FluidStack> newFluidInputs, List<ItemStack> outputI, List<FluidStack> outputF, Recipe r, int numberOfOperations) {
			for (CountableIngredient ci : r.getInputs()) {
				CountableIngredient newIngredient = new CountableIngredient(ci.getIngredient(), ci.getCount() * numberOfOperations);
				newRecipeInputs.add(newIngredient);
			}
			for (FluidStack fs : r.getFluidInputs()) {
				FluidStack newFluid = new FluidStack(fs.getFluid(), fs.amount * numberOfOperations);
				newFluidInputs.add(newFluid);
			}
			for (ItemStack s : r.getOutputs()) {
				int num = s.getCount() * numberOfOperations;
				ItemStack itemCopy = s.copy();
				itemCopy.setCount(num);
				outputI.add(itemCopy);
			}
			for (FluidStack f : r.getFluidOutputs()) {
				int fluidNum = f.amount * numberOfOperations;
				FluidStack fluidCopy = f.copy();
				fluidCopy.amount = fluidNum;
				outputF.add(fluidCopy);
			}
		}

		protected void copyChancedItemOutputs(RecipeBuilder<?> newRecipe, Recipe oldRecipe, int numberOfOperations) {
			for (Recipe.ChanceEntry s : oldRecipe.getChancedOutputs()) {
				ItemStack itemStack = s.getItemStack().copy();
				itemStack.setCount(itemStack.getCount() * numberOfOperations);
				newRecipe.chancedOutput(itemStack, s.getChance(), s.getBoostPerTier());
			}
		}
	}
}

