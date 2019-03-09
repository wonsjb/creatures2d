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
import javafx.scene.transform.Affine;
import net.barbux.creatures2d.proto.Creatures;

import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class SummaryViewController implements Initializable {

    @FXML
    private NumberAxis xAxis ;

    @FXML
    private NumberAxis yAxis;

    @FXML
    LineChart<Number, Number> evolution;

    @FXML
    CategoryAxis histoCategoryAxis;

    @FXML
    NumberAxis histoValues;

    @FXML
    BarChart<String, Number> histogram;

    @FXML
    Slider generationSelector;

    @FXML
    TextField generationField;

    @FXML
    TextField creatureField;

    @FXML
    Canvas creatureViewer;

    @FXML
    Button restartButton;

    @FXML
    Button spiderButton;

    @FXML
    Button loadButton;

    @FXML
    Button viewButton;

    @FXML
    Canvas editCanvas;

    WorldAnimator animator;

    Creature currentCreature;
    Creature editedCreature;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Creatures.Results> resultList = new ArrayList<>();

        SerialUtil.readFile("/home/wons/creatures.save", g -> {
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
            World world = new World(Collections.singletonList(editedCreature));
            world.render(editCanvas, 1, 1, 1, 1, World.CameraType.FIXED);

        });

        editCanvas.setOnMouseClicked(mouseEvent -> {
            System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());
            GraphicsContext gc = editCanvas.getGraphicsContext2D();
            Affine transform = new Affine(gc.getTransform());
            try {
                transform.invert();
            } catch (Exception e) {

            }
            Point2D point = transform.transform(mouseEvent.getX(), mouseEvent.getY());

            double x = point.getX() / 100.0;
            double y = -point.getY() / 100.0;
            System.out.println(x + " " + y);

            editedCreature.allNodes.add(new Creature.Node(editedCreature.allNodes.size(), x, y));

            World world = new World(Collections.singletonList(editedCreature));
            world.render(editCanvas, 1, 1, 1, 1, World.CameraType.FIXED);
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


        SerialUtil.readFile("/home/wons/creatures.save", g -> {
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

