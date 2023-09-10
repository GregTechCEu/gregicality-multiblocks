package gregicality.multiblocks.api.recipes.alloyblast;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.Preconditions;

import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.builders.BlastRecipeBuilder;
import gregtech.api.unification.material.Material;

public class CustomAlloyBlastRecipeProducer extends AlloyBlastRecipeProducer {

    private final int circuitNum;
    private final int gasCircuitNum;
    private final int outputAmount;

    /**
     * @param circuitNum    the custom circuit number to use
     * @param gasCircuitNum the custom gas circuit number to use
     * @param outputAmount  the custom output amount in quantities of
     *                      {@link gregtech.api.unification.ore.OrePrefix#ingot}
     *                      / {@link gregtech.api.GTValues#M}) to use
     */
    public CustomAlloyBlastRecipeProducer(int circuitNum, int gasCircuitNum, int outputAmount) {
        this.circuitNum = circuitNum;
        this.gasCircuitNum = gasCircuitNum;
        Preconditions.checkArgument(outputAmount != 0, "output amount cannot be zero");
        this.outputAmount = outputAmount;
    }

    @Override
    protected int addInputs(@NotNull Material material, @NotNull RecipeBuilder<BlastRecipeBuilder> builder) {
        int amount = super.addInputs(material, builder); // always must be called
        return this.outputAmount < 0 ? amount : this.outputAmount;
    }

    @Override
    protected int getCircuitNum(int componentAmount) {
        return this.circuitNum < 0 ? super.getCircuitNum(componentAmount) : this.circuitNum;
    }

    @Override
    protected int getGasCircuitNum(int componentAmount) {
        return this.gasCircuitNum < 0 ? super.getGasCircuitNum(componentAmount) : this.gasCircuitNum;
    }
}
