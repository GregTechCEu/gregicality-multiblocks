package gregicality.multiblocks.integration.jei;

import gregicality.multiblocks.api.metatileentity.ITieredHatch;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;

@JEIPlugin
public class GCYMJeiPlugin implements IModPlugin {

    public static IIngredientRegistry ingredientRegistry;

    @Override
    public void register(@Nonnull IModRegistry registry) {
        // base stuff
        ingredientRegistry = registry.getIngredientRegistry();


        // tiered hatch
        List<TieredHatchRecipeWrapper> recipes = new ArrayList<>();
        for (Map.Entry<ResourceLocation, Map<Set<ItemStack>, Integer>> entry : ITieredHatch.TIERED_COMPONENTS.entrySet()) {
            recipes.addAll(entry.getValue().entrySet().stream()
                    .sorted(Comparator.comparingInt(Map.Entry::getValue))
                    .map(e -> new TieredHatchRecipeWrapper(entry.getKey(), e.getKey(), e.getValue()))
                    .collect(Collectors.toList()));
        }

        registry.addRecipes(recipes, TieredHatchRecipeCategory.UID);
        registry.addRecipeCatalyst(GCYMMetaTileEntities.TIERED_HATCH.getStackForm(), TieredHatchRecipeCategory.UID);
    }

    @Override
    public void registerCategories(@Nonnull IRecipeCategoryRegistration registry) {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();
        registry.addRecipeCategories(new TieredHatchRecipeCategory(jeiHelpers.getGuiHelper()));
    }
}
