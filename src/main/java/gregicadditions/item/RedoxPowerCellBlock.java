package gregicadditions.item;

import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class RedoxPowerCellBlock extends VariantBlock<RedoxPowerCellBlock.CellType> {
    public RedoxPowerCellBlock() {
        super(Material.IRON);
        this.setTranslationKey("power_cell");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("wrench", 2);
        this.setDefaultState(this.getState(CellType.CUPRONICKEL));
    }

    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public static enum CellType implements IStringSerializable {
        CUPRONICKEL("cupronickel", 10000,  Materials.Cupronickel),
        KANTHAL("kanthal", 100000,  Materials.Kanthal);

        private final String name;
        private final long capacity;
        private final gregtech.api.unification.material.type.Material material;

        private CellType(String name, long capacity, gregtech.api.unification.material.type.Material material) {
            this.name = name;
            this.capacity= capacity;
            this.material = material;
        }

        public String getName() {
            return this.name;
        }

        public long getCapacity() {
            return this.capacity;
        }

        public gregtech.api.unification.material.type.Material getMaterial() {
            return this.material;
        }
    }
}
