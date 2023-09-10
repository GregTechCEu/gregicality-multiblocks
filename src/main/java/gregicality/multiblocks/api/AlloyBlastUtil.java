package gregicality.multiblocks.api;

import net.minecraftforge.fluids.Fluid;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;

import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;

public final class AlloyBlastUtil {

    private AlloyBlastUtil() {}

    /**
     * @param material the material to use
     * @return the correct "molten" fluid for a material
     */
    public static @Nullable Fluid getMoltenFluid(@NotNull Material material) {
        if (material.hasProperty(GCYMPropertyKey.ALLOY_BLAST))
            return material.getProperty(GCYMPropertyKey.ALLOY_BLAST).getFluid();
        if (!OrePrefix.ingotHot.doGenerateItem(material) && material.hasProperty(PropertyKey.FLUID))
            return material.getProperty(PropertyKey.FLUID).getFluid();
        return null;
    }
}
