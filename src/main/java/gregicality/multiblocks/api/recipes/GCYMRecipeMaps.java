package gregicality.multiblocks.api.recipes;

import gregtech.api.gui.GuiTextures;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.BlastRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;

import java.util.Collections;
import java.util.List;

public final class GCYMRecipeMaps {

    public static final RecipeMap<BlastRecipeBuilder> ALLOY_BLAST_RECIPES = new RecipeMap<>("alloy_blast_smelter", 9, 0,
            3, 1, new BlastRecipeBuilder(), false)
                    .setSlotOverlay(false, false, false, GuiTextures.FURNACE_OVERLAY_1)
                    .setSlotOverlay(false, false, true, GuiTextures.FURNACE_OVERLAY_1)
                    .setSlotOverlay(false, true, false, GuiTextures.FURNACE_OVERLAY_2)
                    .setSlotOverlay(false, true, true, GuiTextures.FURNACE_OVERLAY_2)
                    .setSlotOverlay(true, true, false, GuiTextures.FURNACE_OVERLAY_2)
                    .setSlotOverlay(true, true, true, GuiTextures.FURNACE_OVERLAY_2)
                    .setSound(GTSoundEvents.FURNACE);

    public static final RecipeMap<?>[] LINEAR_FORGING_RECIPES = new RecipeMap[]{
            RecipeMaps.BLAST_RECIPES,
            ALLOY_BLAST_RECIPES,
            new RecipeMap<>("lff_dual", 9, 3, 3, 1, new BlastRecipeBuilder(), false)
                    .setSlotOverlay(false, false, false, GuiTextures.FURNACE_OVERLAY_1)
                    .setSlotOverlay(false, true, false, GuiTextures.FURNACE_OVERLAY_2)
                    .setSlotOverlay(true, true, false, GuiTextures.FURNACE_OVERLAY_2)
                    .setSound(GTSoundEvents.FURNACE),
            RecipeMaps.VACUUM_RECIPES,
            new RecipeMap<>("lff_blast_cooled", 3, 3, 3, 2, new BlastRecipeBuilder(), false),
            new RecipeMap<>("lff_alloy_cooled", 9, 0, 3, 2, new BlastRecipeBuilder(), false)
                    .setSlotOverlay(false, false, false, GuiTextures.FURNACE_OVERLAY_1)
                    .setSlotOverlay(false, false, true, GuiTextures.FURNACE_OVERLAY_1)
                    .setSlotOverlay(false, true, false, GuiTextures.FURNACE_OVERLAY_2)
                    .setSlotOverlay(true, true, false, GuiTextures.FURNACE_OVERLAY_2)
                    .setSound(GTSoundEvents.FURNACE),
            new RecipeMap<>("lff_dual_cooled", 12, 3, 4, 2, new BlastRecipeBuilder(), false)
                    .setSlotOverlay(false, false, false, GuiTextures.FURNACE_OVERLAY_1)
                    .setSlotOverlay(false, true, false, GuiTextures.FURNACE_OVERLAY_2)
                    .setSlotOverlay(true, true, false, GuiTextures.FURNACE_OVERLAY_2)
                    .setSound(GTSoundEvents.FURNACE),
            new RecipeMap<>("lff_blast_forging_cooled", 3, 3, 3, 2, new BlastRecipeBuilder(), false),
            new RecipeMap<>("lff_alloy_forging_cooled", 9, 0, 3, 2, new BlastRecipeBuilder(), false)
                    .setSlotOverlay(false, false, false, GuiTextures.FURNACE_OVERLAY_1)
                    .setSlotOverlay(false, false, true, GuiTextures.FURNACE_OVERLAY_1)
                    .setSlotOverlay(false, true, false, GuiTextures.FURNACE_OVERLAY_2)
                    .setSlotOverlay(true, true, false, GuiTextures.FURNACE_OVERLAY_2)
                    .setSound(GTSoundEvents.FURNACE),
            new RecipeMap<>("lff_dual_forging_cooled", 12, 3, 4, 2, new BlastRecipeBuilder(), false)
                    .setSlotOverlay(false, false, false, GuiTextures.FURNACE_OVERLAY_1)
                    .setSlotOverlay(false, true, false, GuiTextures.FURNACE_OVERLAY_2)
                    .setSlotOverlay(true, true, false, GuiTextures.FURNACE_OVERLAY_2)
                    .setSound(GTSoundEvents.FURNACE)
    };

    private GCYMRecipeMaps() {}
}
