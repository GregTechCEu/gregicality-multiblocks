package gregicality.multiblocks.api.capability.impl;

import gregicality.multiblocks.api.capability.IParallelMultiblock;
import gregicality.multiblocks.api.metatileentity.GCYMMultiblockAbility;
import gregicality.multiblocks.api.metatileentity.IGCYMMultiBlock;
import gregicality.multiblocks.api.metatileentity.ITieredHatch;
import gregicality.multiblocks.common.GCYMConfigHolder;
import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;

import java.util.List;

public class GCYMMultiblockRecipeLogic extends MultiblockRecipeLogic {

    public GCYMMultiblockRecipeLogic(RecipeMapMultiblockController tileEntity) {
        super(tileEntity);
    }

    @Override
    public int getParallelLimit() {
        if (metaTileEntity instanceof IParallelMultiblock && ((IParallelMultiblock) metaTileEntity).isParallel())
            return ((IParallelMultiblock) metaTileEntity).getMaxParallel();

        return 1;
    }

    @Override
    public RecipeMapMultiblockController getMetaTileEntity() {
        return (RecipeMapMultiblockController) super.getMetaTileEntity();
    }

    @Override
    protected long getMaxVoltage() {
        if (!GCYMConfigHolder.globalMultiblocks.enableTieredCasings) {
            // not tiered, so get the regular max
            return super.getMaxVoltage();
        }

        if (getMetaTileEntity() instanceof IGCYMMultiBlock && !((IGCYMMultiBlock) getMetaTileEntity()).isTiered()) {
            // not tiered, so get the regular max
            return super.getMaxVoltage();
        }

        List<ITieredHatch> list = getMetaTileEntity().getAbilities(GCYMMultiblockAbility.TIERED_HATCH);
        if (list.isEmpty()) {
            // no tiered hatch present, so get the maximum based on the default
            return Math.min(super.getMaxVoltage(), GTValues.V[GCYMConfigHolder.globalMultiblocks.baseMultiblockTier]);
        }
        // get the maximum based on the hatch
        return Math.min(GTValues.V[list.get(0).getMaxVoltageTier()], super.getMaxVoltage());
    }
}
