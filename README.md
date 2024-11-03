# Exclusion Zone
An incredibly cursed pile of spaghetti code that builds into a more-or-less functional mod for Fabric 1.21
[![build](https://github.com/kd8lvt/exclusionzone/actions/workflows/build.yml/badge.svg)](https://github.com/kd8lvt/exclusionzone/actions/workflows/build.yml)

### What does it do?
- Adds a few utilities that I miss from modded when playing Vanilla
- A dangerous new custom biome
- Adds some structures, which create a small pocket of said custom biome in the area
- Some unique loot items with no purpose besides being display pieces
- A suspiciously malicious-looking item :eyes:
- Potion of Milk

## Mechanics
The Exclusion Zone is intended to be used on a server standalone, to add a little flavor to Vanilla Minecraft, without
completely overtaking the game.

It works fabulously when paired with TheEpicBlock's [PolyMc](https://github.com/TheEpicBlock/PolyMc) in order to allow
fully Vanilla clients to join a lightly modded server.

The features EZ adds are intended to be unobtrusive by default,
while still giving server owners the power to convert vast swaths of terrain into magically irradiated land. 

#### The "Exclusion Zone" Biome
When entering an Exclusion Zone (usually denoted by teal-ish particles in the air, as well as an overgrowth of moss and warped blocks),
the player should be wary of their Toxic Buildup. This is displayed as a bar near the bottom of their screen that steadily fills over time.

As Toxic Buildup increases, the player will begin to suffer more and more debilitating potion effects, eventually culminating in death.

One can prevent Toxic Buildup simply by filling their armor slots.

#### Archaeology
Exclusion Zone piggybacks off the vanilla Archaeology system, implementing suspicious variants of moss and all the colors of concrete powder.
Keep in mind, however, that only Suspicious Moss is currently available to survival players under normal circumstances. 

When digging about in Exclusion Zones, one is likely to find various artifacts and relics of an ancient civilization. Make up your own lore,
it's more fun that way :)


## Building the Mod
Building Exclusion Zone is as easy as cloning the repository and running the `genSources` gradle task, followed by the `build` task.
