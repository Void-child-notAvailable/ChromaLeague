package net.booone.chromaleague.hud.animations;

import net.booone.chromaleague.hud.colors.BackgroundBreathingColor;
import net.booone.chromaleague.hud.colors.CLColor;
import net.booone.chromaleague.hud.parts.Background;
import net.booone.chromaleague.hud.parts.health.HealthBar;
import net.booone.chromaleague.rest.resource.ResourceBars;
import net.booone.chromaleague.rest.resource.ShyvanaDragonFuryBar;
import net.booone.chromaleague.rest.resource.YasuoWindBar;
import net.booone.chromaleague.state.GameStateHelper;
import net.booone.chromaleague.state.RunningState;
import net.booone.razersdk.animation.AnimatedFrame;
import net.booone.razersdk.animation.LayeredFrame;
import net.booone.razersdk.animation.SimpleFrame;
import net.booone.razersdk.color.BreathingColor;
import net.booone.razersdk.color.StaticColor;
import net.booone.razersdk.color.TransitionColor;
import net.booone.razersdk.sdk.RzKey;
import net.booone.razersdk.sdk.RzKeySelector;

import java.util.Arrays;
import java.util.List;

import static net.booone.razersdk.sdk.RzKey.*;

public class RespawnAnimation extends AnimatedFrame {
    private static final List<RzKey> FOURTH_ROW = new RzKeySelector().withRowOf(RZKEY_Q).withColumnBetween(RZKEY_Q, RZKEY_U).asList();
    private static final List<RzKey> THIRD_ROW = new RzKeySelector().withRowOf(RZKEY_A).withColumnBetween(RZKEY_A, RZKEY_H).asList();
    private static final List<RzKey> SECOND_ROW = new RzKeySelector().withRowOf(RZKEY_Z).withColumnBetween(RZKEY_Z, RZKEY_B).asList();
    private static final List<RzKey> FIRST_ROW = new RzKeySelector().withRowOf(RZKEY_LALT).withColumnBetween(RZKEY_LALT, RZKEY_SPACE).asList();
    private static final int STEPS = 20;

    public RespawnAnimation() {
        int delayBetweenRows = 5;
        final List<AnimatedFrame> animatedFrames = Arrays.asList(
                createYellowAnimatedFrame(0, FIRST_ROW),
                createYellowAnimatedFrame(delayBetweenRows << 1, SECOND_ROW),
                createYellowAnimatedFrame(delayBetweenRows * 3, THIRD_ROW),
                createYellowAnimatedFrame(delayBetweenRows << 2, FOURTH_ROW),
                createButtonsGlowAnimatedFrame(delayBetweenRows * 5, ResourceBars.getResourceBarKeys(), delayBetweenRows * 7),
                createButtonsGlowAnimatedFrame(delayBetweenRows * 6, HealthBar.getHealthBarKeys(), delayBetweenRows * 7),
                createButtonsTransitionAnimatedFrame(delayBetweenRows * 7, ResourceBars.getResourceBarKeys(), getResourceColor()),
                createButtonsTransitionAnimatedFrame(delayBetweenRows * 7, HealthBar.getHealthBarKeys(), StaticColor.GREEN)
        );
        for (int i = 0; i < delayBetweenRows * 7 + (STEPS << 1); i++) {
            final LayeredFrame layeredFrame = new LayeredFrame();
            animatedFrames.stream().filter(AnimatedFrame::hasFrame).forEach(layeredFrame::addFrame);
            addAnimationFrame(layeredFrame);
        }
    }

    private static StaticColor getResourceColor() {
        StaticColor toResourceColor;
        if (GameStateHelper.getResourcePercentage() == 0) {
            toResourceColor = Background.DEFAULT_BACKGROUND_COLOR;
        } else {
            toResourceColor = getPlayerResourceToTransitionColor();
        }
        return toResourceColor;
    }

    private static AnimatedFrame createYellowAnimatedFrame(int delay, List<RzKey> keys) {
        final AnimatedFrame animatedFrame = new AnimatedFrame();
        animatedFrame.addAnimationFrame(delay, new SimpleFrame());
        BreathingColor yellowBreathingColor = new BackgroundBreathingColor(StaticColor.YELLOW, STEPS, true);
        for (int i = 0; i < STEPS << 1; i++) {
            final LayeredFrame layeredFrame = new LayeredFrame();
            layeredFrame.addFrame(new SimpleFrame(keys, yellowBreathingColor.getColor()));
            if (i < STEPS) {
                layeredFrame.addFrame(new SimpleFrame(HealthBar.getHealthBarKeys(), Background.getCurrentBackgroundColor()));
                layeredFrame.addFrame(new SimpleFrame(ResourceBars.getResourceBarKeys(), Background.getCurrentBackgroundColor()));
            }
            animatedFrame.addAnimationFrame(layeredFrame);
        }
        return animatedFrame;
    }

    private static AnimatedFrame createButtonsGlowAnimatedFrame(int delay, List<RzKey> keys, int waitTill) {
        final AnimatedFrame animatedFrame = new AnimatedFrame();
        animatedFrame.addAnimationFrame(delay, new SimpleFrame());
        BreathingColor yellowBreathingColor = new BackgroundBreathingColor(StaticColor.YELLOW, STEPS, true);
        for (int i = 0; i < STEPS; i++) {
            animatedFrame.addAnimationFrame(new SimpleFrame(keys, yellowBreathingColor.getColor()));
        }
        for (int i = delay + (STEPS << 1); i < waitTill - delay; i++) {
            animatedFrame.addAnimationFrame(new SimpleFrame(keys, StaticColor.YELLOW));
        }
        return animatedFrame;
    }

    private static AnimatedFrame createButtonsTransitionAnimatedFrame(int delay, List<RzKey> keys, StaticColor toColor) {
        final AnimatedFrame animatedFrame = new AnimatedFrame();
        animatedFrame.addAnimationFrame(delay, new SimpleFrame());
        TransitionColor buttonTransitionColor = new TransitionColor(StaticColor.YELLOW, toColor, STEPS);
        for (int i = 0; i < STEPS; i++) {
            animatedFrame.addAnimationFrame(new SimpleFrame(keys, buttonTransitionColor.getColor()));
        }
        return animatedFrame;
    }

    private static StaticColor getPlayerResourceToTransitionColor() {
        String activePlayerChampionName = RunningState.getGameState().getPlayerList().getActivePlayer().championName();
        if (ResourceBars.getEnergyBarChampions().contains(activePlayerChampionName)) {
            return StaticColor.YELLOW;
        }
        if ("Shyvana".equals(activePlayerChampionName)) {
            return ShyvanaDragonFuryBar.DRAGON_FURY_COLOR;
        }
        if ("Yasuo".equals(activePlayerChampionName)) {
            return YasuoWindBar.WIND_SHIELD_COLOR;
        }
        return CLColor.MANA;
    }
}
