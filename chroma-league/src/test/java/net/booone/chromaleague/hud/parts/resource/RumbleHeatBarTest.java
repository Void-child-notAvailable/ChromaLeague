package net.booone.chromaleague.hud.parts.resource;

import net.booone.chromaleague.IntSteps;
import net.booone.chromaleague.hud.AnimationTester;
import net.booone.chromaleague.rest.resource.RumbleHeatBar;
import org.junit.jupiter.api.Test;

class RumbleHeatBarTest extends AbstractResourceTest {

    @Test
    void testRumbleStandardHeat() {
        final IntSteps heatSteps = new IntSteps(0, 100, 5);
        final IntSteps cooldownSteps = new IntSteps(100, 0, 2);

        final RumbleHeatBar rumbleHeatBar = new RumbleHeatBar();
        new AnimationTester()
                .withBeforeIterationAction(i -> {
                    if (i < 20) {
                        mockResource(heatSteps.nextInt(), 100);
                    } else {
                        mockResource(cooldownSteps.nextInt(), 100);
                    }
                })
                .withSleepTime(300)
                .testAnimation(rumbleHeatBar, 67);
    }

    @Test
    void testRumbleOverheatHeat() {
        final IntSteps heatSteps = new IntSteps(0, 100, 5);

        final RumbleHeatBar rumbleHeatBar = new RumbleHeatBar();
        new AnimationTester()
                .withBeforeIterationAction(i -> mockResource(heatSteps.nextInt(), 100))
                .withSleepTime(300)
                .testAnimation(rumbleHeatBar, 40);
    }
}