package Controller;

import DAO.AppointmentsHelper;
import Model.Appointment;
import Model.Countries;
import Model.JDBC;
import Model.Types;
import com.mysql.cj.result.ValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Controller for Reports FXML. Implements required reports of number of Types of Appointments by Month, Schedule by Contact, and my selected report
 * which is the number of types of appointments by second division level.
 */
public class Reports implements Initializable {
    public TableView<Appointment> ApptTable;
    public TableColumn<Appointment, Integer> ApptID;
    public TableColumn<Appointment, String> Title;
    public TableColumn<Appointment, String> Description;
    public TableColumn<Appointment, String> Type;
    public TableColumn<Appointment, LocalDateTime> StartTime;
    public TableColumn<Appointment, LocalDateTime> EndTime;
    public TableColumn<Appointment, Integer> CustID;
    public Button MainMenu;
    public Button Logout;
    public TableView<Types> TypeTable;
    public TableView<Countries> CountryTable;
    public ComboBox<String> Contacts;
    public TableColumn<Types, String> ApptID1;
    public TableColumn<Types, String> ApptID11;
    public TableColumn<Types, Integer> ApptID12;
    public TableColumn<Countries, String> Country;
    public TableColumn<Countries, String> Division;
    public TableColumn<Countries, String > CountryType;
    public TableColumn<Countries, Integer> CountryAppts;


    Stage stage;
    Parent root;
    Scene scene;

    /**
     * Logout of program.
     * @param event
     */
    @FXML
    void LogoutClick(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    /**
     * Go to Main Menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void toMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Select contact to see scheduling for.
     * @param event
     * @throws SQLException
     */
    @FXML
    void selectContact(ActionEvent event) throws SQLException {
        String contact = Contacts.getValue();
        int id = AppointmentsHelper.getContactID(contact);
        ObservableList<Appointment> appts = AppointmentsHelper.getAllAppointmentsByContactID(id);
        ApptTable.setItems(appts);
        ApptID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("aptID"));
        Title.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        Description.setCellValueFactory(new PropertyValueFactory<Appointment,String>("description"));
        Type.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        StartTime.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("start"));
        EndTime.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("end"));
        CustID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerID"));
        ApptTable.getSortOrder().add(StartTime);
        ApptTable.sort();
    }


    /**
     * Initialize screen. Automatically fills in Appt Type and Appt Locations report.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Contacts.setItems(AppointmentsHelper.getAllContacts());

            ObservableList<Types> allTypes = AppointmentsHelper.getTypebyMonth();
            TypeTable.setItems(allTypes);
            ApptID1.setCellValueFactory(new PropertyValueFactory<Types, String>("type"));
            ApptID11.setCellValueFactory(new PropertyValueFactory<Types, String>("month"));
            ApptID12.setCellValueFactory(new PropertyValueFactory<Types, Integer>("numberOfAppts"));

            ObservableList<Countries> countries = AppointmentsHelper.getTypebyCountry();
            CountryTable.setItems(countries);
            Country.setCellValueFactory(new PropertyValueFactory<Countries, String>("country"));
            Division.setCellValueFactory(new PropertyValueFactory<Countries, String>("division"));
            CountryType.setCellValueFactory(new PropertyValueFactory<Countries, String>("type"));
            CountryAppts.setCellValueFactory(new PropertyValueFactory<Countries, Integer>("numberOfAppts"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
