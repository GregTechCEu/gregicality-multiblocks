package gregicality.machines.common.block;

import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing2;
import gregicality.machines.common.block.blocks.BlockUniqueCasing;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GCYMultiMetaBlocks {

    private GCYMultiMetaBlocks() {

    }

    public static BlockUniqueCasing UNIQUE_CASING;
    public static BlockLargeMultiblockCasing LARGE_MULTIBLOCK_CASING;
    public static BlockLargeMultiblockCasing2 LARGE_MULTIBLOCK_CASING_2;

    public static void init() {
        UNIQUE_CASING = new BlockUniqueCasing();
        UNIQUE_CASING.setRegistryName("unique_casing");
        LARGE_MULTIBLOCK_CASING = new BlockLargeMultiblockCasing();
        LARGE_MULTIBLOCK_CASING.setRegistryName("large_multiblock_casing");
        LARGE_MULTIBLOCK_CASING_2 = new BlockLargeMultiblockCasing2();
        LARGE_MULTIBLOCK_CASING_2.setRegistryName("large_multiblock_casing_2");
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {
        registerItemModel(UNIQUE_CASING);
        registerItemModel(LARGE_MULTIBLOCK_CASING);
        registerItemModel(LARGE_MULTIBLOCK_CASING_2);
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            //noinspection ConstantConditions
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            statePropertiesToString(state.getProperties())));
        }
    }

    private static String statePropertiesToString(Map<IProperty<?>, Comparable<?>> properties) {
        StringBuilder stringbuilder = new StringBuilder();

        List<Map.Entry<IProperty<?>, Comparable<?>>> entries = properties.entrySet().stream()
                .sorted(Comparator.comparing(c -> c.getKey().getName()))
                .collect(Collectors.toList());

        for (Map.Entry<IProperty<?>, Comparable<?>> entry : entries) {
            if (stringbuilder.length() != 0) {
                stringbuilder.append(",");
            }

            IProperty<?> property = entry.getKey();
            stringbuilder.append(property.getName());
            stringbuilder.append("=");
            stringbuilder.append(getPropertyName(property, entry.getValue()));
        }

        if (stringbuilder.length() == 0) {
            stringbuilder.append("normal");
        }

        return stringbuilder.toString();
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> String getPropertyName(IProperty<T> property, Comparable<?> value) {
        return property.getName((T) value);
    }
}
