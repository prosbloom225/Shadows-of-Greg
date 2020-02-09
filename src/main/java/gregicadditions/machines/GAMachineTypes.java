package gregicadditions.machines;

import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;

public class GAMachineTypes {
    public enum LargeMachineType {
        ORE_WASHER(RecipeMaps.ORE_WASHER_RECIPES, MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID), Textures.SOLID_STEEL_CASING,
                Textures.ORE_WASHER_OVERLAY,
                4,
                4.0),
        MACERATOR(RecipeMaps.MACERATOR_RECIPES, MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID), Textures.SOLID_STEEL_CASING,
                Textures.MACERATOR_OVERLAY,
                8,
                1.6),
        CENTRIFUGE(RecipeMaps.CENTRIFUGE_RECIPES, MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID), Textures.SOLID_STEEL_CASING,
                Textures.CENTRIFUGE_OVERLAY,
                6,
                1.25);

        public final RecipeMap recipeMap;
        public final IBlockState casingState;
        public final ICubeRenderer casingRenderer;
        public final OrientedOverlayRenderer renderer;
        public final double speedMulti;
        public final int operationsPerTier;
        public FactoryBlockPattern pattern;

        LargeMachineType(RecipeMap recipeMap, IBlockState casingState, ICubeRenderer casingRenderer, OrientedOverlayRenderer renderer, int operationsPerTier, double speedMulti) {
            this.recipeMap = recipeMap;
            this.casingState = casingState;
            this.casingRenderer = casingRenderer;
            this.renderer = renderer;
            this.operationsPerTier = operationsPerTier;
            this.speedMulti = speedMulti;
        }
    }
}
