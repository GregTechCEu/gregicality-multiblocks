package gregicality.multiblocks.api;

import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class AlloyBlastUtil {

    private AlloyBlastUtil() {/**/}

    /**
     * @param material the material to use
     * @return the correct "molten" fluid for a material
     */
    @Nullable
    public static Fluid getMoltenFluid(@Nonnull Material material) {
        if (material.hasProperty(GCYMPropertyKey.ALLOY_BLAST))
            return material.getProperty(GCYMPropertyKey.ALLOY_BLAST).getFluid();
        if (!OrePrefix.ingotHot.doGenerateItem(material) && material.hasProperty(PropertyKey.FLUID))
            return material.getProperty(PropertyKey.FLUID).getFluid();
        return null;
    }
}
