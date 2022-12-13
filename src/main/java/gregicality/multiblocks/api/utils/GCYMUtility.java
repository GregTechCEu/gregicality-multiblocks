package gregicality.multiblocks.api.utils;

import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.stream.Collectors;

public class GCYMUtility {

    public static Set<GTRecipeInput> stacksToInput(@Nonnull Set<ItemStack> stacks) {
        return stacks.stream().map(GTRecipeItemInput::getOrCreate).collect(Collectors.toSet());
    }
}
