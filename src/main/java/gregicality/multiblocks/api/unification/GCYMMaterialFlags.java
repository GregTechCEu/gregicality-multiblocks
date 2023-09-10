package gregicality.multiblocks.api.unification;

import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.properties.PropertyKey;

public final class GCYMMaterialFlags {

    /**
     * Use to disable alloy blast recipes from generating
     */
    public static final MaterialFlag DISABLE_ALLOY_BLAST = new MaterialFlag.Builder("disable_alloy_blast")
            .requireProps(PropertyKey.BLAST, PropertyKey.FLUID)
            .build();

    /**
     * Use to disable everything related to alloy blasting
     */
    public static final MaterialFlag DISABLE_ALLOY_PROPERTY = new MaterialFlag.Builder("disable_alloy_property")
            .requireProps(PropertyKey.BLAST, PropertyKey.FLUID)
            .requireFlags(DISABLE_ALLOY_BLAST)
            .build();

    private GCYMMaterialFlags() {}
}
