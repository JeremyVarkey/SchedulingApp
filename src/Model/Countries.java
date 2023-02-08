package Model;

public class Countries {
    private String country;
    private String type;
    private int numberOfAppts;

    public Countries (String country, String type, int numberOfAppts) {
        this.country = country;
        this.type = type;
        this.numberOfAppts = numberOfAppts;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumberOfAppts() {
        return numberOfAppts;
    }

    public void setNumberOfAppts(int numberOfAppts) {
        this.numberOfAppts = numberOfAppts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
