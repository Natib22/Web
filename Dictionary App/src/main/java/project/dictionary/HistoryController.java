package project.dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoryController extends SearchPageController {

    @FXML
    private Button deleteButton;


    @FXML
    private ListView<String> historyView;

    private ObservableList<String> historyList;

    @FXML
    public void initialize() {
        // Initialize the list of history items
        historyList = FXCollections.observableArrayList();

        // Populate the ListView with the history items
        historyView.setItems(historyList);

        // Example: Add some history items to the list
        historyList.addAll(newApp.getSearchHistory());
    }

    @FXML
    void deleteButton(ActionEvent event) throws SQLException {
        clearHistory();


    }

    public void clearHistory() throws SQLException {
        String sql = "DELETE FROM search_history;";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        historyList.clear();
        historyList.add("");

    }
}
