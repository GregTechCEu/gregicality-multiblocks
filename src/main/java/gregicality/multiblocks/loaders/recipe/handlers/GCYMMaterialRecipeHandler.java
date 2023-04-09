package gregicality.multiblocks.loaders.recipe.handlers;

import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class GCYMMaterialRecipeHandler {

    private GCYMMaterialRecipeHandler() {/**/}

    public static void register() {
        OrePrefix.ingot.addProcessingHandler(PropertyKey.BLAST, GCYMMaterialRecipeHandler::generateAlloyBlastRecipes);
    }

    /**
     * Generates alloy blast recipes for a material
     *
     * @param material the material to generate for
     * @param property the blast property of the material
     */
    public static void generateAlloyBlastRecipes(@Nullable OrePrefix unused, @Nonnull Material material,
                                                 @Nonnull BlastProperty property) {
        if (material.hasProperty(GCYMPropertyKey.ALLOY_BLAST)) {
            material.getProperty(GCYMPropertyKey.ALLOY_BLAST).getRecipeProducer().produce(material, property);
        }
    }
}
