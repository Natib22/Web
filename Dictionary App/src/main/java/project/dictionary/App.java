package project.dictionary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {

    public ArrayList<String> getWord(String word) throws SQLException {
        String sql = "SELECT * FROM entries WHERE word = ?";
        ArrayList<String> answer = new ArrayList<String>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, word);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Word: " + rs.getString("word"));
                 answer.add(rs.getString("definition"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow to indicate failure to the caller if necessary
        }

//        logSearch(word);
        return answer;
    }

    public void logSearch(String word) throws SQLException {
        String sql = "INSERT INTO search_history (searchedWord) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, word);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow to indicate failure to the caller if necessary
        }
    }

    public ArrayList<String> getSearchHistory() {
        ArrayList<String> history = new ArrayList<String>();
        String sql = "SELECT * FROM search_history ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet res = pstmt.executeQuery()) {

            while (res.next()) {
//                System.out.println(res.getString("searchedWord"));
                history.add(res.getString("searchedWord"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    public void addBookmark(String word) throws SQLException {


        if  (this.isBookmarked(word)) {
            return;
        }

        String sql = "INSERT INTO bookmark (bookMarkedWord) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, word);
            pstmt.executeUpdate();
            System.out.println("Bookmark added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow to indicate failure to the caller if necessary
        }
    }

    public ArrayList<String> getBookMark() throws SQLException {
        String sql = "SELECT * FROM bookmark ORDER BY bookMarkedAT";
        ArrayList<String> BookList = new ArrayList<String>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet res = pstmt.executeQuery()) {

            while (res.next()) {
//                System.out.println(res.getString("bookMarkedWord"));
                BookList.add(res.getString("bookMarkedWord"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow to indicate failure to the caller if necessary
        }
        return BookList;
    }


    public boolean isBookmarked(String word) {
        ArrayList<String> bookmarks = new ArrayList<String>();
        try {
            bookmarks = getBookMark();
            for (String bookmark : bookmarks) {
                if (bookmark.equals(word)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public ArrayList<String> getSuggestions(String prefix) throws SQLException {
        String sql = "SELECT * FROM entries WHERE word LIKE ?";
        Set<String> suggestionSet = new HashSet<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the wildcard search parameter with the prefix%
            pstmt.setString(1, prefix + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String foundWord = rs.getString("word");
                suggestionSet.add(foundWord);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow to indicate failure to the caller if necessary
        }

        return new ArrayList<>(suggestionSet);
    }

    public void unBookmark(String word) throws SQLException{
        String sql = "DELETE from bookmark where bookMarkedWord = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,word);
            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
