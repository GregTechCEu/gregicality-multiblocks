package gregicality.multiblocks.api.unification.properties;

import net.minecraftforge.fluids.Fluid;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import com.google.common.base.Preconditions;

import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;
import gregtech.api.unification.material.properties.PropertyKey;

import gregicality.multiblocks.api.recipes.alloyblast.AlloyBlastRecipeProducer;

public class AlloyBlastProperty implements IMaterialProperty {

    /**
     * Internal material fluid field
     */
    private Fluid fluid;
    private int temperature;

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

    public @NotNull Fluid getFluid() {
        return fluid;
    }

    /**
     * internal usage only
     */
    @ApiStatus.Internal
    public void setFluid(@NotNull Fluid materialFluid) {
        Preconditions.checkNotNull(materialFluid);
        this.fluid = materialFluid;
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
}
