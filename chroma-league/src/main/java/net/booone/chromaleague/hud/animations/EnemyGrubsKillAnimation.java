package net.booone.chromaleague.hud.animations;

import net.booone.razersdk.color.StaticColor;

public class EnemyGrubsKillAnimation extends StaticPartialBlinkingAnimationG {
    public EnemyGrubsKillAnimation(int grubs) {
        super(StaticColor.PURPLE,grubs, "ENEMY");
    }
}
