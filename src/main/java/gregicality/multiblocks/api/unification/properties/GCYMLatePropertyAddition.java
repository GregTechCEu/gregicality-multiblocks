package gregicality.multiblocks.api.unification.properties;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.stack.MaterialStack;

import gregicality.multiblocks.api.unification.GCYMMaterialFlags;

public final class GCYMLatePropertyAddition {

    private GCYMLatePropertyAddition() {}

    public static void init() {
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            if (!material.hasFlag(GCYMMaterialFlags.DISABLE_ALLOY_PROPERTY)) {
                addAlloyBlastProperty(material);
            }
        }
    }

    public static void addAlloyBlastProperty(@NotNull Material material) {
        final List<MaterialStack> components = material.getMaterialComponents();
        // ignore materials which are not alloys
        if (components.size() < 2) return;

        BlastProperty blastProperty = material.getProperty(PropertyKey.BLAST);
        if (blastProperty == null) return;

        if (!material.hasProperty(PropertyKey.FLUID)) return;

        // if there are more than 2 fluid-only components in the material, do not generate a hot fluid
        if (components.stream().filter(GCYMLatePropertyAddition::isMaterialStackFluidOnly).limit(3).count() > 2) {
            return;
        }

        material.setProperty(GCYMPropertyKey.ALLOY_BLAST, new AlloyBlastProperty(material.getBlastTemperature()));
    }

    private static boolean isMaterialStackFluidOnly(@NotNull MaterialStack ms) {
        return !ms.material.hasProperty(PropertyKey.DUST) && ms.material.hasProperty(PropertyKey.FLUID);
    }
}
