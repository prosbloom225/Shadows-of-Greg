package gregicadditions.machines;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.List;

import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;

public class TileEntityPowerStation extends MultiblockWithDisplayBase {

	private boolean isActive = false;
	protected IEnergyContainer energyContainer;

	public static final MultiblockAbility<TileEntityRedoxPowerCell> ABILITY_POWER_CELL = new MultiblockAbility();

	private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = { MultiblockAbility.INPUT_ENERGY, MultiblockAbility.OUTPUT_ENERGY};

	public TileEntityPowerStation(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId);
		//energyContainer = new EnergyContainerHandler(this, 20000000, 2048, 2, 2048, 2);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start(RIGHT, FRONT, UP)
				.aisle("XXSXX", "XXXXX", "XXXXX", "XXXXX", "XXXXX")
				.aisle("XXXXX", "XPPPX", "XPPPX", "XPPPX", "XXXXX").setRepeatable(2, 16)
				.aisle("XXXXX", "XXXXX", "XXXXX", "XXXXX", "XXXXX")
				.where('S', selfPredicate())
				.where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
				.where('P', abilityPartPredicate(ABILITY_POWER_CELL))
				.build();
	}


	public IBlockState getCasingState() {
	    return GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.POWER_STATION_CASING);
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart arg0) {
		return ClientHandler.POWER_STATION_CASING;
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		// TODO Auto-generated method stub
		return new TileEntityPowerStation(metaTileEntityId);
	}

	@Override
	protected void formStructure(PatternMatchContext context) {
		super.formStructure(context);
		this.energyContainer = new EnergyContainerHandler(this,
				// TODO - this allows for mixed power cell types...maybe rebalance later
				this.getAbilities(ABILITY_POWER_CELL).stream().map(c->c.getCapacity()).reduce(0L, Long::sum),
				// TODO - maybe just use the celltype andignore the actual power station values in the update() function
				// this really just adds an extra translation layer the energy has to go through
				this.getAbilities(MultiblockAbility.INPUT_ENERGY).stream().map(i->i.getInputVoltage()).reduce(Long::max).get(),
				this.getAbilities(MultiblockAbility.INPUT_ENERGY).stream().map(i->i.getInputAmperage()).reduce(Long::max).get(),
				this.getAbilities(MultiblockAbility.OUTPUT_ENERGY).stream().map(i->i.getOutputVoltage()).reduce(Long::max).get(),
				this.getAbilities(MultiblockAbility.OUTPUT_ENERGY).stream().map(i->i.getOutputAmperage()).reduce(Long::max).get());
		isActive = true;
	}

	@Override
	protected void updateFormedValid() {
	}

	@Override
	public void invalidateStructure() {
		super.invalidateStructure();
		isActive = false;
		energyContainer = null;
	}

	@Override
	public void update() {
		super.update();
		if (!isActive)
			return;
		List<IEnergyContainer> inputs = getAbilities(MultiblockAbility.INPUT_ENERGY);
		List<IEnergyContainer> outputs = getAbilities(MultiblockAbility.OUTPUT_ENERGY);

		int storagePacketCount = 0;
		int hatchPacketCount = 0;
		// add energy to storage
		for (IEnergyContainer input : inputs){
			hatchPacketCount = 0;
			while (storagePacketCount < energyContainer.getInputAmperage() &&
					hatchPacketCount < input.getInputAmperage() &&
					energyContainer.getEnergyCapacity() - energyContainer.getEnergyStored() >= energyContainer.getInputVoltage() &&
					input.getEnergyStored() >= input.getInputVoltage()) {
				input.removeEnergy(input.getInputVoltage());
				energyContainer.addEnergy(input.getInputVoltage());
				storagePacketCount++;
				hatchPacketCount++;
			}
		}
		storagePacketCount = 0;
		// add energy to output
		for (IEnergyContainer output : outputs){
			hatchPacketCount = 0;
			while (storagePacketCount < energyContainer.getOutputAmperage() &&
					hatchPacketCount < output.getOutputAmperage() &&
					energyContainer.getEnergyStored() >= energyContainer.getOutputVoltage() &&
					output.getEnergyStored() <= output.getOutputVoltage()) {
				energyContainer.removeEnergy(output.getOutputVoltage());
				output.addEnergy(output.getOutputVoltage());
				storagePacketCount++;
				hatchPacketCount++;
			}
		}
	}

	@Override
	public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
		this.getBaseTexture(null).render(renderState, translation, pipeline);
		ClientHandler.FUSION_REACTOR_OVERLAY.render(renderState, translation, pipeline, this.getFrontFacing(), isActive);
	}

	@Override
	protected void addDisplayText(List<ITextComponent> textList) {
		super.addDisplayText(textList);
		if (isStructureFormed()) {
			if (!isActive) {
				textList.add(new TextComponentTranslation("gregtech.multiblock.work_paused"));
			} else if (isActive) {
				textList.add(new TextComponentTranslation("gregtech.multiblock.running"));
				textList.add(new TextComponentTranslation("gregtech.multiblock.generation_eu",
						energyContainer.getOutputVoltage() * energyContainer.getOutputAmperage()));
				textList.add(new TextComponentTranslation("gregtech.multiblock.input_eu",
						energyContainer.getInputVoltage() * energyContainer.getInputAmperage()));
				textList.add(new TextComponentTranslation("gregtech.multiblock.capacity_eu",
						energyContainer.getEnergyCapacity()));
				textList.add(new TextComponentTranslation("gregtech.multiblock.stored_eu",
						energyContainer.getEnergyStored()));
			}
		}
	}
}
