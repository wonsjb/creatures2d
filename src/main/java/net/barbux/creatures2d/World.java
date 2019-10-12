package net.barbux.creatures2d;

import com.google.protobuf.CodedOutputStream;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import net.barbux.creatures2d.proto.Creatures;

import java.time.Duration;
import java.util.*;
import java.util.function.Supplier;

public class World {
    final ArrayList<Creature> creatures = new ArrayList<>();
    final Supplier<Physics> physicsSupplier;
    final ParallelExecutor parallelExecutor;

    long lastUpdateNanos;

    World(Supplier<Physics> physicsSupplier, Random random) {
        this.physicsSupplier = physicsSupplier;
        for (int i = 0; i < 2000; ++i) {
            Creature creature = new RandomCreature(random);
            creature.creatureId = i;
            creatures.add(creature);
        }
        lastUpdateNanos = 0;
        parallelExecutor = new ParallelExecutor(Runtime.getRuntime().availableProcessors());
    }

    World(Supplier<Physics> physicsSupplier) {
        this.physicsSupplier = physicsSupplier;
        lastUpdateNanos = 0;
        parallelExecutor = new ParallelExecutor(8);
    }

    World(Supplier<Physics> physicsSupplier, Collection<Creature> creatures) {
        this.physicsSupplier = physicsSupplier;
        lastUpdateNanos = 0;
        this.creatures.addAll(creatures);
        parallelExecutor = new ParallelExecutor(8);

    }

    public World clone() {
        World world = new World(physicsSupplier);
        for (Creature creature : creatures) {
            world.creatures.add(creature.clone());
        }
        return world;
    }

    World getNextGeneration(Random random) {
        creatures.sort(Collections.reverseOrder(Comparator.comparing(Creature::getFitness)));
        World newWord = new World(physicsSupplier);
        for (int i = 0; i < creatures.size() / 2; ++i) {
            Creature creature = creatures.get(i).clone();
            newWord.creatures.add(creature);
            creature.creatureId = newWord.creatures.size() - 1;

            boolean done = false;
            do {
                Creature mutant = creature.clone();
                if (mutant.mutate(random)) {
                    newWord.creatures.add(mutant);
                    mutant.creatureId = newWord.creatures.size() - 1;
                    done = true;
                }
            } while (!done);
        }
        return newWord;
    }

    void initializePhysics() {
        for (Creature creature : creatures) {
            creature.initializePhysics(physicsSupplier);
        }
    }

    void update(long currentNanos) {
        if (lastUpdateNanos == 0) {
            initializePhysics();
        }
        long diff = currentNanos - lastUpdateNanos;

        parallelExecutor.execute(creatures, creature -> creature.update(diff));

        lastUpdateNanos = currentNanos;
    }

    enum CameraType {
        FIXED,
        FOLLOW_CENTER
    }

    void render(Canvas canvas, double showx, double showy, int divideInX, int divideInY, CameraType cameraType, boolean showNumberAndRuler) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setTransform(new Affine());

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (showNumberAndRuler) {
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font(10));
            gc.fillText("Sim time: " + Duration.ofNanos(lastUpdateNanos).toString(), 0, 20);
        }



        Affine t = getTransform(canvas.getWidth(), canvas.getHeight(),0, 0, 0, 0, showx, showy);

        gc.setTransform(t);


        int pos = 0;
        creatures.sort(Collections.reverseOrder(Comparator.comparing(Creature::getFitness)));
        for (Creature creature : creatures) {
            if (showNumberAndRuler) {
                switch (cameraType) {
                    case FIXED:
                        gc.setTransform(getTransform(canvas.getWidth() / divideInX, canvas.getHeight() / divideInY, 0, -0.05, pos % divideInX, (pos / divideInX) % divideInY, showx, showy));
                        drawRuler(gc, 0, showx);
                        break;
                    case FOLLOW_CENTER:
                        Geometry.Vector center = creature.getCenter();
                        gc.setTransform(getTransform(canvas.getWidth() / divideInX, canvas.getHeight() / divideInY, center.x - (showx / 2), -0.05, pos % divideInX, (pos / divideInX) % divideInY, showx, showy));
                        drawRuler(gc, (int) (center.x - showx), center.x + showx);
                        break;
                }
            }


            creature.render(gc, showNumberAndRuler);

            ++pos;
        }
    }

    private void drawRuler(GraphicsContext gc, int start, double end) {
        for (int i = start; i < end; ++i) {
            gc.setFill(Color.gray(i % 2 == 0 ? 0.3 : 0.5));
            gc.fillRect(i, -0.05, 1, 0.05);

            gc.save();
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font(6));
            gc.scale(0.01, -0.01);
            gc.fillText(i + " m", i * 100, 5);
            gc.restore();
        }
    }

    private Affine getTransform(double width, double height, double translatex, double translatey, int x, int y, double showx, double showy) {
        Affine t = new Affine();
        double xratio = showx / width;
        double yratio = -showy / height;
        t.appendTranslation(-showx * x + translatex, showy * (y + 1) + translatey);
        t.appendScale( xratio, yratio );
        try
        {
            t.invert();
        }
        catch(NonInvertibleTransformException e)
        {
            throw new RuntimeException(e);
        }
        return t;
    }



    void saveResultsToFile(CodedOutputStream file, int generation) {
        Creatures.Results.Builder builder = Creatures.Results.newBuilder();
        builder.setGeneration(generation);
        for (Creature creature : creatures) {
            builder.putResults(creature.creatureId, creature.getFitness());
        }

        try {
            file.writeMessageNoTag(builder.build());
            file.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void saveNewGenerationToFile(CodedOutputStream file, int generation) {
        Creatures.Generation.Builder builder = Creatures.Generation.newBuilder();
        builder.setGeneration(generation);
        for (Creature creature : creatures) {
            builder.addCreatures(creature.serialize());
        }

        try {
            file.writeMessageNoTag(builder.build());
            file.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

