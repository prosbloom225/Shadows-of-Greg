package gregicadditions.item;

import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GAMultiblockCasing extends VariantBlock<GAMultiblockCasing.CasingType> {

	public GAMultiblockCasing() {
		super(Material.IRON);
		setTranslationKey("ga_multiblock_casing");
		setHardness(5.0f);
		setResistance(10.0f);
		setSoundType(SoundType.METAL);
		setHarvestLevel("wrench", 2);
		setDefaultState(getState(CasingType.COKE_OVEN_BRICKS));
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
		return false;
	}

	public enum CasingType implements IStringSerializable {

		COKE_OVEN_BRICKS("coke_oven_bricks"),
		TUNGSTENSTEEL_GEARBOX_CASING("tungstensteel_gearbox_casing"),
		ZIRCONIUM_CARBIDE_CASING("zirconium_carbide_casing"),
		CENTRIFUGE_CASING("centrifuge_casing"),
		MACERATOR_CASING("macerator_casing"),
		ORE_WASHER_CASING("ore_washer_casing"),
		POWER_STATION_CASING("power_station_casing"),
		SIEVE_CASING("sieve_casing"),
		SIEVE_GRATE("sieve_grate"),
		CUTTER_CASING("cutter_casing"),
		BLAST_SMELTER_CONTAINMENT_CASTING("blast_smelter_containment_casing"),
		WIREMILL_CASING("wiremill_casing"),
		ELECTROLYZER_CASING("electrolyzer_casing"),
		FARM_CASING("farm_casing")
		;

		private final String name;

		CasingType(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}

	}
}
