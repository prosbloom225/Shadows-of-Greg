package gregicadditions.item;

import gregicadditions.GAConfig;
import gregtech.api.items.materialitem.MaterialMetaItem;
import gregtech.api.items.metaitem.ElectricStats;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemComponent;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.items.MetaItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class GAMetaItem extends MaterialMetaItem {

	public GAMetaItem() {
		super(OrePrefix.valueOf("plateCurved"), OrePrefix.valueOf("ingotDouble"), OrePrefix.valueOf("round"), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	@Override
	public void registerSubItems() {
		GAMetaItems.GLASS_FIBER = addItem(21, "component.glass.fiber");
		GAMetaItems.PETRI_DISH = addItem(23, "component.petri.dish");
		GAMetaItems.COMPRESSED_COKE_CLAY = addItem(32, "compressed.coke.clay");

		if (Loader.isModLoaded("forestry") && GAConfig.GT6.electrodes) {
			GAMetaItems.ELECTRODE_APATITE = addItem(108, "electrode.apatite");
			GAMetaItems.ELECTRODE_BLAZE = addItem(109, "electrode.blaze");
			GAMetaItems.ELECTRODE_BRONZE = addItem(110, "electrode.bronze");
			GAMetaItems.ELECTRODE_COPPER = addItem(111, "electrode.copper");
			GAMetaItems.ELECTRODE_DIAMOND = addItem(112, "electrode.diamond");
			GAMetaItems.ELECTRODE_EMERALD = addItem(113, "electrode.emerald");
			GAMetaItems.ELECTRODE_ENDER = addItem(114, "electrode.ender");
			GAMetaItems.ELECTRODE_GOLD = addItem(115, "electrode.gold");
			if (Loader.isModLoaded("ic2") || Loader.isModLoaded("binniecore")) GAMetaItems.ELECTRODE_IRON = addItem(116, "electrode.iron");
			GAMetaItems.ELECTRODE_LAPIS = addItem(117, "electrode.lapis");
			GAMetaItems.ELECTRODE_OBSIDIAN = addItem(118, "electrode.obsidian");
			if (Loader.isModLoaded("extrautils2")) GAMetaItems.ELECTRODE_ORCHID = addItem(119, "electrode.orchid");
			if (Loader.isModLoaded("ic2") || Loader.isModLoaded("techreborn") || Loader.isModLoaded("binniecore")) GAMetaItems.ELECTRODE_RUBBER = addItem(120, "electrode.rubber");
			GAMetaItems.ELECTRODE_TIN = addItem(121, "electrode.tin");
		}

		if (GAConfig.GT5U.enableZPMandUVBats) {
			GAMetaItems.ENERGY_MODULE = addItem(122, "energy.module").addComponents(new IItemComponent[] { ElectricStats.createRechargeableBattery(10000000000L, 7) }).setModelAmount(8);
			GAMetaItems.ENERGY_CLUSTER = addItem(123, "energy.cluster").addComponents(new IItemComponent[] { ElectricStats.createRechargeableBattery(100000000000L, 8) }).setModelAmount(8);
		}

		if (GAConfig.GT5U.replaceUVwithMAXBat) {
			GAMetaItems.MAX_BATTERY = addItem(124, "max.battery").addComponents(new IItemComponent[] { ElectricStats.createRechargeableBattery(9223372036854775807L, 9) }).setModelAmount(8);
			MetaItems.ZPM2.setInvisible();
		}

		GAMetaItems.SCHEMATIC = addItem(131, "schematic").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.StainlessSteel, 7257600L)));
		GAMetaItems.SCHEMATIC_2X2 = addItem(132, "schematic.2by2").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.StainlessSteel, 7257600L)));
		GAMetaItems.SCHEMATIC_3X3 = addItem(133, "schematic.3by3").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.StainlessSteel, 7257600L)));
		GAMetaItems.SCHEMATIC_DUST = addItem(134, "schematic.dust").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.StainlessSteel, 7257600L)));

		GAMetaItems.NEURO_PROCESSOR = addItem(15, "processor.neuro");
		GAMetaItems.STEM_CELLS = addItem(18, "stemcells");

		GAMetaItems.CIRCUIT_CONTROL = addItem(19, "circuit.control");
		GAMetaItems.COMPLETE_CASING = addItem(135, "casing.complete");
		GAMetaItems.COMPLETE_CASING_HALF = addItem(136, "casing.complete.half");
		GAMetaItems.CORE_CHIP_GOLD = addItem(137, "core.chip.gold");
		GAMetaItems.CORE_CHIP_DIAMOND = addItem(138, "core.chip.diamond");
		GAMetaItems.CORE_CHIP_EMERALD_ADVANCED = addItem(139, "core.chip.emerald.advanced");
		GAMetaItems.CORE_CHIP_EMERALD_HIGH_ADVANCED = addItem(140, "core.chip.emerald.high.advanced");
		GAMetaItems.CORE_PROCESSOR_GOLD = addItem(141, "core.processor.gold");
		GAMetaItems.CORE_PROCESSOR_DIAMOND = addItem(142, "core.processor.diamond");
		GAMetaItems.CORE_PROCESSOR_EMERALD = addItem(143, "core.processor.emerald");
		GAMetaItems.CORE_PROCESSOR_ADVANCED_EMERALD = addItem(144, "core.processor.advanced.emerald");
		GAMetaItems.CORE_CHIP_SPATIAL = addItem(145, "core.chip.spatial");
		GAMetaItems.CORE_PROCESSOR_SPATIAL = addItem(146, "core.processor.spatial");
		GAMetaItems.DISPLAY = addItem(147, "display");
		GAMetaItems.CIRCUIT_BOARD = addItem(148, "circuit.board");
		GAMetaItems.GOOD_CIRCUIT_BOARD = addItem(149, "good.circuit.board");
		GAMetaItems.ADVANCED_CIRCUIT_BOARD = addItem(150, "advanced.circuit.board");
		GAMetaItems.MORE_ADVANCED_CIRCUIT_BOARD = addItem(151, "more.advanced.circuit.board");
		GAMetaItems.ELITE_CIRCUIT_BOARD = addItem(152, "elite.circuit.board");
		GAMetaItems.EXTREME_WETWARE_LIFESUPPORT_CIRCUIT_BOARD= addItem(153, "extreme.wetware.lifesupport.circuit.board");
		GAMetaItems.PLASTIC_CIRCUIT_BOARD = addItem(154, "plastic.circuit.board");
		GAMetaItems.ULTRA_BIO_MUTATED_CIRCUIT_BOARD = addItem(155, "ultra.bio.mutated.circuit.board");
		GAMetaItems.ENGINE_CORE = addItem(156, "engine.core");
		GAMetaItems.GOOD_ELECTRONIC_CIRCUIT = addItem(157, "good.electronic.circuit");
		GAMetaItems.WORKSTATION = addItem(158, "workstation");
		GAMetaItems.MAINFRAME = addItem(159, "mainframe");
		GAMetaItems.NANOPROCESSOR_MAINFRAME = addItem(160, "nanoprocessor.mainframe");
		GAMetaItems.ELITE_NANOCOMPUTER = addItem(161, "elite.nanocomputer");
		GAMetaItems.QUANTUMPROCESSOR_ASSEMBLY = addItem(162, "quantumprocessor.assembly");
		GAMetaItems.MASTER_QUANTUMCOMPUTER = addItem(163, "master.quantumcomputer");
		GAMetaItems.CRYSTALPROCESSOR_ASSEMBLY = addItem(164, "crystalprocessor.assembly");
		GAMetaItems.ELECTRONIC_CIRCUIT = addItem(165, "electronic.circuit");
		GAMetaItems.ADVANCED_CIRCUIT = addItem(166, "advanced.circuit");
		GAMetaItems.QUANTUMPROCESSOR_MAINFRAME = addItem(167, "quantumprocessor.mainframe");
		GAMetaItems.ULTIMATE_CRYSTALCOMPUTER = addItem(168, "ultimate.crystalcomputer");
		GAMetaItems.BIOPROCESSOR = addItem(169, "bioprocessor");
		GAMetaItems.CRYSTALPROCESSOR_MAINFRAME = addItem(170, "crystalprocessor.mainframe");
		GAMetaItems.BIOWAREPROCESSOR_ASSEMBLY = addItem(171, "biowareprocessor.assembly");
		GAMetaItems.BIOWARE_SUPERCOMPUTER = addItem(172, "bioware.supercomputer");
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return stack.copy();
	}
}
