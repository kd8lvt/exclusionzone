execute as @a[name=kd8lvt,scores={exclz_tmp=0}] run tellraw @s {"text":"Exclusion-Zone Has Been Reloaded!"}
execute if score UpdatedLootDrops exclz_tmp matches 1.. as @a[name=kd8lvt,scores={exclz_tmp=0}] run tellraw @s ["","Updated ",{"score":{"name":"UpdatedLootDrops","objective":"exclz_tmp"}}," loot spots to use new format."]
scoreboard players reset UpdatedLootDrops exclz_tmp
scoreboard players set kd8lvt exclz_tmp 1