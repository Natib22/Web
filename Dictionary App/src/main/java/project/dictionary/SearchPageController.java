
package project.dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static java.lang.Math.min;


public class SearchPageController  {



    @FXML
    private Button addToBookMarkButton;

    @FXML
    private ImageView addtoBookmarkImageView;

    @FXML
    private Button copyButton;

    @FXML
    private ImageView copyButtonImageView;

    @FXML
    private AnchorPane definitionPanel;

    @FXML
    private AnchorPane searchPage;

    @FXML
    private AnchorPane searchPanel;

    @FXML
    private Button searchWordButton;

    @FXML
    private TextField searchWordField;

    @FXML
    private ListView<String> suggestionBox;

    //to manage the list that i add to the list view
    private ObservableList<String> suggestionList;

    @FXML
    private TextArea searchedWordDefnition;



    App newApp = new App();


    public void initialize() throws SQLException {
        // Initialize the list of bookmarks
        suggestionList = FXCollections.observableArrayList();

        // Populate the ListView with the bookmarks
        suggestionBox.setItems(suggestionList);

        // Example: Add some bookmarks to the list
        suggestionList.addAll("");
    }

    @FXML
    void searchWordButton(ActionEvent event) throws SQLException {
//        suggestionList.add("hiiiiiii");


        searchWord();


    }

    @FXML
    void addToBookMarkButton(ActionEvent event) throws SQLException {
        System.out.println("getting clicked");
        String word = searchWordField.getText();

        if (word != ""  & !newApp.getWord(word).isEmpty() && !newApp.isBookmarked(word)) {
            newApp.addBookmark(word);
            if (newApp.isBookmarked(word)) {
                changeToYellow();
            }

        }
        else {
            newApp.unBookmark(word);
            changeToBlack();
        }

    }

    @FXML
    void copyButton(ActionEvent event) {

        System.out.println("it getting clicked");

        // Get the content from the TextArea
        String content = searchedWordDefnition.getText();

        // Create a ClipboardContent object
        ClipboardContent clipboardContent = new ClipboardContent();

        // Put the content into the ClipboardContent object
        clipboardContent.putString(content);

        // Get the system clipboard and set the content
        Clipboard clipboard = Clipboard.getSystemClipboard();
        clipboard.setContent(clipboardContent);

    }


    @FXML
    void searchWordField(KeyEvent event) throws SQLException {

        suggestionList.clear();

        // Populate the ListView with the bookmarks
        suggestionBox.setItems(suggestionList);

        // Example: Add some bookmarks to the list
        if (event.getCode() == KeyCode.ENTER) {
            //System.out.println("hereee");
            searchWord();
        }
        else {

            String prefix = searchWordField.getText();
            ArrayList<String> answer = newApp.getSuggestions(prefix);
            for (int i=0 ; i < min(answer.size(),8) ; i+=1){
                suggestionList.add(answer.get(i));
            }
            if (suggestionList.size() == 0){
                suggestionList.add("");
            }

        }
        if (searchWordField.getText().length() == 0){
            suggestionList.clear();
            suggestionList.add("");
        }

    }



    void changeToYellow(){
        String imagePath = "/project/dictionary/yellowBookmark.png";
        Image newImage = new Image(getClass().getResource(imagePath).toString());
        if (newImage.isError()) {
            System.out.println("Error loading the image");
        } else {
            addtoBookmarkImageView.setImage(newImage);
        }
    }

    void changeToBlack(){
        String imagePath = "/project/dictionary/bookmark_add_48dp_FILL0_wght400_GRAD0_opsz48.png";
        Image newImage = new Image(getClass().getResource(imagePath).toString());
        if (newImage.isError()){
            System.out.println("Error loading the image");
        } else {
            addtoBookmarkImageView.setImage(newImage);
        }
    }


    private void searchWord() throws SQLException {
        String word = searchWordField.getText();
        ArrayList<String> answer = null;
        searchedWordDefnition.clear();
        changeToBlack();
        if (word.length() > 0) {
            answer = newApp.getWord(word);
            searchedWordDefnition.appendText("\n\n");
            int n = 1;
            if (answer.size() > 0) {
                for (String defi : answer) {
                    searchedWordDefnition.appendText(n + " . " + defi + "\n\n");
                    n++;
                }
                newApp.logSearch(word);
                if (newApp.isBookmarked(word)) {
                    changeToYellow();
                }
            } else {
                searchedWordDefnition.appendText("Word Not Found");
            }
        } else {
            searchedWordDefnition.appendText("\n\nEmpty Search Field");
        }
    }

    @FXML
    void checkIfEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                searchWord();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void suggestionBoxClicked(MouseEvent event) throws SQLException {

        searchWordByInput(suggestionBox.getSelectionModel().getSelectedItem());
        searchWordField.setText(suggestionBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    void suggestionBoxPressed(KeyEvent event) throws SQLException {
        searchWordByInput(suggestionBox.getSelectionModel().getSelectedItem());
        searchWordField.setText(suggestionBox.getSelectionModel().getSelectedItem());
    }

    void searchWordByInput(String w) throws SQLException {
        String word = w;
        ArrayList<String> answer = null;
        searchedWordDefnition.clear();
        changeToBlack();
        if (word.length() > 0) {
            answer = newApp.getWord(word);
            searchedWordDefnition.appendText("\n\n");
            int n = 1;
            if (answer.size() > 0) {
                for (String defi : answer) {
                    searchedWordDefnition.appendText(n + " . " + defi + "\n\n");
                    n++;
                }
                newApp.logSearch(word);
                if (newApp.isBookmarked(word)) {
                    changeToYellow();
                }
            } else {
                searchedWordDefnition.appendText("Word Not Found");
            }
        } else {
            searchedWordDefnition.appendText("\n\nEmpty Search Field");
        }


    }



}