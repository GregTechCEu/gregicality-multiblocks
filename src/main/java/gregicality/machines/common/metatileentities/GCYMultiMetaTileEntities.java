package gregicality.machines.common.metatileentities;

import gregicality.machines.GregicalityMultiblocks;
import gregicality.machines.common.metatileentities.multiblock.standard.*;
import gregicality.machines.common.metatileentities.multiblock.unique.MetaTileEntityChemicalPlant;
import net.minecraft.util.ResourceLocation;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

public class GCYMultiMetaTileEntities {

    public static MetaTileEntityLargeMacerator LARGE_MACERATOR;
    public static MetaTileEntityAlloyBlastSmelter ALLOY_BLAST_SMELTER;
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
    public static MetaTileEntityLargeSifter LARGE_SIFTER;
    public static MetaTileEntityLargeWiremill LARGE_WIREMILL;
    public static MetaTileEntityElectricImplosionCompressor ELECTRIC_IMPLOSION_COMPRESSOR;
    public static MetaTileEntityLargeMassFabricator LARGE_MASS_FABRICATOR;
    public static MetaTileEntityLargeReplicator LARGE_REPLICATOR;
    public static MetaTileEntityMegaBlastFurnace MEGA_BLAST_FURNACE;
    public static MetaTileEntityMegaVacuumFreezer MEGA_VACUUM_FREEZER;


    public static void init() {
        LARGE_MACERATOR = registerMetaTileEntity(2000, new MetaTileEntityLargeMacerator(gcyMultiId("large_macerator")));
        ALLOY_BLAST_SMELTER = registerMetaTileEntity(2001, new MetaTileEntityAlloyBlastSmelter(gcyMultiId("alloy_blast_smelter")));
        LARGE_ARC_FURNACE = registerMetaTileEntity(2002, new MetaTileEntityLargeArcFurnace(gcyMultiId("large_arc_furnace")));
        LARGE_ASSEMBLER = registerMetaTileEntity(2003, new MetaTileEntityLargeAssembler(gcyMultiId("large_assembler")));
        LARGE_AUTOCLAVE = registerMetaTileEntity(2004, new MetaTileEntityLargeAutoclave(gcyMultiId("large_autoclave")));
        LARGE_BENDER = registerMetaTileEntity(2005, new MetaTileEntityLargeBender(gcyMultiId("large_bender")));
        LARGE_BREWERY = registerMetaTileEntity(2006, new MetaTileEntityLargeBrewery(gcyMultiId("large_brewer")));
        LARGE_CENTRIFUGE = registerMetaTileEntity(2007, new MetaTileEntityLargeCentrifuge(gcyMultiId("large_centrifuge")));
        LARGE_CHEMICAL_BATH = registerMetaTileEntity(2008, new MetaTileEntityLargeChemicalBath(gcyMultiId("large_chemical_bath")));
        CHEMICAL_PLANT = registerMetaTileEntity(2009, new MetaTileEntityChemicalPlant(gcyMultiId("chemical_plant")));
        LARGE_EXTRACTOR = registerMetaTileEntity(2010, new MetaTileEntityLargeExtractor(gcyMultiId("large_extractor")));
        LARGE_COMPRESSOR = registerMetaTileEntity(2011, new MetaTileEntityLargeCompressor(gcyMultiId("large_compressor")));
        LARGE_CUTTER = registerMetaTileEntity(2012, new MetaTileEntityLargeCutter(gcyMultiId("large_cutter")));
        LARGE_DISTILLERY = registerMetaTileEntity(2013, new MetaTileEntityLargeDistillery(gcyMultiId("large_distillery")));
        LARGE_ELECTROLYZER = registerMetaTileEntity(2014, new MetaTileEntityLargeElectrolyzer(gcyMultiId("large_electrolyzer")));
        LARGE_POLARIZER = registerMetaTileEntity(2015, new MetaTileEntityLargePolarizer(gcyMultiId("large_polarizer")));
        LARGE_EXTRUDER = registerMetaTileEntity(2016, new MetaTileEntityLargeExtruder(gcyMultiId("large_extruder")));
        LARGE_SOLIDIFIER = registerMetaTileEntity(2017, new MetaTileEntityLargeSolidifier(gcyMultiId("large_solidifier")));
        LARGE_MIXER = registerMetaTileEntity(2018, new MetaTileEntityLargeMixer(gcyMultiId("large_mixer")));
        LARGE_PACKAGER = registerMetaTileEntity(2019, new MetaTileEntityLargePackager(gcyMultiId("large_packager")));
        LARGE_ENGRAVER = registerMetaTileEntity(2020, new MetaTileEntityLargeEngraver(gcyMultiId("large_engraver")));
        LARGE_SIFTER = registerMetaTileEntity(2021, new MetaTileEntityLargeSifter(gcyMultiId("large_sifter")));
        LARGE_WIREMILL = registerMetaTileEntity(2022, new MetaTileEntityLargeWiremill(gcyMultiId("large_wiremill")));
        ELECTRIC_IMPLOSION_COMPRESSOR = registerMetaTileEntity(2023, new MetaTileEntityElectricImplosionCompressor(gcyMultiId("electric_implosion_compressor")));
        LARGE_MASS_FABRICATOR = registerMetaTileEntity(2024, new MetaTileEntityLargeMassFabricator(gcyMultiId("large_mass_fabricator")));
        LARGE_REPLICATOR = registerMetaTileEntity(2025, new MetaTileEntityLargeReplicator(gcyMultiId("large_replicator")));
        MEGA_BLAST_FURNACE = registerMetaTileEntity(2026, new MetaTileEntityMegaBlastFurnace(gcyMultiId("mega_blast_furnace")));
        MEGA_VACUUM_FREEZER = registerMetaTileEntity(2027, new MetaTileEntityMegaVacuumFreezer(gcyMultiId("mega_vacuum_freezer")));
    }

    private static ResourceLocation gcyMultiId(String name) {
        return new ResourceLocation(GregicalityMultiblocks.MODID, name);
    }
}
