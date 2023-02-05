package Controller;


import Controller.MainMenu;
import DAO.AppointmentsHelper;
import DAO.CustomerHelper;
import DAO.GeneralHelper;
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
import java.time.*;
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
    void SaveClick(ActionEvent event) throws Throwable {
        String title = Title.getText();
        String description = Description.getText();
        String location = Location.getText();
        String type = Type.getText();
        int userid = UserID.getValue();
        int custid = CustomerID.getValue();
        String contact = ContactID.getValue();
        LocalDate sdate = StartDate.getValue();
        LocalDate edate = EndDate.getValue();
        ZonedDateTime zdtLocalStart = null;
        ZonedDateTime zdtLocalEnd = null;

        try {
            //converting single digits in Start Time to double digits
            if (Integer.parseInt(StartHour.getText()) < 10) {
                StartHour.setText("0" + Integer.parseInt(StartHour.getText()));
            }
            if (Integer.parseInt(StartMinute.getText()) < 10) {
                StartMinute.setText("0" + Integer.parseInt(StartMinute.getText()));
            }
            LocalTime stime = LocalTime.parse(StartHour.getText() + ":" + StartMinute.getText() + ":00");
            LocalDateTime sdatetime = LocalDateTime.of(sdate,stime);
            zdtLocalStart = sdatetime.atZone(ZoneId.systemDefault());

            //converting single digits in End Time to double digits
            if (Integer.parseInt(EndHour.getText()) < 10) {
                EndHour.setText("0" + Integer.parseInt(EndHour.getText()));
            }
            if (Integer.parseInt(EndMinute.getText()) < 10) {
                EndMinute.setText("0" + Integer.parseInt(EndMinute.getText()));
            }
            LocalTime etime = LocalTime.parse(EndHour.getText() + ":" + EndMinute.getText() + ":00");
            LocalDateTime edatetime = LocalDateTime.of(edate, etime);
            zdtLocalEnd = edatetime.atZone(ZoneId.systemDefault());
        } catch (Exception e) {
            GeneralHelper.createErrorMessage("Please input number in military time for start and end times!", "Time Error!");
        }

        //Checking appointment time against each other, so start is before end
        System.out.println(zdtLocalStart.toString());
        System.out.println(zdtLocalEnd.toString());



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
            try {
                UserID.getItems().addAll(AppointmentsHelper.getAllUserID());
                CustomerID.getItems().addAll(AppointmentsHelper.getAllCustID());
                ContactID.getItems().addAll(AppointmentsHelper.getAllContacts());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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
            StartHour.setText((MainMenu.apptToModify.getStart().toLocalTime().getHour()<10?"0":"") +
                    MainMenu.apptToModify.getStart().toLocalTime().getHour());
            StartMinute.setText((MainMenu.apptToModify.getStart().toLocalTime().getMinute()<10?"0":"") +
                    MainMenu.apptToModify.getStart().toLocalTime().getMinute());
            EndHour.setText((MainMenu.apptToModify.getEnd().toLocalTime().getHour()<10?"0":"") +
                    MainMenu.apptToModify.getEnd().toLocalTime().getHour());
            EndMinute.setText((MainMenu.apptToModify.getEnd().toLocalTime().getMinute()<10?"0":"") +
                    MainMenu.apptToModify.getEnd().toLocalTime().getMinute());
        }
    }

}
