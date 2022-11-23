package DAO;

import Model.Customer;
import Model.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.sound.midi.SysexMessage;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class CustomerHelper {

    /**
     * Get all countries in Country table
     * @throws SQLException
     */
    public static LinkedList getCountries() throws SQLException {
        String sql = "SELECT Country FROM countries";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        LinkedList<String> ls = new LinkedList<>();
        while (rs.next()) {
            ls.add(rs.getString("Country"));
        }
        return ls;
    }

    /**
     * Get Country ID based on inputted Country String.
     * @param country String
     * @return Country ID
     * @throws SQLException
     */
    public static int getCountryID(String country) throws SQLException {
        String sql = "SELECT Country_ID FROM countries WHERE Country = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, country);
        ResultSet rs = ps.executeQuery();
        try {
            rs.next();
            return rs.getInt("Country_ID");
        } catch (SQLException e) {
            return -1;
        }
    }

    /**
     * Get List of Second Division information based on inputted Country ID.
     * @param countryID int
     * @return LinkedList of Second Division items.
     * @throws SQLException
     */
    public static LinkedList getSecondDivision(int countryID) throws SQLException {
        String sql = "SELECT Division FROM first_level_divisions WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, countryID);
        ResultSet rs = ps.executeQuery();
        LinkedList<String> states = new LinkedList<>();
        try {
            while(rs.next()){
                states.add(rs.getString("Division"));
            }
        } catch (SQLException e) {
            GeneralHelper.createErrorMessage("No selection returned!", "Error!");
        }
        return states;
    }

    /**
     * Gets Second Division ID int using division name. Returns -1 if none.
     * @param division String
     * @return int Second Division ID
     * @throws SQLException
     */
    public static int getSecondDivisionID(String division) throws SQLException {
        String sql = "SELECT Division_ID FROM first_level_divisions WHERE Division = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, division);
        ResultSet rs = ps.executeQuery();
        try {
            rs.next();
            return rs.getInt("Division_ID");
        } catch (SQLException e) {
            return -1;
        }
    }

    public static int insertNewCustomer(String Name, String address, String postal, String phone, String user, int divisionID) throws SQLException{
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, Name);
        ps.setString(2, address);
        ps.setString(3, postal);
        ps.setString(4, phone);
        ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()).format(GeneralHelper.format())));
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now(ZoneId.systemDefault()));
        System.out.println(LocalDateTime.now(ZoneId.of("UTC")));
        System.out.println(GeneralHelper.LocaltoUTC(LocalDateTime.now()).format(GeneralHelper.format()));
        System.out.println(Timestamp.valueOf(GeneralHelper.LocaltoUTC(LocalDateTime.now()).format(GeneralHelper.format())));
        ps.setString(6, user);
        ps.setTimestamp(7,Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()).format(GeneralHelper.format())));
        ps.setString(8, user);
        ps.setInt(9,divisionID);

        try {
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLIntegrityConstraintViolationException | IllegalStateException e) {
            return -1;
        }
    }

    public static ObservableList getAllCustomers() throws SQLException {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            customers.add(new Customer(rs.getInt("Customer_ID"),
                    rs.getString("Customer_Name"),
                    rs.getString("Address"),
                    rs.getString("Postal_Code"),
                    rs.getString("Phone"),
                    rs.getInt("Division_ID"),
                    CustomerHelper.getSecondDivisionName(rs.getInt("Division_ID")),
                    rs.getString("Created_By"),
                    rs.getTimestamp("Last_Update").toLocalDateTime().format(GeneralHelper.format())));
        }
        return customers;
    }


    public static String getSecondDivisionName(int divisionID) throws SQLException {
        String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, divisionID);
        ResultSet rs = ps.executeQuery();
        try {
            rs.next();
            return rs.getString("Division");
        } catch (SQLException e) {
            GeneralHelper.createErrorMessage("No selection returned!", "Error!");
            return "";
        }
    }

    /**
     * Get country name from Country table using CountryID
     * @throws SQLException
     */
    public static String getCountry(int countryID) throws SQLException {
        String sql = "SELECT Country FROM countries WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, countryID);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString("Country");
    }

    /**
     * Get countryID from SecondDivisionID
     * @throws SQLException
     */
    public static int getCountryIDfromDivisionID(int divisionID) throws SQLException {
        String sql = "SELECT Country_ID FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, divisionID);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("Country_ID");
    }
}
