package gregicality.multiblocks.api.unification.properties;

import gregtech.api.unification.material.properties.PropertyKey;

public final class GCYMPropertyKey {

    public static final PropertyKey<AlloyBlastProperty> ALLOY_BLAST = new PropertyKey<>("blast_alloy",
            AlloyBlastProperty.class);

    private GCYMPropertyKey() {}
}
