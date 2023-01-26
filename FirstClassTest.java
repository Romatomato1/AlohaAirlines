import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class FirstClassTest.
 *
 * @author  Roman Gofman
 * @version 4/28/2020
 */
public class FirstClassTest
{
    FirstClass firstClass;
    CustomerConfirmation customerConfirmation;
    Flight flight1;
    /**
     * Default constructor for test class FirstClassTest
     */
    public FirstClassTest()
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
        customerConfirmation = new CustomerConfirmation("Joe Smith", "ABSDEF", true, 82.0);
        flight1 = new Flight("123ABC", "SEA", "JFK", 90.99);
        firstClass = new FirstClass("2B", customerConfirmation, flight1);    
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
        firstClass = null;
        customerConfirmation = null;
    }

    //____________________________________________________________________________
    // Happy Path Tests
    //----------------------------------------------------------------------------

    @Test
    public void testConstructorsAndGets(){
        assertEquals("2B", firstClass.getSeatNumber());
        assertEquals(customerConfirmation, firstClass.getCustomerConfirmation());
        assertEquals(10, firstClass.getRecline());
        assertEquals(164, firstClass.getPrice(), .001);
        assertEquals(Seat.Meal.GOURMET, firstClass.getMealType());
    }

    @Test
    public void testIsCustomerConfirmed(){
        assertEquals(true, firstClass.isCustomerConfirmed());
    }

    //____________________________________________________________________________
    // Preconditions
    //----------------------------------------------------------------------------

    @Test(expected = IllegalArgumentException.class)
    public void testSeatNumberIsNull() {
        new FirstClass(null, customerConfirmation, flight1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSeatNumberIsEmpty() {
        new FirstClass("", customerConfirmation, flight1);
    }
}
