package gregicality.multiblocks.api.fluids.fluidType;

import crafttweaker.annotations.ZenRegister;
import gregtech.api.fluids.fluidType.FluidType;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenClass("mods.gcym.material.FluidTypes")
@ZenRegister
public class GCYMFluidTypes {

    @ZenProperty
    public static final FluidType MOLTEN = new FluidTypeMolten("molten", "molten", null, "gcym.fluid.molten");
}
