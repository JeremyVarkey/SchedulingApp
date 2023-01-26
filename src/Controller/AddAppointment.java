package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddAppointment {
    Stage stage;
    Scene scene;
    Parent root;
    Integer appointmentID;
    String title;
    String description;
    String location;
    String type;
    Integer contactID;
    Integer customerID;
    Integer userID;
    LocalDate startDate;
    LocalDate endDate;
    LocalTime startHour;
    LocalTime startMinute;
    LocalTime startTime;
    LocalTime endTime;
    LocalTime endHour;
    LocalTime endMinute;




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

}
