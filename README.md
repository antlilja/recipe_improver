# Recipe Improver Mod
 
### This mods allows you to remove recipes and have smelting recipes output an item count.
### You use this mod through datapacks.
 
## Usage:
To remove vanilla recipes you create a recipe json file in the minecraft namespace. The file has to have the same name as the vanilla recipe file and be a valid recipe file with a result value of "minecraft:air".
To add smelting, blasting or cooking recipes that output multiple items you create a "count" field with the desired value in the recipe file.
 
## Examples:
### Removing iron ingot recipe from furnace:
File: datapackname/data/minecraft/recipes/iron_ingot.json
```json
{
    "type": "minecraft:smelting",
    "ingredient": { "item": "minecraft:air" },
    "result": "minecraft:air"
}
```

### Adding double iron ingot output from blast furnace:
File: datapackname/data/minecraft/recipes/iron_ingot_blasting.json
```json
{
    "type": "minecraft:smelting",
    "ingredient": { "item": "minecraft:iron_ore" },
    "result": "minecraft:iron_ingot",
    "count": 2
}
```
