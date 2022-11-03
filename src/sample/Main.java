package sample;
import Controller.Login;
import DAO.LoginHelper;
import Model.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();

    }


    public static void main(String[] args) throws SQLException {
        Locale.setDefault(new Locale("fr"));
        JDBC.makeConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
