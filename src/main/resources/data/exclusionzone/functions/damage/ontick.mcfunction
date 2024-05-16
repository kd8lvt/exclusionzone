execute as @e if entity @s[scores={exclz_ondeath=1..,toxic_buildup=1..}] run scoreboard players reset @s toxic_buildup
execute as @e if entity @s[scores={exclz_ondeath=1..}] run scoreboard players reset @s exclz_ondeath 

execute as @a at @s if biome ~ ~ ~ exclusionzone:exclusion_zone unless score @s toxic_buildup > hundo toxic_buildup unless entity @s[nbt={Inventory:[{Slot:100b},{Slot:101b},{Slot:102b},{Slot:103b}]}] run scoreboard players add @s toxic_buildup 1
execute as @a at @s if biome ~ ~ ~ exclusionzone:exclusion_zone if entity @s[nbt={Inventory:[{Slot:100b},{Slot:101b},{Slot:102b},{Slot:103b}]}] if score @s toxic_buildup > zero toxic_buildup run scoreboard players remove @s toxic_buildup 1
execute as @a at @s unless biome ~ ~ ~ exclusionzone:exclusion_zone if score @s toxic_buildup > zero toxic_buildup run scoreboard players remove @s toxic_buildup 1
execute as @a if score @s toxic_buildup > zero toxic_buildup unless score @s toxic_buildup > ten toxic_buildup run title @s actionbar ["",{"text":"\u26a0 ","color":"yellow"},{"text":"░░░░░░░░░░","color":"#00A776"},{"text":" \u26a0","color":"yellow"}]
execute as @a if score @s toxic_buildup > ten toxic_buildup unless score @s toxic_buildup > twenty toxic_buildup run title @s actionbar ["",{"text":"\u26a0 ","color":"yellow"},{"text":"█░░░░░░░░░","color":"#00A776"},{"text":" \u26a0","color":"yellow"}]
execute as @a if score @s toxic_buildup > twenty toxic_buildup unless score @s toxic_buildup > thirty toxic_buildup run title @s actionbar ["",{"text":"\u26a0 ","color":"yellow"},{"text":"██░░░░░░░░","color":"#00A776"},{"text":" \u26a0","color":"yellow"}]
execute as @a if score @s toxic_buildup > thirty toxic_buildup unless score @s toxic_buildup > fourty toxic_buildup run title @s actionbar ["",{"text":"\u26a0 ","color":"yellow"},{"text":"███░░░░░░░","color":"#00A776"},{"text":" \u26a0","color":"yellow"}]
execute as @a if score @s toxic_buildup > fourty toxic_buildup unless score @s toxic_buildup > fifty toxic_buildup run title @s actionbar ["",{"text":"\u26a0 ","color":"yellow"},{"text":"████░░░░░░","color":"#00A776"},{"text":" \u26a0","color":"yellow"}]
execute as @a if score @s toxic_buildup > fifty toxic_buildup unless score @s toxic_buildup > sixty toxic_buildup run title @s actionbar ["",{"text":"\u26a0 ","color":"yellow"},{"text":"█████░░░░░","color":"#00A776"},{"text":" \u26a0","color":"yellow"}]
execute as @a if score @s toxic_buildup > sixty toxic_buildup unless score @s toxic_buildup > seventy toxic_buildup run title @s actionbar ["",{"text":"\u26a0 ","color":"yellow"},{"text":"██████░░░░","color":"#00A776"},{"text":" \u26a0","color":"yellow"}]
execute as @a if score @s toxic_buildup > seventy toxic_buildup unless score @s toxic_buildup > eighty toxic_buildup run title @s actionbar ["",{"text":"\u26a0 ","color":"yellow"},{"text":"███████░░░","color":"#00A776"},{"text":" \u26a0","color":"yellow"}]
execute as @a if score @s toxic_buildup > eighty toxic_buildup unless score @s toxic_buildup > ninety toxic_buildup run title @s actionbar ["",{"text":"\u26a0 ","color":"yellow"},{"text":"████████░░","color":"#00A776"},{"text":" \u26a0","color":"yellow"}]
execute as @a if score @s toxic_buildup > ninety toxic_buildup unless score @s toxic_buildup > hundo toxic_buildup run title @s actionbar ["",{"text":"\u26a0 ","color":"yellow"},{"text":"█████████░","color":"#00A776"},{"text":" \u26a0","color":"yellow"}]
execute as @a if score @s toxic_buildup > hundo toxic_buildup run title @s actionbar ["",{"text":"\u26a0 ","color":"yellow"},{"text":"██████████","color":"#00A776"},{"text":" \u26a0","color":"yellow"}]

# >5 seconds of Toxic Buildup
execute as @a if score @s toxic_buildup > ten toxic_buildup run effect give @s weakness 10 0 true

# >10 seconds 
execute as @a if score @s toxic_buildup > twenty toxic_buildup run effect give @s hunger 10 0 true

# >15 seconds
execute as @a if score @s toxic_buildup > thirty toxic_buildup run effect give @s slowness 10 0 true
execute as @a if score @s toxic_buildup > thirty toxic_buildup run effect give @s weakness 10 1 true
execute as @a if score @s toxic_buildup > thirty toxic_buildup run effect give @s hunger 10 1 true

# >20 seconds
execute as @a if score @s toxic_buildup > fourty toxic_buildup run effect give @s blindness 10 0 true

# >25 seconds
execute as @a if score @s toxic_buildup > fifty toxic_buildup run effect give @s nausea 10 0 true

# >30 seconds
execute as @a if score @s toxic_buildup > sixty toxic_buildup run effect give @s mining_fatigue 10 0 true

# >35 seconds
execute as @a if score @s toxic_buildup > seventy toxic_buildup run effect give @s weakness 10 2 true
execute as @a if score @s toxic_buildup > seventy toxic_buildup run effect give @s hunger 10 2 true
execute as @a if score @s toxic_buildup > seventy toxic_buildup run effect give @s slowness 10 1 true

# >40 seconds
execute as @a if score @s toxic_buildup > seventy toxic_buildup run effect give @s mining_fatigue 10 2 true

# >45 seconds
execute as @a if score @s toxic_buildup > eighty toxic_buildup run effect give @s mining_fatigue 10 4 true

# >50 seconds
execute as @a if score @s toxic_buildup > ninety toxic_buildup run effect give @s poison 10 0 true

# >55 seconds
execute as @a if score @s toxic_buildup > hundo toxic_buildup run effect give @s wither 10 10 true