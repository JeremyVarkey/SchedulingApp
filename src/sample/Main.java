package sample;
import Controller.Login;
import DAO.AppointmentsHelper;
import DAO.LoginHelper;
import Model.Appointment;
import Model.JDBC;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;

/**
 * Main Method to begin application.
 */
public class Main extends Application {

    /**
     * Begins application with the Login FXML
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();

    }


    /**
     * Begin application
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        //Locale.setDefault(new Locale("fr"));
        JDBC.makeConnection();
        launch(args);
        JDBC.closeConnection();


    }
}
