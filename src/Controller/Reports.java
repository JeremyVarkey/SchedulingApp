package Controller;

import DAO.AppointmentsHelper;
import Model.Appointment;
import Model.JDBC;
import Model.Types;
import com.mysql.cj.result.ValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
    public TableView CountryTable;
    public ComboBox<String> Contacts;
    public TableColumn<Types, String> ApptID1;
    public TableColumn<Types, String> ApptID11;
    public TableColumn<Types, Integer> ApptID12;


    Stage stage;
    Parent root;
    Scene scene;

    @FXML
    void LogoutClick(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    @FXML
    void toMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Contacts.setItems(AppointmentsHelper.getAllContacts());

            ObservableList<Types> allTypes = AppointmentsHelper.getTypebyMonth();
            TypeTable.setItems(allTypes);
            ApptID1.setCellValueFactory(new PropertyValueFactory<Types, String>("type"));
            ApptID11.setCellValueFactory(new PropertyValueFactory<Types, String>("month"));
            ApptID12.setCellValueFactory(new PropertyValueFactory<Types, Integer>("numberOfAppts"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
