package gregicality.machines.api.unification;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.machines.api.unification.GCYMMaterials.NikolineAlloy;
import static gregtech.api.unification.material.Materials.Electrotine;
import static gregtech.api.unification.material.Materials.Silicon;
import static gregtech.api.unification.material.info.MaterialFlags.*;

public class GCYMHighDegreeMaterials {

    public static void init() {
        NikolineAlloy = new Material.Builder(3041, "nikoline_alloy")
                .ingot()
                .color(0x325A8C).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Electrotine, 1, Silicon, 1)
                .blastTemp(1593)
                .build();
    }
}
