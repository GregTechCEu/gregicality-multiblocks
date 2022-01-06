package gregicality.machines.api.capability.impl;

import gregicality.machines.api.capability.IParallelMultiblock;
import gregicality.machines.api.metatileentity.GCYMMultiblockAbility;
import gregicality.machines.common.GCYMConfigHolder;
import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
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
    public void setParallelRecipesPerformed(int amount) {
        super.setParallelRecipesPerformed(amount);
    }

    @Override
    public RecipeMapMultiblockController getMetaTileEntity() {
        return (RecipeMapMultiblockController) super.getMetaTileEntity();
    }

    @Override
    protected long getMaxVoltage() {
        if (!GCYMConfigHolder.globalMultiblocks.enableTieredCasings)
            return super.getMaxVoltage();

        List<ITieredMetaTileEntity> list = getMetaTileEntity().getAbilities(GCYMMultiblockAbility.TIERED_HATCH);

        if (list.isEmpty())
            return super.getMaxVoltage();

        return Math.min(GTValues.V[list.get(0).getTier()], super.getMaxVoltage());
    }
}
