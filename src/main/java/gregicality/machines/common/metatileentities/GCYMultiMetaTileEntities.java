package gregicality.machines.common.metatileentities;

import gregicality.machines.GregicalityMultiblocks;
import gregicality.machines.common.metatileentities.multiblock.standard.*;
import gregicality.machines.common.metatileentities.multiblock.unique.MetaTileEntityChemicalPlant;
import net.minecraft.util.ResourceLocation;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

public class GCYMultiMetaTileEntities {

    public static MetaTileEntityLargeMacerator LARGE_MACERATOR;
    public static MetaTileEntityLargeArcFurnace LARGE_ARC_FURNACE;
    public static MetaTileEntityLargeAssembler LARGE_ASSEMBLER;
    public static MetaTileEntityLargeAutoclave LARGE_AUTOCLAVE;
    public static MetaTileEntityLargeBender LARGE_BENDER;
    public static MetaTileEntityLargeBrewery LARGE_BREWERY;
    public static MetaTileEntityLargeCentrifuge LARGE_CENTRIFUGE;
    public static MetaTileEntityLargeChemicalBath LARGE_CHEMICAL_BATH;
    public static MetaTileEntityChemicalPlant CHEMICAL_PLANT;
    public static MetaTileEntityLargeExtractor LARGE_EXTRACTOR;
    public static MetaTileEntityLargeCompressor LARGE_COMPRESSOR;
    public static MetaTileEntityLargeCutter LARGE_CUTTER;
    public static MetaTileEntityLargeDistillery LARGE_DISTILLERY;
    public static MetaTileEntityLargeElectrolyzer LARGE_ELECTROLYZER;
    public static MetaTileEntityLargePolarizer LARGE_POLARIZER;
    public static MetaTileEntityLargeExtruder LARGE_EXTRUDER;
    public static MetaTileEntityLargeSolidifier LARGE_SOLIDIFIER;
    public static MetaTileEntityLargeMixer LARGE_MIXER;
    public static MetaTileEntityLargePackager LARGE_PACKAGER;
    public static MetaTileEntityLargeEngraver LARGE_ENGRAVER;

    public static void init() {
        LARGE_MACERATOR = registerMetaTileEntity(2000, new MetaTileEntityLargeMacerator(gcyMultiId("large_macerator")));
        LARGE_ARC_FURNACE = registerMetaTileEntity(2001, new MetaTileEntityLargeArcFurnace(gcyMultiId("large_arc_furnace")));
        LARGE_ASSEMBLER = registerMetaTileEntity(2002, new MetaTileEntityLargeAssembler(gcyMultiId("large_assembler")));
        LARGE_AUTOCLAVE = registerMetaTileEntity(2003, new MetaTileEntityLargeAutoclave(gcyMultiId("large_autoclave")));
        LARGE_BENDER = registerMetaTileEntity(2004, new MetaTileEntityLargeBender(gcyMultiId("large_bender")));
        LARGE_BREWERY = registerMetaTileEntity(2005, new MetaTileEntityLargeBrewery(gcyMultiId("large_brewer")));
        LARGE_CENTRIFUGE = registerMetaTileEntity(2006, new MetaTileEntityLargeCentrifuge(gcyMultiId("large_centrifuge")));
        LARGE_CHEMICAL_BATH = registerMetaTileEntity(2007, new MetaTileEntityLargeChemicalBath(gcyMultiId("large_chemical_bath")));
        CHEMICAL_PLANT = registerMetaTileEntity(2008, new MetaTileEntityChemicalPlant(gcyMultiId("chemical_plant")));
        LARGE_EXTRACTOR = registerMetaTileEntity(2009, new MetaTileEntityLargeExtractor(gcyMultiId("large_extractor")));
        LARGE_COMPRESSOR = registerMetaTileEntity(2010, new MetaTileEntityLargeCompressor(gcyMultiId("large_compressor")));
        LARGE_CUTTER = registerMetaTileEntity(2011, new MetaTileEntityLargeCutter(gcyMultiId("large_cutter")));
        LARGE_DISTILLERY = registerMetaTileEntity(2012, new MetaTileEntityLargeDistillery(gcyMultiId("large_distillery")));
        LARGE_ELECTROLYZER = registerMetaTileEntity(2013, new MetaTileEntityLargeElectrolyzer(gcyMultiId("large_electrolyzer")));
        LARGE_POLARIZER = registerMetaTileEntity(2014, new MetaTileEntityLargePolarizer(gcyMultiId("large_polarizer")));
        LARGE_EXTRUDER = registerMetaTileEntity(2015, new MetaTileEntityLargeExtruder(gcyMultiId("large_extruder")));
        LARGE_SOLIDIFIER = registerMetaTileEntity(2016, new MetaTileEntityLargeSolidifier(gcyMultiId("large_solidifier")));
        LARGE_MIXER = registerMetaTileEntity(2017, new MetaTileEntityLargeMixer(gcyMultiId("large_mixer")));
        LARGE_PACKAGER = registerMetaTileEntity(2018, new MetaTileEntityLargePackager(gcyMultiId("large_packager")));
        LARGE_ENGRAVER = registerMetaTileEntity(2019, new MetaTileEntityLargeEngraver(gcyMultiId("large_engraver")));
    }

    private static ResourceLocation gcyMultiId(String name) {
        return new ResourceLocation(GregicalityMultiblocks.MODID, name);
    }
}
