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
import java.util.LinkedList;
import java.util.List;
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
    @FXML private ChoiceBox<String> StateSelection;

    @FXML
    void CancelButtonClick(ActionEvent event) {

    }

    @FXML
    void SaveButtonClick(ActionEvent event) {

    }

    /**
     * Initialize method. Sets choicebox values based on user input.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CountrySelection.getItems().addAll(CustomerHelper.getCountries());
            CountrySelection.valueProperty().addListener((obs, oldValue, newvalue) -> {
                if (newvalue == null) {
                    StateSelection.getItems().clear();
                    StateSelection.setDisable(true);
                } else {
                    try {
                        LinkedList<String> states = setSecondDivision();
                        StateSelection.getItems().setAll(states);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter method to find Country ID of Country Choice Box, and find corresponding list of Second Division items.
     * @return LinkedList of Second Division items
     * @throws SQLException
     */
    public LinkedList<String> setSecondDivision() throws SQLException {
        String country = CountrySelection.getValue();
        int Cid = CustomerHelper.getCountryID(country);
        return CustomerHelper.getSecondDivision(Cid);
    }
}
