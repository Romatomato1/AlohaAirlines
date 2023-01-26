/**
 * A seat in economy class.
 *
 * @author Roman Gofman
 * @version 4/28/2020
 */
public class Economy extends Seat
{
    /**---------------------------- Instance Variables ------------------------------------*/     

    
    /** The seat number with a row and a column */
    private String seatNumber;
    
    /** Customer Confirmation object associated with a seat */
    private CustomerConfirmation customerConfirmation;
    
    /** Fllight object the seat is associated with it */
    private Flight flight;
    
    /**--------------------------------- Constructor---------------------------------------*/

    /** inches that the seat can recline */
    private static final int RECLINE = 3;
    
    /** The meal associated with the seat */
    private static final Meal MEAL_TYPE = Meal.SNACK;
    
    /** The discount for members of Aloha Club */
    private static final double ALOHA_CLUB_DISCOUNT = .02;
    
    /** The multiple of base price to get the price */
    private static final double RELATION_TO_BASE_PRICE = 1.0;
    
    /** The number of rows in economy class */
    public static final int NUM_ROWS = 30;
    
    /** The number of columns in economy class */
    public static final int NUM_COLUMNS = 8;
    
    /** The first row in economy class */
    public static final int FIRST_ROW = 15;
    
    /** The first column in economy class */
    public static final char FIRST_COLUMN = 'A';

    
    /**
     * Economy Constructor to create an economy object.
     *
     * @param seatNumber A parameter representing the seat number.
     * @param customerConfirmation A parameter for the customer confirmation.
     * @param flight A parameter for the flight
     */
    public Economy(String seatNumber,  
    CustomerConfirmation customerConfirmation,
    Flight flight)
    {
        super(seatNumber, MEAL_TYPE, RECLINE,customerConfirmation, RELATION_TO_BASE_PRICE,
              ALOHA_CLUB_DISCOUNT, flight);
    }

    /**
     * Method toString takes in data and returns it in an appealing manner.
     *
     * @return a string value of the data.
     */
    @Override
    public String toString(){
        return super.getCustomerConfirmation().toString() + "\nSeat Number: " + super.getSeatNumber() + "\nClass: " + 
        "Economy\nMeal: " + MEAL_TYPE + "\nPrice: $" + String.format("%.2f", getPrice()) + "\nRecline: " + RECLINE;
    }
}
