package gregicality.multiblocks.api.metatileentity;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gregicality.multiblocks.api.utils.GCYMLog;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.util.GTUtility;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ZenRegister
@ZenClass("mods.gcym.multiblocks.ITieredHatch")
public interface ITieredHatch {

    /**
     * A registry mapping a multiblock's {@link gregtech.api.metatileentity.MetaTileEntity#metaTileEntityId} to
     * voltage maximum tiers and their requirements
     */
    Map<ResourceLocation, Map<Set<ItemStack>, Integer>> TIERED_COMPONENTS = new Object2ObjectOpenHashMap<>();

    /**
     * Add a new multiblock or update an existing multiblock
     * <p>
     * Removes the tier entry if the ItemStack set is null.
     *
     * @param multiBlock the multiblock to add
     * @param stacks     the required items
     * @param tier       the non-negative voltage tier to provide
     */
    static void addMultiblockTier(@Nonnull MultiblockControllerBase multiBlock, @Nullable Set<ItemStack> stacks, int tier) {
        ResourceLocation metaTileEntityId = multiBlock.metaTileEntityId;
        if (TIERED_COMPONENTS.containsKey(metaTileEntityId)) {
            if (stacks == null) TIERED_COMPONENTS.remove(metaTileEntityId);
            else TIERED_COMPONENTS.get(metaTileEntityId).put(stacks, tier);
        } else {
            TIERED_COMPONENTS.put(metaTileEntityId, new Object2IntOpenHashMap<>());
            TIERED_COMPONENTS.get(metaTileEntityId).put(stacks, tier);
        }
    }

    /**
     * Use to remove a multiblock's tier data
     *
     * @param multiBlock the multiblock to remove data from
     * @param tier       the tier of the data to remove
     * @return true if the tier was removed successfully, otherwise false
     */
    static boolean removeMultiBlockTier(@Nonnull MultiblockControllerBase multiBlock, int tier) {
        if (!TIERED_COMPONENTS.containsKey(multiBlock.metaTileEntityId)) {
            GCYMLog.logger.error("Could not find multiblock tier data for multiblock {}", multiBlock.metaTileEntityId);
            return false;
        }
        Map<Set<ItemStack>, Integer> map = TIERED_COMPONENTS.get(multiBlock.metaTileEntityId);
        if (!map.containsValue(tier)) {
            GCYMLog.logger.error("Could not find multiblock tier data for multiblock {} of tier {}", multiBlock.metaTileEntityId, tier);
            return false;
        }
        return map.values().remove(tier);
    }

    @SuppressWarnings("unused")
    @ZenMethod("addMultiblockTier")
    static void addMultiblockTierCT(@Nonnull IItemStack stack, IItemStack[] stacks, int tier) {
        ItemStack itemStack = CraftTweakerMC.getItemStack(stack);
        MetaTileEntity metaTileEntity = GTUtility.getMetaTileEntity(itemStack);
        if (metaTileEntity == null) {
            CraftTweakerAPI.logError("Could not find MetaTileEntity for IItemStack " + stack.getName());
            return;
        }
        if (!(metaTileEntity instanceof MultiblockControllerBase)) {
            CraftTweakerAPI.logError("MetaTileEntity " + metaTileEntity.metaTileEntityId + " for IItemStack "
                    + stack.getName() + " is not a Multiblock Controller");
            return;
        }
        if (tier < GTValues.ULV) {
            CraftTweakerAPI.logError("Voltage tier " + tier + " cannot be less than 0.");
            return;
        }
        if (tier > GTValues.MAX) {
            CraftTweakerAPI.logError("Voltage tier " + tier + " cannot be more than 14.");
            return;
        }
        addMultiblockTier((MultiblockControllerBase) metaTileEntity,
                Arrays.stream(CraftTweakerMC.getItemStacks(stacks))
                        .filter(s -> !s.isEmpty()).collect(Collectors.toSet()),
                tier);
    }

    @SuppressWarnings("unused")
    @ZenMethod("removeMultiblockTier")
    static void removeMultiblockTierCT(@Nonnull IItemStack stack, int tier) {
        ItemStack itemStack = CraftTweakerMC.getItemStack(stack);
        MetaTileEntity metaTileEntity = GTUtility.getMetaTileEntity(itemStack);
        if (metaTileEntity == null) {
            CraftTweakerAPI.logError("Could not find MetaTileEntity for IItemStack " + stack.getName());
            return;
        }
        if (!(metaTileEntity instanceof MultiblockControllerBase)) {
            CraftTweakerAPI.logError("MetaTileEntity " + metaTileEntity.metaTileEntityId + " for IItemStack "
                    + stack.getName() + " is not a Multiblock Controller");
            return;
        }
        if (!removeMultiBlockTier((MultiblockControllerBase) metaTileEntity, tier)) {
            CraftTweakerAPI.logError("Failed to remove tier data for multiblock " + metaTileEntity.metaTileEntityId +
                    " of tier " + tier);
        }
    }

    /**
     * @return the maximum voltage tier this hatch provides
     */
    int getMaxVoltageTier();
}
