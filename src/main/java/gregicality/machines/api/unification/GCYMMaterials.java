package gregicality.machines.api.unification;

import gregtech.api.unification.material.Material;

public class GCYMMaterials {

    /*
     * First Degree Materials
     */
    public static Material TitaniumGold;
    public static Material WatertightSteel;
    public static Material MaragingSteel300;
    public static Material GalvanizedSteel;
    public static Material Kovar;
    public static Material Duraluminium;
    public static Material HastelloyC276;
    public static Material EglinSteel;
    public static Material HastelloyX;
    public static Material VibraniumSteel;
    public static Material Zamak;

    /*
     * Second Degree Materials
     */
    public static Material HSLASteel;
    public static Material TitaniumTungstenCarbide;
    public static Material Incoloy813;

    /*
     * High Degree Materials
     */
    public static Material NikolineAlloy;


    /**
     * Gregicality: 3000-19999
     */
    public static void init() {
        // First Degree 3000-3019
        GCYMFirstDegreeMaterials.init();

        // Second Degree 3020-3039
        GCYMSecondDegreeMaterials.init();

        // High Degree 3040-3059
        GCYMHighDegreeMaterials.init();
    }
}
