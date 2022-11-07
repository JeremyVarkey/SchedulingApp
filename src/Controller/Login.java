package Controller;

import DAO.GeneralHelper;
import DAO.LoginHelper;
import Model.JDBC;
import com.mysql.cj.log.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.io.*;


/**
 * Login screen controller.
 */
public class Login implements Initializable {

    Stage stage;
    Parent scene;

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
            pw.println(user + " successfully logged in at " + ZonedDateTime.now(ZoneId.of("UTC")));
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Main Menu");
            stage.show();
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
