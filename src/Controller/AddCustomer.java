package Controller;

import DAO.CustomerHelper;
import DAO.GeneralHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomer implements Initializable {

    Stage stage;
    Scene scene;
    Parent root;

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
    void CancelButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/CustomerMenu.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Save a new Customer or edit an existing customer. Error check that all inputs are filled.
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void SaveButtonClick(ActionEvent event) throws SQLException, IOException {
        String name = CustomerName.getText();
        String address = Address.getText();
        String postal = PostalCode.getText();
        String phone = PhoneNumber.getText();
        String user = Login.loggedUser;
        int divisionId = CustomerHelper.getSecondDivisionID(StateSelection.getValue());

        if (name.isEmpty() || address.isEmpty() || postal.isEmpty() || phone.isEmpty() || user.isEmpty() || divisionId == -1) {
            GeneralHelper.createErrorMessage("Please fill in all inputs!", "Error!");
        } else {
            if (!CustomerMenu.toModifyScreen) {
                CustomerHelper.insertNewCustomer(name, address, postal, phone, user, divisionId);
                GeneralHelper.createInformMessage("Successfully added new Customer!", "Success!");
            } else {
                int id = Integer.parseInt(CustomerID.getText());
                CustomerHelper.editCustomer(name, address, postal, phone, user, divisionId, id);
                GeneralHelper.createInformMessage("Successfully edited Customer!", "Success!");
            }

            root = FXMLLoader.load(getClass().getResource("/View/CustomerMenu.fxml"));
            stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Initialize method. Sets choicebox values based on user input. Prepopulates the Customer Info if a customer has been selected.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!CustomerMenu.toModifyScreen) {
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
                            StateSelection.setValue("");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (CustomerMenu.customerToModify != null) {
            CustomerID.setText(((Integer)CustomerMenu.customerToModify.getId()).toString());
            CustomerName.setText(CustomerMenu.customerToModify.getCustomerName());
            Address.setText(CustomerMenu.customerToModify.getAddress());
            PostalCode.setText(CustomerMenu.customerToModify.getPostalCode());
            PhoneNumber.setText(CustomerMenu.customerToModify.getPhone());

            try {
                CountrySelection.getItems().addAll(CustomerHelper.getCountries());
                String CustCountry = CustomerHelper.getCountry(
                        CustomerHelper.getCountryIDfromDivisionID(
                                CustomerMenu.customerToModify.getDivisionID()));
                CountrySelection.setValue(CustCountry);

                LinkedList<String> states = setSecondDivision();
                StateSelection.getItems().setAll(states);
                StateSelection.setValue(CustomerMenu.customerToModify.getDivisionName());

                /**
                 * Lambda for creating listener on Country Selection to change the second division list, and initially set as Empty
                 * when a new country selection is made.
                 */
                CountrySelection.valueProperty().addListener((obs, oldValue, newvalue) -> {
                    try {
                        LinkedList<String> divisions = setSecondDivision();
                        StateSelection.getItems().setAll(divisions);
                        StateSelection.setValue("");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
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
