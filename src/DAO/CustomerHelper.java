package DAO;

import Model.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
}
