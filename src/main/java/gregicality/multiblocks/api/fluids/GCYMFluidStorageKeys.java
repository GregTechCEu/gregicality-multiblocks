package gregicality.multiblocks.api.fluids;

import gregicality.multiblocks.api.unification.material.GCYMMaterialIconTypes;
import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;
import gregtech.api.fluids.store.FluidStorageKey;

import static gregicality.multiblocks.api.utils.GCYMUtil.gcymId;

public final class GCYMFluidStorageKeys {

    public static final FluidStorageKey MOLTEN = new FluidStorageKey(gcymId("molten"),
            GCYMMaterialIconTypes.molten,
            s -> "molten." + s,
            m -> {
                if (m.hasProperty(GCYMPropertyKey.ALLOY_BLAST)) {
                    return "gcym.fluid.molten";
                }
                return "gregtech.fluid.generic";
            });

    private GCYMFluidStorageKeys() {}
}
