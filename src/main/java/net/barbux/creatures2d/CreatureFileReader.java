package net.barbux.creatures2d;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import net.barbux.creatures2d.proto.Creatures;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class CreatureFileReader extends Application {



    @Override
    public void start(Stage primaryStage) {

        final long simulationStepNanos = 100_000L;

        Canvas canvas = new Canvas();

        Group group = new Group(canvas);

        primaryStage.setTitle("2d Creatures file reader");
        Scene scene = new Scene(group, 1000, 375);

        canvas.heightProperty().bind(scene.heightProperty());
        canvas.widthProperty().bind(scene.widthProperty());


        primaryStage.setScene(scene);
        primaryStage.show();

        int seekGeneration = 9;
        int seekCreature = 0;
        final AtomicReference<Creature> creature = new AtomicReference<>();

        SerialUtil.readFile("/home/wons/creatures.save", generation -> {
            if (generation.getGeneration() == seekGeneration) {
                for (Creatures.Creature protoCreature : generation.getCreaturesList()) {
                    if (protoCreature.getCreatureId() == seekCreature) {
                        creature.set(Creature.deserialize(protoCreature));
                        break;
                    }
                }
            }
            System.out.println("Found generation " + generation.getGeneration() + " with " + generation.getCreaturesCount() + " creatures");
        }, results -> {
            int bestCreature = 0;
            double bestCreatureResult = Double.MIN_VALUE;

            for (Map.Entry<Integer, Double> entry : results.getResultsMap().entrySet()) {
                if (entry.getValue() > bestCreatureResult) {
                    bestCreatureResult = entry.getValue();
                    bestCreature = entry.getKey();
                }
            }
            System.out.println("Best creature of generation " + results.getGeneration() + " is " + bestCreature + " with " + bestCreatureResult + " m");
        });

        if (creature.get() == null) {
            throw new RuntimeException("Could not find creature " + seekCreature + " on generation " + seekGeneration);
        }

        final World world = new World(Collections.singletonList(creature.get()));

        AnimationTimer animationTimer = new AnimationTimer() {
            long lastTimeRendered = 0;
            long lastSimTimeRendered = 0;
            long firstRunTime = 0;
            long currentWorldTime = 0;
            double speed = 1.0;


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

                    world.render(canvas,2, 0.75, 1, 1, World.CameraType.FOLLOW_CENTER);
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
