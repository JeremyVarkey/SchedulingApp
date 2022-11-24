package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {
    Stage stage;
    Parent root;
    Scene scene;


    @FXML private RadioButton AllAppointmentsButton;
    @FXML private RadioButton MonthlyViewButton;
    @FXML private ToggleGroup TimeFrame;
    @FXML private RadioButton ViewCustomersButton;
    @FXML private RadioButton WeeklyViewButton;

    @FXML
    void ViewCustomersButtonClick(ActionEvent event) throws IOException {
        if (ViewCustomersButton.isSelected()) {
            root = FXMLLoader.load(getClass().getResource("/View/CustomerMenu.fxml"));
            stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
            scene = new Scene(root);
            stage.setTitle("Customer Menu");
            stage.setScene(scene);
            stage.show();
        }
    }

}

