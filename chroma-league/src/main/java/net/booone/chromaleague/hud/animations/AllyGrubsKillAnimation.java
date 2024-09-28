package net.booone.chromaleague.hud.animations;

import net.booone.razersdk.color.StaticColor;

public class AllyGrubsKillAnimation extends StaticPartialBlinkingAnimationG {
    public AllyGrubsKillAnimation(int grubs) {
        super(StaticColor.PURPLE,grubs,"ALLY");
    }
}
