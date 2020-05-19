package gregicadditions.recipes;

import gregicadditions.GAAlloys;
import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.item.GAIngotMaterial;
import gregicadditions.item.GAMetaItems;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;

import java.util.Arrays;
import java.util.List;

import static gregtech.api.unification.material.Materials.*;

public class GTNHRecipeAddition {
    public static void init() {
        circuitComponents();
        chemicals();
        if (GAConfig.gtnh.gtnhDistillationTowerRecipes)
        distillationTower();
        if (GAConfig.gtnh.gtnhPyrolyseRecipes)
            pyrolyseOven();
        if (GAConfig.gtnh.gtnhCircuitRecipes)
            circuits();
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

        // Raw Advanced Crystal Chip
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().inputs(MetaItems.CRYSTAL_SYSTEM_ON_CHIP.getStackForm()).notConsumable(OreDictUnifier.get(OrePrefix.lens, Emerald, 1))
        .outputs(GAMetaItems.RAW_ADVANCED_CRYSTAL_CHIP.getStackForm(1)).duration(1200).EUt(80000).buildAndRegister();
    }

    private static final MaterialStack[] solderingList = {
            // TODO - enable solders
            new MaterialStack(Materials.SolderingAlloy, 1L),
            //new MaterialStack(Materials.Tin, 2L),
            //new MaterialStack(Lead, 4L)
    };

    private static final List<MetaItem.MetaValueItem> diodeList = Arrays.asList(
            MetaItems.DIODE,
            MetaItems.SMD_DIODE);
    private static final List<MetaItem.MetaValueItem> transistorList = Arrays.asList(
            MetaItems.TRANSISTOR,
            MetaItems.SMD_TRANSISTOR);
    private static final List<MetaItem.MetaValueItem> resistorList = Arrays.asList(
            MetaItems.RESISTOR,
            MetaItems.SMD_RESISTOR);
    private static final List<MetaItem.MetaValueItem> capacitorList = Arrays.asList(
            MetaItems.CAPACITOR,
            MetaItems.SMD_CAPACITOR);

    public static void circuits() {
        // Circuits
        ModHandler.addShapedRecipe("electronic_circuit", GAMetaItems.ELECTRONIC_CIRCUIT.getStackForm(),
                "RPR", "TCT", "WWW",
                'R', MetaItems.RESISTOR.getStackForm(),
                'P', new UnificationEntry(OrePrefix.plate, Steel),
                'T', MetaItems.VACUUM_TUBE.getStackForm(),
                'C', GAMetaItems.CIRCUIT_BOARD.getStackForm(),
                'W', new UnificationEntry(OrePrefix.cableGtSingle, Copper));

        // Circuit Assembler
        for (MaterialStack stack : solderingList) {
            IngotMaterial solder = (IngotMaterial) stack.material;
            int multiplier = (int) stack.amount;

            // LV - Electronic Circuit
            resistorList.forEach(r->
                    GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.CIRCUIT_BOARD.getStackForm(), MetaItems.VACUUM_TUBE.getStackForm(2), OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Copper, 2),
                            r.getStackForm(2)
                    ).fluidInputs(solder.getFluid(multiplier * 72)).outputs(GAMetaItems.ELECTRONIC_CIRCUIT.getStackForm(1)).duration(200).EUt(16).buildAndRegister()
            );

