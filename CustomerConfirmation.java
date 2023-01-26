/**
 * Determines whether a customer has confirmed their reservation or not.
 *
 * @author Roman Gofman
 * @version 4/28/2020
 */
public class CustomerConfirmation
{

    /**---------------------------- Instance Variables ------------------------------------*/     

    /** The customer's name*/
    private String customerName;

    /** The customer's confirmation  code*/
    private String confirmationCode;

    /** If they have an Aloha Club membership*/
    private boolean alohaClub;

    /** The price at which the customer booked the seat at*/
    private double priceBooked;

    /**--------------------------------- Constructor---------------------------------------*/

    /**
     * Constructor for objects of class CustomerConfirmation
     * 
     * @param   customerName; must have only letters or whitespace  
     * @param   confirmationCode; must have only letters; must have only 6 characters
     * @param   alohaClub; if the customer is a member of Aloha Club
     * @param   priceBooked; the price the seat was purchased at; must be greater than zero
     */
    public CustomerConfirmation(String customerName, String confirmationCode, boolean alohaClub, double priceBooked) {
        setCustomerName(customerName);
        setConfirmationCode(confirmationCode);
        if (priceBooked <= 0.0){
            throw new IllegalArgumentException("Price must be above zero.");
        }
        this.priceBooked = priceBooked;
        this.alohaClub = alohaClub;
    }

    /**-------------------------------------- Accessors ------------------------------------*/

    /**
     * Returns the customer's name.
     *
     * @return    customerName; the customer's name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Returns the customer's confirmation code.
     *
     * @return  confirmation code; the confirmation code
     */
    public String getConfirmationCode() {
        return confirmationCode;
    }

    /**
     * Returns whether the customer has a membership.
     *
     * @return  alohaClub; if the customer is a member of Aloha Club
     */
    public boolean getAlohaClub(){
        return alohaClub;
    }

    /**
     * Returns the price at which the seat was booked.
     *
     * @return  priceBooked; booking price of the seat
     */
    public double getPriceBooked(){
        return priceBooked;
    }

    /**--------------------------------------- Mutators ------------------------------------*/

    /**
     * Validates and changes value of instance variable.
     * 
     * @param   customerName; must have only letters or whitespace  
     */
    public void setCustomerName(String customerName) {
        if (customerName == null || !customerName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("The given name should be made up of only letters" 
                + "and spaces and should not be null.");
        }
        this.customerName = customerName; 
    }

    /**
     * Validates and changes value of instance variable.
     * 
     * @param   confirmationCode; must have only letters; must have only 6 characters  
     */
    public void setConfirmationCode(String confirmationCode){
        if (confirmationCode == null || !confirmationCode.matches("[A-Za-z0-9]{6}")){
            throw new IllegalArgumentException("Confirmation code must be alphanumeric and 6 characters.");
        }
        this.confirmationCode = confirmationCode; 
    }

    /**-------------------------------------- Other Methods --------------------------------*/

    /**
     * Returns all useful data from this class in an appealing manor
     * 
     * @return  the customer's name and confirmation code with other strings for organization
     */
    public String toString(){
        return "\n" + "Name: " + customerName +"\nConfirmation Code: " + confirmationCode +
        "\nAloha Club: " + alohaClub;
    }
}
