package gregicality.machines.common.metatileentities.multiblock.standard;

import gregicality.machines.api.metatileentity.GCYMMultiblockAbility;
import gregicality.machines.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.machines.api.render.GCYMultiTextures;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.electric.multiblockpart.MetaTileEntityMultiFluidHatch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static gregtech.api.util.RelativeDirection.*;

public class MetaTileEntityLargeDistillery extends GCYMRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = new MultiblockAbility[]{
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY, GCYMMultiblockAbility.PARALLEL_HATCH
    };

    public MetaTileEntityLargeDistillery(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.DISTILLATION_RECIPES);
        this.recipeMaps = new RecipeMap[]{RecipeMaps.DISTILLATION_RECIPES, RecipeMaps.DISTILLERY_RECIPES};
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityLargeDistillery(this.metaTileEntityId);
    }

    @Override
    protected Function<BlockPos, Integer> multiblockPartSorter() {
        return BlockPos::getY; // todo this needs to be "relative up" with Freedom Wrench
    }

    @Override
    protected BlockPattern createStructurePattern() {
        Predicate<PatternMatchContext> exactlyOneHatch = context -> context.getInt("HatchesAmount") == 1;
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("#YYY#", "YYYYY", "YYYYY", "YYYYY", "#YYY#")
                .aisle("#XSX#", "XAAAX", "XAPAX", "XAAAX", "#XXX#")
                .aisle("##X##", "#XAX#", "XAPAX", "#XAX#", "##X##")
                .aisle("##X##", "#XAX#", "XAPAX", "#XAX#", "##X##").setRepeatable(0, 11)
                .aisle("#####", "#ZZZ#", "#ZCZ#", "#ZZZ#", "#####")
                .setAmountAtLeast('L', 40)
                .where('S', selfPredicate())
                .where('Y', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES))
                        .or(maintenancePredicate(getCasingState())))
                .where('X', dtAbilityPartPredicate().or(maintenancePredicate(getCasingState())))
                .where('Z', statePredicate(getCasingState()))
                .where('P', statePredicate(getCasingState2()))
                .where('C', abilityPartPredicate(MultiblockAbility.MUFFLER_HATCH))
                .where('A', isAirPredicate())
                .where('#', (tile) -> true)
                .where('L', statePredicate(getCasingState()))
                .validateLayer(2, exactlyOneHatch)
                .validateLayer(3, exactlyOneHatch)
                .build();
    }

    private Predicate<BlockWorldState> dtAbilityPartPredicate() {
        return countMatch("HatchesAmount", abilityPartPredicate(MultiblockAbility.EXPORT_FLUIDS)).and(tilePredicate((state, tile) -> {
            if (tile instanceof IMultiblockAbilityPart<?>) {
                MultiblockAbility<?> ability = ((IMultiblockAbilityPart<?>) tile).getAbility();
                return ability == MultiblockAbility.EXPORT_FLUIDS && !(tile instanceof MetaTileEntityMultiFluidHatch);
            }
            return false;
        }));
    }

    private IBlockState getCasingState() {
        return GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.DISTILLERY_CASING);
    }

    private IBlockState getCasingState2() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMultiTextures.DISTILLlERY_CASING;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (isStructureFormed()) {
            FluidStack stackInTank = importFluids.drain(Integer.MAX_VALUE, false);
            if (stackInTank != null && stackInTank.amount > 0) {
                TextComponentTranslation fluidName = new TextComponentTranslation(stackInTank.getFluid().getUnlocalizedName(stackInTank));
                textList.add(new TextComponentTranslation("gregtech.multiblock.distillation_tower.distilling_fluid", fluidName));
            }
        }
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMultiTextures.LARGE_DISTILLERY_OVERLAY;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }

    @Override
    public boolean hasMultipleRecipeMaps() {
        return true;
    }
}
