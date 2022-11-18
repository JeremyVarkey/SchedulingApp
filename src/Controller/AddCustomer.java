package Controller;

import DAO.CustomerHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomer implements Initializable {

    @FXML private TextField Address;
    @FXML private Button Cancel;
    @FXML private ChoiceBox<String> CountrySelection;
    @FXML private TextField CustomerID;
    @FXML private TextField CustomerName;
    @FXML private TextField PhoneNumber;
    @FXML private TextField PostalCode;
    @FXML private Button Save;
    @FXML private ChoiceBox<?> StateSelection;

    @FXML
    void CancelButtonClick(ActionEvent event) {

    }

    @FXML
    void SaveButtonClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CountrySelection.getItems().addAll(CustomerHelper.getCountries());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSecondDivision() throws SQLException {
        String country = CountrySelection.getValue();
        System.out.println(CustomerHelper.getCountryID(country));
    }
}
