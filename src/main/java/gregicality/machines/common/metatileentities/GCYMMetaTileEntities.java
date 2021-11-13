package gregicality.machines.common.metatileentities;

import gregicality.machines.GregicalityMachines;
import gregicality.machines.common.metatileentities.multiblock.*;
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
    public static MetaTileEntityLargeExtractor LARGE_EXTRACTOR;

    public static void init() {
        LARGE_MACERATOR = registerMetaTileEntity(2000, new MetaTileEntityLargeMacerator(gcymId("large_macerator")));
        LARGE_ARC_FURNACE = registerMetaTileEntity(2001, new MetaTileEntityLargeArcFurnace(gcymId("large_arc_furnace")));
        LARGE_ASSEMBLER = registerMetaTileEntity(2002, new MetaTileEntityLargeAssembler(gcymId("large_assembler")));
        // LARGE_ALLOY_SMELTER
        LARGE_AUTOCLAVE = registerMetaTileEntity(2003, new MetaTileEntityLargeAutoclave(gcymId("large_autoclave")));
        LARGE_BENDER = registerMetaTileEntity(2004, new MetaTileEntityLargeBender(gcymId("large_bender")));
        LARGE_BREWERY = registerMetaTileEntity(2005, new MetaTileEntityLargeBrewery(gcymId("large_brewer")));
        LARGE_CENTRIFUGE = registerMetaTileEntity(2006, new MetaTileEntityLargeCentrifuge(gcymId("large_centrifuge")));
        LARGE_CHEMICAL_BATH = registerMetaTileEntity(2007, new MetaTileEntityLargeChemicalBath(gcymId("large_chemical_bath")));
        LARGE_EXTRACTOR = registerMetaTileEntity(2008, new MetaTileEntityLargeExtractor(gcymId("large_extractor")));
    }

    private static ResourceLocation gcymId(String name) {
        return new ResourceLocation(GregicalityMachines.MODID, name);
    }
}
