package gregicality.machines.common.metatileentities.multiblock.standard;

import gregicality.machines.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.machines.api.render.GCYMultiTextures;
import gregtech.api.GTValues;
import gregtech.api.capability.IHeatingCoil;
import gregtech.api.capability.impl.HeatingCoilRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.recipeproperties.TemperatureProperty;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityMegaBlastFurnace extends GCYMRecipeMapMultiblockController implements IHeatingCoil {

    private int blastFurnaceTemperature;

    public MetaTileEntityMegaBlastFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.BLAST_RECIPES);
        this.recipeMapWorkable = new HeatingCoilRecipeLogic(this);
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
        if (type instanceof BlockWireCoil.CoilType)
            this.blastFurnaceTemperature = ((BlockWireCoil.CoilType) type).getCoilTemperature();
        else if(type instanceof BlockWireCoil2.CoilType2)
            this.blastFurnaceTemperature = ((BlockWireCoil2.CoilType2) type).getCoilTemperature();
        else
            this.blastFurnaceTemperature = BlockWireCoil.CoilType.CUPRONICKEL.getCoilTemperature();

        this.blastFurnaceTemperature += 100 * Math.max(0, GTUtility.getTierByVoltage(getEnergyContainer().getInputVoltage()) - GTValues.MV);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.blastFurnaceTemperature = 0;
    }

    @Override
    public boolean checkRecipe(@Nonnull Recipe recipe, boolean consumeIfSuccess) {
        return this.blastFurnaceTemperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0);
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
                .where('S', selfPredicate())
                .where('f', states(getFrameState()))
                .where('F', states(getFrameState2()))
                .where('X', states(getCasingState()).setMinGlobalLimited(420).or(autoAbilities()))
                .where('T', states(getCasingState2()))
                .where('C', heatingCoils())
                .where('P', states(getFrameWorkState()))
                .where('p', states(getCasingState3()))
                .where('G', states(getCasingState4()))
                .where('m', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('R', states(getCasingState5()))
                .where('B', states(getCasingState6()))
                .where('#', any())
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
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS);
    }

    private IBlockState getCasingState5() {
        return MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.TUNGSTENSTEEL_FIREBOX);
    }

    private IBlockState getCasingState6() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.1"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.2"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.3"));
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

    @Override
    public int getCurrentTemperature() {
        return this.blastFurnaceTemperature;
    }
}
