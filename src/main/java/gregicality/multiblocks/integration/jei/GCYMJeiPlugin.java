package gregicality.multiblocks.integration.jei;

import gregicality.multiblocks.api.metatileentity.ITieredHatch;
import gregicality.multiblocks.common.GCYMConfigHolder;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

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

        if (GCYMConfigHolder.globalMultiblocks.enableTieredCasings) {
            // tiered hatch
            Collection<TieredHatchRecipeWrapper> recipes = new ArrayList<>();
            for (Map.Entry<ResourceLocation, List<Pair<Set<GTRecipeInput>, Integer>>> entry : ITieredHatch.TIERED_COMPONENTS.entrySet()) {
                recipes.addAll(entry.getValue().stream() // sort by voltage tier ascending
                        .sorted(Comparator.comparingInt(Pair::getValue))
                        .map(e -> new TieredHatchRecipeWrapper(entry.getKey(),
                                e.getKey().stream()
                                        .sorted(Comparator.comparing(ri -> ri.getInputStacks()[0].getTranslationKey())) // sort by translation key
                                        .collect(Collectors.toList()),
                                e.getValue()))
                        .collect(Collectors.toList()));
            }

            registry.addRecipes(recipes, TieredHatchRecipeCategory.UID);
            registry.addRecipeCatalyst(GCYMMetaTileEntities.TIERED_HATCH.getStackForm(), TieredHatchRecipeCategory.UID);
        }
    }

    @Override
    public void registerCategories(@Nonnull IRecipeCategoryRegistration registry) {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();
        if (GCYMConfigHolder.globalMultiblocks.enableTieredCasings) {
            registry.addRecipeCategories(new TieredHatchRecipeCategory(jeiHelpers.getGuiHelper()));
        }
    }
}
