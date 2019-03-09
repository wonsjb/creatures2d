package net.barbux.creatures2d;

import com.google.protobuf.CodedOutputStream;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

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

        Random random = new Random(42);


        AnimationTimer animationTimer = new AnimationTimer() {
            long lastTimeRendered = 0;
            long lastSimTimeRendered = 0;
            long firstRunTime = 0;
            long currentWorldTime = 0;
            double speed = 1;
            World world;
            int generation = 1;
            final CodedOutputStream file;
            final FileOutputStream stream;
            {
                world = new World(random);

                try {
                    stream = new FileOutputStream(System.getProperty("user.home") + "/creatures.save");
                    file = CodedOutputStream.newInstance(stream);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                world.saveNewGenerationToFile(file, generation);
            }

            @Override
            public void handle(long currentTimeNanos) {
                if (firstRunTime == 0) {
                    firstRunTime = currentTimeNanos;
                }
                long simulationTime = (long)((currentTimeNanos - firstRunTime) * speed);
                while (currentWorldTime < simulationTime) {
                    world.update(currentWorldTime);

                    currentWorldTime += simulationStepNanos;

                    if (currentWorldTime >= TimeUnit.SECONDS.toNanos(15)) {
                        // spend 15 seconds. Let's go to next generation
                        world.saveResultsToFile(file, generation);
                        world = world.getNextGeneration(random);
                        ++generation;
                        world.saveNewGenerationToFile(file, generation);

                        try {
                            stream.flush();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        currentWorldTime = 0;
                        lastSimTimeRendered = 0;
                    }

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

                    world.render(canvas, 2.0, 1.5, 50, 40, World.CameraType.FIXED);
                    System.out.println("Generation[" + generation + "] Current sim time : " +
                            Duration.ofNanos(currentWorldTime) + ", " + fps + " fps, sim speed : " + simSpeed + "% , Best creature: " +
                            world.creatures.get(0).creatureId + " with " + world.creatures.get(0).getFitness() + " m");
                }
            }
        };
        animationTimer.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
