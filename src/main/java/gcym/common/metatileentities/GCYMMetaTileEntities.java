package gcym.common.metatileentities;

import gcym.GregicalityMultiblocks;
import gcym.common.metatileentities.multiblock.*;
import net.minecraft.util.ResourceLocation;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

public class GCYMMetaTileEntities {

    public static MetaTileEntityLargeMacerator LARGE_MACERATOR;
    public static MetaTileEntityLargeArcFurnace LARGE_ARC_FURNACE;
    public static MetaTileEntityLargeAssembler LARGE_ASSEMBLER;
    public static MetaTileEntityLargeAutoclave LARGE_AUTOCLAVE;
    public static MetaTileEntityLargeBender LARGE_BENDER;
    public static MetaTileEntityLargeBrewery LARGE_BREWERY;
    public static MetaTileEntityLargeCentrifuge LARGE_CENTRIFUGE;
    public static MetaTileEntityLargeChemicalBath LARGE_CHEMICAL_BATH;

    public static void init() {
        LARGE_MACERATOR = registerMetaTileEntity(4900, new MetaTileEntityLargeMacerator(gcymId("large_macerator")));
        LARGE_ARC_FURNACE = registerMetaTileEntity(4901, new MetaTileEntityLargeArcFurnace(gcymId("large_arc_furnace")));
        LARGE_ASSEMBLER = registerMetaTileEntity(4902, new MetaTileEntityLargeAssembler(gcymId("large_assembler")));
        // LARGE_ASSEMBLER
        LARGE_AUTOCLAVE = registerMetaTileEntity(4903, new MetaTileEntityLargeAutoclave(gcymId("large_autoclave")));
        LARGE_BENDER = registerMetaTileEntity(4904, new MetaTileEntityLargeBender(gcymId("large_bender")));
        LARGE_BREWERY = registerMetaTileEntity(4905, new MetaTileEntityLargeBrewery(gcymId("large_brewer")));
        LARGE_CENTRIFUGE = registerMetaTileEntity(4906, new MetaTileEntityLargeCentrifuge(gcymId("large_centrifuge")));
        LARGE_CHEMICAL_BATH = registerMetaTileEntity(4907, new MetaTileEntityLargeChemicalBath(gcymId("large_chemical_bath")));
    }

    private static ResourceLocation gcymId(String name) {
        return new ResourceLocation(GregicalityMultiblocks.MODID, name);
    }
}
