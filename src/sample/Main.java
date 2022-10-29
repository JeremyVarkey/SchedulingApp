package sample;
import Model.*;
import Model.JDBC;
import com.mysql.cj.log.Log;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }


    public static void main(String[] args) throws SQLException {
        JDBC.makeConnection();
        LoginHelper.insertUser("jeremy", "Sonicteam4",
                "jeremy", "jeremy");

        launch(args);
        JDBC.closeConnection();
    }
}
