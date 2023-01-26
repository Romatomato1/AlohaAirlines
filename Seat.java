/**
 * Abstract class for types of seats.
 *
 * @author Roman Gofman
 * @version 4/28/2020
 */
public abstract class Seat
{

    /**---------------------------- Instance Variables ------------------------------------*/

    /** Is the seat booked*/
    private CustomerConfirmation customerConfirmation;

    /** The seat number*/
    private String seatNumber;

    /** Inches the seat can recline*/
    private int recline;

    /** The flight that the seat is on*/
    private Flight flight;

    /** The increase to base price based on seat.*/
    private double relationToBasePrice;

    /** Discount for members*/
    private double alohaClubDiscount;

    /** The meal associated with the seat */
    private Meal mealType;

    /**---------------------------------- Constants ---------------------------------------*/

    /** The first row in the plane*/
    public static final int FIRST_ROW = 1;

    /** Columns are defined by chars the first is A*/
    public static final char FIRST_COLUMN = 'A';

    /**--------------------------------- Constructor---------------------------------------*/

    /**
     * Seat Constructor for objects of seat.
     *
     * @param seatNumber A parameter representing the seat number
     * @param mealType A parameter representing the meal type
     * @param recline A parameter showing how far in inches  a seat can recline 
     * @param customerConfirmation A parameter the customer's confirmation
     * @param relationToBasePrice A parameter the amount of change from the base price
     * @param alohaClubDiscount A parameter showing the percent discount to be subtracted if they are a member.
     * @param flight A parameter for a flight.
     */
    public Seat(String seatNumber,
    Meal mealType,
    int recline,
    CustomerConfirmation customerConfirmation,
    double relationToBasePrice,
    double alohaClubDiscount,
    Flight flight) {
        // Initialization
        //Validation
        if (seatNumber == null || seatNumber.matches("")){
            throw new IllegalArgumentException("The seat number can't equal null and must have a number then a  letter");
        }
        if (flight.seatToRow(seatNumber) < 0 || flight.seatToRow(seatNumber) > flight.TOTAL_ROWS + FIRST_ROW){
            throw new IllegalArgumentException("the rows should be more than 0 and less than the number of rows. ");
        }
        if (flight.seatToColumn(seatNumber) < 0 || flight.seatToColumn(seatNumber) > flight.TOTAL_COLUMNS + FIRST_ROW){
            throw new IllegalArgumentException("column should be between one and the number of columns.");
        }
        if (recline < 0){
            throw new IllegalArgumentException("recline should not be below zero.");
        }
        this.seatNumber = seatNumber;
        this.mealType = mealType;
        this.recline = recline;
        this.customerConfirmation = customerConfirmation;
        this.relationToBasePrice = relationToBasePrice;
        this.alohaClubDiscount = alohaClubDiscount;
        this.flight = flight;
    }

    /**
     * Seat Constructor
     *
     * @param seatNumber A parameter representing the seat number
     * @param mealType A parameter representing the meal type
     * @param recline A parameter showing how far in inches  a seat can recline 
     * @param   name; must have only letters or whitespace  
     * @param   code; must have only letters; must have only 6 characters
     * @param   alohaClub; if the customer is a member of Aloha Club
     * @param relationToBasePrice A parameter
     * @param alohaClubDiscount A parameter
     * @param flight A parameter
     */
    public Seat(String seatNumber,
    Meal mealType,
    int recline,
    String name,
    String code,
    boolean alohaClub,
    double relationToBasePrice,
    double alohaClubDiscount,
    Flight flight) {
        // Initialization
        this.seatNumber = seatNumber;
        this.mealType = mealType;
        this.recline = recline;
        this.customerConfirmation = new CustomerConfirmation(name, code, alohaClub, getPrice());
        this.relationToBasePrice = relationToBasePrice;
        this.alohaClubDiscount = alohaClubDiscount;
        this.flight = flight;
    }

    /**-------------------------------------- Accessors ------------------------------------*/

    /**
     * Method getMealType returns the meal type of the seat
     *
     * @return  enum Meal meal type
     */
    public Meal getMealType() {
        return mealType;
    }

    /**
     * Method getPrice calculates the price of a seat.
     *
     * @return the price of a seat.
     */
    public double getPrice(){
        double result = relationToBasePrice * flight.getBasePrice();
        if (customerConfirmation != null && customerConfirmation.getAlohaClub()){
            result -= result * alohaClubDiscount;
        }
        return Math.round(result);
    }

    /**
     * Method getRecline returns the inches that the seat can recline
     *
     * @return the inches that a seat can recline
     */
    public int getRecline(){
        return recline;
    }

    /**
     * Method getCustomerConfirmation returns the seat's confirmation if it has one
     *
     * @return the values of the seat's confirmation
     */
    public CustomerConfirmation getCustomerConfirmation() {
        return this.customerConfirmation;
    }

    /**
     * Method getSeatNumber returns the seat number
     *
     * @return the seat number
     */
    public String getSeatNumber(){
        return this.seatNumber;
    }

    /** -------------------------------------- Other Methods --------------------------------*/

    /**
     * Method confirm creates a customer confirmation
     *
     * @param   name; must have only letters or whitespace  
     * @param   code; must have only letters; must have only 6 characters
     * @param   alohaClub; if the customer is a member of Aloha Club
     */
    public void confirm(String name, String code, boolean alohaClub){
        this.customerConfirmation = new CustomerConfirmation(name, code, alohaClub, getPrice());
    }

    /**
     * Determines whether a cutomer has confirmed a seat
     * 
     * @return if confirmed
     */
    public boolean isCustomerConfirmed() {
        boolean confirmed = true;
        if (customerConfirmation == null){
            confirmed = false;
        }
        return confirmed;
    }

    /**
     * Deletes the customer confirmation assigned to a seat
     * 
     */
    public void releaseSeat() {
        customerConfirmation = null;
    }

    /**
     * Abstract toString method.
     */
    public abstract String toString(); 

    /**
     * Meal is an enum that represents a meal associated with a seat
     */
    public enum Meal
    {
        /** A type of meal called SNACK */
        SNACK,

        /** A type of meal called GOURMET */
        GOURMET, 

        /** A type of meal called FULL */
        FULL;
    }
}
