package gregicality.multiblocks.api.metatileentity;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.oredict.IOreDictEntry;
import gregicality.multiblocks.api.utils.GCYMLog;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.recipes.ingredients.CraftTweakerItemInputWrapper;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.GTRecipeOreInput;
import gregtech.api.util.GTUtility;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

@ZenRegister
@ZenClass("mods.gcym.multiblocks.ITieredHatch")
public interface ITieredHatch {

    /**
     * A registry mapping a multiblock's {@link gregtech.api.metatileentity.MetaTileEntity#metaTileEntityId} to
     * voltage maximum tiers and their requirements
     */
    Map<ResourceLocation, List<Pair<Set<GTRecipeInput>, Integer>>> TIERED_COMPONENTS = new Object2ObjectOpenHashMap<>();

    /**
     * Add a new multiblock or update an existing multiblock
     * <p>
     * Removes the tier entry if the ItemStack set is null.
     *
     * @param multiBlock the multiblock to add
     * @param inputs     the required inputs
     * @param tier       the non-negative voltage tier to provide
     */
    static void addMultiblockTier(@Nonnull MultiblockControllerBase multiBlock, int tier, @Nullable GTRecipeInput... inputs) {
        addMultiblockTier(multiBlock, tier, inputs == null ? null : new ObjectOpenHashSet<>(inputs));
    }

    /**
     * Add a new multiblock or update an existing multiblock
     * <p>
     * Removes the tier entry if the ItemStack set is null.
     *
     * @param multiBlock the multiblock to add
     * @param inputs     the required inputs
     * @param tier       the non-negative voltage tier to provide
     */
    static void addMultiblockTier(@Nonnull MultiblockControllerBase multiBlock, int tier, @Nullable Set<GTRecipeInput> inputs,
                                  @Nullable GTRecipeInput... additional) {
        Set<GTRecipeInput> set;
        if (additional != null) {
            set = new ObjectOpenHashSet<>(additional);
            if (inputs != null) set.addAll(inputs);
        } else if (inputs != null) {
            set = new ObjectOpenHashSet<>(inputs);
        } else {
            set = null;
        }
        addMultiblockTier(multiBlock, tier, set);
    }

    /**
     * Add a new multiblock or update an existing multiblock
     * <p>
     * Removes the tier entry if the ItemStack set is null.
     *
     * @param multiBlock the multiblock to add
     * @param inputs     the required inputs
     * @param tier       the non-negative voltage tier to provide
     */
    static void addMultiblockTier(@Nonnull MultiblockControllerBase multiBlock, int tier, @Nullable Set<GTRecipeInput> inputs) {
        ResourceLocation metaTileEntityId = multiBlock.metaTileEntityId;
        if (TIERED_COMPONENTS.containsKey(metaTileEntityId)) {
            if (inputs == null) TIERED_COMPONENTS.remove(metaTileEntityId);
            else TIERED_COMPONENTS.get(metaTileEntityId).add(Pair.of(inputs, tier));
        } else if (inputs != null) {
            List<Pair<Set<GTRecipeInput>, Integer>> list = new ArrayList<>();
            TIERED_COMPONENTS.put(metaTileEntityId, list);
            TIERED_COMPONENTS.get(metaTileEntityId).add(Pair.of(inputs, tier));
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
        List<Pair<Set<GTRecipeInput>, Integer>> list = TIERED_COMPONENTS.get(multiBlock.metaTileEntityId);
        if (!list.stream().map(Pair::getValue).collect(Collectors.toSet()).contains(tier)) {
            GCYMLog.logger.error("Could not find multiblock tier data for multiblock {} of tier {}", multiBlock.metaTileEntityId, tier);
            return false;
        }
        Iterator<Pair<Set<GTRecipeInput>, Integer>> itr = list.iterator();
        while (itr.hasNext()) {
            if (itr.next().getValue() == tier) {
                itr.remove();
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unused")
    @ZenMethod("addMultiblockTier")
    static void addMultiblockTierCT(@Nonnull IItemStack stack, int tier, IItemStack[] stacks) {
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
        addMultiblockTier((MultiblockControllerBase) metaTileEntity, tier,
                Arrays.stream(CraftTweakerMC.getItemStacks(stacks))
                        .filter(s -> !s.isEmpty())
                        .map(GTRecipeItemInput::getOrCreate)
                        .collect(Collectors.toSet()));
    }

    @SuppressWarnings("unused")
    @ZenMethod("addMultiblockTier")
    static void addMultiblockTierCT(@Nonnull IItemStack stack, int tier, IIngredient[] ingredients) {
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

        Set<GTRecipeInput> inputs = new ObjectOpenHashSet<>();
        for (IIngredient ingredient : ingredients) {
            String oreDict = null;
            if (ingredient instanceof IOreDictEntry) {
                oreDict = ((IOreDictEntry) ingredient).getName();
            } else if (ingredient.getInternal() instanceof IOreDictEntry) {
                oreDict = ((IOreDictEntry) ingredient.getInternal()).getName();
            }

            if (ingredient.getItems().isEmpty()) {
                if (oreDict != null) {
                    CraftTweakerAPI.logError("Invalid Ore Dictionary [" + oreDict + "]: contains no items");
                } else {
                    CraftTweakerAPI.logError("Invalid Item [" + ingredient + "]: item not found");
                }
                return;
            }

            if (oreDict != null) {
                inputs.add(GTRecipeOreInput.getOrCreate(oreDict, ingredient.getAmount()));
            } else {
                inputs.add(CraftTweakerItemInputWrapper.getOrCreate(ingredient, ingredient.getAmount()));
            }
        }

        addMultiblockTier((MultiblockControllerBase) metaTileEntity, tier, inputs);
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
