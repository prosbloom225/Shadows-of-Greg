package gregicadditions.machines;

import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockMetalCasing.MetalCasingType;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;


public class TileEntityLargeChemReactor extends RecipeMapMultiblockController {

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};

	public TileEntityLargeChemReactor(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, RecipeMaps.CHEMICAL_RECIPES);
		LargeChemicalWorkable  largeChemicalWorkable = new LargeChemicalWorkable(this);
		this.recipeMapWorkable = largeChemicalWorkable;
	// extend from the chemical recipes
		/*
		RecipeMaps.CHEMICAL_RECIPES.getRecipeList().forEach(r->{
			RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
					.fluidInputs(r.getFluidInputs())
					.fluidOutputs(r.getFluidOutputs())
					.outputs(r.getOutputs())
					.duration(r.getDuration()/2)
					.EUt(r.getEUt())
					.buildAndRegister(); });
		 */
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
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
		return MetaBlocks.METAL_CASING.getState(MetalCasingType.STEEL_SOLID);

	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart arg0) {
		// TODO Auto-generated method stub
		return Textures.SOLID_STEEL_CASING;
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		// TODO Auto-generated method stub
		return new TileEntityLargeChemReactor(metaTileEntityId);
	}

	protected class LargeChemicalWorkable extends MultiblockRecipeLogic {

		public LargeChemicalWorkable(RecipeMapMultiblockController tileEntity) {
			super(tileEntity);
		}

		@Override
		protected int[] calculateOverclock(int EUt, long voltage, int duration) {
			return super.calculateOverclock(EUt, voltage, duration/2);
		}
		@Override
		protected int getOverclockingTier(long voltage) {
			return super.getOverclockingTier(voltage);
		}
	}
}

