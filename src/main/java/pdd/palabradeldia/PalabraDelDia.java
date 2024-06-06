package pdd.palabradeldia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PalabraDelDia extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PalabraDelDia.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Adivina la Palabra del Día");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
