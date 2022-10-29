package Model;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDateTime;

public abstract class LoginHelper {

    /**
     * Helper function to create adjustable error message.
     * @param message String of desired error message
     */
    public static void createErrorMessage(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Error!");
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }

    public static int insertUser(String username, String password,
                                 String createdBy, String lastUpdatedBy) throws SQLException {
        String sql = "INSERT INTO users (User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
        Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());

        ps.setString(1, username);
        ps.setString(2, password);
        ps.setTimestamp(3, createDate);
        ps.setString(4, createdBy);
        ps.setTimestamp(5, lastUpdate);
        ps.setString(6, lastUpdatedBy);

        try {
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLIntegrityConstraintViolationException | IllegalStateException e) {
            System.out.println("Duplicate username! Please enter a different one.");
            return 0;
        }

    }


}
