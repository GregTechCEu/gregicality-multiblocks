package gregicality.multiblocks.api.material;

import java.util.Set;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Sets;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;

import gregicality.multiblocks.Bootstrap;
import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

public class AlloyBlastTest {

    @BeforeAll
    public static void prepare() {
        Bootstrap.perform();
    }

    @Test
    public void testPropertyAddition() {
        Set<Material> blastMats = new ObjectOpenHashSet<>();
        Set<Material> fluidMats = new ObjectOpenHashSet<>();
        Set<Material> alloyMats = new ObjectOpenHashSet<>();
        Set<Material> nonBinaryMats = new ObjectOpenHashSet<>();

        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            if (material.hasProperty(PropertyKey.BLAST)) {
                blastMats.add(material);
            }
            if (material.hasProperty(PropertyKey.FLUID)) {
                fluidMats.add(material);
            }
            if (material.hasProperty(GCYMPropertyKey.ALLOY_BLAST)) {
                alloyMats.add(material);
            }
            if (material.getMaterialComponents().size() > 1) {
                nonBinaryMats.add(material);
            }
        }

        Set<Material> blastFluids = Sets.intersection(blastMats, fluidMats);
        Set<Material> alloyBlastFluids = Sets.intersection(nonBinaryMats, blastFluids);

        // ensure every appropriate material gets the alloy blast prop
        MatcherAssert.assertThat(alloyMats, Matchers.containsInAnyOrder(alloyBlastFluids.toArray(new Material[0])));
        MatcherAssert.assertThat(alloyMats.size(), Matchers.is(alloyBlastFluids.size()));
    }
}
