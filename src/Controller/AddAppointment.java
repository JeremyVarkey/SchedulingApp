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
    public ChoiceBox<Integer> ContactID;
    public ChoiceBox<Integer> CustomerID;
    public ChoiceBox<Integer> UserID;
    public DatePicker StartDate;
    public DatePicker EndDate;
    public Spinner<LocalTime> StartTime;
    public Spinner<LocalTime> EndTime;
    public Button Save;
    public Button Cancel;

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
                ContactID.getItems().addAll(AppointmentsHelper.getAllContactID());

                DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");

                SpinnerValueFactory<LocalTime> startValueFactory = new SpinnerValueFactory<LocalTime>() {
                    {
                        setConverter(new LocalTimeStringConverter(format,null));
                    }

                    @Override
                    public void decrement(int i) {
                        if(this.getValue() == null) {
                            this.setValue(LocalTime.now());
                        }else {
                            LocalTime now = getValue();
                            setValue(now.minusMinutes(1));
                        }
                    }

                    @Override
                    public void increment(int i) {
                        if(this.getValue() == null) {
                            this.setValue(LocalTime.now());
                        } else {
                            LocalTime now = getValue();
                            setValue(now.plusMinutes(1));
                        }
                    }
                };
                StartTime.setValueFactory(startValueFactory);

                SpinnerValueFactory<LocalTime> endValueFactory = new SpinnerValueFactory<LocalTime>() {
                    {
                        setConverter(new LocalTimeStringConverter(format,null));
                    }

                    @Override
                    public void decrement(int i) {
                        if(this.getValue() == null) {
                            this.setValue(LocalTime.now());
                        }else {
                            LocalTime now = getValue();
                            setValue(now.minusMinutes(1));
                        }
                    }

                    @Override
                    public void increment(int i) {
                        if(this.getValue() == null) {
                            this.setValue(LocalTime.now());
                        } else {
                            LocalTime now = getValue();
                            setValue(now.plusMinutes(1));
                        }
                    }
                };
                EndTime.setValueFactory(endValueFactory);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
