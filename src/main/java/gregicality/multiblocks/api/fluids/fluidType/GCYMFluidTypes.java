package gregicality.multiblocks.api.fluids.fluidType;

import gregtech.api.fluids.fluidType.FluidType;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenClass("mods.gcym.material.FluidTypes")
@ZenRegister
public final class GCYMFluidTypes {

    @ZenProperty
    public static final FluidType MOLTEN = new FluidTypeMolten("molten", "molten", null, "gcym.fluid.molten");

    private GCYMFluidTypes() {}
}
