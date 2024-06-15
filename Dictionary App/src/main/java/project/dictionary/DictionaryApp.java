package project.dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DictionaryApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DictionaryApp.class.getResource("design.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Dictionary Application");
        stage.setScene(scene);
        stage.setWidth(788); // Set the width to 800 pixels
        stage.setHeight(550); // Set the height to 600 pixels
        stage.setResizable(false); // Make the window non-resizable if desired
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
