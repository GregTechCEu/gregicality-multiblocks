package gregicality.machines.integration.jei.multiblock.info.standard;

import com.google.common.collect.Lists;
import gregicality.machines.GregicalityMultiblocks;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.machines.common.block.blocks.BlockUniqueCasing;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
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

import java.util.List;

public class LargeArcFurnaceInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GCYMultiMetaTileEntities.LARGE_ARC_FURNACE;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
                .aisle("#XXX#", "#XXX#", "#XXX#", "#XXX#", "#####")
                .aisle("XXXXX", "XC#CX", "XC#CX", "XCXCX", "#C#C#")
                .aisle("XXXXX", "X###X", "X###X", "XXXXX", "#####")
                .aisle("XXXXE", "X#C#X", "X#C#X", "XXCXX", "##C##")
                .aisle("#OML#", "#ISF#", "#XXX#", "#XXX#", "#####")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.BLAST_CASING))
                .where('C', GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.LARGE_ELECTRODE))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.BLAST_CASING)), EnumFacing.WEST)
                .build();
        return Lists.newArrayList(shapeInfo);
    }


    @Override
    public String[] getDescription() {
        return new String[]{I18n.format(String.format("%s.multiblock.large_arc_furnace.description", GregicalityMultiblocks.MODID))};
    }

    @Override
    public float getDefaultZoom() {
        return 0.8f;
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 30).setStyle(new Style().setColor(TextFormatting.AQUA));
        addBlockTooltip(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getItemVariant(BlockLargeMultiblockCasing.CasingType.BLAST_CASING), tooltip);
    }
}
