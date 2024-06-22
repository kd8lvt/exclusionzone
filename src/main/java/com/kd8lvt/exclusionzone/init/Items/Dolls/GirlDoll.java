package com.kd8lvt.exclusionzone.init.Items.Dolls;

import com.kd8lvt.exclusionzone.init.Items.Doll;
import com.kd8lvt.exclusionzone.init.ModSounds;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

public class GirlDoll extends Doll {
    public GirlDoll() {
        super(ModSounds.DOLL_SQUEAK,ModSounds.DOLL_CHICKEN,0.2);
        this.tt.addAll(Text.of("Research Notes").getWithStyle(Style.EMPTY.withColor(TextColor.parse("gray").getOrThrow())));
        this.tt.addAll(Text.of("A discarded children's toy.").getWithStyle(Style.EMPTY.withColor(TextColor.parse("gray").getOrThrow())));
        this.tt.addAll(Text.of("Judging from the colors, this one appears to be a girl.").getWithStyle(Style.EMPTY.withColor(TextColor.parse("gray").getOrThrow())));

    }
}
