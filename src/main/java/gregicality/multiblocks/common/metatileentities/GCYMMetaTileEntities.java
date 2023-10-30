package gregicality.multiblocks.common.metatileentities;

import static gregicality.multiblocks.api.utils.GCYMUtil.gcymId;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;

import gregicality.multiblocks.common.metatileentities.multiblock.generator.MetaTileEntitySteamEngine;
import gregicality.multiblocks.common.metatileentities.multiblock.standard.*;
import gregicality.multiblocks.common.metatileentities.multiblockpart.MetaTileEntityParallelHatch;
import gregicality.multiblocks.common.metatileentities.multiblockpart.MetaTileEntityTieredHatch;

public final class GCYMMetaTileEntities {

    public static MetaTileEntityLargeMacerator LARGE_MACERATOR;
    public static MetaTileEntityAlloyBlastSmelter ALLOY_BLAST_SMELTER;
    public static MetaTileEntityLargeArcFurnace LARGE_ARC_FURNACE;
    public static MetaTileEntityLargeAssembler LARGE_ASSEMBLER;
    public static MetaTileEntityLargeAutoclave LARGE_AUTOCLAVE;
    public static MetaTileEntityLargeBender LARGE_BENDER;
    public static MetaTileEntityLargeBrewery LARGE_BREWERY;
    public static MetaTileEntityLargeCentrifuge LARGE_CENTRIFUGE;
    public static MetaTileEntityLargeChemicalBath LARGE_CHEMICAL_BATH;
    public static MetaTileEntityLargeExtractor LARGE_EXTRACTOR;
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
    public static MetaTileEntitySteamEngine STEAM_ENGINE;
    public static MetaTileEntityLargeCircuitAssembler LARGE_CIRCUIT_ASSEMBLER;

    public static MetaTileEntityParallelHatch[] PARALLEL_HATCH = new MetaTileEntityParallelHatch[4];
    public static MetaTileEntityTieredHatch[] TIERED_HATCH = new MetaTileEntityTieredHatch[GTValues.V.length];

    private GCYMMetaTileEntities() {}

    public static void init() {
        // Multiblocks
        LARGE_MACERATOR = registerMetaTileEntity(2000, new MetaTileEntityLargeMacerator(gcymId("large_macerator")));
        ALLOY_BLAST_SMELTER = registerMetaTileEntity(2001,
                new MetaTileEntityAlloyBlastSmelter(gcymId("alloy_blast_smelter")));
        LARGE_ARC_FURNACE = registerMetaTileEntity(2002,
                new MetaTileEntityLargeArcFurnace(gcymId("large_arc_furnace")));
        LARGE_ASSEMBLER = registerMetaTileEntity(2003, new MetaTileEntityLargeAssembler(gcymId("large_assembler")));
        LARGE_AUTOCLAVE = registerMetaTileEntity(2004, new MetaTileEntityLargeAutoclave(gcymId("large_autoclave")));
        LARGE_BENDER = registerMetaTileEntity(2005, new MetaTileEntityLargeBender(gcymId("large_bender")));
        LARGE_BREWERY = registerMetaTileEntity(2006, new MetaTileEntityLargeBrewery(gcymId("large_brewer")));
        LARGE_CENTRIFUGE = registerMetaTileEntity(2007, new MetaTileEntityLargeCentrifuge(gcymId("large_centrifuge")));
        LARGE_CHEMICAL_BATH = registerMetaTileEntity(2008,
                new MetaTileEntityLargeChemicalBath(gcymId("large_chemical_bath")));
        // FREE ID: 2009
        LARGE_EXTRACTOR = registerMetaTileEntity(2010, new MetaTileEntityLargeExtractor(gcymId("large_extractor")));
        LARGE_CUTTER = registerMetaTileEntity(2011, new MetaTileEntityLargeCutter(gcymId("large_cutter")));
        LARGE_DISTILLERY = registerMetaTileEntity(2012, new MetaTileEntityLargeDistillery(gcymId("large_distillery")));
        LARGE_ELECTROLYZER = registerMetaTileEntity(2013,
                new MetaTileEntityLargeElectrolyzer(gcymId("large_electrolyzer")));
        LARGE_POLARIZER = registerMetaTileEntity(2014, new MetaTileEntityLargePolarizer(gcymId("large_polarizer")));
        LARGE_EXTRUDER = registerMetaTileEntity(2015, new MetaTileEntityLargeExtruder(gcymId("large_extruder")));
        LARGE_SOLIDIFIER = registerMetaTileEntity(2016, new MetaTileEntityLargeSolidifier(gcymId("large_solidifier")));
        LARGE_MIXER = registerMetaTileEntity(2017, new MetaTileEntityLargeMixer(gcymId("large_mixer")));
        LARGE_PACKAGER = registerMetaTileEntity(2018, new MetaTileEntityLargePackager(gcymId("large_packager")));
        LARGE_ENGRAVER = registerMetaTileEntity(2019, new MetaTileEntityLargeEngraver(gcymId("large_engraver")));
        LARGE_SIFTER = registerMetaTileEntity(2020, new MetaTileEntityLargeSifter(gcymId("large_sifter")));
        LARGE_WIREMILL = registerMetaTileEntity(2021, new MetaTileEntityLargeWiremill(gcymId("large_wiremill")));
        ELECTRIC_IMPLOSION_COMPRESSOR = registerMetaTileEntity(2022,
                new MetaTileEntityElectricImplosionCompressor(gcymId("electric_implosion_compressor")));
        // LARGE_MASS_FABRICATOR = registerMetaTileEntity(2023, new
        // MetaTileEntityLargeMassFabricator(gcymId("large_mass_fabricator"))); todo replication system
        // LARGE_REPLICATOR = registerMetaTileEntity(2024, new
        // MetaTileEntityLargeReplicator(gcymId("large_replicator")));
        MEGA_BLAST_FURNACE = registerMetaTileEntity(2025,
                new MetaTileEntityMegaBlastFurnace(gcymId("mega_blast_furnace")));
        MEGA_VACUUM_FREEZER = registerMetaTileEntity(2026,
                new MetaTileEntityMegaVacuumFreezer(gcymId("mega_vacuum_freezer")));
        STEAM_ENGINE = registerMetaTileEntity(2027, new MetaTileEntitySteamEngine(gcymId("steam_engine")));
        LARGE_CIRCUIT_ASSEMBLER = registerMetaTileEntity(2028,
                new MetaTileEntityLargeCircuitAssembler(gcymId("large_circuit_assembler")));

        // Hatches
        for (int i = 0; i < PARALLEL_HATCH.length; i++) {
            int tier = GTValues.IV + i;
            PARALLEL_HATCH[i] = registerMetaTileEntity(2050 + i, new MetaTileEntityParallelHatch(
                    gcymId(String.format("parallel_hatch.%s", GTValues.VN[tier])), tier));
        }
        for (int i = 0; i < TIERED_HATCH.length; i++) {
            if (!GregTechAPI.isHighTier() && i > GTValues.UHV)
                break;

            TIERED_HATCH[i] = registerMetaTileEntity(2054 + i,
                    new MetaTileEntityTieredHatch(gcymId(String.format("tiered_hatch.%s", GTValues.VN[i])), i));
        }
    }
}
