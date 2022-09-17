package gregicality.multiblocks.loaders;

import com.google.common.collect.ImmutableSet;
import gregicality.multiblocks.api.metatileentity.ITieredHatch;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.common.items.MetaItems;

public class TileEntityMechanics {

    public static void register() {
        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_ASSEMBLER, ImmutableSet.of(MetaItems.CARBON_FIBER_PLATE.getStackForm(), MetaItems.NAQUADAH_BOULE.getStackForm()), GTValues.UV);

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_ASSEMBLER, ImmutableSet.of(MetaItems.NANO_BOOTS.getStackForm(), MetaItems.ADVANCED_SMD_TRANSISTOR.getStackForm()), GTValues.ZPM);
    }
}
