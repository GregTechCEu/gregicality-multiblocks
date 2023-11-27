package gregicality.multiblocks.api.unification.properties;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.Preconditions;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;

import gregicality.multiblocks.api.recipes.alloyblast.AlloyBlastRecipeProducer;

public class AlloyBlastProperty implements IMaterialProperty {

    private int temperature;
    private boolean canGenerateMolten = true;
    private boolean forceGenerateMolten = false;

    private AlloyBlastRecipeProducer recipeProducer = AlloyBlastRecipeProducer.DEFAULT_PRODUCER;

    public AlloyBlastProperty(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public void verifyProperty(@NotNull MaterialProperties materialProperties) {
        materialProperties.ensureSet(PropertyKey.BLAST);
        materialProperties.ensureSet(PropertyKey.FLUID);
        this.temperature = materialProperties.getProperty(PropertyKey.BLAST).getBlastTemperature();
    }

    public void setTemperature(int fluidTemperature) {
        Preconditions.checkArgument(fluidTemperature > 0, "Invalid temperature");
        this.temperature = fluidTemperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setRecipeProducer(@NotNull AlloyBlastRecipeProducer recipeProducer) {
        this.recipeProducer = recipeProducer;
    }

    public @NotNull AlloyBlastRecipeProducer getRecipeProducer() {
        return this.recipeProducer;
    }

    /**
     * @param canGenerateMolten if a molten fluid is allowed to generate
     */
    public void setCanGenerateMolten(boolean canGenerateMolten) {
        this.canGenerateMolten = canGenerateMolten;
    }

    /**
     * @param forceGenerateMolten if a molten fluid should always generate
     */
    public void setForceGenerateMolten(boolean forceGenerateMolten) {
        this.forceGenerateMolten = forceGenerateMolten;
    }

    public boolean shouldGenerateMolten(@NotNull Material material) {
        if (forceGenerateMolten) return true;
        return canGenerateMolten && OrePrefix.ingotHot.doGenerateItem(material);
    }
}
