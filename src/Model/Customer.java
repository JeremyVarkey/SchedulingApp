package Model;

/**
 * Class to hold Customer data. This class includes getters and setters for id, customer name, address,
 * postal code, phone, division id, and division name
 */
public class Customer {
    private int id;
    private String customerName;
    private String address;
    private int postalCode;
    private String phone;
    private int divisionID;
    private String divisionName;

    /**
     * Constructor for Customer object.
     * @param id
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionID
     * @param divisionName
     */
    public Customer(int id, String customerName, String address, int postalCode, String phone, int divisionID, String divisionName) {
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }

    /**
     * Getter for customer ID.
     * @return int customer ID
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for customer id.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get customer name.
     * @return String customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Setter for Customer Name.
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Getter for customer address.
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for customer address.
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for customer postal code.
     * @return int PostalCode
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     * Setter postal code.
     * @param postalCode
     */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Getter for phone.
     * @return String customer phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter for phone.
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter for division id.
     * @return int division id
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Setter for division id
     * @param divisionID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * Getter for division name.
     * @return String division name
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Setter for division name.
     * @param divisionName
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
}
