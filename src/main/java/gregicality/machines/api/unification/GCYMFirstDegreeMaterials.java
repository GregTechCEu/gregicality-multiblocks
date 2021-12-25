package gregicality.machines.api.unification;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static gregicality.machines.api.unification.GCYMMaterials.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;

public class GCYMFirstDegreeMaterials {

    public static void init() {
        TitaniumGold = new Material.Builder(3000, "titanium_gold")
                .ingot()
                .color(0xDEDEFF).iconSet(MaterialIconSet.BRIGHT)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Titanium, 3, Gold, 1)
                .blastTemp(1790)
                .build();

        WatertightSteel = new Material.Builder(3001, "watertight_steel")
                .ingot()
                .color(0x355D6A).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Titanium, 9, Carbon, 9, Potassium, 9, Lithium, 9, Sulfur, 9, Hydrogen, 5)
                .blastTemp(3850)
                .build();

        MaragingSteel300 = new Material.Builder(3002, "maraging_steel300")
                .ingot()
                .color(0x637087).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Steel, 16, Titanium, 1, Aluminium, 1, Nickel, 4, Cobalt, 2)
                .blastTemp(5001)
                .build();

        GalvanizedSteel = new Material.Builder(3003, "galvanized_steel")
                .ingot()
                .color(0xFAF0F0).iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME, NO_WORKING)
                .components(Steel, 9, Zinc, 1)
                .blastTemp(1201)
                .build();

        Kovar = new Material.Builder(3004, "kovar")
                .ingot()
                .color(0xCBC0A6)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Iron, 5, Nickel, 3, Cobalt, 2)
                .blastTemp(4002)
                .build();

        Duraluminium = new Material.Builder(3005, "duralumin")
                .ingot()
                .color(0x01A557)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Aluminium, 3, Copper, 1, Magnesium, 1, Magnesium, 1)
                .blastTemp(4111)
                .build();

        HastelloyC276 = new Material.Builder(3006, "hastelloy_c276")
                .ingot()
                .color(0xCF3939).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Cobalt, 1, Molybdenum, 8, Tungsten, 1, Chrome, 7, Nickel, 32)
                .blastTemp(3601)
                .build();

        EglinSteel = new Material.Builder(3007, "eglin_steel")
                .ingot()
                .color(0x8B4513)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Iron, 9, Nickel, 2, Silicon, 4, Sulfur, 1, Carbon, 1)
                .blastTemp(3200)
                .build();

        HastelloyX = new Material.Builder(3008, "hastelloy_x")
                .ingot()
                .color(0x6BA3E3).iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Steel, 3, Tungsten, 4, Molybdenum, 2, Chrome, 1, Niobium, 1)
                .blastTemp(4200)
                .build();

        VibraniumSteel = new Material.Builder(3009, "vibranium_steel")
                .ingot()
                .color(0x281832).iconSet(MaterialIconSet.BRIGHT)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Vibranium, 1, Steel, 3)
                .blastTemp(8747)
                .build();

        Zamak = new Material.Builder(3010, "zamak")
                .ingot()
                .color(0x2B75DE).iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Aluminium, 4, Copper, 3, Magnesium, 1)
                .blastTemp(2999)
                .build();
    }
}
