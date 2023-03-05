package gregicality.multiblocks.common.block.blocks;

import gregtech.api.block.VariantActiveBlock;
import gregtech.client.utils.BloomEffectUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;

public class BlockUniqueCasing extends VariantActiveBlock<BlockUniqueCasing.UniqueCasingType> {

    public BlockUniqueCasing() {
        super(Material.IRON);
        setTranslationKey("unique_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(UniqueCasingType.CRUSHING_WHEELS));
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    @Override
    public boolean canRenderInLayer(@Nonnull IBlockState state, @Nonnull BlockRenderLayer layer) {
        if (getState(state) == UniqueCasingType.MOLYBDENUM_DISILICIDE_COIL) {
            return layer == BlockRenderLayer.SOLID || layer == BloomEffectUtil.getRealBloomLayer();
        }

        return super.canRenderInLayer(state, layer) || layer == BloomEffectUtil.getRealBloomLayer();
    }

    public enum UniqueCasingType implements IStringSerializable {

        CRUSHING_WHEELS("crushing_wheels"),
        SLICING_BLADES("slicing_blades"),
        ELECTROLYTIC_CELL("electrolytic_cell"),
        HEAT_VENT("heat_vent"),
        MOLYBDENUM_DISILICIDE_COIL("molybdenum_disilicide_coil");

        private final String name;

        UniqueCasingType(String name) {
            this.name = name;
        }

        @Nonnull
        @Override
        public String getName() {
            return this.name;
        }

    }
}
