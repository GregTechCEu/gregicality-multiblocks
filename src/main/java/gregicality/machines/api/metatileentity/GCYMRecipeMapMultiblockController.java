package gregicality.machines.api.metatileentity;

import gregicality.machines.api.capability.IParallelMultiblock;
import gregicality.machines.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.machines.common.GCYMConfigHolder;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.RecipeMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class GCYMRecipeMapMultiblockController extends RecipeMapMultiblockController implements IParallelMultiblock {


    public GCYMRecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap);
        this.recipeMapWorkable = new GCYMMultiblockRecipeLogic(this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        if (isParallel())
            tooltip.add(I18n.format("gcym.tooltip.parallel_enabled"));
        if (GCYMConfigHolder.globalMultiblocks.enableTieredCasings && isTiered())
            tooltip.add(I18n.format("gcym.tooltip.tiered_hatch_enabled"));
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        List<ITieredMetaTileEntity> list = getAbilities(GCYMMultiblockAbility.TIERED_HATCH);
        if (GCYMConfigHolder.globalMultiblocks.enableTieredCasings && !list.isEmpty()) {
            long maxVoltage = Math.min(GTValues.V[list.get(0).getTier()], Math.max(energyContainer.getInputVoltage(), energyContainer.getOutputVoltage()));
            String voltageName = GTValues.VNF[list.get(0).getTier()];
            textList.add(new TextComponentTranslation("gcym.multiblock.tiered_hatch.tooltip", maxVoltage, voltageName));
        }
    }

    @Override
    public boolean isParallel() {
        return true;
    }

    @Override
    public int getMaxParallel() {
        return this.getAbilities(GCYMMultiblockAbility.PARALLEL_HATCH).isEmpty() ? 1 :
                this.getAbilities(GCYMMultiblockAbility.PARALLEL_HATCH).get(0).getCurrentParallel();
    }

    public boolean isTiered() {
        return GCYMConfigHolder.globalMultiblocks.enableTieredCasings;
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, true);
    }

    @Override
    public TraceabilityPredicate autoAbilities(boolean checkEnergyIn, boolean checkMaintenance, boolean checkItemIn, boolean checkItemOut, boolean checkFluidIn, boolean checkFluidOut, boolean checkMuffler) {
        TraceabilityPredicate predicate = super.autoAbilities(checkEnergyIn, checkMaintenance, checkItemIn, checkItemOut, checkFluidIn, checkFluidOut, checkMuffler);
        if (isParallel())
            predicate = predicate.or(abilities(GCYMMultiblockAbility.PARALLEL_HATCH).setMaxGlobalLimited(1).setPreviewCount(1));
        return predicate;
    }

    @Nonnull
    public static TraceabilityPredicate tieredCasing() {
        return new TraceabilityPredicate(abilities(GCYMMultiblockAbility.TIERED_HATCH).setMinGlobalLimited(GCYMConfigHolder.globalMultiblocks.enableTieredCasings ? 1 : 0).setMaxGlobalLimited(1));
    }
}
