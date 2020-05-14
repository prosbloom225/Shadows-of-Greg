package gregicadditions.jei;

import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMetalCasing.MetalCasingType;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class TreeFarmInfo extends MultiblockInfoPage {

	public TreeFarmInfo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public MultiblockControllerBase getController() {
		return GATileEntities.TREE_FARM;
	}

	@Override
	public List<MultiblockShapeInfo> getMatchingShapes() {
		MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder().aisle("EXX", "XXX", "XXX").aisle("IXX", "X#X", "XXX").aisle("OXX", "XSX", "XXX")
				.where('S', GATileEntities.TREE_FARM, EnumFacing.SOUTH)
				.where('X', getCasingState())
				.where('#', Blocks.AIR.getDefaultState())
				.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.WEST)
				//.where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.LV], EnumFacing.WEST)
				.where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.LV], EnumFacing.WEST)
				.where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.LV], EnumFacing.WEST).build();

		return Lists.newArrayList(shapeInfo);
	}

	public IBlockState getCasingState() {
		return GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.FARM_CASING);
	}

	@Override
	public String[] getDescription() {
		// TODO Auto-generated method stub
		return new String[] { I18n.format("gregtech.multiblock.tree_farm.description") };
	}

}
