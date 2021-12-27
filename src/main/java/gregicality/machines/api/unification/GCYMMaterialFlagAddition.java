package gregicality.machines.api.unification;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlags;

public class GCYMMaterialFlagAddition {

    public static void init() {

        // Frames
        Materials.TungstenCarbide.addFlags(MaterialFlags.GENERATE_FRAME);
        Materials.Tungsten.addFlags(MaterialFlags.GENERATE_FRAME);

        // Small Gears
        Materials.TungstenCarbide.addFlags(MaterialFlags.GENERATE_SMALL_GEAR);

        // Rods
        Materials.Graphite.addFlags(MaterialFlags.GENERATE_ROD);

        // Long Rods
        Materials.Neutronium.addFlags(MaterialFlags.GENERATE_LONG_ROD);

        // Springs
        Materials.Neutronium.addFlags(MaterialFlags.GENERATE_SPRING);

        // Dense Plates
        Materials.Neutronium.addFlags(MaterialFlags.GENERATE_DENSE);
    }
}
