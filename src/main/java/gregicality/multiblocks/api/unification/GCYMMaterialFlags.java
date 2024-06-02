package gregicality.multiblocks.api.unification;

import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.properties.PropertyKey;

public final class GCYMMaterialFlags {

    /**
     * Use to disable alloy blast recipes from generating
     */
    public static final MaterialFlag NO_ALLOY_BLAST_RECIPES = new MaterialFlag.Builder("no_alloy_blast_recipes")
            .requireProps(PropertyKey.BLAST, PropertyKey.FLUID)
            .build();

    /**
     * Use to disable everything related to alloy blasting
     */
    public static final MaterialFlag DISABLE_ALLOY_PROPERTY = new MaterialFlag.Builder("disable_alloy_property")
            .requireProps(PropertyKey.BLAST, PropertyKey.FLUID)
            .requireFlags(NO_ALLOY_BLAST_RECIPES)
            .build();

    /**
     * Use to disable forging recipes from generating where this material is an input. Not compatible with ore entries.
     */
    public static final MaterialFlag NO_FORGING_RECIPES_IN = new MaterialFlag.Builder("no_forging_recipes_in")
            .build();

    /**
     * Use to disable forging recipes from forging this material as an output.
     */
    public static final MaterialFlag NO_FORGING_OUT = new MaterialFlag.Builder("no_forging_out")
            .build();

    private GCYMMaterialFlags() {}
}
