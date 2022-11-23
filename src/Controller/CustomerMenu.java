package Controller;

import DAO.CustomerHelper;
import Model.Customer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerMenu implements Initializable {

    Stage stage;
    Scene scene;
    Parent root;

    @FXML private Button AddCustomerButton;
    @FXML private TableColumn Address;
    @FXML private RadioButton AllAppointmentsButton;
    @FXML private TableColumn CreateBy;
    @FXML private TableColumn CustomerID;
    @FXML private TableColumn CustomerName;
    @FXML private Button DeleteCustomerButton;
    @FXML private Button EditCustomerButton;
    @FXML private TableColumn LastUpdate;
    @FXML private Button LogoutButton;
    @FXML private RadioButton MonthlyViewButton;
    @FXML private TableColumn Phone;
    @FXML private TableColumn PostalCode;
    @FXML private TableColumn State;
    @FXML private ToggleGroup TimeFrame;
    @FXML private RadioButton ViewCustomersButton;
    @FXML private RadioButton WeeklyViewButton;
    @FXML private TableView CustomerTable;

    @FXML
    void AddCustomerButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void DeleteCustomerButtonClick(ActionEvent event) {

    }

    @FXML
    void EditCustomerButtonClick(ActionEvent event) throws IOException {

    }

    @FXML
    void LogoutButtonClick(ActionEvent event) {

    }

    @FXML
    void toMainScreenAllAppointments(ActionEvent event) {

    }

    @FXML
    void toMainScreenMonthlyView(ActionEvent event) {

    }

    @FXML
    void toMainScreenWeeklyView(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Customer> allCustomers = CustomerHelper.getAllCustomers();
            CustomerID.setCellValueFactory(new PropertyValueFactory<>("id"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
