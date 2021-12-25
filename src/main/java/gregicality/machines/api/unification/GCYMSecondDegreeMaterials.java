package gregicality.machines.api.unification;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.machines.api.unification.GCYMMaterials.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;

public class GCYMSecondDegreeMaterials {

    public static void init() {
        HSLASteel = new Material.Builder(3020, "hsla_steel")
                .ingot()
                .color(0x808080).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Invar, 2, Vanadium, 1, Titanium, 1, Molybdenum, 1)
                .blastTemp(1811)
                .build();

        TitaniumTungstenCarbide = new Material.Builder(3021, "titanium_tungsten_carbide")
                .ingot()
                .color(0x800D0D).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(TungstenCarbide, 7, Titanium, 3)
                .blastTemp(3800)
                .build();

        Incoloy813 = new Material.Builder(3022, "incoloy813")
                .ingot()
                .color(0x37BF7E).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(VanadiumSteel, 4, Osmiridium, 2, Iridium, 7, Ruthenium, 5)
                .blastTemp(3625)
                .build();
    }
}
