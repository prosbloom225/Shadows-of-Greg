package gregicadditions.item;

import java.util.List;

import gregicadditions.GregicAdditions;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.common.items.MetaItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.oredict.OreDictionary;
import scala.swing.MainFrame;

public class GAMetaItems {

	private static List<MetaItem<?>> ITEMS = MetaItem.getMetaItems();

	public static MetaItem<?>.MetaValueItem GLASS_FIBER;
	public static MetaItem<?>.MetaValueItem PETRI_DISH;
	public static MetaItem<?>.MetaValueItem COMPRESSED_COKE_CLAY;
	public static MetaItem<?>.MetaValueItem ENERGY_MODULE;
	public static MetaItem<?>.MetaValueItem ENERGY_CLUSTER;
	public static MetaItem<?>.MetaValueItem MAX_BATTERY;

	public static MetaItem<?>.MetaValueItem ELECTRODE_APATITE;
	public static MetaItem<?>.MetaValueItem ELECTRODE_BLAZE;
	public static MetaItem<?>.MetaValueItem ELECTRODE_BRONZE;
	public static MetaItem<?>.MetaValueItem ELECTRODE_COPPER;
	public static MetaItem<?>.MetaValueItem ELECTRODE_DIAMOND;
	public static MetaItem<?>.MetaValueItem ELECTRODE_EMERALD;
	public static MetaItem<?>.MetaValueItem ELECTRODE_ENDER;
	public static MetaItem<?>.MetaValueItem ELECTRODE_GOLD;
	public static MetaItem<?>.MetaValueItem ELECTRODE_IRON;
	public static MetaItem<?>.MetaValueItem ELECTRODE_LAPIS;
	public static MetaItem<?>.MetaValueItem ELECTRODE_OBSIDIAN;
	public static MetaItem<?>.MetaValueItem ELECTRODE_ORCHID;
	public static MetaItem<?>.MetaValueItem ELECTRODE_RUBBER;
	public static MetaItem<?>.MetaValueItem ELECTRODE_TIN;

	public static MetaItem<?>.MetaValueItem BENDING_CYLINDER;
	public static MetaItem<?>.MetaValueItem SMALL_BENDING_CYLINDER;

	public static MetaItem<?>.MetaValueItem SCHEMATIC;
	public static MetaItem<?>.MetaValueItem SCHEMATIC_2X2;
	public static MetaItem<?>.MetaValueItem SCHEMATIC_3X3;
	public static MetaItem<?>.MetaValueItem SCHEMATIC_DUST;

	public static MetaItem<?>.MetaValueItem NEURO_PROCESSOR;
	public static MetaItem<?>.MetaValueItem STEM_CELLS;

	public static MetaItem<?>.MetaValueItem CIRCUIT_CONTROL;

