package net.booone.chromaleague.hud.parts.resource;

import net.booone.chromaleague.IntSteps;
import net.booone.chromaleague.hud.AnimationTester;
import net.booone.chromaleague.rest.resource.RedFuryBar;
import org.junit.jupiter.api.Test;

class RedFuryBarTest extends AbstractResourceTest {
    @Test
    void testRedFuryBar() {
        final IntSteps intSteps = new IntSteps(0, 100, 5);
        final RedFuryBar yoneCloneBar = new RedFuryBar();

        new AnimationTester()
                .withBeforeIterationAction(i -> mockResource(intSteps.nextInt(), 100))
                .testAnimation(yoneCloneBar, 40);
    }
}