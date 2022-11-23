package Controller;

import DAO.CustomerHelper;
import Model.Customer;
import Model.JDBC;
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
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CustomerMenu implements Initializable {

    Stage stage;
    Scene scene;
    Parent root;

    @FXML private Button AddCustomerButton;
    @FXML private TableColumn<Customer, String> Address;
    @FXML private RadioButton AllAppointmentsButton;
    @FXML private TableColumn<Customer, String> CreateBy;
    @FXML private TableColumn<Customer, Integer> CustomerID;
    @FXML private TableColumn<Customer, String> CustomerName;
    @FXML private Button DeleteCustomerButton;
    @FXML private Button EditCustomerButton;
    @FXML private TableColumn<Customer, String> LastUpdate;
    @FXML private Button LogoutButton;
    @FXML private RadioButton MonthlyViewButton;
    @FXML private TableColumn<Customer, String> Phone;
    @FXML private TableColumn<Customer, String> PostalCode;
    @FXML private TableColumn<Customer, String> State;
    @FXML private ToggleGroup TimeFrame;
    @FXML private RadioButton ViewCustomersButton;
    @FXML private RadioButton WeeklyViewButton;
    @FXML private TableView<Customer> CustomerTable;

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
        JDBC.closeConnection();
        System.exit(0);
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
            CustomerTable.setItems(allCustomers);
            CustomerID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
            CustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
            Address.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
            PostalCode.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
            Phone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
            CreateBy.setCellValueFactory(new PropertyValueFactory<Customer, String>("createdBy"));
            LastUpdate.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastUpdate"));
            State.setCellValueFactory(new PropertyValueFactory<Customer, String>("divisionName"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
