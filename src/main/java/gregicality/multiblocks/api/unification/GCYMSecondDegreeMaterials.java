package gregicality.multiblocks.api.unification;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty;

import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;

public class GCYMSecondDegreeMaterials {

    public static void init() {
        HSLASteel = new Material.Builder(3020, "hsla_steel")
                .ingot().fluid()
                .color(0x808080).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_SPRING, GENERATE_FRAME)
                .components(Invar, 2, Vanadium, 1, Titanium, 1, Molybdenum, 1)
                .blastTemp(1711, BlastProperty.GasTier.LOW, GTValues.VA[GTValues.HV], 1000)
                .build();

        TitaniumTungstenCarbide = new Material.Builder(3021, "titanium_tungsten_carbide")
                .ingot().fluid()
                .color(0x800D0D).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE)
                .components(TungstenCarbide, 1, TitaniumCarbide, 2)
                .blastTemp(3800, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.EV], 1000)
                .build();

        IncoloyMA956 = new Material.Builder(3022, "incoloy_ma_956")
                .ingot().fluid()
                .color(0x37BF7E).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(VanadiumSteel, 4, Manganese, 2, Aluminium, 5, Yttrium, 2)
                .blastTemp(3625, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 800)
                .build();
    }
}
