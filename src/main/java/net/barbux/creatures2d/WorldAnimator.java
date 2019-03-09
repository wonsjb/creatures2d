package net.barbux.creatures2d;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;

public class WorldAnimator {

    final World initialWorld;
    AnimationTimer animationTimer;
    long lastTimeRendered = 0;
    long lastSimTimeRendered = 0;
    long firstRunTime = 0;
    long currentWorldTime = 0;
    double speed = 1.0;
    World animatedWorld;
    long simulationStepNanos = 100_000L;


    public WorldAnimator(World world, Canvas canvas) {
        this.initialWorld = world;
        reset();


        animationTimer = new AnimationTimer() {

            @Override
            public void handle(long currentTimeNanos) {
                if (firstRunTime == 0) {
                    firstRunTime = currentTimeNanos;
                }
                long simulationTime = (long)((currentTimeNanos - firstRunTime) * speed);
                while (currentWorldTime < simulationTime) {
                    animatedWorld.update(currentWorldTime);

                    currentWorldTime += simulationStepNanos;

                    if (System.nanoTime() > currentTimeNanos + 100_000_000L) {
                        break;
                    }
                }

                long nanoTime = System.nanoTime();
                if (nanoTime - lastTimeRendered > 10_000_000L) {
                    lastTimeRendered = nanoTime;
                    lastSimTimeRendered = currentWorldTime;
                    animatedWorld.render(canvas,5, 3, 1, 1, World.CameraType.FOLLOW_CENTER, true);
                }
            }
        };
    }

    public void start() {
        animationTimer.start();
    }

    public void stop() {
        animationTimer.stop();
    }

    public void reset() {
        lastTimeRendered = 0;
        lastSimTimeRendered = 0;
        firstRunTime = 0;
        currentWorldTime = 0;
        speed = 1.0;
        animatedWorld = initialWorld.clone();
    }
}
