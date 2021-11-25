package gregicality.machines.common.metatileentities.multiblock.standard;

import gregicality.machines.render.GCYMultiTextures;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.recipeproperties.BlastTemperatureProperty;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Predicate;

public class MetaTileEntityMegaBlastFurnace extends RecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = new MultiblockAbility[]{
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY
    };

    private int blastFurnaceTemperature;

    public MetaTileEntityMegaBlastFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.BLAST_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityMegaBlastFurnace(this.metaTileEntityId);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.blast_furnace.max_temperature", blastFurnaceTemperature)
                    .setStyle(new Style().setColor(TextFormatting.RED)));
        }
        super.addDisplayText(textList);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object type = context.get("CoilType");
        if (type instanceof BlockWireCoil.CoilType) {
            this.blastFurnaceTemperature = ((BlockWireCoil.CoilType) type).getCoilTemperature();
        } else if(type instanceof BlockWireCoil2.CoilType2) {
            this.blastFurnaceTemperature = ((BlockWireCoil2.CoilType2) type).getCoilTemperature();
        } else {
            this.blastFurnaceTemperature = BlockWireCoil.CoilType.CUPRONICKEL.getCoilTemperature();
        }

        if (ConfigHolder.U.GT5u.ebfTemperatureBonuses)
            this.blastFurnaceTemperature += 100 * Math.max(0, GTUtility.getTierByVoltage(getEnergyContainer().getInputVoltage()) - GTValues.MV);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.blastFurnaceTemperature = 0;
    }

    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        int recipeRequiredTemp = recipe.getProperty(BlastTemperatureProperty.getInstance(), 0);
        return this.blastFurnaceTemperature >= recipeRequiredTemp;
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
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
                .aisle("#####XXSXX#####", "#####XXGXX#####", "#####XXXXX#####", "#####XXpXX#####", "#######p#######", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############")
                .setAmountAtLeast('L', 420)
                .where('S', selfPredicate())
                .where('f', statePredicate(getFrameState()))
                .where('F', statePredicate(getFrameState2()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)
                                .or(maintenancePredicate(getCasingState()))))
                .where('T', statePredicate(getCasingState2()))
                .where('C', heatingCoilPredicate())
                .where('P', statePredicate(getFrameWorkState()))
                .where('p', statePredicate(getCasingState3()))
                .where('G', statePredicate(getCasingState4()))
                .where('m', abilityPartPredicate(MultiblockAbility.MUFFLER_HATCH))
                .where('R', statePredicate(getCasingState5()))
                .where('B', statePredicate(getCasingState6()))
                .where('#', (tile) -> true)
                .where('L', statePredicate(getCasingState()))
                .build();
    }

    private IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF);
    }

    public static IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(Materials.BlueSteel).getDefaultState();
    }

    public static IBlockState getFrameState2() {
        return MetaBlocks.FRAMES.get(Materials.NaquadahAlloy).getDefaultState();
    }

    private IBlockState getCasingState2() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST);
    }

    private IBlockState getFrameWorkState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF);
    }

    private IBlockState getCasingState3() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE);
    }

    private IBlockState getCasingState4() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockTransparentCasing.CasingType.TEMPERED_GLASS);
    }

    private IBlockState getCasingState5() {
        return MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.TUNGSTENSTEEL_FIREBOX);
    }

    private IBlockState getCasingState6() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS);
    }

    public static Predicate<BlockWorldState> heatingCoilPredicate() {
        return blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if ((blockState.getBlock() instanceof BlockWireCoil)) {
                BlockWireCoil blockWireCoil = (BlockWireCoil) blockState.getBlock();
                BlockWireCoil.CoilType coilType = blockWireCoil.getState(blockState);
                Object currentCoilType = blockWorldState.getMatchContext().getOrPut("CoilType", coilType);
                return currentCoilType.toString().equals(coilType.getName());
            } else if ((blockState.getBlock() instanceof BlockWireCoil2)) {
                BlockWireCoil2 blockWireCoil = (BlockWireCoil2) blockState.getBlock();
                BlockWireCoil2.CoilType2 coilType = blockWireCoil.getState(blockState);
                Object currentCoilType = blockWorldState.getMatchContext().getOrPut("CoilType", coilType);
                return currentCoilType.toString().equals(coilType.getName());
            }
            return false;
        };
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.HEAT_PROOF_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMultiTextures.MEGA_BLAST_FURNACE_OVERLAY;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }
}
