package Model;
import java.time.Month;

/**
 * Object for Reports page, for the report of Type of Appointments by Month.
 */
public class Types {
    private String type;
    private String month;
    private int numberOfAppts;

    /**
     * Constructor for Types object.
     * @param type
     * @param month
     * @param numberOfAppts
     */
    public Types (String type, int month, int numberOfAppts) {
        this.type = type;
        this.month = Month.of(month).name();
        this.numberOfAppts = numberOfAppts;
    }


    /**
     * Gets type name.
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type name.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets Month for type appointment.
     * @return
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets Month for type appointment.
     * @param month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * Gets number of appointments by Type and Month.
     * @return
     */
    public int getNumberOfAppts() {
        return numberOfAppts;
    }

    /**
     * Sets number of appointments by Type and Month.
     * @param numberOfAppts
     */
    public void setNumberOfAppts(int numberOfAppts) {
        this.numberOfAppts = numberOfAppts;
    }
}
