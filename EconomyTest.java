import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class EconomyTest.
 *
 * @author  Roman Gofman
 * @version 4/28/2020
 */
public class EconomyTest
{
    Economy economy;
    CustomerConfirmation customerConfirmation;
    Flight flight1;
    /**
     * Default constructor for test class EconomyTest
     */
    public EconomyTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        customerConfirmation = new CustomerConfirmation("Joeseph Average", "CD34EF", false, 82.0);
        flight1 = new Flight("123ABC", "SEA", "JFK", 90.99);
        economy = new Economy("2B", customerConfirmation, flight1);
        for (int row = Flight.FIRST_ROW; row < FirstClass.NUM_ROWS + Flight.FIRST_ROW; row ++){ 
            for (int column = Flight.FIRST_ROW; column < FirstClass.NUM_COLUMNS + Flight.FIRST_ROW; column++){
                if (row % 2 == 0 & column % 2 == 0){
                    flight1.getSeat(row,column).confirm("Joe Smith", "ABSDEF", true);
                }
            }
        }

        for (int row = Flight.FIRST_ROW; row < Comfort.NUM_ROWS + Flight.FIRST_ROW; row ++){ 
            for (int column = Flight.FIRST_ROW; column < Comfort.NUM_COLUMNS + Flight.FIRST_ROW; column++){
                if (row % 3 == 0){
                    flight1.getSeat(row + FirstClass.NUM_ROWS,column).confirm("Joe Comfort", "BCDEFG", false);
                }
            }
        }

        for (int row = Flight.FIRST_ROW; row < Economy.NUM_ROWS + Flight.FIRST_ROW; row ++){ 
            for (int column = Flight.FIRST_ROW; column < Economy.NUM_COLUMNS + Flight.FIRST_ROW; column++){
                if (row % 3.5 == 0){
                    flight1.getSeat(row + FirstClass.NUM_ROWS + Comfort.NUM_ROWS,column).confirm("Joeseph Average", "CD34EF", false);
                }
            }
        }
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        flight1 = null;
        economy = null;
        customerConfirmation = null;
    }

    //____________________________________________________________________________
    // Happy Path Tests
    //----------------------------------------------------------------------------

    @Test
    public void testConstructorsAndGets(){
        assertEquals("2B", economy.getSeatNumber());
        assertEquals(customerConfirmation, economy.getCustomerConfirmation());
        assertEquals(3, economy.getRecline());
        assertEquals(91, economy.getPrice(), .001);
        assertEquals(Seat.Meal.SNACK, economy.getMealType());
    }

    @Test
    public void testIsCustomerConfirmed(){
        assertEquals(true, economy.isCustomerConfirmed());
    }

    //____________________________________________________________________________
    // Preconditions
    //----------------------------------------------------------------------------

    @Test(expected = IllegalArgumentException.class)
    public void testSeatNumberIsNull() {
        new Economy(null, customerConfirmation, flight1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSeatNumberIsEmpty() {
        new Economy("", customerConfirmation, flight1);
    }
}
