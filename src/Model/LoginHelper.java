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

    /**
     * Helper function to insert new User into User table.
     * @param username String for desired username
     * @param password String for desired password
     * @param createdBy String of user who created
     * @param lastUpdatedBy String of last user who updated
     * @return Int to signify successful entry
     * @throws SQLException
     */
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

    /**
     * Helper function to update user in User table.
     * @param userID Int to identify user PK
     * @param modifiedBy String to identify who modified
     * @return Int to signify successful modification
     * @throws SQLException
     */
    public static int updateUser(int userID, String modifiedBy) throws SQLException {
        Timestamp modifiedTS = Timestamp.valueOf(LocalDateTime.now());

        String sql = "UPDATE users SET Last_Update = ?, Last_Updated_By = ?  WHERE User_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(3, userID);
        ps.setTimestamp(1,modifiedTS);
        ps.setString(2, modifiedBy);

        try{
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (Exception e) {
            System.out.println("Update failed! Please try again.");
            return 0;
        }
    }



}
