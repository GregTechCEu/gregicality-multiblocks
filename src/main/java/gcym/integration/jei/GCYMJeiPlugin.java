package gcym.integration.jei;

import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gcym.integration.jei.multiblock.GCYMMultiblockInfoCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

import javax.annotation.Nonnull;

@JEIPlugin
public class GCYMJeiPlugin implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new GCYMMultiblockInfoCategory(registry.getJeiHelpers()));
    }

    @Override
    public void register(@Nonnull IModRegistry registry) {
        GCYMMultiblockInfoCategory.registerRecipes(registry);

        //Multiblock info page registration
        GCYMMultiblockInfoCategory.multiblockRecipes.values().forEach(v -> {
            MultiblockInfoPage infoPage = v.getInfoPage();
            registry.addIngredientInfo(infoPage.getController().getStackForm(),
                    VanillaTypes.ITEM,
                    infoPage.getDescription());
        });

    }
}
