package project.dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {

    @FXML
    private Button bookmarkButton;

    @FXML
    private ImageView bookmarkImageView;

    @FXML
    private Button historyButton;

    @FXML
    private ImageView historyImageView;

    @FXML
    private AnchorPane leftPanel;

    @FXML
    private AnchorPane rightPanel;

    @FXML
    private Button searchButton;

    @FXML
    private ImageView searchIconImageView;

    @FXML
    public void initialize() {
        // Make the ImageView ignore mouse events
        bookmarkImageView.setMouseTransparent(true);
        historyImageView.setMouseTransparent(true);
        searchIconImageView.setMouseTransparent(true);

        // Alternatively, forward mouse events from ImageView to respective Button
        bookmarkImageView.setOnMouseClicked(event -> {
            event.consume(); // Prevent the event from further propagation
            bookmarkButton.fire(); // Trigger the button's action
        });

        historyImageView.setOnMouseClicked(event -> {
            event.consume(); // Prevent the event from further propagation
            historyButton.fire(); // Trigger the button's action
        });

        searchIconImageView.setOnMouseClicked(event -> {
            event.consume(); // Prevent the event from further propagation
            searchButton.fire(); // Trigger the button's action
        });
    }

    @FXML
    void searchButton(ActionEvent event) {
        try {
            // Load the searchDesign.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/dictionary/searchDesign.fxml"));
            Parent searchDesignContent = loader.load();

            // Clear the current content of rightPanel and set the new content
            rightPanel.getChildren().setAll(searchDesignContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bookmarkButton(ActionEvent event) {

        try {
            // Load the searchDesign.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/dictionary/bookmark.fxml"));
            Parent searchDesignContent = loader.load();

            // Clear the current content of rightPanel and set the new content
            rightPanel.getChildren().setAll(searchDesignContent);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @FXML
    void historyButton(ActionEvent event) {

        try {
            // Load the searchDesign.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/dictionary/history.fxml"));
            Parent searchDesignContent = loader.load();

            // Clear the current content of rightPanel and set the new content
            rightPanel.getChildren().setAll(searchDesignContent);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // You can similarly define methods for bookmarkButton and historyButton actions if needed
}
