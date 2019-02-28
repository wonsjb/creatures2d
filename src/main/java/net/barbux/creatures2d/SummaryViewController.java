package net.barbux.creatures2d;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import net.barbux.creatures2d.proto.Creatures;

import java.net.URL;
import java.util.ArrayList;
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
    BarChart<Double, Double> histogram;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Creatures.Results> resultList = new ArrayList<>();

        SerialUtil.readFile("/home/wons/creatures.save", generation -> {

        }, resultList::add);

        double max = resultList.stream().mapToDouble(r -> r.getResultsMap().values().stream().mapToDouble(Double::doubleValue).max().getAsDouble()).max().getAsDouble();

        NumberAxis xAxis = new NumberAxis(resultList.get(0).getGeneration(), resultList.get(resultList.size()-1).getGeneration(), 10);
        xAxis.setLabel("Generation");

        NumberAxis yAxis = new NumberAxis(0, max, 1);
        yAxis.setLabel("How far");
        evolution.getXAxis().setTick

        LineChart<Number, Number> linechart = new LineChart<>(xAxis, yAxis);




    }
}
