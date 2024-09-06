execute as @e[tag=exclusionzone_loot,tag=!exclusionzone_villagegravel_loot] run tag @s add exclusionzone_villagegravel_loot
execute as @e[tag=exclusionzone_loot,tag=exclusionzone_villagegravel_loot] run scoreboard players add UpdatedLootDrops exclz_tmp 1
execute as @e[tag=exclusionzone_loot,tag=exclusionzone_villagegravel_loot] run tag @s remove exclusionzone_loot
execute as @e[type=marker,tag=exclusionzone_villagegravel_loot] at @s run setblock ~ ~-1 ~ minecraft:suspicious_gravel{LootTable:"exclusionzone:archaeology/village_gravel",exclusionzone:{loot:true}}
execute as @e[type=marker,tag=exclusionzone_villagemoss_loot] at @s run setblock ~ ~-1 ~ exclusionzone:suspicious_moss{LootTable:"exclusionzone:archaeology/village_moss",exclusionzone:{loot:true}}
execute as @e[type=marker,tag=exclusionzone_wildmoss_loot] at @s run setblock ~ ~-1 ~ exclusionzone:suspicious_moss{LootTable:"exclusionzone:archaeology/wild_moss",exclusionzone:{loot:true}}
tellraw @a {"text":"You hear a quiet rumbling from the direction of the Exclusion Zone... Perhaps the shaking has stirred up some new artifacts to collect.","italic":true,"color":"gray"}