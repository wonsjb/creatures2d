package net.barbux.creatures2d;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.time.Duration;
import java.util.Collections;

public class SingleCreature extends Application {

    @Override
    public void start(Stage primaryStage) {

        final long simulationStepNanos = 100_000L;

        Canvas canvas = new Canvas();

        Group group = new Group(canvas);

        primaryStage.setTitle("2d Creatures");
        Scene scene = new Scene(group, 1000, 750);

        canvas.heightProperty().bind(scene.heightProperty());
        canvas.widthProperty().bind(scene.widthProperty());


        primaryStage.setScene(scene);
        primaryStage.show();



        AnimationTimer animationTimer = new AnimationTimer() {
            long lastTimeRendered = 0;
            long lastSimTimeRendered = 0;
            long firstRunTime = 0;
            long currentWorldTime = 0;
            double speed = 1;
            final World world = new World(Collections.singletonList(new Triangle2Spider()));

            @Override
            public void handle(long currentTimeNanos) {
                if (firstRunTime == 0) {
                    firstRunTime = currentTimeNanos;
                }
                long simulationTime = (long)((currentTimeNanos - firstRunTime) * speed);
                while (currentWorldTime < simulationTime) {
                    world.update(currentWorldTime);

                    currentWorldTime += simulationStepNanos;

                    if (System.nanoTime() > currentTimeNanos + 100_000_000L) {
                        break;
                    }
                }

                long nanoTime = System.nanoTime();
                if (nanoTime - lastTimeRendered > 10_000_000L) {
                    double timeSimed = currentWorldTime - lastSimTimeRendered;
                    int simSpeed = (int)((timeSimed / (nanoTime - lastTimeRendered)) * 100 + 0.5);
                    double fps = (1_000_000_000d / (nanoTime - lastTimeRendered));
                    lastTimeRendered = nanoTime;
                    lastSimTimeRendered = currentWorldTime;

                    world.render(canvas, 4.0, 3, 1, 1, World.CameraType.FOLLOW_CENTER);
                    System.out.println("Current sim time : " +
                            Duration.ofNanos(currentWorldTime) + ", " + fps + " fps, sim speed : " + simSpeed + "% , Best creature: " + world.creatures.get(0).getFitness() + " m");
                }
            }
        };
        animationTimer.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
