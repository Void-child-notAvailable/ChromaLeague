package net.booone.chromaleague.hud.parts.resource;

import net.booone.chromaleague.IntSteps;
import net.booone.chromaleague.hud.AnimationTester;
import net.booone.chromaleague.rest.resource.YasuoWindBar;
import org.junit.jupiter.api.Test;

class YasuoWindBarTest extends AbstractResourceTest {
    @Test
    void testYasuoWindBar() {
        final IntSteps intSteps = new IntSteps(0, 100, 5);
        final YasuoWindBar yasuoWindBar = new YasuoWindBar();

        new AnimationTester()
                .withBeforeIterationAction(i -> {
                    if (i >= 20 && i <= 50) {
                        mockResource(100, 100);
                    } else {
                        mockResource(intSteps.nextInt(), 100);
                    }
                })
                .testAnimation(yasuoWindBar, 70);

    }
}