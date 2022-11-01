package Controller;

import DAO.GeneralHelper;
import DAO.LoginHelper;
import Model.JDBC;
import com.mysql.cj.log.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Login {

    Stage stage;
    Parent scene;

    @FXML private Button exitButton;
    @FXML private ComboBox<?> language;
    @FXML private Button loginButton;
    @FXML private TextField password;
    @FXML private TextField timezone;
    @FXML private TextField username;

    @FXML
    void ExitProgram(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    @FXML
    void LoginToProgram(ActionEvent event) throws IOException, SQLException {
        String user = username.getText();
        String pass = password.getText();

        if (LoginHelper.getUserPasswordMatch(user, pass)) {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            GeneralHelper.createErrorMessage("Incorrect Username or Password. Please try again.");
        }
    }

}
