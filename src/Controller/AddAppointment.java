package Controller;


import Controller.MainMenu;
import DAO.AppointmentsHelper;
import DAO.CustomerHelper;
import Model.Appointment;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {

    public TextField AppointmentID;
    public TextField Title;
    public TextField Description;
    public TextField Location;
    public TextField Type;
    public ChoiceBox<String> ContactID;
    public ChoiceBox<Integer> CustomerID;
    public ChoiceBox<Integer> UserID;
    public DatePicker StartDate;
    public DatePicker EndDate;
    public Button Save;
    public Button Cancel;
    public TextField StartHour;
    public TextField StartMinute;
    public TextField EndHour;
    public TextField EndMinute;

    Stage stage;
    Scene scene;
    Parent root;





    /**
     * Cancel Add Customer to go back to Main Screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void CancelClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        stage.setTitle("Main Menu");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void SaveClick(ActionEvent event) {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!MainMenu.toModifyScreen) {
            try {
                UserID.getItems().addAll(AppointmentsHelper.getAllUserID());
                CustomerID.getItems().addAll(AppointmentsHelper.getAllCustID());
                ContactID.getItems().addAll(AppointmentsHelper.getAllContacts());

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (MainMenu.apptToModify != null) {
            AppointmentID.setText(((Integer)MainMenu.apptToModify.getAptID()).toString());
            Title.setText(MainMenu.apptToModify.getTitle());
            Description.setText(MainMenu.apptToModify.getDescription());
            Type.setText(MainMenu.apptToModify.getType());
            UserID.setValue(MainMenu.apptToModify.getUserID());
            CustomerID.setValue(MainMenu.apptToModify.getCustomerID());
            ContactID.setValue(MainMenu.apptToModify.getContact());
            Location.setText(MainMenu.apptToModify.getLocation());
            StartDate.setValue(MainMenu.apptToModify.getStart().toLocalDate());
            EndDate.setValue(MainMenu.apptToModify.getEnd().toLocalDate());
            StartHour.setText(((Integer)MainMenu.apptToModify.getStart().toLocalTime().getHour()).toString());
            StartMinute.setText(((Integer)MainMenu.apptToModify.getStart().toLocalTime().getMinute()).toString());
            EndHour.setText(((Integer)MainMenu.apptToModify.getEnd().toLocalTime().getHour()).toString());
            EndMinute.setText(((Integer)MainMenu.apptToModify.getEnd().toLocalTime().getMinute()).toString());
        }
    }

}
