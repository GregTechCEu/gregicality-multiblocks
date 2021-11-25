package gregicality.machines.jei.multiblock.info.standard;

import gregicality.machines.GregicalityMultiblocks;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.*;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class MegaBlastFurnaceInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GCYMultiMetaTileEntities.MEGA_BLAST_FURNACE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapeInfos = new ArrayList<>();

        for (BlockWireCoil.CoilType coilType : BlockWireCoil.CoilType.values()){
            shapeInfos.add(MultiblockShapeInfo.builder()
                    .aisle("#####XXXXX#####", "#####XXGXX#####", "#####XXXXX#####", "#####XXpXX#####", "#######p#######", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("#F#XXXBBBXXX#F#", "#F#XXX#R#XXX#F#", "#F#XXX#p#XXX#F#", "#F#XpXXpXXpX#F#", "#F##ppppppp##F#", "#FFFFFFFFFFFFF#", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("##XXBBXBXBBXX##", "##GXR#X#X#RXG##", "##XXp#X#X#pXX##", "##pXpXXXXXpXp##", "##ppp#####ppp##", "#FFFFFFFFFFFFF#", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("#XXXBBXBXBBXXX#", "#XXX##X#X##XXX#", "#XXX##X#X##XXX#", "#XXXXXXXXXXXXX#", "##p#########p##", "#FF#########FF#", "######TTT######", "#######T#######", "###############", "###############", "###############", "###############", "###############", "###############", "######TTT######")
                    .aisle("#XBBXBBXBBXBBX#", "#GR#X##X##X#RG#", "#Xp#X##X##X#pX#", "#ppXXXXXXXXXpp#", "#pp####T####pp#", "#FF####T####FF#", "####TTTTTTT####", "#######f#######", "#######f#######", "#######f#######", "#######f#######", "#######f#######", "#######f#######", "#######f#######", "####TTTTTTT####")
                    .aisle("XXBBBXBXBXBBBXX", "XX###X#X#X###XX", "XX###X#X#X###XX", "XXXXXXXXXXXXXXX", "#pp##T###T###p#", "#FF##T###T##FF#", "####TTTTTTT####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "####TTTTTTT####")
                    .aisle("XXXXBBXXXBBXXBX", "XXXX##XXX##XXXX", "XXXX##XXX##XXXX", "XXXXXXXXXXXXXXX", "##pp#########p#", "#FF#########FF#", "###TTTTTTTTT###", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "###TTTTTTTTT###")
                    .aisle("GGGpXXXPXXXBBBX", "GGGpXXXPXXX##RG", "GGGpXXXPXXX##pX", "GGGpXXXPXXXXXpp", "##ppT##P##T##pp", "#FF#T##P##T#FF#", "###TTTTTTTTT###", "###TfC###CfT###", "####fC###Cf####", "####fC###Cf####", "####fC###Cf####", "####fC###Cf####", "####fC###Cf####", "####fC###Cf####", "###TTTTmTTTT###")
                    .aisle("XXXXBBXXXBBXXBX", "XXXX##XXX##XXXX", "XXXX##XXX##XXXX", "XXXXXXXXXXXXXXX", "#############p#", "#FF#########FF#", "###TTTTTTTTT###", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "###TTTTpTTTT###")
                    .aisle("XXBBBXBXBXBBBXX", "XX###X#X#X###XX", "XX###X#X#X###XX", "XXXXXXXXXXXXXXX", "#pp##T###T###p#", "#FF##T###T##FF#", "####TTTTTTT####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "####TTTpTTT####")
                    .aisle("#XBBXBBXBBXBBX#", "#GR#X##X##X#RG#", "#Xp#X##X##X#pX#", "#ppXXXXXXXXXpp#", "#pp####T####pp#", "#FF####T####FF#", "####TTTTTTT####", "#######p#######", "#######p#######", "#######p#######", "#######p#######", "#######p#######", "#######p#######", "#######p#######", "####TTTpTTT####")
                    .aisle("#XXXBBXBXBBXXX#", "#XXX##X#X##XXX#", "#XXX##X#X##XXX#", "#XXXXXXXXXXXXX#", "##p#########p##", "#FF#########FF#", "######TTT######", "#######p#######", "###############", "###############", "###############", "###############", "###############", "###############", "######TTT######")
                    .aisle("##XXBBXBXBBXX##", "##GXR#X#X#RXG##", "##XXp#X#X#pXX##", "##pXpXXXXXpXp##", "##ppp#####ppp##", "#FFFFFXXXFFFFF#", "###############", "#######p#######", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("#F#XXXBBBXXX#F#", "#F#XXX#R#XXX#F#", "#F#XXX#p#XXX#F#", "#F#XpXXpXXpX#F#", "#F##ppppppp##F#", "#FFFFFXpXFFFFF#", "#######p#######", "#######p#######", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("#####OMSEA#####", "#####IXGXJ#####", "#####XXXXX#####", "#####XXpXX#####", "#######p#######", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .where('S', getController(), EnumFacing.WEST)
                    .where('f', MetaBlocks.FRAMES.get(Materials.BlueSteel).getDefaultState())
                    .where('F', MetaBlocks.FRAMES.get(Materials.NaquadahAlloy).getDefaultState())
                    .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF))
                    .where('T', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST))
                    .where('C', MetaBlocks.WIRE_COIL.getState(coilType))
                    .where('P', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF))
                    .where('p', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                    .where('G', MetaBlocks.TRANSPARENT_CASING.getState(BlockTransparentCasing.CasingType.TEMPERED_GLASS))
                    .where('g', MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))
                    .where('m', MetaTileEntities.MUFFLER_HATCH[GTValues.HV - 1], EnumFacing.UP)
                    .where('R', MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.TUNGSTENSTEEL_FIREBOX))
                    .where('B', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS))
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                    .where('J', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                    .where('A', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.UV], EnumFacing.WEST)
                    .where('M', maintenanceIfEnabled(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF)), EnumFacing.WEST)
                    .build());
        }

        for (BlockWireCoil2.CoilType2 coilType : BlockWireCoil2.CoilType2.values()){
            shapeInfos.add(MultiblockShapeInfo.builder()
                    .aisle("#####XXXXX#####", "#####XXGXX#####", "#####XXXXX#####", "#####XXpXX#####", "#######p#######", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("#F#XXXBBBXXX#F#", "#F#XXX#R#XXX#F#", "#F#XXX#p#XXX#F#", "#F#XpXXpXXpX#F#", "#F##ppppppp##F#", "#FFFFFFFFFFFFF#", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("##XXBBXBXBBXX##", "##GXR#X#X#RXG##", "##XXp#X#X#pXX##", "##pXpXXXXXpXp##", "##ppp#####ppp##", "#FFFFFFFFFFFFF#", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("#XXXBBXBXBBXXX#", "#XXX##X#X##XXX#", "#XXX##X#X##XXX#", "#XXXXXXXXXXXXX#", "##p#########p##", "#FF#########FF#", "######TTT######", "#######T#######", "###############", "###############", "###############", "###############", "###############", "###############", "######TTT######")
                    .aisle("#XBBXBBXBBXBBX#", "#GR#X##X##X#RG#", "#Xp#X##X##X#pX#", "#ppXXXXXXXXXpp#", "#pp####T####pp#", "#FF####T####FF#", "####TTTTTTT####", "#######f#######", "#######f#######", "#######f#######", "#######f#######", "#######f#######", "#######f#######", "#######f#######", "####TTTTTTT####")
                    .aisle("XXBBBXBXBXBBBXX", "XX###X#X#X###XX", "XX###X#X#X###XX", "XXXXXXXXXXXXXXX", "#pp##T###T###p#", "#FF##T###T##FF#", "####TTTTTTT####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "####TTTTTTT####")
                    .aisle("XXXXBBXXXBBXXBX", "XXXX##XXX##XXXX", "XXXX##XXX##XXXX", "XXXXXXXXXXXXXXX", "##pp#########p#", "#FF#########FF#", "###TTTTTTTTT###", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "###TTTTTTTTT###")
                    .aisle("GGGpXXXPXXXBBBX", "GGGpXXXPXXX##RG", "GGGpXXXPXXX##pX", "GGGpXXXPXXXXXpp", "##ppT##P##T##pp", "#FF#T##P##T#FF#", "###TTTTTTTTT###", "###TfC###CfT###", "####fC###Cf####", "####fC###Cf####", "####fC###Cf####", "####fC###Cf####", "####fC###Cf####", "####fC###Cf####", "###TTTTmTTTT###")
                    .aisle("XXXXBBXXXBBXXBX", "XXXX##XXX##XXXX", "XXXX##XXX##XXXX", "XXXXXXXXXXXXXXX", "#############p#", "#FF#########FF#", "###TTTTTTTTT###", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "#####C###C#####", "###TTTTpTTTT###")
                    .aisle("XXBBBXBXBXBBBXX", "XX###X#X#X###XX", "XX###X#X#X###XX", "XXXXXXXXXXXXXXX", "#pp##T###T###p#", "#FF##T###T##FF#", "####TTTTTTT####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "#####CCCCC#####", "####TTTpTTT####")
                    .aisle("#XBBXBBXBBXBBX#", "#GR#X##X##X#RG#", "#Xp#X##X##X#pX#", "#ppXXXXXXXXXpp#", "#pp####T####pp#", "#FF####T####FF#", "####TTTTTTT####", "#######p#######", "#######p#######", "#######p#######", "#######p#######", "#######p#######", "#######p#######", "#######p#######", "####TTTpTTT####")
                    .aisle("#XXXBBXBXBBXXX#", "#XXX##X#X##XXX#", "#XXX##X#X##XXX#", "#XXXXXXXXXXXXX#", "##p#########p##", "#FF#########FF#", "######TTT######", "#######p#######", "###############", "###############", "###############", "###############", "###############", "###############", "######TTT######")
                    .aisle("##XXBBXBXBBXX##", "##GXR#X#X#RXG##", "##XXp#X#X#pXX##", "##pXpXXXXXpXp##", "##ppp#####ppp##", "#FFFFFXXXFFFFF#", "###############", "#######p#######", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("#F#XXXBBBXXX#F#", "#F#XXX#R#XXX#F#", "#F#XXX#p#XXX#F#", "#F#XpXXpXXpX#F#", "#F##ppppppp##F#", "#FFFFFXpXFFFFF#", "#######p#######", "#######p#######", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .aisle("#####OMSEA#####", "#####IXGXJ#####", "#####XXXXX#####", "#####XXpXX#####", "#######p#######", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                    .where('S', getController(), EnumFacing.WEST)
                    .where('f', MetaBlocks.FRAMES.get(Materials.BlueSteel).getDefaultState())
                    .where('F', MetaBlocks.FRAMES.get(Materials.NaquadahAlloy).getDefaultState())
                    .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF))
                    .where('T', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST))
                    .where('C', MetaBlocks.WIRE_COIL2.getState(coilType))
                    .where('P', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF))
                    .where('p', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                    .where('G', MetaBlocks.TRANSPARENT_CASING.getState(BlockTransparentCasing.CasingType.TEMPERED_GLASS))
                    .where('g', MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))
                    .where('m', MetaTileEntities.MUFFLER_HATCH[GTValues.HV - 1], EnumFacing.UP)
                    .where('R', MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.TUNGSTENSTEEL_FIREBOX))
                    .where('B', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS))
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                    .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                    .where('J', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                    .where('A', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.UV], EnumFacing.WEST)
                    .where('M', maintenanceIfEnabled(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF)), EnumFacing.WEST)
                    .build());
        }
        return shapeInfos;
    }


    @Override
    public String[] getDescription() {
        return new String[]{I18n.format(String.format("%s.multiblock.alloy_blast_smelter.description", GregicalityMultiblocks.MODID))};
    }

    @Override
    public float getDefaultZoom() {
        return 0.8f;
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 420).setStyle(new Style().setColor(TextFormatting.AQUA));
        addBlockTooltip(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF), tooltip);

        ITextComponent mufflerTooltip = new TextComponentTranslation("gregtech.multiblock.preview.only_location", I18n.format("gregtech.multiblock.preview.location.t_c")).setStyle(new Style().setColor(TextFormatting.DARK_RED));
        addBlockTooltip(MetaTileEntities.MUFFLER_HATCH[GTValues.HV - 1].getStackForm(), mufflerTooltip);
    }
}
