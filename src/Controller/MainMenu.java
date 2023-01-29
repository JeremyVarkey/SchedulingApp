package Controller;

import DAO.AppointmentsHelper;
import DAO.CustomerHelper;
import DAO.GeneralHelper;
import Model.Appointment;
import Model.Customer;
import Model.JDBC;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    Stage stage;
    Parent root;
    Scene scene;


    @FXML private RadioButton AllAppointmentsButton;
    @FXML private RadioButton MonthlyViewButton;
    @FXML private ToggleGroup TimeFrame;
    @FXML private RadioButton ViewCustomersButton;
    @FXML private RadioButton WeeklyViewButton;
    @FXML private TableView ApptTable;
    @FXML private Button Logout;
    @FXML private Button Reports;
    @FXML private Button DeleteAppt;
    @FXML private Button EditAppt;
    @FXML private Button AddAppt;
    @FXML private DatePicker CalendarSelector;
    @FXML private TableColumn<Appointment, Integer> ApptID;
    @FXML private TableColumn<Appointment, String> Title;
    @FXML private TableColumn<Appointment, String> Location;
    @FXML private TableColumn<Appointment, String> Contact;
    @FXML private TableColumn<Appointment, String> Type;
    @FXML private TableColumn<Appointment, Integer> CustID;
    @FXML private TableColumn<Appointment, Integer> UserID;
    @FXML private TableColumn<Appointment, LocalDateTime> StartTime;
    @FXML private TableColumn<Appointment, LocalDateTime> EndTime;

    public static Appointment apptToModify;
    public static boolean toModifyScreen;

    /**
     * Go to Customers Menu
     * @param event
     * @throws IOException
     */
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

    /**
     * Logout
     * @param event
     */
    @FXML
    void LogoutClick(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    /**
     * Go to selected Reports
     * @param event
     */
    @FXML
    void ReportsClick(ActionEvent event) {

    }

    /**
     * Delete selected Appt
     * @param event
     */
    @FXML
    void DeleteApptClick(ActionEvent event) {
        try {
            ObservableList<Appointment> c = ApptTable.getSelectionModel().getSelectedItems();
            apptToModify = c.get(0);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you wish to proceed deleting this appointment?");
            Optional<ButtonType> confirm = alert.showAndWait();
            if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                AppointmentsHelper.deleteAppointment(apptToModify.getAptID());
                GeneralHelper.createInformMessage("Delete successful!", "Delete!");

                root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
                stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
                scene = new Scene(root);
                stage.setTitle("Main Menu");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            GeneralHelper.createErrorMessage("Please select an appointment!", "Error!");
        }
    }

    /**
     * Edit Selected appt.
     * @param event
     * @throws IOException
     */
    @FXML
    void EditApptClick(ActionEvent event) throws IOException {
        try {
            ObservableList<Appointment> appt = ApptTable.getSelectionModel().getSelectedItems();
            apptToModify = appt.get(0);
            toModifyScreen = true;
            root = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
            stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
            scene = new Scene(root);
            stage.setTitle("Edit Appointment");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            GeneralHelper.createErrorMessage("Please select an appointment!", "Error!");
        }

    }

    /**
     * Go to add new appointment screen to add appointment.
     * @param event
     * @throws IOException
     */
    @FXML
    void AddApptClick(ActionEvent event) throws IOException {
        toModifyScreen = false;
        root = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Select date of appointments.
     * @param event
     */
    @FXML
    void CalendarSelect(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Appointment> appts = AppointmentsHelper.getAllAppointments();
            ApptTable.setItems(appts);
            ApptID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("aptID"));
            Title.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
            Location.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
            Contact.setCellValueFactory(new PropertyValueFactory<Appointment, String>("contact"));
            Type.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
            CustID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerID"));
            UserID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userID"));
            StartTime.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("start"));
            EndTime.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("end"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

