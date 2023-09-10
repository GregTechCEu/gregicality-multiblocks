package gregicality.multiblocks.api.metatileentity;

import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;

import gregicality.multiblocks.api.capability.IParallelHatch;

@SuppressWarnings("InstantiationOfUtilityClass")
public final class GCYMMultiblockAbility {

    public static final MultiblockAbility<IParallelHatch> PARALLEL_HATCH = new MultiblockAbility<>("parallel_hatch");

    public static final MultiblockAbility<ITieredMetaTileEntity> TIERED_HATCH = new MultiblockAbility<>("tiered_hatch");

    private GCYMMultiblockAbility() {}
}
