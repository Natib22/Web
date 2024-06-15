package project.dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.SQLException;

public class BookmarksController extends SearchPageController {

    @FXML
    private ListView<String> bookmarkView;
    private ObservableList<String> BookMarkList;
    public void initialize() throws SQLException {
        // Initialize the list of history items
        BookMarkList = FXCollections.observableArrayList();

        // Populate the ListView with the history items
        bookmarkView.setItems(BookMarkList);

        // Example: Add some history items to the list
        BookMarkList.addAll(newApp.getBookMark().reversed());
    }

}
