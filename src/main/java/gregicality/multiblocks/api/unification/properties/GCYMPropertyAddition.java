package gregicality.multiblocks.api.unification.properties;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;

public class GCYMPropertyAddition {

    public static void init() {
        for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
            addAlloyBlastProperty(material);
        }
    }

    public static void addAlloyBlastProperty(Material material) {
        // ignore materials which are not alloys
        if (material.getMaterialComponents().size() <= 1) return;

        if (material.hasFlag(MaterialFlags.DISABLE_DECOMPOSITION)) return;

        BlastProperty blastProperty = material.getProperty(PropertyKey.BLAST);
        if (blastProperty == null) return;

        if (!OrePrefix.ingotHot.doGenerateItem(material) && !material.hasProperty(PropertyKey.FLUID))
            return;

        if (material.getMaterialComponents().stream().anyMatch(materialStack -> !materialStack.material.hasProperty(PropertyKey.DUST)))
            return;

        material.setProperty(GCYMPropertyKey.ALLOY_BLAST, new AlloyBlastProperty(material.getBlastTemperature()));
    }
}
