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
                    rs.getInt(14)
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


}
