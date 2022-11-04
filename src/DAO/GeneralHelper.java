package DAO;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class GeneralHelper {

    /**
     * Helper function to create adjustable error message.
     * @param message String of desired error message
     */
    public static void createErrorMessage(String message, String title) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(title);
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }

    /**
     * returns standard format for inputting into DB
     * @return DateTimeFormatter
     */
    public static DateTimeFormatter format(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * Converts LocalDateTime object from Local time to UTC time
     * @param ldt LocalDateTime
     * @return LocalDateTime UTC
     */
    public static LocalDateTime LocaltoUTC(LocalDateTime ldt) {
        ZonedDateTime zdtLocal = ldt.atZone(ZoneId.systemDefault());
        ZonedDateTime zdtUTC = zdtLocal.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime ldtUTC = zdtUTC.toLocalDateTime();
        return ldtUTC;
    }

    /**
     * Converts LocalDateTime object from Eastern time to UTC time
     * @param ldt LocalDateTime EST
     * @return LocalDateTime UTC
     */
    public static LocalDateTime ESTtoUTC(LocalDateTime ldt) {
        System.out.println("Local Date Time EST" + ldt.toString());
        ZonedDateTime zdtLocal = ldt.atZone(ZoneId.of("US/Eastern"));
        System.out.println("Zoned Date Time EST" + zdtLocal.toString());
        ZonedDateTime zdtUTC = zdtLocal.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("Zoned Date Time UTC" + zdtUTC.toString());
        LocalDateTime ldtUTC = zdtUTC.toLocalDateTime();
        System.out.println("Local Date Time UTC" + ldtUTC.toString());
        System.out.println(ldtUTC.format(format()));
        return ldtUTC;
    }


    /**
     * Converts LocalDateTime object from Local time to EST time
     * @param ldt LocalDateTime
     * @return LocalDateTime EST
     */
    public static LocalDateTime LocaltoEST(LocalDateTime ldt) {
        ZonedDateTime zdtLocal = ldt.atZone(ZoneId.systemDefault());
        ZonedDateTime zdtEST = zdtLocal.withZoneSameInstant(ZoneId.of("US/Eastern"));
        LocalDateTime ldtEST = zdtEST.toLocalDateTime();
        return ldtEST;
    }
    /**
     * Converts LocalDateTime object from UTC to Eastern time
     * @param ldt LocalDateTime EST
     * @return LocalDateTime UTC
     */
    public static LocalDateTime UTCtoEST(LocalDateTime ldt) {
        ZonedDateTime zdtUTC = ldt.atZone(ZoneId.of("UTC"));
        ZonedDateTime zdtEST = zdtUTC.withZoneSameInstant(ZoneId.of("US/Eastern"));
        LocalDateTime ldtEST = zdtEST.toLocalDateTime();
        System.out.println(ldtEST.format(format()));
        return ldtEST;
    }
}
