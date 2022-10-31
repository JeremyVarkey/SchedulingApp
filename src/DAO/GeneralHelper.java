package DAO;
import javafx.scene.control.Alert;
import java.sql.*;
import java.time.*;

public class GeneralHelper {
    /**
     * Helper function to create adjustable error message.
     * @param message String of desired error message
     */
    public static void createErrorMessage(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Error!");
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }

}
