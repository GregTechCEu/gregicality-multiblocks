# Changelog

## v1.0.0

* Initial Commit

## v1.0.1

* Renames Mod Name to no longer use a colon
* Fixes error message in chat due to lack of ZenExpansion for CraftTweaker RecipeMaps
* Removes unneeded JEI Plugin
* Updates Base Directory Name (internal)

## v1.0.2

* Fixes Alloy Blast Smelter recipe being IV tier instead of EV

## v1.0.3

* Adds compat for GTCEu v2.0.5-beta
* Changes multiblock recipes to use Platinum cables instead of Tungsten
* Fixes voltage of some mixer recipes

## v1.0.4

* Adds compat for GTCEu v2.1.0-beta

## v1.0.5

* Fixes large wiremill using a sifter instead of wiremill in its recipe
* Reduces cost of parallel hatches

## v1.1.0

* Adds the Large Circuit Assembler, making circuit assembling its own machine and prevents progression skips
* Changes the Chemical Plant to only show the Chemical Plant RecipeMap when a mod uses it, allows parallel recipes for
  Large Chemical Reactor mode
* Adds the paused overlay textures to all multiblocks

## v1.1.1

* Fix inconsistent High Temperature Casing crafting and assembler recipes
* Remove the requirement for fluids in order to generate Alloy Blast Recipes
* Fix alloy blast recipe autogeneration with CraftTweaker materials
* New structure for the Rotary Hearth Furnace
* Reworked Parallel for the Rotary Hearth Furnace and Bulk Blast Chiller
* Deprecated Chemical Plant multiblock for implementation in another mod. It will be REMOVED in the following update.
* Fixed Graphene Foils missing for the Molybdenum Disilicide Coil recipe
* Updated Molten fluid generation system to use the new GTCEu MetaFluids API

## v1.1.2

* Fix Large Distillery Structure: adds tiered casing support and blocks multi-fluid hatches (#5)
* Internal: Moved to non-deprecated mod loaded check (#9)
* Adds compat for GTCEu v2.2.0-beta
* Removed muffler hatch from the Large Laser Engraver

## v1.1.3

* Prevent parallel hatches from being shared between multiblocks
* Removed duplicated slicing blade casing recipe (#11)
* Added compat for GTCEu v2.2.2-beta

## v1.1.4

* Removed Deprecated Chemical Plant multiblock
* Changed casings to use wrenches instead of pickaxes for harvesting
* Changed Rotary Hearth Furnace and Bulk Blast Chiller to use parallel hatches instead of their base rate
* Added distinct buses to the Large Chemical Bath
* Fixed the Heat Vent block not being emissive
* Allowed the Alloy Blast Smelter to display heating coils added by CraftTweaker in the structure preview
* Fixed Rotary Hearth Furnace casing predicate and increased minimum casings to 360, effective hatch maximum is now ~30
* Added compat for GTCEu 2.4.0
* Fixed recipe registration issues with Tiered Hatches
* Added interaction with GCYS for recipes

## v1.1.5

* Fix GCYS integration
* Fix server crash with parallel hatches
* Added compat for GTCEu 2.4.3

## v1.1.6

* Reduced Maraging Steel Temperature to 4000K for coil bonuses
* Added liquid helium requirement parity with hot ingots for cooling molten metals at 5000K+
* Added compat with GTFO. Slicer can be used with the Large cutter, and Cuisine Assembler with the Large Assembler.
* Added compat for GTCEu 2.5.0

## v1.2.0

* Added additional Alloy Blast Smelter recipes for materials containing fluids and alloy smelter materials
* Added new material flag "disable_alloy_blast" to disable alloy blast recipes
* Fix Fractionating Distillery requiring maintenance hatch with config disabled
* Added ru_ru translation (#20)
* Updated zn_cn translation (#10)
* Added compat for GTCEu 2.5.3 (#37)

## v1.2.1

* Added compat for GTCEu 2.6.0

## v1.2.2

* Updated Alloy Blast Smelter recipe generation
* Fixed Niobium Nitride easily conflicting with Niobium Titanium in ABS
* Fixed HV Superconductor giving wrong output amount in ABS
* Added distinct buses to the Large Wiremill (#44)

## v1.2.3

* Fixed Alloy Blast recipes not generating for materials missing hot ingots
* No longer using deprecated features as of GTCEu v2.6.2

## v1.2.4

* Added Distinct Buses to the Large Circuit Assembler (#53)
* Added Compat for GTCEu 2.7.0 (#52)

## v1.2.5

* Fixed missing recipes due to materials missing double plates (#56)
