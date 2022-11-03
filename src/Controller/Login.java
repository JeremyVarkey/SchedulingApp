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
import java.util.Locale;
import java.util.ResourceBundle;

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

    @FXML
    void ExitProgram(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    @FXML
    void LoginToProgram(ActionEvent event) throws IOException, SQLException {
        String user = username.getText();
        String pass = password.getText();
        ResourceBundle resource = ResourceBundle.getBundle("Controller/Nat", Locale.getDefault());

        if (LoginHelper.getUserPasswordMatch(user, pass)) {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Main Menu");
            stage.show();
        } else {
            GeneralHelper.createErrorMessage(resource.getString("loginError"), resource.getString("error"));
        }
    }

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
