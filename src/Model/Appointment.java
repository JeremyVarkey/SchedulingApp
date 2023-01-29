package Model;
import java.time.LocalDateTime;

/**
 * Class for an appointment object to hold Appointment information from database
 */
public class Appointment {
    private int aptID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createdDateTime;
    private String createdBy;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;
    private String contact;

    /**
     * Constructor for an Appointment object
     * @param aptID
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param createdDateTime
     * @param createdBy
     * @param lastUpdated
     * @param lastUpdatedBy
     * @param customerID
     * @param userID
     * @param contactID
     * @param contact
     */
    public Appointment(int aptID, String title, String description, String location,String type, LocalDateTime start,
                       LocalDateTime end, LocalDateTime createdDateTime, String createdBy, LocalDateTime lastUpdated,
                       String lastUpdatedBy, int customerID, int userID, int contactID, String contact) {

        this.aptID = aptID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createdDateTime = createdDateTime;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.contact = contact;
    }

    /**
     * Getter for Title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for Apt ID
     * @return
     */
    public int getAptID() {
        return aptID;
    }

    /**
     * Getter for Description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for location
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * Getter for type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for start time
     * @return
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Getter for end time
     * @return
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Getter for created datetime
     * @return
     */
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Getter for created by name
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Getter for last updated datetime
     * @return
     */
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Getter for lastupdated by name
     * @return
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Getter for customer ID
     * @return
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Getter for User ID
     * @return
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Getter for contact ID
     * @return
     */
    public int getContactID() {
        return contactID;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