            // LV - Integrated Logic Circuit
            resistorList.forEach(r->
                    diodeList.forEach(d->
                            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.CIRCUIT_BOARD.getStackForm(), MetaItems.INTEGRATED_LOGIC_CIRCUIT.getStackForm(), OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper, 2), OreDictUnifier.get(OrePrefix.bolt, Materials.Tin, 2),
                                    r.getStackForm(2), d.getStackForm(2)
                            ).fluidInputs(solder.getFluid(multiplier * 72)).outputs(MetaItems.ADVANCED_CIRCUIT_PARTS_LV.getStackForm(1)).duration(200).EUt(16).buildAndRegister()
                    ));

            // LV - Microprocessor
            resistorList.forEach(r->
                    capacitorList.forEach(c->
                            transistorList.forEach(t->
                                    GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.PLASTIC_CIRCUIT_BOARD.getStackForm(), MetaItems.CENTRAL_PROCESSING_UNIT.getStackForm(),OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper, 2),
                                            r.getStackForm(2), c.getStackForm(2), t.getStackForm(2)
                                    ).fluidInputs(solder.getFluid(multiplier * 72)).outputs(MetaItems.ADVANCED_CIRCUIT_PARTS_LV.getStackForm(2)).duration(400).EUt(60).buildAndRegister()
                            )));
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.PLASTIC_CIRCUIT_BOARD.getStackForm(), MetaItems.SYSTEM_ON_CHIP.getStackForm(),
                    OreDictUnifier.get(OrePrefix.wireFine, Copper, 2), OreDictUnifier.get(OrePrefix.bolt, Copper, 2)
            ).fluidInputs(solder.getFluid(multiplier * 72)).outputs(MetaItems.ADVANCED_CIRCUIT_PARTS_LV.getStackForm(2)).duration(50).EUt(600).buildAndRegister();

            // MV - Good Electronic Circuit
            diodeList.forEach(d->
                    GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.GOOD_CIRCUIT_BOARD.getStackForm(), MetaItems.BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm(2), OreDictUnifier.get(OrePrefix.wireGtSingle, Copper, 2),
                            d.getStackForm(4)
                    ).fluidInputs(solder.getFluid(multiplier * 72)).outputs(GAMetaItems.GOOD_ELECTRONIC_CIRCUIT.getStackForm(2)).duration(600).EUt(30).buildAndRegister()
            );

            // MV - Good Integrated Circuit
            resistorList.forEach(r->
                    transistorList.forEach(t->
                            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.GOOD_CIRCUIT_BOARD.getStackForm(), MetaItems.BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm(2),OreDictUnifier.get(OrePrefix.wireFine, Materials.Gold, 4),
                                    r.getStackForm(4),  t.getStackForm(4)
                            ).fluidInputs(solder.getFluid(multiplier * 72)).outputs(MetaItems.GOOD_INTEGRATED_CIRCUIT_MV.getStackForm(2)).duration(400).EUt(24).buildAndRegister()
                    ));

            // MV - Integrated Processor
            resistorList.forEach(r->
                    capacitorList.forEach(c->
                            transistorList.forEach(t->
                                    GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.PLASTIC_CIRCUIT_BOARD.getStackForm(), MetaItems.CENTRAL_PROCESSING_UNIT.getStackForm(),OreDictUnifier.get(OrePrefix.wireFine, Materials.RedAlloy, 4),
                                            r.getStackForm(4), c.getStackForm(4), t.getStackForm(4)
                                    ).fluidInputs(solder.getFluid(multiplier * 72)).outputs(MetaItems.ADVANCED_CIRCUIT_MV.getStackForm(1)).duration(200).EUt(60).buildAndRegister()
                            )));
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.PLASTIC_CIRCUIT_BOARD.getStackForm(), MetaItems.SYSTEM_ON_CHIP.getStackForm(),
                    OreDictUnifier.get(OrePrefix.wireFine, Materials.RedAlloy, 4), OreDictUnifier.get(OrePrefix.bolt, Materials.AnnealedCopper, 4)
            ).fluidInputs(solder.getFluid(multiplier * 72)).outputs(MetaItems.ADVANCED_CIRCUIT_MV.getStackForm(1)).duration(50).EUt(2400).buildAndRegister();

            // HV - Advanced Circuit
            transistorList.forEach(t->
                    GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(MetaItems.GOOD_INTEGRATED_CIRCUIT_MV.getStackForm(2), MetaItems.INTEGRATED_LOGIC_CIRCUIT.getStackForm(2), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(2),
                            OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 8), OreDictUnifier.get(OrePrefix.bolt, Materials.AnnealedCopper, 8),
                            t.getStackForm(4)
                    ).fluidInputs(solder.getFluid(multiplier * 72)).outputs(GAMetaItems.ADVANCED_CIRCUIT.getStackForm(1)).duration(800).EUt(30).buildAndRegister()
            );

            // HV - Processor Assembly
            capacitorList.forEach(c->
                    GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.PLASTIC_CIRCUIT_BOARD.getStackForm(), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), MetaItems.ADVANCED_CIRCUIT_MV.getStackForm(2),
                            OreDictUnifier.get(OrePrefix.wireFine, Materials.RedAlloy, 8), MetaItems.SMALL_COIL.getStackForm(4),
                            c.getStackForm(8)
                    ).fluidInputs(solder.getFluid(multiplier * 144)).outputs(MetaItems.PROCESSOR_ASSEMBLY_HV.getStackForm(1)).duration(200).EUt(96).buildAndRegister()
            );

            // HV - Nanoprocessor
            resistorList.forEach(r->
                    capacitorList.forEach(c->
                            transistorList.forEach(t->
                                    GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.ADVANCED_CIRCUIT_BOARD.getStackForm(), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(),OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 8),
                                            r.getStackForm(8), c.getStackForm(8), t.getStackForm(8)
                                    ).fluidInputs(solder.getFluid(multiplier * 72)).outputs(MetaItems.NANO_PROCESSOR_HV.getStackForm(1)).duration(200).EUt(600).buildAndRegister()
                            )));
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.ADVANCED_CIRCUIT_BOARD.getStackForm(6), MetaItems.ADVANCED_SYSTEM_ON_CHIP.getStackForm(6),
                    OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 48), OreDictUnifier.get(OrePrefix.bolt, Materials.Platinum, 48)
            ).fluidInputs(solder.getFluid(multiplier * 288)).outputs(MetaItems.NANO_PROCESSOR_HV.getStackForm(1)).duration(200).EUt(9600).buildAndRegister();

            // EV - Workstation
            diodeList.forEach(d->
                    GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.PLASTIC_CIRCUIT_BOARD.getStackForm(), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(8), MetaItems.PROCESSOR_ASSEMBLY_HV.getStackForm(2),
                            OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 16), OreDictUnifier.get(OrePrefix.bolt, Materials.BlueSteel, 16),
                            d.getStackForm(4)
                    ).fluidInputs(solder.getFluid(multiplier * 144)).outputs(GAMetaItems.WORKSTATION.getStackForm(1)).duration(200).EUt(96).buildAndRegister()
            );

            // EV - Nanoprocessor Assembly
            capacitorList.forEach(c->
                    GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.ADVANCED_CIRCUIT_BOARD.getStackForm(), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(8), MetaItems.NANO_PROCESSOR_HV.getStackForm(2),
                            OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 16), MetaItems.SMALL_COIL.getStackForm(8),
                            c.getStackForm(8)
                    ).fluidInputs(solder.getFluid(multiplier * 144)).outputs(MetaItems.NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(1)).duration(400).EUt(600).buildAndRegister()
            );

            // EV - Quantumprocessor
            capacitorList.forEach(c->
                    transistorList.forEach(t->
                            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.MORE_ADVANCED_CIRCUIT_BOARD.getStackForm(), MetaItems.QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(),
                                    OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 16),
                                    c.getStackForm(12), t.getStackForm(12)
                            ).fluidInputs(solder.getFluid(multiplier * 72)).outputs(MetaItems.QUANTUM_PROCESSOR_EV.getStackForm(1)).duration(200).EUt(2400).buildAndRegister()
                    ));
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.MORE_ADVANCED_CIRCUIT_BOARD.getStackForm(6), MetaItems.ADVANCED_SYSTEM_ON_CHIP.getStackForm(6),
                    OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 64), OreDictUnifier.get(OrePrefix.bolt, Materials.NiobiumTitanium, 48)
            ).fluidInputs(solder.getFluid(multiplier * 288)).outputs(MetaItems.QUANTUM_PROCESSOR_EV.getStackForm(1)).duration(200).EUt(38400).buildAndRegister();

            // IV - Mainframe
            capacitorList.forEach(c->
                    GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.Aluminium, 2), GAMetaItems.WORKSTATION.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(12),
                            OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.AnnealedCopper, 24), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(16),
                            c.getStackForm(24)
                    ).fluidInputs(solder.getFluid(multiplier * 288)).outputs(GAMetaItems.MAINFRAME.getStackForm(1)).duration(1600).EUt(480).buildAndRegister()
            );

            // IV - Elite Nanocomputer
            diodeList.forEach(d->
                    GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.ADVANCED_CIRCUIT_BOARD.getStackForm(), MetaItems.NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(2), MetaItems.NOR_MEMORY_CHIP.getStackForm(4),
                            OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 16), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(16),
                            d.getStackForm(8)
                    ).fluidInputs(solder.getFluid(multiplier * 144)).outputs(GAMetaItems.ELITE_NANOCOMPUTER.getStackForm(1)).duration(200).EUt(600).buildAndRegister()
            );

            // IV - Quantumprocessor Assembly
            capacitorList.forEach(c->
                    GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.MORE_ADVANCED_CIRCUIT_BOARD.getStackForm(), MetaItems.QUANTUM_PROCESSOR_EV.getStackForm(2), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4),
                            OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 24), MetaItems.SMALL_COIL.getStackForm(12),
                            c.getStackForm(16)
                    ).fluidInputs(solder.getFluid(multiplier * 144)).outputs(GAMetaItems.QUANTUMPROCESSOR_ASSEMBLY.getStackForm(1)).duration(400).EUt(2400).buildAndRegister()
            );

            // LuV - Nanoprocessor Mainframe
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(OrePrefix.frameGt, Aluminium, 2), GAMetaItems.ELITE_NANOCOMPUTER.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(16),
                    OreDictUnifier.get(OrePrefix.wireGtSingle, AnnealedCopper, 24), MetaItems.SMD_CAPACITOR.getStackForm(32), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(16)
            ).fluidInputs(solder.getFluid(multiplier * 144)).outputs(GAMetaItems.NANOPROCESSOR_MAINFRAME.getStackForm(1)).duration(1600).EUt(1920).buildAndRegister();
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(OrePrefix.frameGt, Aluminium, 2), GAMetaItems.ELITE_NANOCOMPUTER.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(16),
                    OreDictUnifier.get(OrePrefix.wireGtSingle, AnnealedCopper, 24), GAMetaItems.ADVANCED_SMD_CAPACITOR.getStackForm(16), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(16)
            ).fluidInputs(solder.getFluid(multiplier * 144)).outputs(GAMetaItems.NANOPROCESSOR_MAINFRAME.getStackForm(1)).duration(800).EUt(2400).buildAndRegister();

            // LuV - Master Quantumcomputer
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.MORE_ADVANCED_CIRCUIT_BOARD.getStackForm(), GAMetaItems.QUANTUMPROCESSOR_ASSEMBLY.getStackForm(2), GAMetaItems.ADVANCED_SMD_DIODE.getStackForm(2),
                    MetaItems.NOR_MEMORY_CHIP.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(16), OreDictUnifier.get(OrePrefix.wireFine, Platinum, 48)
            ).fluidInputs(solder.getFluid(multiplier * 144)).outputs(GAMetaItems.MASTER_QUANTUMCOMPUTER.getStackForm(1)).duration(200).EUt(2400).buildAndRegister();
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.MORE_ADVANCED_CIRCUIT_BOARD.getStackForm(), GAMetaItems.QUANTUMPROCESSOR_ASSEMBLY.getStackForm(2), MetaItems.SMD_DIODE.getStackForm(8),
                    MetaItems.NOR_MEMORY_CHIP.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(16), OreDictUnifier.get(OrePrefix.wireFine, Platinum, 48)
            ).fluidInputs(solder.getFluid(multiplier * 144)).outputs(GAMetaItems.MASTER_QUANTUMCOMPUTER.getStackForm(1)).duration(400).EUt(2400).buildAndRegister();

            // LuV - Crystalprocessor Assembly
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.ELITE_CIRCUIT_BOARD.getStackForm(6), MetaItems.CRYSTAL_PROCESSOR_IV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(64),
                    GAMetaItems.ADVANCED_SMD_DIODE.getStackForm(48), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(64), OreDictUnifier.get(OrePrefix.wireFine, NiobiumTitanium, 64)
            ).fluidInputs(solder.getFluid(multiplier * 576)).outputs(GAMetaItems.CRYSTALPROCESSOR_ASSEMBLY.getStackForm(1)).duration(800).EUt(9600).buildAndRegister();

            // LuV - Wetwareprocessor
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.NEURO_PROCESSOR.getStackForm(6), MetaItems.CRYSTAL_PROCESSOR_IV.getStackForm(6), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(6),
                    GAMetaItems.ADVANCED_SMD_CAPACITOR.getStackForm(48), GAMetaItems.ADVANCED_SMD_TRANSISTOR.getStackForm(48), OreDictUnifier.get(OrePrefix.wireFine, YttriumBariumCuprate, 48)
            ).fluidInputs(solder.getFluid(multiplier * 288)).outputs(MetaItems.WETWARE_PROCESSOR_LUV.getStackForm(1)).duration(800).EUt(38400).buildAndRegister();

            // ZPM - Quantumprocessor Mainframe
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(OrePrefix.frameGt, Aluminium, 2), GAMetaItems.MASTER_QUANTUMCOMPUTER.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(24),
                    GAMetaItems.ADVANCED_SMD_CAPACITOR.getStackForm(12), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(24), OreDictUnifier.get(OrePrefix.wireGtSingle, AnnealedCopper, 48)
            ).fluidInputs(solder.getFluid(multiplier * 288)).outputs(GAMetaItems.QUANTUMPROCESSOR_MAINFRAME.getStackForm(1)).duration(800).EUt(7680).buildAndRegister();
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(OrePrefix.frameGt, Aluminium, 2), GAMetaItems.MASTER_QUANTUMCOMPUTER.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(24),
                    MetaItems.SMD_CAPACITOR.getStackForm(48), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(24), OreDictUnifier.get(OrePrefix.wireGtSingle, AnnealedCopper, 48)
            ).fluidInputs(solder.getFluid(multiplier * 288)).outputs(GAMetaItems.QUANTUMPROCESSOR_MAINFRAME.getStackForm(1)).duration(1600).EUt(7680).buildAndRegister();

            // ZPM - Ultimate Crystalcomputer
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.ELITE_CIRCUIT_BOARD.getStackForm(6), GAMetaItems.CRYSTALPROCESSOR_ASSEMBLY.getStackForm(2), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(24),
                    MetaItems.NOR_MEMORY_CHIP.getStackForm(64), MetaItems.NAND_MEMORY_CHIP.getStackForm(64), OreDictUnifier.get(OrePrefix.wireFine, NiobiumTitanium, 64)
            ).fluidInputs(solder.getFluid(multiplier * 576)).outputs(GAMetaItems.ULTIMATE_CRYSTALCOMPUTER.getStackForm(1)).duration(1600).EUt(9600).buildAndRegister();

            // ZPM - Wetwareprocessor Assembly
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.EXTREME_WETWARE_LIFESUPPORT_CIRCUIT_BOARD.getStackForm(6), MetaItems.WETWARE_PROCESSOR_LUV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(64),
                    GAMetaItems.ADVANCED_SMD_CAPACITOR.getStackForm(64), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(64), OreDictUnifier.get(OrePrefix.wireFine, NiobiumTitanium, 64)
            ).fluidInputs(solder.getFluid(multiplier * 576)).outputs(MetaItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm(1)).duration(1200).EUt(38400).buildAndRegister();

            // ZPM - Bioprocessor
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(GAMetaItems.BIO_PROCESSING_UNIT.getStackForm(6), GAMetaItems.RAW_ADVANCED_CRYSTAL_CHIP.getStackForm(6), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(12),
                    GAMetaItems.ADVANCED_SMD_CAPACITOR.getStackForm(64), GAMetaItems.ADVANCED_SMD_TRANSISTOR.getStackForm(64), OreDictUnifier.get(OrePrefix.wireFine, NiobiumTitanium, 64)
            ).fluidInputs(solder.getFluid(multiplier * 288)).outputs(GAMetaItems.BIOPROCESSOR.getStackForm(1)).duration(1200).EUt(153600).buildAndRegister();

            // UV - Crystalprocessor Mainframe
            GARecipeMaps.CIRCUIT_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(OrePrefix.frameGt, Aluminium, 12), GAMetaItems.ULTIMATE_CRYSTALCOMPUTER.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(64),
                    GAMetaItems.ADVANCED_SMD_CAPACITOR.getStackForm(64), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(64),OreDictUnifier.get(OrePrefix.wireGtSingle, GAAlloys.SUPERCONDUCTOR_LUV, 64)
            ).fluidInputs(solder.getFluid(multiplier * 1152)).outputs(GAMetaItems.CRYSTALPROCESSOR_MAINFRAME.getStackForm(1)).duration(3200).EUt(30720).buildAndRegister();
        }
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
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.log, Wood, 16))
                .outputs(OreDictUnifier.get(OrePrefix.gem, Charcoal, 20))
                .notConsumable(new IntCircuitIngredient(9))
                .fluidOutputs(WoodTar.getFluid(1500))
                .duration(640).EUt(64).buildAndRegister();
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.log, Wood, 16))
                .fluidInputs(Nitrogen.getFluid(1000))
                .outputs(OreDictUnifier.get(OrePrefix.gem, Charcoal, 1))
                .notConsumable(new IntCircuitIngredient(10))
                .fluidOutputs(WoodTar.getFluid(1500))
                .duration(320).EUt(96).buildAndRegister();

    }
    private static void distillationTower() {
        // Wood Tar
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(WoodTar.getFluid(1000))
                .fluidOutputs(Benzene.getFluid(350), Toluene.getFluid(75), Creosote.getFluid(300), Phenol.getFluid(75), GAMaterials.DIMETHYL_BENZENE.getFluid(200))
                .duration(40).EUt(256).buildAndRegister();
    }

}
