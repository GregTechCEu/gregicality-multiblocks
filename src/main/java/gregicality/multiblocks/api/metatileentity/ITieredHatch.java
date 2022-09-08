package gregicality.multiblocks.api.metatileentity;

import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;

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
     * @return the maximum voltage tier this hatch provides
     */
    int getMaxVoltageTier();
}
