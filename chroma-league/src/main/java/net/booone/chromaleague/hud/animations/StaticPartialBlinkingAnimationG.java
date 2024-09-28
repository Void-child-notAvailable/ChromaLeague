package net.booone.chromaleague.hud.animations;

import net.booone.chromaleague.hud.PredefinedKeySets;
import net.booone.razersdk.animation.AnimatedFrame;
import net.booone.razersdk.animation.SimpleFrame;
import net.booone.razersdk.color.Color;
import net.booone.razersdk.color.StaticColor;
import net.booone.razersdk.sdk.RzKey;

import java.util.Arrays;
import java.util.List;


public abstract class StaticPartialBlinkingAnimationG extends AnimatedFrame {
    private static final int BLINK_TIMES = 2;
    private static final List<List<RzKey>> Keyboard_rows = Arrays.asList(PredefinedKeySets.BLACKWIDOW_FIRST_ROW,PredefinedKeySets.BLACKWIDOW_THIRD_ROW,PredefinedKeySets.BLACKWIDOW_FIFTH_ROW,PredefinedKeySets.BLACKWIDOW_SECOND_ROW,PredefinedKeySets.BLACKWIDOW_FOURTH_ROW,PredefinedKeySets.BLACKWIDOW_SIXTH_ROW);

    protected StaticPartialBlinkingAnimationG(Color color,int grubs,String team) {
        switch(team) {
            case "ALLY"->{
                for (int i = 0; i < BLINK_TIMES; i++) {
                    for (int j = 0; j < grubs; j++) {
                        addAnimationFrame(new SimpleFrame(Keyboard_rows.get(j), color));
                        addAnimationFrame(3, new SimpleFrame(PredefinedKeySets.BLACKWIDOW_FUNCTIONAL.get(j), StaticColor.BLACK));
                    }
                }
            }
            case "ENEMY" -> {
                for (int i = 0; i < BLINK_TIMES; i++) {
                    for (int j = 0; j < grubs; j++) {
                        addAnimationFrame(new SimpleFrame(PredefinedKeySets.BLACKWIDOW_FUNCTIONAL_GRUBS.get(j), color));
                        addAnimationFrame(3, new SimpleFrame(PredefinedKeySets.BLACKWIDOW_FUNCTIONAL.get(j), StaticColor.BLACK));
                    }
                }
            }
        }
    }
}