package net.barbux.creatures2d;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SummaryViewer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SummaryView.fxml"));
        SummaryViewController controller = new SummaryViewController();
        loader.setController(controller);

        Parent root = loader.load();
        primaryStage.setTitle("Summary viewer");
        primaryStage.setScene(new Scene(root, 1000, 750));
        primaryStage.show();

    }
}
