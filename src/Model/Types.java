package Model;
import java.time.Month;

public class Types {
    private String type;
    private String month;
    private int numberOfAppts;

    public Types (String type, int month, int numberOfAppts) {
        this.type = type;
        this.month = Month.of(month).name();
        this.numberOfAppts = numberOfAppts;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getNumberOfAppts() {
        return numberOfAppts;
    }

    public void setNumberOfAppts(int numberOfAppts) {
        this.numberOfAppts = numberOfAppts;
    }
}
