package gregicality.machines.common.block.blocks;

import gregtech.common.blocks.VariantActiveBlock;
import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
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
        setHarvestLevel("pickaxe", 2);
        setDefaultState(getState(UniqueCasingType.CRUSHING_WHEELS));
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum UniqueCasingType implements IStringSerializable {

        CRUSHING_WHEELS("crushing_wheels"),
        LARGE_ELECTRODE("large_electrode"),
        SLICING_BLADES("slicing_blades"),
        ELECTROLYTIC_CELL("electrolytic_cell");

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
