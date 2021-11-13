package gcym.integration.jei.multiblock;

import com.google.common.collect.ImmutableMap;
import gcym.integration.jei.multiblock.info.*;
import gregtech.api.gui.GuiTextures;
import gregtech.integration.jei.multiblock.MultiblockInfoRecipeWrapper;
import gcym.GregicalityMultiblocks;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.gui.recipes.RecipeLayout;
import net.minecraft.client.resources.I18n;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GCYMMultiblockInfoCategory implements IRecipeCategory<MultiblockInfoRecipeWrapper> {

    private final IDrawable background;
    private final IGuiHelper guiHelper;

    public GCYMMultiblockInfoCategory(IJeiHelpers helpers) {
        this.guiHelper = helpers.getGuiHelper();
        this.background = guiHelper.createBlankDrawable(176, 166);
    }

    public static final ImmutableMap<String, MultiblockInfoRecipeWrapper> multiblockRecipes = new ImmutableMap.Builder<String, MultiblockInfoRecipeWrapper>()
            .put("large_macerator", new MultiblockInfoRecipeWrapper(new LargeMaceratorInfo()))
            .put("large_arc_furnace", new MultiblockInfoRecipeWrapper(new LargeArcFurnaceInfo()))
            .put("large_assembler", new MultiblockInfoRecipeWrapper(new LargeAssemblerInfo()))
            // .put("large_alloy_smelter", new MultiblockInfoRecipeWrapper(new LargeAlloySmelterInfo()))
            .put("large_autoclave", new MultiblockInfoRecipeWrapper(new LargeAutoclaveInfo()))
            .put("large_bender", new MultiblockInfoRecipeWrapper(new LargeBenderInfo()))
            .put("large_brewery", new MultiblockInfoRecipeWrapper(new LargeBreweryInfo()))
            .put("large_centrifuge", new MultiblockInfoRecipeWrapper(new LargeCentrifugeInfo()))
            .put("large_chemical_bath", new MultiblockInfoRecipeWrapper(new LargeChemicalBathInfo()))
            .build();

    public static void registerRecipes(IModRegistry registry) {
        registry.addRecipes(multiblockRecipes.values(), String.format("%s:multiblock_info", GregicalityMultiblocks.MODID));
    }

    @Nonnull
    @Override
    public String getUid() {
        return String.format("%s:multiblock_info", getModName());
    }

    @Nonnull
    @Override
    public String getTitle() {
        return I18n.format("gregtech.multiblock.title");
    }

    @Nonnull
    @Override
    public String getModName() {
        return GregicalityMultiblocks.MODID;
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, MultiblockInfoRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
        recipeWrapper.setRecipeLayout((RecipeLayout) recipeLayout, guiHelper);

        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
        itemStackGroup.addTooltipCallback(recipeWrapper::addBlockTooltips);
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return guiHelper.drawableBuilder(GuiTextures.MULTIBLOCK_CATEGORY.imageLocation, 0, 0, 18, 18).setTextureSize(18, 18).build();
    }
}
