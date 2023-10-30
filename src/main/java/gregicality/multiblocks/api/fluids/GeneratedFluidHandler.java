package gregicality.multiblocks.api.fluids;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.material.properties.FluidProperty;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.PropertyKey;

import gregicality.multiblocks.api.unification.GCYMMaterialFlags;
import gregicality.multiblocks.api.unification.properties.AlloyBlastProperty;
import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;

/**
 * Handles generation of fluids based on material properties
 */
@ApiStatus.Internal
public final class GeneratedFluidHandler {

    private GeneratedFluidHandler() {}

    public static void init() {
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            createMoltenFluid(material);
        }
    }

    public static void createMoltenFluid(@NotNull Material material) {
        // ignore materials set not to be alloy blast handled
        if (material.hasFlag(GCYMMaterialFlags.DISABLE_ALLOY_PROPERTY)) return;

        // ignore materials which are not alloys
        if (material.getMaterialComponents().size() <= 1) return;

        BlastProperty blastProperty = material.getProperty(PropertyKey.BLAST);
        if (blastProperty == null) return;

        AlloyBlastProperty alloyBlastProperty = material.getProperty(GCYMPropertyKey.ALLOY_BLAST);
        if (alloyBlastProperty == null) return;

        FluidProperty fluidProperty = material.getProperty(PropertyKey.FLUID);
        if (fluidProperty == null) return;

        if (alloyBlastProperty.shouldGenerateMolten(material)) {
            fluidProperty.getStorage().enqueueRegistration(GCYMFluidStorageKeys.MOLTEN, new FluidBuilder()
                    .temperature(alloyBlastProperty.getTemperature()));
        } else {
            // not hot enough to produce molten fluid, so produce regular fluid
            fluidProperty.getStorage().store(GCYMFluidStorageKeys.MOLTEN, material.getFluid(FluidStorageKeys.LIQUID));
        }
    }
}
