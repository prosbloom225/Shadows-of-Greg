package gregicadditions.machines;

import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import net.minecraft.block.state.IBlockState;

import static gregtech.api.metatileentity.multiblock.MultiblockControllerBase.statePredicate;
import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;

public class GAMachineTypes {
    public enum LargeMachineType {
        ORE_WASHER(RecipeMaps.ORE_WASHER_RECIPES, GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.ORE_WASHER_CASING), ClientHandler.ORE_WASHER_MACHINE_CASING,
                Textures.ORE_WASHER_OVERLAY,
                4,
                4.0,
                FactoryBlockPattern.start()
                        .aisle("XXXXXXX", "XXXXXXX", "XXXXXXX")
                        .aisle("XXXXXXX", "X#####X", "X#####X")
                        .aisle("XXXXXXX", "X#####X", "X#####X")
                        .aisle("XXXXXXX", "X#####X", "X#####X")
                        .aisle("XXXSXXX", "XXXXXXX", "XXXXXXX")
                ),
        MACERATOR(RecipeMaps.MACERATOR_RECIPES, GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.MACERATOR_CASING), ClientHandler.MACERATOR_MACHINE_CASING,
                Textures.MACERATOR_OVERLAY,
                8,
                1.6,
                FactoryBlockPattern.start(RIGHT, FRONT, UP)
                        .aisle("XSX", "XXX", "XXX")
                        .aisle("XXX", "X#X", "XXX")
                        .aisle("XXX", "X#X", "XXX")
                        .aisle("XXX", "X#X", "XXX")
                        .aisle("XXX", "X#X", "XXX")
                        .aisle("XXX", "XXX", "XXX")
                ),
        SIFTER(RecipeMaps.SIFTER_RECIPES, GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.SIEVE_CASING), ClientHandler.SIEVE_CASING,
                Textures.SIFTER_OVERLAY,
                4,
                4,
                FactoryBlockPattern.start()
                        .aisle("XXXXX", "XXXXX", "XXXXX")
                        .aisle("XXXXX", "XZZZX", "XZZZX")
                        .aisle("XXXXX", "XZZZX", "XZZZX")
                        .aisle("XXXXX", "XZZZX", "XZZZX")
                        .aisle("XXSXX", "XXXXX", "XXXXX")
                        .where('Z', statePredicate(GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.SIEVE_GRATE)))
        ),
        CENTRIFUGE(RecipeMaps.CENTRIFUGE_RECIPES, GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.CENTRIFUGE_CASING), ClientHandler.CENTRIFUGE_MACHINE_CASING,
                Textures.CENTRIFUGE_OVERLAY,
                6,
                1.25,
                null),
        CUTTER(RecipeMaps.CUTTER_RECIPES, GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.CUTTER_CASING), ClientHandler.CUTTER_MACHINE_CASING,
                Textures.CUTTER_OVERLAY,
                4,
                2,
                FactoryBlockPattern.start()
                        .aisle("XXX", "XXX", "XXX")
                        .aisle("XXX", "X#X", "XXX")
                        .aisle("XXX", "X#X", "XXX")
                        .aisle("XXX", "X#X", "XXX")
                        .aisle("XXX", "XSX", "XXX")
        ),
        WIREMILL(RecipeMaps.WIREMILL_RECIPES, GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.WIREMILL_CASING), ClientHandler.WIREMILL_CASING,
                Textures.WIREMILL_OVERLAY,
                4,
                2,
                FactoryBlockPattern.start()
                        .aisle("XXX", "XXX", "XXX")
                        .aisle("XXX", "X#X", "XXX")
                        .aisle("XXX", "X#X", "XXX")
                        .aisle("XXX", "X#X", "XXX")
                        .aisle("XXX", "XSX", "XXX")
        ),
        ELECTROLYZER(RecipeMaps.ELECTROLYZER_RECIPES, GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.ELECTROLYZER_CASING), ClientHandler.ELECTROLYZER_CASING,
                Textures.ELECTROLYZER_OVERLAY,
                2,
                1.8,
                null
        ),

        // TODO - this isnt really a largeMachine - should maybe move to MetaTileEntities
        ALLOY_BLAST_SMELTER(GARecipeMaps.ALLOY_BLAST_SMELTER, GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.ZIRCONIUM_CARBIDE_CASING), ClientHandler.ZIRCONIUM_CARBIDE_MACHINE_CASING,
                Textures.ALLOY_SMELTER_OVERLAY,
                1,
                1.00,
                FactoryBlockPattern.start(RIGHT, FRONT, UP)
                        .aisle("XSX", "XXX", "XXX")
                        .aisle("ZZZ", "Z#Z", "ZZZ")
                        .aisle("ZZZ", "Z#Z", "ZZZ")
                        .aisle("XXX", "XXX", "XXX")
                        .where('Z', statePredicate(GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.BLAST_SMELTER_CONTAINMENT_CASTING)))
                ),
        // TODO - this isnt really a largeMachine - should maybe move to MetaTileEntities
        TREE_FARM(GARecipeMaps.TREE_FARM, GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.FARM_CASING), ClientHandler.FARM_CASING,
        ClientHandler.MASS_FAB_OVERLAY,
                1,
                1.00,
                null
                );

        public final RecipeMap recipeMap;
        public final IBlockState casingState;
        public final ICubeRenderer casingRenderer;
        public final OrientedOverlayRenderer renderer;
        public final double speedMulti;
        public final int operationsPerTier;
        public FactoryBlockPattern pattern;


        LargeMachineType(RecipeMap recipeMap, IBlockState casingState, ICubeRenderer casingRenderer, OrientedOverlayRenderer renderer, int operationsPerTier, double speedMulti, FactoryBlockPattern pattern) {
            this.recipeMap = recipeMap;
            this.casingState = casingState;
            this.casingRenderer = casingRenderer;
            this.renderer = renderer;
            this.operationsPerTier = operationsPerTier;
            this.speedMulti = speedMulti;
            this.pattern = pattern;
        }
    }
}
