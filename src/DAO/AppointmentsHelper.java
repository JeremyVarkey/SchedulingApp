package DAO;

import Model.JDBC;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
     * Returns an ObversableList of all contact Names in the database
     * @return
     * @throws SQLException
     */
    public static ObservableList<String> getAllContacts () throws SQLException {
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

    public static boolean isAppointmentOverlapEdit (LocalDateTime apptStartTime, LocalDateTime apptEndTime, int custID, int apptID) throws SQLException {
        String sql = "SELECT Start, End FROM appointments WHERE Customer_ID = ? AND NOT Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, custID);
        ps.setInt(2, apptID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            if ((apptStartTime.isAfter(start) && apptStartTime.isBefore(end)) ||
                    (apptStartTime.isBefore(start) && apptEndTime.isAfter(end)) ||
                    (apptEndTime.isAfter(start) && apptEndTime.isBefore(end)) ||
                    (apptStartTime.isEqual(start) || apptEndTime.isEqual(end))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAppointmentOverlapAdd (LocalDateTime apptStartTime, LocalDateTime apptEndTime, int custID) throws SQLException {
        String sql = "SELECT Start, End FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, custID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            if ((apptStartTime.isAfter(start) && apptStartTime.isBefore(end)) ||
                    (apptStartTime.isBefore(start) && apptEndTime.isAfter(end)) ||
                    (apptEndTime.isAfter(start) && apptEndTime.isBefore(end)) ||
                    ((apptStartTime.isEqual(start) || apptEndTime.isEqual(end)))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Editing existing appointments.
     * @param aptID
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param user
     * @param custID
     * @param userID
     * @param contactID
     * @return
     * @throws SQLException
     */
    public static int editAppointment (int aptID, String title, String description, String location, String type,
                                       LocalDateTime start, LocalDateTime end, String user,
                                       int custID, int userID, int contactID) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?," +
                " Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ?" +
                " WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start.format(GeneralHelper.format())));
        ps.setTimestamp(6, Timestamp.valueOf(end.format(GeneralHelper.format())));
        ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()).format(GeneralHelper.format())));
        ps.setString(8, user);
        ps.setInt(9, custID);
        ps.setInt(10, userID);
        ps.setInt(11, contactID);
        ps.setInt(12, aptID);
        try {
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLIntegrityConstraintViolationException | IllegalStateException s) {
            GeneralHelper.createErrorMessage("Edit appt failed!", "Error!");
            return -1;
        }
    }

    /**
     * Getting contact ID (INT) from contact name (String.)
     * @param contact
     * @return
     * @throws SQLException
     */
    public static int getContactID (String contact) throws SQLException {
        String sql = "SELECT Contact_ID FROM contacts WHERE Contact_Name = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, contact);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("Contact_ID");
        } else {
            return -1;
        }
    }

    /**
     * Adding new appointment to database.
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param user
     * @param custID
     * @param userID
     * @param contactID
     * @return
     * @throws SQLException
     */
    public static int addAppointment (String title, String description, String location, String type,
                                       LocalDateTime start, LocalDateTime end, String user,
                                       int custID, int userID, int contactID) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By," +
                " Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start.format(GeneralHelper.format())));
        ps.setTimestamp(6, Timestamp.valueOf(end.format(GeneralHelper.format())));
        ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()).format(GeneralHelper.format())));
        ps.setString(8, user);
        ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()).format(GeneralHelper.format())));
        ps.setString(10, user);
        ps.setInt(11, custID);
        ps.setInt(12, userID);
        ps.setInt(13, contactID);

        try {
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLIntegrityConstraintViolationException | IllegalStateException s) {
            GeneralHelper.createErrorMessage("Add appt failed!", "Error!");
            return -1;
        }
    }

}
