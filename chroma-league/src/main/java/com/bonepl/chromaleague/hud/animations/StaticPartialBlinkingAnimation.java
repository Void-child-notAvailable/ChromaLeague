package com.bonepl.chromaleague.hud.animations;

import com.bonepl.razersdk.animation.Color;
import com.bonepl.razersdk.animation.AnimatedFrame;
import com.bonepl.razersdk.animation.Frame;
import com.bonepl.razersdk.sdk.RzKey;

import java.util.List;

public class StaticPartialBlinkingAnimation extends AnimatedFrame {

    public StaticPartialBlinkingAnimation(List<RzKey> rzKeys, int times, Color color) {
        for (int i = 0; i < times; i++) {
            withAnimationFrame(new Frame(rzKeys, color));
            withAnimationFrame(3, new Frame(rzKeys, Color.BLACK));
        }
    }
}
