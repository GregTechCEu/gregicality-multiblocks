package gregicality.multiblocks.api.fluids;

import static gregicality.multiblocks.api.utils.GCYMUtil.gcymId;

import gregtech.api.fluids.store.FluidStorageKey;

import gregicality.multiblocks.api.unification.material.GCYMMaterialIconTypes;
import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;

public final class GCYMFluidStorageKeys {

    public static final FluidStorageKey MOLTEN = new FluidStorageKey(gcymId("molten"),
            GCYMMaterialIconTypes.molten,
            m -> "molten." + m.getName(),
            m -> {
                if (m.hasProperty(GCYMPropertyKey.ALLOY_BLAST)) {
                    return "gcym.fluid.molten";
                }
                return "gregtech.fluid.generic";
            });

    private GCYMFluidStorageKeys() {}
}