	public static MetaItem<?>.MetaValueItem COMPLETE_CASING;
	public static MetaItem<?>.MetaValueItem COMPLETE_CASING_HALF;
	// TODO - add recipes for these
	public static MetaItem<?>.MetaValueItem CORE_CHIP_GOLD;
	public static MetaItem<?>.MetaValueItem CORE_CHIP_DIAMOND;
	public static MetaItem<?>.MetaValueItem CORE_CHIP_EMERALD_ADVANCED;
	public static MetaItem<?>.MetaValueItem CORE_CHIP_EMERALD_HIGH_ADVANCED;
	public static MetaItem<?>.MetaValueItem CORE_CHIP_SPATIAL;
	public static MetaItem<?>.MetaValueItem CORE_PROCESSOR_GOLD;
	public static MetaItem<?>.MetaValueItem CORE_PROCESSOR_DIAMOND;
	public static MetaItem<?>.MetaValueItem CORE_PROCESSOR_EMERALD;
	public static MetaItem<?>.MetaValueItem CORE_PROCESSOR_ADVANCED_EMERALD;
	public static MetaItem<?>.MetaValueItem CORE_PROCESSOR_SPATIAL;
	public static MetaItem<?>.MetaValueItem DISPLAY;
	public static MetaItem<?>.MetaValueItem CIRCUIT_BOARD;
	public static MetaItem<?>.MetaValueItem GOOD_CIRCUIT_BOARD;
	public static MetaItem<?>.MetaValueItem ADVANCED_CIRCUIT_BOARD;
	public static MetaItem<?>.MetaValueItem MORE_ADVANCED_CIRCUIT_BOARD;
	public static MetaItem<?>.MetaValueItem ELITE_CIRCUIT_BOARD;
	public static MetaItem<?>.MetaValueItem EXTREME_WETWARE_LIFESUPPORT_CIRCUIT_BOARD;
	public static MetaItem<?>.MetaValueItem PLASTIC_CIRCUIT_BOARD;
	public static MetaItem<?>.MetaValueItem ULTRA_BIO_MUTATED_CIRCUIT_BOARD;
	public static MetaItem<?>.MetaValueItem ENGINE_CORE;
	public static MetaItem<?>.MetaValueItem GOOD_ELECTRONIC_CIRCUIT;
	public static MetaItem<?>.MetaValueItem WORKSTATION;
	public static MetaItem<?>.MetaValueItem MAINFRAME;
	public static MetaItem<?>.MetaValueItem NANOPROCESSOR_MAINFRAME;
	public static MetaItem<?>.MetaValueItem ELITE_NANOCOMPUTER;
	public static MetaItem<?>.MetaValueItem QUANTUMPROCESSOR_ASSEMBLY;
	public static MetaItem<?>.MetaValueItem MASTER_QUANTUMCOMPUTER;
	public static MetaItem<?>.MetaValueItem CRYSTALPROCESSOR_ASSEMBLY;
	public static MetaItem<?>.MetaValueItem ELECTRONIC_CIRCUIT;
	public static MetaItem<?>.MetaValueItem ADVANCED_CIRCUIT;
	public static MetaItem<?>.MetaValueItem QUANTUMPROCESSOR_MAINFRAME;
	public static MetaItem<?>.MetaValueItem ULTIMATE_CRYSTALCOMPUTER;
	public static MetaItem<?>.MetaValueItem BIOPROCESSOR;
	public static MetaItem<?>.MetaValueItem CRYSTALPROCESSOR_MAINFRAME;
	public static MetaItem<?>.MetaValueItem BIOWAREPROCESSOR_ASSEMBLY;
	public static MetaItem<?>.MetaValueItem BIOWARE_SUPERCOMPUTER;
	public static MetaItem<?>.MetaValueItem ADVANCED_SMD_RESISTOR;
	public static MetaItem<?>.MetaValueItem ADVANCED_SMD_CAPACITOR;
	public static MetaItem<?>.MetaValueItem ADVANCED_SMD_TRANSISTOR;
	public static MetaItem<?>.MetaValueItem ADVANCED_SMD_DIODE;
	public static MetaItem<?>.MetaValueItem BIO_PROCESSING_UNIT;
	public static MetaItem<?>.MetaValueItem RAW_ADVANCED_CRYSTAL_CHIP;
	public static MetaItem<?>.MetaValueItem BIO_BALL;
	public static MetaItem<?>.MetaValueItem COMPRESSED_BIO_BALL;
	public static MetaItem<?>.MetaValueItem BIO_CHUNK;
	public static MetaItem<?>.MetaValueItem BIO_CARBON_PLATE;

	public static MetaItem<?>.MetaValueItem COVER_SOLAR_PANEL_MV;
	public static MetaItem<?>.MetaValueItem COVER_SOLAR_PANEL_HV;
	public static MetaItem<?>.MetaValueItem COVER_SOLAR_PANEL_EV;
	public static MetaItem<?>.MetaValueItem COVER_SOLAR_PANEL_IV;
	public static MetaItem<?>.MetaValueItem COVER_SOLAR_PANEL_LUV;
	public static MetaItem<?>.MetaValueItem COVER_SOLAR_PANEL_ZPM;
	public static MetaItem<?>.MetaValueItem COVER_SOLAR_PANEL_UV;
	public static MetaItem<?>.MetaValueItem IRRADIANT_REINFORCED_ALUMINIUM_PLATE;
	public static MetaItem<?>.MetaValueItem IRRADIANT_REINFORCED_BEDROCKIUM_PLATE;
	public static MetaItem<?>.MetaValueItem IRRADIANT_REINFORCED_CHROME_PLATE;
	public static MetaItem<?>.MetaValueItem IRRADIANT_REINFORCED_IRIDIUM_PLATE;
	public static MetaItem<?>.MetaValueItem IRRADIANT_REINFORCED_NAQUADRIA_PLATE;
	public static MetaItem<?>.MetaValueItem IRRADIANT_REINFORCED_NEUTRONIUM_PLATE;
	public static MetaItem<?>.MetaValueItem IRRADIANT_REINFORCED_TITANIUM_PLATE;
	public static MetaItem<?>.MetaValueItem IRRADIANT_REINFORCED_TUNGSTEN_PLATE;
	public static MetaItem<?>.MetaValueItem IRRADIANT_REINFORCED_TUNGSTENSTEEL_PLATE;
	public static MetaItem<?>.MetaValueItem ALUMINIUM_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem REINFORCED_ALUMINIUM_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem BEDROCKIUM_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem REINFORCED_BEDROCKIUM_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem CHROME_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem REINFORCED_CHROME_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem IRIDIUM_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem REINFORCED_IRIDIUM_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem NAQUADRIA_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem REINFORCED_NAQUADRIA_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem NEUTRONIUM_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem REINFORCED_NEUTRONIUM_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem TITANIUM_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem REINFORCED_TITANIUM_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem TUNGSTEN_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem REINFORCED_TUNGSTEN_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem TUNGSTENSTEEL_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem REINFORCED_TUNGSTENSTEEL_IRON_PLATE;
	public static MetaItem<?>.MetaValueItem ENRICHED_NAQUADRIA_SUNNARIUM_ALLOY;
	public static MetaItem<?>.MetaValueItem ENRICHED_NAQUADRIA_NEUTRONIUM_SUNNARIUM_ALLOY;
	public static MetaItem<?>.MetaValueItem ENRICHED_X_SUNNARIUM_ALLOY;
	public static MetaItem<?>.MetaValueItem POWER_IC;
	public static MetaItem<?>.MetaValueItem POWER_IC_WAFER;
	public static MetaItem<?>.MetaValueItem HIGH_POWER_IC;
	public static MetaItem<?>.MetaValueItem HIGH_POWER_IC_WAFER;
	public static MetaItem<?>.MetaValueItem ULTRA_HIGH_POWER_IC;
	public static MetaItem<?>.MetaValueItem ULTRA_HIGH_POWER_IC_WAFER;

