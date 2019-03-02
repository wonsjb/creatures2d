package net.barbux.creatures2d;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.Collation;
import javafx.scene.chart.*;
import javafx.scene.control.Slider;
import net.barbux.creatures2d.proto.Creatures;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Creatures.Results> resultList = new ArrayList<>();

        SerialUtil.readFile("/home/wons/creatures.save", generation -> {

        }, resultList::add);

        double min = resultList.stream().mapToDouble(r -> r.getResultsMap().values().stream().mapToDouble(Double::doubleValue).max().getAsDouble()).min().getAsDouble();
        double max = resultList.stream().mapToDouble(r -> r.getResultsMap().values().stream().mapToDouble(Double::doubleValue).max().getAsDouble()).max().getAsDouble();

        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(resultList.get(0).getGeneration());
        xAxis.setUpperBound(resultList.get(resultList.size()-1).getGeneration());
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

        setHistogramData(resultList.get(resultList.size() - 1));

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

