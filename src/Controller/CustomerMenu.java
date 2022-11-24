package Controller;

import DAO.AppointmentsHelper;
import DAO.CustomerHelper;
import DAO.GeneralHelper;
import Model.Appointment;
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
import java.util.Optional;
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

    public static Customer customerToModify;
    public static boolean toModifyScreen;


    @FXML
    void AddCustomerButtonClick(ActionEvent event) throws IOException {
        toModifyScreen = false;
        root = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void DeleteCustomerButtonClick(ActionEvent event) throws SQLException {
        try {
            ObservableList<Customer> c = CustomerTable.getSelectionModel().getSelectedItems();
            customerToModify = c.get(0);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Deleting a Customer deletes all of their appointments as well. Are you sure you wish to proceed?");
            Optional<ButtonType> confirm = alert.showAndWait();
            if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                AppointmentsHelper.deleteAppointmentFromCustomerID(customerToModify.getId());
                CustomerHelper.deleteCustomerFromCustomerID(customerToModify.getId());
                GeneralHelper.createInformMessage("Delete successful!", "Delete!");

                root = FXMLLoader.load(getClass().getResource("/View/CustomerMenu.fxml"));
                stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
                scene = new Scene(root);
                stage.setTitle("Customer Menu");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            GeneralHelper.createErrorMessage("Please select a customer!", "Error!");
        }
    }

    @FXML
    void EditCustomerButtonClick(ActionEvent event) throws IOException {
        try {
            ObservableList<Customer> c = CustomerTable.getSelectionModel().getSelectedItems();
            customerToModify = c.get(0);
            toModifyScreen = true;
            root = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
            stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
            stage.setTitle("Edit Customer");
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            GeneralHelper.createErrorMessage("Please select a customer!", "Error!");
        }
    }

    @FXML
    void LogoutButtonClick(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    @FXML
    void toMainScreenAllAppointments(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
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
