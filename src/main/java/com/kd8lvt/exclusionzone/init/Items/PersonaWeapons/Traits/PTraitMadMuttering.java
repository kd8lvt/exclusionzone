package com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.Traits;

import com.kd8lvt.exclusionzone.ExclusionZone;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class PTraitMadMuttering extends PTrait {
    ArrayList<String> mutterings = new ArrayList<>();
    public PTraitMadMuttering() {
        tt.add(Text.of("Mad Muttering:"));
        tt.add(Text.of("This weapon mutters madness into the mind of the wielder occasionally."));
        tt.add(Text.of("It seems to think it is a wizard."));
        mutterings.addAll(List.of(
            "By the seven moons of Zorgon, I declare today's breakfast shall be served with a side of flying pigs!",
            "Beware the tickling curse, for it shall turn your enemies into giggling jellybeans!",
            "Ah, the mystical art of sock puppetry is the key to unlocking the secrets of the universe!",
            "I once turned my hat into a dancing squirrel, but then it stole my wand and joined the circus.",
            "Toothpaste! The secret ingredient for potent potions and minty-fresh breath!",
            "Listen closely, for the whispers of cheese hold the secrets to eternal happiness!",
            "Behold! The mystical dance of the enchanted rubber chicken!",
            "I once tried to summon a storm, but accidentally summoned a choir of singing frogs instead.",
            "The mystical art of interpretive dance is the true language of dragons!",
            "In the land of dreams, the clouds taste like cotton candy and the rivers flow with chocolate milk.",
            "Do not anger the garden gnomes, for they have a penchant for stealing socks and reciting bad poetry!",
            "To find the lost treasure, one must follow the trail of glitter left by disco-dancing unicorns!",
            "Beware the curse of the itchy sweater! It will plague you for seven years and a day!",
            "I once brewed a potion that made everyone speak in rhyming couplets for a fortnight!",
            "The key to unlocking the secrets of the universe lies within the belly button lint of a cosmic sloth!",
            "Never trust a talking teapot, for they have a tendency to spill the beans!",
            "To summon the mighty thunderstorm, one must perform the sacred dance of the electric eel!",
            "Behold, the mystical powers of the enchanted kazoo! It can summon cheese sandwiches on demand!",
            "I once turned my apprentice into a turnip, but then he developed a taste for salsa dancing!",
            "In the realm of absurdity, logic is but a fleeting butterfly chased by a herd of philosophical goats!",
            "To vanquish your foes, simply tickle them into submission with a feather duster!",
            "Ah, the mystical art of juggling flaming marshmallows! It's not as easy as it sounds, you know.",
            "Beware the wrath of the enchanted rubber duckies, for they have a penchant for mischief and bubble baths!",
            "I once brewed a potion that turned all the town's chickens into opera singers. The eggs were never the same again.",
            "The secret to immortality lies within the belly button lint of a cosmic sloth, but good luck getting it out!",
            "To navigate the treacherous waters of the Enchanted Lagoon, one must ride a rubber duck and wear a tutu!",
            "I once tried to brew a potion of invisibility, but all I got was a batch of invisible socks!",
            "Beware the curse of the perpetual hiccup! It can only be cured by a spoonful of laughter and a pinch of moon dust.",
            "Ah, the mystical powers of the enchanted feather duster! It can clean your house and tell bad jokes simultaneously!",
            "To summon the spirit of the great cheese dragon, one must recite the sacred chant while wearing polka-dot pajamas backwards!")
        );
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof PlayerEntity)) super.inventoryTick(stack, world, entity, slot, selected);
        Random random = world.getRandom();
        if (random.nextBetween(1, ExclusionZone.muttering_debug ?100:6000) == 1) entity.sendMessage(Text.of("<"+stack.getName().getString()+"> "+mutterings.get(random.nextBetween(0, mutterings.size()-1))));
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
