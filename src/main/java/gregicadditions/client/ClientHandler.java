package gregicadditions.client;

import gregicadditions.GregicAdditions;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.machines.CrateRenderer;
import gregicadditions.machines.DrumRenderer;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.OrientedOverlayRenderer.OverlayFace;
import gregtech.api.render.SimpleCubeRenderer;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = GregicAdditions.MODID, value = Side.CLIENT)
public class ClientHandler {

	public static SimpleCubeRenderer COKE_OVEN_BRICKS = new SimpleCubeRenderer("casings/solid/machine_coke_oven_bricks");
	public static SimpleCubeRenderer FUSION_TEXTURE = new SimpleCubeRenderer("casings/fusion/machine_casing_fusion_glass");
	public static SimpleCubeRenderer ACTIVE_FUSION_TEXTURE = new SimpleCubeRenderer("gregtech:casings/fusion/machine_casing_fusion_glass_yellow");
	public static OrientedOverlayRenderer COKE_OVEN_OVERLAY = new OrientedOverlayRenderer("machines/coke_oven", new OverlayFace[] { OverlayFace.FRONT });
	public static OrientedOverlayRenderer NAQADAH_OVERLAY = new OrientedOverlayRenderer("machines/naquadah_reactor", new OverlayFace[] { OverlayFace.FRONT, OverlayFace.BACK, OverlayFace.TOP });
	public static OrientedOverlayRenderer REPLICATOR_OVERLAY = new OrientedOverlayRenderer("machines/replicator", new OverlayFace[] { OverlayFace.FRONT });
	public static OrientedOverlayRenderer MASS_FAB_OVERLAY = new OrientedOverlayRenderer("machines/mass_fab", new OverlayFace[] { OverlayFace.FRONT });
	public static OrientedOverlayRenderer FUSION_REACTOR_OVERLAY = new OrientedOverlayRenderer("machines/fusion_reactor", new OverlayFace[] { OverlayFace.FRONT });
	public static DrumRenderer BARREL = new DrumRenderer("storage/drums/barrel");
	public static DrumRenderer DRUM = new DrumRenderer("storage/drums/drum");
	public static CrateRenderer WOODEN_CRATE = new CrateRenderer("storage/crates/wooden_crate");
	public static CrateRenderer METAL_CRATE = new CrateRenderer("storage/crates/metal_crate");
	public static final TextureArea BRONZE_FLUID_SLOT = TextureArea.fullImage("textures/gui/steam/fluid_slot.png");
	public static final TextureArea COAL_OVERLAY = TextureArea.fullImage("textures/gui/steam/bronze/overlay_bronze_coal.png");

	public static SimpleCubeRenderer ZIRCONIUM_CARBIDE_MACHINE_CASING = new SimpleCubeRenderer("casings/solid/machine_casing_zirconium_carbide");
	public static SimpleCubeRenderer CENTRIFUGE_MACHINE_CASING = new SimpleCubeRenderer("casings/solid/machine_casing_centrifuge");
	public static SimpleCubeRenderer MACERATOR_MACHINE_CASING = new SimpleCubeRenderer("casings/solid/machine_casing_macerator");
	public static SimpleCubeRenderer ORE_WASHER_MACHINE_CASING = new SimpleCubeRenderer("casings/solid/machine_casing_ore_washer");
	public static SimpleCubeRenderer POWER_STATION_CASING = new SimpleCubeRenderer("casings/solid/machine_casing_power_station");

	public static SimpleCubeRenderer []REDOX_POWER_CELL = new SimpleCubeRenderer[]{
			new SimpleCubeRenderer("casings/redox/redox1"),
			new SimpleCubeRenderer("casings/redox/redox2"),
			new SimpleCubeRenderer("casings/redox/redox3"),
			new SimpleCubeRenderer("casings/redox/redox4"),
			new SimpleCubeRenderer("casings/redox/redox5"),
			new SimpleCubeRenderer("casings/redox/redox6")};

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		GAMetaBlocks.registerItemModels();
	}
}
