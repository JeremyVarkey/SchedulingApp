package DAO;

import Model.JDBC;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Helper class for Appointments table in database
 */
public class AppointmentsHelper {

    /**
     * Get all appointments from appointments table in database. Returns ObservableList of Appointment objects
     * @return ObservableList
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Appointment apt = new Appointment(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getTimestamp(6).toLocalDateTime(),
                    rs.getTimestamp(7).toLocalDateTime(),
                    rs.getTimestamp(8).toLocalDateTime(),
                    rs.getString(9),
                    rs.getTimestamp(10).toLocalDateTime(),
                    rs.getString(11),
                    rs.getInt(12),
                    rs.getInt(13),
                    rs.getInt(14),
                    AppointmentsHelper.getContact(rs.getInt(14))
                    );
            appointments.add(apt);
        }
        return appointments;
    }

    /**
     * Deletes appointment of specific Appointment ID from appointments table in database.
     * @param aptID
     * @return int rows affected
     * @throws SQLException
     */
    public static int deleteAppointment(int aptID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, aptID);
        try {
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (Exception e) {
            System.out.println("Delete failed! Please try again.");
            return 0;
        }
    }


    /**
     * Deletes appointments for a specified customer ID (Int).
     * @param customerID
     * @return
     * @throws SQLException
     */
    public static int deleteAppointmentFromCustomerID(int customerID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerID);
        try {
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (Exception e) {
            System.out.println("Delete failed! Please try again.");
            return 0;
        }
    }

    /**
     * Returns an ObversableList of all User IDs in the database
     * @return
     * @throws SQLException
     */
    public static ObservableList<Integer> getAllUserID () throws SQLException {
        ObservableList<Integer> IDs = FXCollections.observableArrayList();
        String sql = "SELECT User_ID FROM users";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            IDs.add(rs.getInt("User_ID"));
        }
        return IDs;
    }

    /**
     * Returns an ObversableList of all customer IDs in the database
     * @return
     * @throws SQLException
     */
    public static ObservableList<Integer> getAllCustID () throws SQLException {
        ObservableList<Integer> IDs = FXCollections.observableArrayList();
        String sql = "SELECT Customer_ID FROM customers";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            IDs.add(rs.getInt("Customer_ID"));
        }
        return IDs;
    }

    /**
     * Returns an ObversableList of all contact IDs in the database
     * @return
     * @throws SQLException
     */
    public static ObservableList<String> getAllContactID () throws SQLException {
        ObservableList<String> IDs = FXCollections.observableArrayList();
        String sql = "SELECT Contact_Name FROM contacts";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            IDs.add(rs.getString("Contact_Name"));
        }
        return IDs;
    }

    /**
     * Returns an String of all contact in the database based on ID
     * @return
     * @throws SQLException
     */
    public static String getContact (int id) throws SQLException {
        String sql = "SELECT Contact_Name FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("Contact_Name");
        }
        return "No Contact";
    }
}
