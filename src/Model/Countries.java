package Model;

/**
 * Country object for use in the Reports section, for Appts by Second Division Level
 */
public class Countries {
    private String country;
    private String division;
    private String type;
    private int numberOfAppts;

    /**
     * Constructor for Country object.
     * @param country
     * @param division
     * @param type
     * @param numberOfAppts
     */
    public Countries (String country, String division, String type, int numberOfAppts) {
        this.country = country;
        this.division = division;
        this.type = type;
        this.numberOfAppts = numberOfAppts;
    }

    /**
     * Get country name.
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set country name.
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get number of appts for the second division level by type.
     * @return
     */
    public int getNumberOfAppts() {
        return numberOfAppts;
    }

    /**
     * Set number of appointments for the second division level by type.
     * @param numberOfAppts
     */
    public void setNumberOfAppts(int numberOfAppts) {
        this.numberOfAppts = numberOfAppts;
    }

    /**
     * Get Type of appointment
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Set Type for appointment.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get second level division for country by type.
     * @return
     */
    public String getDivision() {
        return division;
    }

    /**
     * Set second level division for country by type.
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
    }
}
