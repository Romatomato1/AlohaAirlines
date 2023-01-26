
/**
 * A seat in first class.
 *
 * @author Roman Gofman
 * @version 4/28/2020
 */
public class FirstClass extends Seat
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
    public static final int RECLINE = 8;

    /** The meal associated with the seat */
    public static final Meal MEAL_TYPE = Meal.GOURMET;

    /** The discount for members of Aloha Club */
    public static final double ALOHA_CLUB_DISCOUNT = .1;

    /** The multiple of base price to get the price */
    public static final int RELATION_TO_BASE_PRICE = 2;

    /** The number of rows in first class */
    public static final int NUM_ROWS = 6;

    /** The number of columns in first class */
    public static final int NUM_COLUMNS = 4;

    /** The first row in first class */
    public static final int FIRST_ROW = 1;

    /** The first column in first class */
    public static final char FIRST_COLUMN = 'A';

    /** Extra inches of increase for seat with extra room. */
    private static final int EXTRA_ROOM = 2;

    /**
     * Economy Constructor to create an economy object.
     *
     * @param seatNumber A parameter representing the seat number.
     * @param customerConfirmation A parameter for the customer confirmation.
     * @param flight A parameter for the flight
     */
    public FirstClass(String seatNumber,
    CustomerConfirmation customerConfirmation, 
    Flight flight)
    {
        super(seatNumber, MEAL_TYPE, RECLINE,customerConfirmation, RELATION_TO_BASE_PRICE,
            ALOHA_CLUB_DISCOUNT, flight);
    }

    /**
     * Method getRecline returns the inches that the seat can recline
     *
     * @return the inches a seat can recline
     */
    @Override
    public int getRecline(){
        int recline = RECLINE;
        if (flight.seatToRow(super.getSeatNumber()) == 1 || flight.seatToRow(super.getSeatNumber()) == 2){
            recline += EXTRA_ROOM;
        }
        return recline;
    }

    /**
     * Method toString takes in data and returns it in an appealing manner.
     *
     * @return a string value of the data.
     */
    @Override
    public String toString(){
        return super.getCustomerConfirmation().toString()  + "\nSeat Number: " + super.getSeatNumber() + "\nClass: " + 
        "FirstClass\nMeal: " + MEAL_TYPE + "\nPrice: $" + String.format("%.2f", getPrice())  + "\nRecline: " + getRecline();
    }
}
