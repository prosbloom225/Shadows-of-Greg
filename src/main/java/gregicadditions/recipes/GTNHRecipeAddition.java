package gregicadditions.recipes;

import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaItems;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;

import static gregtech.api.unification.material.Materials.*;

public class GTNHRecipeAddition {
    public static void init() {
        chemicals();
        distillationTower();
        circuitComponents();
    }
    public static void circuitComponents() {
        // Advanced SMD Resistor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(OrePrefix.dust, Graphene, 1), OreDictUnifier.get(OrePrefix.wireFine, Platinum, 4)
        ).fluidInputs(GAMaterials.POLYBENZIMIDAZOLE.getFluid(360)).outputs(GAMetaItems.ADVANCED_SMD_RESISTOR.getStackForm(16)).duration(160).EUt(384).buildAndRegister();

        // Advanced SMD Diode
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(OrePrefix.dust, IndiumGalliumPhosphide, 1), OreDictUnifier.get(OrePrefix.wireFine, NiobiumTitanium, 16)
        ).fluidInputs(GAMaterials.POLYBENZIMIDAZOLE.getFluid(288)).outputs(GAMetaItems.ADVANCED_SMD_DIODE.getStackForm(64)).duration(600).EUt(480).buildAndRegister();

        // Advanced SMD Transistor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(OrePrefix.foil, VanadiumGallium, 1), OreDictUnifier.get(OrePrefix.wireFine, HSSG, 8)
        ).fluidInputs(GAMaterials.POLYBENZIMIDAZOLE.getFluid(144)).outputs(GAMetaItems.ADVANCED_SMD_TRANSISTOR.getStackForm(16)).duration(160).EUt(480).buildAndRegister();

        // Advanced SMD Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(OrePrefix.foil, Polystyrene, 2), OreDictUnifier.get(OrePrefix.foil, HSSS, 1)
        ).fluidInputs(GAMaterials.POLYBENZIMIDAZOLE.getFluid(36)).outputs(GAMetaItems.ADVANCED_SMD_CAPACITOR.getStackForm(16)).duration(160).EUt(480).buildAndRegister();
    }
    private static void chemicals() {

        // Polybenzimidazole
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(GAMaterials.THREE_THREE_DIAMINOBENZIDINE.getFluid(1000), GAMaterials.DIPHENYL_ISOPHTALATE.getFluid(1000))
                .fluidOutputs(GAMaterials.POLYBENZIMIDAZOLE.getFluid(1000), Phenol.getFluid(1000))
                .duration(100).EUt(7680).buildAndRegister();
        // 3-3 Diaminobenzidine
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.dust, Zinc, 1))
                .fluidInputs(Ammonia.getFluid(2000), GAMaterials.THREE_THREE_DICHLOROBENZIDINE.getFluid(1000))
                .fluidOutputs(GAMaterials.THREE_THREE_DIAMINOBENZIDINE.getFluid(1000), HydrochloricAcid.getFluid(2000))
                .duration(100).EUt(7680).buildAndRegister();
        // 3-3 Dichlorobenzidine
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.dustTiny, Copper, 1))
                .fluidInputs(GAMaterials.TWO_NITROCHLOROBENZENE.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(GAMaterials.THREE_THREE_DICHLOROBENZIDINE.getFluid(1000))
                .duration(200).EUt(1920).buildAndRegister();
        // 2 Nitrochlorobenzene
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.dustTiny, Copper, 1))
                .fluidInputs(NitrationMixture.getFluid(1000), GAMaterials.CHLOROBENZENE.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(2))
                .fluidOutputs(GAMaterials.TWO_NITROCHLOROBENZENE.getFluid(1000), DilutedSulfuricAcid.getFluid(1000))
                .duration(100).EUt(480).buildAndRegister();
        // Chlorobenzene
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.dustTiny, Copper, 1))
                .fluidInputs(Chlorine.getFluid(2000), Benzene.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(11))
                .fluidOutputs(GAMaterials.CHLOROBENZENE.getFluid(1000), HydrochloricAcid.getFluid(1000))
                .duration(240).EUt(30).buildAndRegister();
        // Diphenyl Isophtalate
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Phenol.getFluid(1000), SulfuricAcid.getFluid(1000), GAMaterials.PHTALIC_ACID.getFluid(1000) )
                .notConsumable(new IntCircuitIngredient(2))
                .fluidOutputs(GAMaterials.DIPHENYL_ISOPHTALATE.getFluid(1000), DilutedSulfuricAcid.getFluid(1000))
                .duration(100).EUt(7680).buildAndRegister();
        // Phtalic Acid
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.dustTiny, Potassium, 1))
                .fluidInputs(Oxygen.getFluid(2000), GAMaterials.DIMETHYL_BENZENE.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(21))
                .fluidOutputs(Water.getFluid(2000), GAMaterials.PHTALIC_ACID.getFluid(1000))
                .duration(100).EUt(1920).buildAndRegister();
        // Dimethyl Benzene
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder()
                .fluidInputs(WoodTar.getFluid(200))
                .notConsumable(new IntCircuitIngredient(5))
                .fluidOutputs(GAMaterials.DIMETHYL_BENZENE.getFluid(40))
                .duration(16).EUt(64).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder()
                .fluidInputs(CharcoalByproducts.getFluid(40))
                .notConsumable(new IntCircuitIngredient(5))
                .fluidOutputs(GAMaterials.DIMETHYL_BENZENE.getFluid(40))
                .duration(20).EUt(120).buildAndRegister();
    }

    private static void pyrolyseOven() {
        /*
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .fluidInputs(GAMaterials.THREE_THREE_DIAMINOBENZIDINE.getFluid(1000), GAMaterials.DIPHENYL_ISOPHTALATE.getFluid(1000))
                .fluidOutputs(GAMaterials.POLYBENZIMIDAZOLE.getFluid(1000), Phenol.getFluid(1000))
                .duration(100).EUt(7680).buildAndRegister();
         */

    }
    private static void distillationTower() {
        // Wood Tar
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(WoodTar.getFluid(1000))
                .fluidOutputs(Benzene.getFluid(350), Toluene.getFluid(75), Creosote.getFluid(300), Phenol.getFluid(75), GAMaterials.DIMETHYL_BENZENE.getFluid(200))
                .duration(40).EUt(256).buildAndRegister();
    }
}
