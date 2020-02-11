package gregicadditions.machines;

import gregicadditions.client.ClientHandler;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.metatileentities.electric.multiblockpart.MetaTileEntityMultiblockPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class TileEntityRedoxPowerCell extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<TileEntityRedoxPowerCell> {
    private int tier;
    private long []capacity = new long[]{10000, 100000, 250000, 500000, 1000000, 10000000};

    public TileEntityRedoxPowerCell(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
        this.tier = tier;
    }

    public long getCapacity() {
        return capacity[tier-4];
    }

    @Override
    public ICubeRenderer getBaseTexture() {
        return ClientHandler.REDOX_POWER_CELL[tier-4];
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new TileEntityRedoxPowerCell(metaTileEntityId, 4);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }
    @Override
    protected boolean openGUIOnRightClick() {
        return false;
    }

    @Override
    public MultiblockAbility<TileEntityRedoxPowerCell> getAbility() {
        return TileEntityPowerStation.ABILITY_POWER_CELL;
    }

    @Override
    public void registerAbilities(List<TileEntityRedoxPowerCell> list) {
        list.add(this);
    }
}
