package DAO;
import Model.JDBC;
import javafx.scene.control.Alert;
import java.sql.*;
import java.time.*;

/**
 * Helper class for interacting with Users table from database.
 */
public abstract class LoginHelper {

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
        ZonedDateTime createDate = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime lastUpdate = ZonedDateTime.now(ZoneId.of("UTC"));

        ps.setString(1, username);
        ps.setString(2, password);
        ps.setObject(3, createDate);
        ps.setString(4, createdBy);
        ps.setObject(5, lastUpdate);
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
        ZonedDateTime modifiedTS = ZonedDateTime.now(ZoneId.of("UTC"));

        String sql = "UPDATE users SET Last_Update = ?, Last_Updated_By = ?  WHERE User_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(3, userID);
        ps.setObject(1,modifiedTS);
        ps.setString(2, modifiedBy);

        try{
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (Exception e) {
            System.out.println("Update failed! Please try again.");
            return 0;
        }
    }

    /**
     * Function to delete user from user table
     * @param userID int that is a primary key to determine User
     * @return if delete was successful
     * @throws SQLException
     */
    public static int deleteUser(int userID) throws SQLException {
        String sql = "DELETE FROM users WHERE User_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, userID);

        try {
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (Exception e) {
            System.out.println("Delete failed! Please try again.");
            return 0;
        }
    }

    /**
     * Print all User_ID, Usernames, and passwords in User table
     * @throws SQLException
     */
    public static void printUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int user_ID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            Timestamp datetime = rs.getTimestamp("Create_Date");
            System.out.println(user_ID + " | " + userName + " | " + password + " | " + datetime);
            GeneralHelper.ESTtoUTC(datetime.toLocalDateTime());
        }

    }

    /**
     * Check username and password input combinations against database
     * @param username String for user inputted username
     * @param password String for user inputted password
     * @return Boolean for TRUE user/password match, FALSE if no match or error due to empty username or password
     * @throws SQLException
     */
    public static Boolean getUserPasswordMatch(String username, String password) throws SQLException {
        String sql = "SELECT User_Name, Password FROM users WHERE User_Name = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        try {
            rs.next();
            return rs.getString("Password").equals(password);
        } catch (SQLException e) {
            System.out.println("Please input a username!");
            return false;
        }
    }


}
