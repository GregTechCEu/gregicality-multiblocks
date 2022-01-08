package gregicality.multiblocks.api.unification.properties;

import com.google.common.base.Preconditions;
import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;
import gregtech.api.unification.material.properties.PropertyKey;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nonnull;

public class AlloyBlastProperty implements IMaterialProperty<AlloyBlastProperty> {

    /**
     * Internal material fluid field
     */
    private Fluid fluid;

    private int temperature;

    public AlloyBlastProperty(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public void verifyProperty(MaterialProperties materialProperties) {
        materialProperties.ensureSet(PropertyKey.BLAST);
        materialProperties.ensureSet(PropertyKey.FLUID);
        this.temperature = materialProperties.getProperty(PropertyKey.BLAST).getBlastTemperature();
    }

    public Fluid getFluid() {
        return fluid;
    }

    /**
     * internal usage only
     */
    public void setFluid(@Nonnull Fluid materialFluid) {
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
}
