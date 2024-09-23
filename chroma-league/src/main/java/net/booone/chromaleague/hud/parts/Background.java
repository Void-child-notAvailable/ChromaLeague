package net.booone.chromaleague.hud.parts;

import net.booone.chromaleague.hud.animations.*;
import net.booone.chromaleague.hud.colors.BackgroundBreathingColor;
import net.booone.chromaleague.rest.gamestats.MapTerrain;
import net.booone.chromaleague.state.GameStateHelper;
import net.booone.chromaleague.state.RunningState;
import net.booone.razersdk.animation.AnimatedFrame;
import net.booone.razersdk.animation.LayeredFrame;
import net.booone.razersdk.animation.SimpleFrame;
import net.booone.razersdk.color.BreathingColor;
import net.booone.razersdk.color.Color;
import net.booone.razersdk.color.StaticColor;

public class Background extends LayeredFrame {
    public static final StaticColor DEFAULT_BACKGROUND_COLOR = new StaticColor(5, 5, 5);
    private static final AnimatedFrame BARON_BUFF_BACKGROUND_ANIMATION = new BaronBuffBackgroundAnimation();
    private static final AnimatedFrame BARON_BUFF_BACKGROUND_ANIMATION2 = new BaronBuffBackgroundAnimation2();
    private static final AnimatedFrame BARON_BUFF_BACKGROUND_ANIMATION3 = new BaronBuffBackgroundAnimation3();
    private static final AnimatedFrame BARON_BUFF_BACKGROUND_ANIMATION4 = new BaronBuffBackgroundAnimation4();
    private static final AnimatedFrame BARON_BUFF_BACKGROUND_ANIMATION5 = new BaronBuffBackgroundAnimation5();
    private static final BreathingColor DEAD_BACKGROUND = new BackgroundBreathingColor(new StaticColor(60, 40, 40));

    public Background() {
        addFrame(new SimpleFrame(getCurrentBackgroundColor()));
        if (GameStateHelper.hasBaronBuff()) {
            double elapsedTime= (RunningState.getGameState().getGameStats().gameTime()-RunningState.getGameState().getEventData().getBaronBuffEnd())*-1;
            if (elapsedTime<36){
                addFrame(BARON_BUFF_BACKGROUND_ANIMATION5);
            }else if (elapsedTime<72){
                addFrame(BARON_BUFF_BACKGROUND_ANIMATION4);
            }else if (elapsedTime<108){
                addFrame(BARON_BUFF_BACKGROUND_ANIMATION3);
            }else if (elapsedTime<144){
                addFrame(BARON_BUFF_BACKGROUND_ANIMATION2);
            }else{
                addFrame(BARON_BUFF_BACKGROUND_ANIMATION);
            }
        }
    }

    public static Color getCurrentBackgroundColor() {
        if (GameStateHelper.isActivePlayerAlive()) {
            if (RunningState.getGameState().getEventData().didRiftAnimationPlay()) {
                return MapTerrain.fromApiType(RunningState.getGameState().getGameStats().mapTerrain()).getBackgroundColor();
            } else {
                return DEFAULT_BACKGROUND_COLOR;
            }
        }
        return DEAD_BACKGROUND.getColor();
    }
}
