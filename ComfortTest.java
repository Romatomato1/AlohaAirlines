import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ComfortTest.
 *
 * @author  Roman Gofman
 * @version 4/28/2020
 */
public class ComfortTest
{
    Comfort comfort;
    CustomerConfirmation customerConfirmation;
    Flight flight1;
    /**
     * Default constructor for test class ComfortTest
     */
    public ComfortTest()
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
        customerConfirmation = new CustomerConfirmation("Joe Comfort", "BCDEFG", false, 82.0);
        flight1 = new Flight("123ABC", "SEA", "JFK", 90.99);
        comfort = new Comfort("2B", customerConfirmation, flight1);
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
        comfort = null;
        customerConfirmation = null;
    }

    //____________________________________________________________________________
    // Happy Path Tests
    //----------------------------------------------------------------------------

    @Test
    public void testConstructorsAndGets(){
        assertEquals("2B", comfort.getSeatNumber());
        assertEquals(customerConfirmation, comfort.getCustomerConfirmation());
        assertEquals(6, comfort.getRecline());
        assertEquals(136, comfort.getPrice(), .001);
        assertEquals(Seat.Meal.FULL, comfort.getMealType());
    }

    @Test
    public void testIsCustomerConfirmed(){
        assertEquals(true, comfort.isCustomerConfirmed());
    }

    //____________________________________________________________________________
    // Preconditions
    //----------------------------------------------------------------------------

    @Test(expected = IllegalArgumentException.class)
    public void testSeatNumberIsNull() {
        new Comfort(null, customerConfirmation, flight1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSeatNumberIsEmpty() {
        new Comfort("", customerConfirmation, flight1);
    }
}
