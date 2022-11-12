package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
public class AddCustomer {

    @FXML
    private TextField Address;

    @FXML
    private Button Cancel;

    @FXML
    private ChoiceBox<?> CountrySelection;

    @FXML
    private TextField CustomerID;

    @FXML
    private TextField CustomerName;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private TextField PostalCode;

    @FXML
    private Button Save;

    @FXML
    private ChoiceBox<?> StateSelection;

    @FXML
    void CancelButtonClick(ActionEvent event) {

    }

    @FXML
    void SaveButtonClick(ActionEvent event) {

    }
}
