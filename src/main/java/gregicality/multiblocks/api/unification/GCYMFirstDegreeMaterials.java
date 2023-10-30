package gregicality.multiblocks.api.unification;

import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregicality.multiblocks.api.utils.GCYMUtil.gcymId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty;

public final class GCYMFirstDegreeMaterials {

    private GCYMFirstDegreeMaterials() {}

    public static void init() {
        Stellite100 = new Material.Builder(3000, gcymId("stellite_100"))
                .ingot().fluid()
                .color(0xDEDEFF).iconSet(MaterialIconSet.BRIGHT)
                .flags(GENERATE_PLATE)
                .components(Iron, 4, Chrome, 3, Tungsten, 2, Molybdenum, 1)
                .blastTemp(3790, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.EV], 1000)
                .build();

        WatertightSteel = new Material.Builder(3001, gcymId("watertight_steel"))
                .ingot().fluid()
                .color(0x355D6A).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Iron, 7, Aluminium, 4, Nickel, 2, Chrome, 1, Sulfur, 1)
                .blastTemp(3850, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 800)
                .build();

        MaragingSteel300 = new Material.Builder(3002, gcymId("maraging_steel_300"))
                .ingot().fluid()
                .color(0x637087).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Iron, 16, Titanium, 1, Aluminium, 1, Nickel, 4, Cobalt, 2)
                .blastTemp(4000, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.EV], 1000)
                .build();

        HastelloyC276 = new Material.Builder(3003, gcymId("hastelloy_c_276"))
                .ingot().fluid()
                .color(0xCF3939).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Nickel, 12, Molybdenum, 8, Chrome, 7, Tungsten, 1, Cobalt, 1, Copper, 1)
                .blastTemp(4625, BlastProperty.GasTier.MID)
                .build();

        HastelloyX = new Material.Builder(3004, gcymId("hastelloy_x"))
                .ingot().fluid()
                .color(0x6BA3E3).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Nickel, 8, Iron, 3, Tungsten, 4, Molybdenum, 2, Chrome, 1, Niobium, 1)
                .blastTemp(4200, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.EV], 900)
                .build();

        Trinaquadalloy = new Material.Builder(3005, gcymId("trinaquadalloy"))
                .ingot().fluid()
                .color(0x281832).iconSet(MaterialIconSet.BRIGHT)
                .flags(GENERATE_PLATE, GENERATE_DOUBLE_PLATE)
                .components(Trinium, 6, Naquadah, 2, Carbon, 1)
                .blastTemp(8747, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.ZPM], 1200)
                .build();

        Zeron100 = new Material.Builder(3006, gcymId("zeron_100"))
                .ingot().fluid()
                .color(0x325A8C).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE)
                .components(Iron, 10, Nickel, 2, Tungsten, 2, Niobium, 1, Cobalt, 1)
                .blastTemp(3693, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 1000)
                .build();

        TitaniumCarbide = new Material.Builder(3007, gcymId("titanium_carbide"))
                .ingot().fluid()
                .color(0xB20B3A).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE)
                .components(Titanium, 1, Carbon, 1)
                .blastTemp(3430, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 1000)
                .build();

        TantalumCarbide = new Material.Builder(3008, gcymId("tantalum_carbide"))
                .ingot().fluid()
                .color(0x56566A).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_DENSE)
                .components(Tantalum, 1, Carbon, 1)
                .blastTemp(4120, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 1200)
                .build();

        MolybdenumDisilicide = new Material.Builder(3009, gcymId("molybdenum_disilicide"))
                .ingot().fluid()
                .color(0x6A5BA3).iconSet(MaterialIconSet.METALLIC)
                .flags(EXT_METAL, GENERATE_DOUBLE_PLATE, GENERATE_LONG_ROD, GENERATE_SPRING, GENERATE_RING)
                .components(Molybdenum, 1, Silicon, 2)
                .blastTemp(2300, BlastProperty.GasTier.MID, GTValues.VA[GTValues.EV], 800)
                .build();
    }
}
