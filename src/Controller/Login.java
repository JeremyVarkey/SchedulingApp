package Controller;

import DAO.AppointmentsHelper;
import DAO.GeneralHelper;
import DAO.LoginHelper;
import Model.JDBC;
import com.mysql.cj.log.Log;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.io.*;
import Model.Appointment;

/**
 * Login screen controller.
 */
public class Login implements Initializable {

    Stage stage;
    Parent scene;
    public static String loggedUser;

    @FXML private Button exitButton;
    @FXML private TextField language;
    @FXML private Button loginButton;
    @FXML private TextField password;
    @FXML private TextField timezone;
    @FXML private TextField username;
    @FXML private Text LanguageText;
    @FXML private Text LocationText;
    @FXML private Text PasswordText;
    @FXML private Text UsernameText;

    /**
     * Exit program when user hits the Exit button
     * @param event
     */
    @FXML
    void ExitProgram(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    /**
     * Action upon user hitting Login button. If an approved user, move to Main Screen. Also logs user logins attempts.
     * Also notifies user if there is an appointment within 15 minutes.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void LoginToProgram(ActionEvent event) throws IOException, SQLException {
        FileWriter file = new FileWriter("login_log.txt", true);
        PrintWriter pw = new PrintWriter(file);

        String user = username.getText();
        String pass = password.getText();
        ResourceBundle resource = ResourceBundle.getBundle("Controller/Nat", Locale.getDefault());

        if (LoginHelper.getUserPasswordMatch(user, pass)) {
            loggedUser = user;
            pw.println(user + " successfully logged in at " + ZonedDateTime.now(ZoneId.of("UTC")));
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Main Menu");
            stage.show();

            ObservableList<Appointment> appointments = AppointmentsHelper.getAllAppointments();
            Boolean aptWithin15Min = false;
            LocalDateTime nowPlus15 = LocalDateTime.now().plusMinutes(15);
            LocalDateTime nowMinus15 = LocalDateTime.now().minusMinutes(15);
            for (Appointment apt:appointments) {
                LocalDateTime aptLocalStart = apt.getStart();
                if ((aptLocalStart.isBefore(nowPlus15) || aptLocalStart.isEqual(nowPlus15)) &&
                        (aptLocalStart.isAfter(nowMinus15) || aptLocalStart.isEqual(nowMinus15)) &&
                        (apt.getUserID() == LoginHelper.getUserID(user))) {
                    aptWithin15Min = true;
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Appointment ID #" + apt.getAptID()
                            + " is within 15 minutes, at " + apt.getStart().format(GeneralHelper.format()));
                    alert.showAndWait();
                }
            }
            if (aptWithin15Min == false) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No appointments within 15 minutes.");
                alert.showAndWait();
            }

        } else {
            pw.println(user + " unsuccessfully logged in at " + ZonedDateTime.now(ZoneId.of("UTC")));
            GeneralHelper.createErrorMessage(resource.getString("loginError"), resource.getString("error"));
        }
        pw.close();
    }

    /**
     * Initialize the login screen so that words are based off of user location
     * @param url
     * @param resource
     */
    @Override
    public void initialize(URL url, ResourceBundle resource) {
        Locale locale = Locale.getDefault();
        Locale.setDefault(locale);
        ZoneId location = ZoneId.systemDefault();

        resource = ResourceBundle.getBundle("Controller/Nat", Locale.getDefault());

        exitButton.setText(resource.getString("exit"));
        loginButton.setText(resource.getString("login"));
        UsernameText.setText(resource.getString("username"));
        PasswordText.setText(resource.getString("password"));
        LanguageText.setText(resource.getString("language"));
        LocationText.setText(resource.getString("location"));
        language.setText(resource.getString("languageSpoken"));
        timezone.setText(location.toString());
    }

}
