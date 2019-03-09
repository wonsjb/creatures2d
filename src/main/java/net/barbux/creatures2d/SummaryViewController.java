package net.barbux.creatures2d;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.print.Collation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import net.barbux.creatures2d.proto.Creatures;

import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class SummaryViewController implements Initializable {

    @FXML private NumberAxis xAxis ;
    @FXML private NumberAxis yAxis;
    @FXML LineChart<Number, Number> evolution;
    @FXML CategoryAxis histoCategoryAxis;
    @FXML NumberAxis histoValues;
    @FXML BarChart<String, Number> histogram;
    @FXML Slider generationSelector;
    @FXML TextField generationField;
    @FXML TextField creatureField;
    @FXML Canvas creatureViewer;
    @FXML Button restartButton;
    @FXML Button spiderButton;
    @FXML Button loadButton;
    @FXML Button viewButton;
    @FXML Canvas editCanvas;
    @FXML Button modeButton;
    @FXML Text modeText;
    @FXML Button unselectButton;
    @FXML Button addBoneButton;
    @FXML Button deleteButton;
    @FXML Button disconnectButton;

    WorldAnimator animator;

    Creature currentCreature;
    Creature editedCreature;

    enum CurrentMode {
        ADD_NODES("Add Nodes"),
        SELECT_NODES("Select Nodes"),
        MOVE_NODES("Move Nodes");

        final String text;

        CurrentMode(String text) {
            this.text = text;
        }
    }

    CurrentMode currentMode = CurrentMode.ADD_NODES;
    Creature.Node movingNode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Creatures.Results> resultList = new ArrayList<>();

        SerialUtil.readFile(System.getProperty("user.home") + "/creatures.save", g -> {
        }, resultList::add);

        double min = resultList.stream().mapToDouble(r -> r.getResultsMap().values().stream().mapToDouble(Double::doubleValue).max().getAsDouble()).min().getAsDouble();
        double max = resultList.stream().mapToDouble(r -> r.getResultsMap().values().stream().mapToDouble(Double::doubleValue).max().getAsDouble()).max().getAsDouble();

        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(resultList.get(0).getGeneration());
        xAxis.setUpperBound(resultList.get(resultList.size() - 1).getGeneration());
        xAxis.setTickUnit(10);
        xAxis.setLabel("Generation");

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(min * 0.98);
        yAxis.setUpperBound(max * 1.02);
        yAxis.setTickUnit(1);
        yAxis.setLabel("How Far (m)");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        for (Creatures.Results results : resultList) {
            double maxForGen = results.getResultsMap().values().stream().mapToDouble(Double::doubleValue).max().getAsDouble();
            series.getData().add(new XYChart.Data<>(results.getGeneration(), maxForGen));
        }

        evolution.getData().add(series);

        histogram.setCategoryGap(0);
        histogram.setBarGap(0);
        histogram.setAnimated(false);

        Creatures.Results lastGenResult = resultList.get(resultList.size() - 1);

        setHistogramData(lastGenResult);

        generationSelector.setMin(1);
        generationSelector.setMax(resultList.size());
        generationSelector.setMajorTickUnit(50.0);
        generationSelector.setMinorTickCount(5);
        generationSelector.setShowTickLabels(true);
        generationSelector.setShowTickMarks(true);
        generationSelector.setValue(resultList.size());

        histoValues.setLabel("Count");

        generationSelector.valueProperty().addListener((observable, oldValue, newValue) ->
                setHistogramData(resultList.get(newValue.intValue() - 1)
                ));

        generationField.setText(Integer.toString(lastGenResult.getGeneration()));
        int bestCreature = 0;
        double bestCreatureResult = Double.MIN_VALUE;
        for (Map.Entry<Integer, Double> results : lastGenResult.getResultsMap().entrySet()) {
            if (results.getValue() > bestCreatureResult) {
                bestCreatureResult = results.getValue();
                bestCreature = results.getKey();
            }
        }
        creatureField.setText(Integer.toString(bestCreature));

        animateCreature();

        creatureField.textProperty().addListener(g -> animateCreature());
        generationField.textProperty().addListener(g -> animateCreature());

        restartButton.setOnAction(value -> {
            if (animator != null) animator.reset();
        });

        spiderButton.setOnAction(value -> {
            if (animator != null) {
                animator.stop();
            }
            currentCreature = new Triangle2Spider();
            World world = new World(Collections.singletonList(currentCreature.clone()));
            animator = new WorldAnimator(world, creatureViewer);
            animator.start();
        });

        loadButton.setOnAction(value -> {
            editedCreature = currentCreature.clone();
            refreshEditor();

        });

        editCanvas.setOnMouseClicked(mouseEvent -> {

            Point2D point = getMousePoint(mouseEvent);

            if (currentMode == CurrentMode.ADD_NODES) {
                editedCreature.allNodes.add(new Creature.Node(editedCreature.allNodes.size(), point.getX(), point.getY()));

                refreshEditor();
            } else if (currentMode == CurrentMode.MOVE_NODES) {
                if (movingNode == null) {
                    movingNode = findNode(point);
                } else {
                    movingNode = null;
                }
            } else if (currentMode == CurrentMode.SELECT_NODES) {
                Creature.Node node = findNode(point);
                if (node != null) {
                    node.color = node.color.equals(Color.BROWN) ? Color.LIGHTGREEN : Color.BROWN;
                    refreshEditor();
                }
            }
        });

        editCanvas.setOnMouseMoved(mouseEvent -> {
            if (currentMode == CurrentMode.MOVE_NODES && movingNode != null) {
                Point2D point = getMousePoint(mouseEvent);

                movingNode.p.x = point.getX();
                movingNode.p.y = point.getY();
                movingNode.originalPos.x = point.getX();
                movingNode.originalPos.y = point.getY();

                editedCreature.resizeBones();

                refreshEditor();
            }
        });

        viewButton.setOnAction(value -> {
            if (animator != null) {
                animator.stop();
            }
            currentCreature = editedCreature.clone();
            World world = new World(Collections.singletonList(currentCreature.clone()));
            animator = new WorldAnimator(world, creatureViewer);
            animator.start();
        });

        modeText.setText(currentMode.text);

        modeButton.setOnAction(value -> {
            currentMode = CurrentMode.values()[(currentMode.ordinal() + 1) % CurrentMode.values().length];
            modeText.setText(currentMode.text);
        });

        unselectButton.setOnAction(value -> {
            for (Creature.Node node : editedCreature.allNodes) {
                node.color = Color.BROWN;
            }
            refreshEditor();
        });

        addBoneButton.setOnAction(value -> {
            List<Creature.Node> selected = editedCreature.allNodes.stream().filter(n -> n.color.equals(Color.LIGHTGREEN)).collect(Collectors.toList());

            if (selected.size() == 2) {
                editedCreature.allBones.add(new Creature.Bone(selected.get(0), selected.get(1)));
            }
            refreshEditor();
        });

        deleteButton.setOnAction(value -> {
            editedCreature.allBones.removeIf(connection -> connection.node1.color.equals(Color.LIGHTGREEN) || connection.node2.color.equals(Color.LIGHTGREEN));
            editedCreature.allMuscles.removeIf(connection -> connection.node1.color.equals(Color.LIGHTGREEN) || connection.node2.color.equals(Color.LIGHTGREEN));
            editedCreature.allNodes.removeIf(node -> node.color.equals(Color.LIGHTGREEN));
            refreshEditor();
        });

        disconnectButton.setOnAction(value -> {
            List<Creature.Node> selected = editedCreature.allNodes.stream().filter(n -> n.color.equals(Color.LIGHTGREEN)).collect(Collectors.toList());

            if (selected.size() == 2) {
                editedCreature.allBones.removeIf(bone -> (bone.node1.nodeId == selected.get(0).nodeId && bone.node2.nodeId == selected.get(1).nodeId)
                        || (bone.node1.nodeId == selected.get(1).nodeId && bone.node2.nodeId == selected.get(0).nodeId));
            }
            refreshEditor();
        });

    }

    private void refreshEditor() {
        World world = new World(Collections.singletonList(editedCreature));
        world.render(editCanvas, 1, 1, 1, 1, World.CameraType.FIXED, false);
    }

    private Creature.Node findNode(Point2D point) {
        for (Creature.Node node : editedCreature.allNodes) {
            if (Geometry.distance2(new Geometry.Vector(point), node.p) < (node.size * node.size)) {
                return node;
            }
        }
        return null;
    }

    Point2D getMousePoint(MouseEvent mouseEvent) {
        GraphicsContext gc = editCanvas.getGraphicsContext2D();
        Affine transform = new Affine(gc.getTransform());
        try {
            transform.invert();
        } catch (Exception e) {

        }
        return transform.transform(mouseEvent.getX(), mouseEvent.getY());
    }

    void animateCreature()
    {

        final int generationToShow;
        final int creatureToShow;
        try {
            generationToShow = Integer.parseInt(generationField.getText());
            creatureToShow = Integer.parseInt(creatureField.getText());
        } catch (Exception e) {
            return;
        }
        if (animator != null) {
            animator.stop();
        }


        SerialUtil.readFile(System.getProperty("user.home") + "/creatures.save", g -> {
            if (g.getGeneration() == generationToShow) {
                for (Creatures.Creature creature : g.getCreaturesList()) {
                    if (creature.getCreatureId() == creatureToShow) {
                        currentCreature = Creature.deserialize(creature);
                        break;
                    }
                }
            }
        }, r -> {
        });

        World world = new World(Collections.singletonList(currentCreature.clone()));

        animator = new WorldAnimator(world, creatureViewer);

        animator.start();
    }

    private void setHistogramData(Creatures.Results generationToShow) {
        histoCategoryAxis.setLabel("MaxLength [Gen " + generationToShow.getGeneration() + "]");

        double[] results = new double[100];
        double maxLastGen = generationToShow.getResultsMap().values().stream().mapToDouble(Double::doubleValue).max().getAsDouble();
        double minLastGen = generationToShow.getResultsMap().values().stream().mapToDouble(Double::doubleValue).min().getAsDouble();

        for (Double length : generationToShow.getResultsMap().values()) {
            int bucket = Math.min(results.length - 1, (int) (100.0 * (length - minLastGen) / (maxLastGen - minLastGen)));
            results[bucket]++;
        }

        XYChart.Series<String, Number> histogramData = new XYChart.Series<>();
        for (int i = 0; i < results.length; ++i) {
            histogramData.getData().add(new XYChart.Data<>(Double.toString(i  * (maxLastGen - minLastGen) / 100.0 + minLastGen).substring(0, 5), results[i]));
        }
        histogram.getData().clear();
        histogram.getData().add(histogramData);
    }
}

