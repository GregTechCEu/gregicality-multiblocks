package gregicality.multiblocks.api.metatileentity;

import gregicality.multiblocks.api.capability.IParallelMultiblock;
import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.common.GCYMConfigHolder;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public abstract class GCYMRecipeMapMultiblockController extends MultiMapMultiblockController implements IParallelMultiblock, IGCYMMultiBlock {

    public GCYMRecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        this(metaTileEntityId, new RecipeMap<?>[]{recipeMap});
    }

    public GCYMRecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?>[] recipeMaps) {
        super(metaTileEntityId, recipeMaps);
        this.recipeMapWorkable = new GCYMMultiblockRecipeLogic(this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        if (isParallel()) {
            tooltip.add(I18n.format("gcym.tooltip.parallel_enabled"));
        }
        if (GCYMConfigHolder.globalMultiblocks.enableTieredCasings && isTiered()) {
            tooltip.add(I18n.format("gcym.tooltip.tiered_hatch_enabled", GTValues.VNF[GCYMConfigHolder.globalMultiblocks.baseMultiblockTier]));
        }
    }

    @Override
    protected void addExtraDisplayInfo(List<ITextComponent> textList) {
        super.addExtraDisplayInfo(textList);
        if (GCYMConfigHolder.globalMultiblocks.enableTieredCasings && this.isTiered()) {
            long maxVoltage;
            String voltageName;
            List<ITieredHatch> list = getAbilities(GCYMMultiblockAbility.TIERED_HATCH);

            if (list.isEmpty()) {
                maxVoltage = Math.min(GTValues.V[GCYMConfigHolder.globalMultiblocks.baseMultiblockTier], Math.max(energyContainer.getInputVoltage(), energyContainer.getOutputVoltage()));
                voltageName = GTValues.VNF[GTUtility.getTierByVoltage(maxVoltage)];
            } else {
                int maxVoltageTier = list.get(0).getMaxVoltageTier();
                maxVoltage = Math.min(GTValues.V[maxVoltageTier], Math.max(energyContainer.getInputVoltage(), energyContainer.getOutputVoltage()));
                voltageName = GTValues.VNF[maxVoltageTier];
            }
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

    @Override
    public boolean isTiered() {
        return GCYMConfigHolder.globalMultiblocks.enableTieredCasings;
    }

    @Override
    public TraceabilityPredicate autoAbilities(boolean checkEnergyIn, boolean checkMaintenance, boolean checkItemIn, boolean checkItemOut, boolean checkFluidIn, boolean checkFluidOut, boolean checkMuffler) {
        TraceabilityPredicate predicate = super.autoAbilities(checkEnergyIn, checkMaintenance, checkItemIn, checkItemOut, checkFluidIn, checkFluidOut, checkMuffler);
        if (this.isParallel()) {
            predicate = predicate.or(abilities(GCYMMultiblockAbility.PARALLEL_HATCH)
                    .setMaxGlobalLimited(1)
                    .setPreviewCount(1));
        }
        if (this.isTiered()) {
            predicate = predicate.or(abilities(GCYMMultiblockAbility.TIERED_HATCH)
                    .setMaxGlobalLimited(1)
                    .setPreviewCount(1));
        }
        return predicate;
    }
}