	// TODO - nanosuit/quantumsuit mats
	//public static MetaItem<?>.MetaValueItem REINFORCED_ALUMINIUM_IRON_PLATE;


	public static void init() {
		GAMetaItem item = new GAMetaItem();
		item.setRegistryName("ga_meta_item");
		GAMetaTool tool = new GAMetaTool();
		tool.setRegistryName("ga_meta_tool");
	}

	public static void registerOreDict() {
		for (MetaItem<?> item : ITEMS) {
			if (item instanceof GAMetaItem) {
				((GAMetaItem) item).registerOreDict();
			}
		}
		// Circuit oredicts
		GOOD_ELECTRONIC_CIRCUIT.addOreDict("circuitGood");
		WORKSTATION.addOreDict("circuitExtreme");
		MAINFRAME.addOreDict("circuitElite");
		NANOPROCESSOR_MAINFRAME.addOreDict("circuitMaster");
		ELITE_NANOCOMPUTER.addOreDict("circuitElite");
		QUANTUMPROCESSOR_ASSEMBLY.addOreDict("circuitElite");
		MASTER_QUANTUMCOMPUTER.addOreDict("circuitMaster");
		CRYSTALPROCESSOR_ASSEMBLY.addOreDict("circuitMaster");
		ELECTRONIC_CIRCUIT.addOreDict("circuitBasic");
		ADVANCED_CIRCUIT.addOreDict("circuitAdvanced");
		QUANTUMPROCESSOR_MAINFRAME.addOreDict("circuitUltimate");
		ULTIMATE_CRYSTALCOMPUTER.addOreDict("circuitUltimate");
		BIOPROCESSOR.addOreDict("circuitUltimate");
		CRYSTALPROCESSOR_MAINFRAME.addOreDict("circuitSuperconductor");
		BIOWAREPROCESSOR_ASSEMBLY.addOreDict("circuitSuperconductor");
		BIOWARE_SUPERCOMPUTER.addOreDict("circuitInfinite");
	}

	public static void registerRecipes() {
		for (MetaItem<?> item : ITEMS) {
			if (item instanceof GAMetaTool) ((GAMetaTool) item).registerRecipes();
		}
	}

	public static ItemStack getFilledCell(Fluid fluid, int count) {
		ItemStack fluidCell = MetaItems.FLUID_CELL.getStackForm().copy();
		IFluidHandlerItem fluidHandlerItem = fluidCell.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
		try {
			fluidHandlerItem.fill(new FluidStack(fluid, 1000), true);

		} catch (Exception e) {
			GregicAdditions.LOGGER.error("The fluid " + fluid.toString() + " failed to do something with getFilledCell");
			GregicAdditions.LOGGER.error(e);
			fluidHandlerItem.fill(new FluidStack(FluidRegistry.WATER, 1000), true);
		}
		fluidCell = fluidHandlerItem.getContainer();
		fluidCell.setCount(count);
		return fluidCell;
	}

	public static ItemStack getFilledCell(Fluid fluid) {
		return getFilledCell(fluid, 1);
	}

	public static boolean hasPrefix(ItemStack stack, String prefix, String... ignore) {
		for (int i : OreDictionary.getOreIDs(stack)) {
			if (OreDictionary.getOreName(i).startsWith(prefix)) {
				boolean valid = true;
				for (String s : ignore) {
					if (OreDictionary.getOreName(i).startsWith(s)) valid = false;
				}
				if (!valid) continue;
				return true;
			}
		}
		return false;
	}
}
