package net.barbux.creatures2d;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.Collation;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.*;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
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

    AnimationTimer animationTimer;

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
        if (animationTimer != null) {
            animationTimer.stop();
        }

        AtomicReference<Creatures.Creature> creatureRef = new AtomicReference<>();

        SerialUtil.readFile("/home/wons/creatures.save", g -> {
            if (g.getGeneration() == generationToShow) {
                for (Creatures.Creature creature : g.getCreaturesList()) {
                    if (creature.getCreatureId() == creatureToShow) {
                        creatureRef.set(creature);
                        break;
                    }
                }
            }
        }, r -> {
        });

        World world = new World(Collections.singletonList(Creature.deserialize(creatureRef.get())));

        final long simulationStepNanos = 100_000L;

        animationTimer = new AnimationTimer() {
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
                    lastTimeRendered = nanoTime;
                    lastSimTimeRendered = currentWorldTime;
                    world.render(creatureViewer,5, 3, 1, 1, World.CameraType.FOLLOW_CENTER);
                }
            }
        };
        animationTimer.start();
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

