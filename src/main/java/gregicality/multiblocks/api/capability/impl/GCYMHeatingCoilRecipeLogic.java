package gregicality.multiblocks.api.capability.impl;

import gregtech.api.capability.IHeatingCoil;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.logic.OverclockingLogic;
import gregtech.api.recipes.recipeproperties.IRecipePropertyStorage;
import gregtech.api.recipes.recipeproperties.TemperatureProperty;
import org.jetbrains.annotations.NotNull;

public class GCYMHeatingCoilRecipeLogic extends GCYMMultiblockRecipeLogic {
    public GCYMHeatingCoilRecipeLogic(RecipeMapMultiblockController metaTileEntity) {
        super(metaTileEntity);
        if (!(metaTileEntity instanceof IHeatingCoil)) {
            throw new IllegalArgumentException("MetaTileEntity must be instanceof IHeatingCoil");
        }
    }

    protected void modifyOverclockPre(@NotNull int[] values, @NotNull IRecipePropertyStorage storage) {
        super.modifyOverclockPre(values, storage);
        values[0] = OverclockingLogic.applyCoilEUtDiscount(values[0], ((IHeatingCoil)this.metaTileEntity).getCurrentTemperature(), (Integer)storage.getRecipePropertyValue(TemperatureProperty.getInstance(), 0));
    }

    protected @NotNull int[] runOverclockingLogic(@NotNull IRecipePropertyStorage propertyStorage, int recipeEUt, long maxVoltage, int duration, int amountOC) {
        return OverclockingLogic.heatingCoilOverclockingLogic(Math.abs(recipeEUt), maxVoltage, duration, amountOC, ((IHeatingCoil)this.metaTileEntity).getCurrentTemperature(), (Integer)propertyStorage.getRecipePropertyValue(TemperatureProperty.getInstance(), 0));
    }
}
