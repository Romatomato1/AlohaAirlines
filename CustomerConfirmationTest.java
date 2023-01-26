import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CustomerConfirmationTest.
 *
 * @author  Roman Gofman
 * @version 4/18/2020
 */
public class CustomerConfirmationTest {
    CustomerConfirmation customer1;
    /**
     * Default constructor for test class CustomerConfirmationTest
     */
    public CustomerConfirmationTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
       customer1 = new CustomerConfirmation("John Doe", "aBcDeF", true, 60.0);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        customer1 = null;
    }

    //----------------------------------------------------------------
    //            HAPPY PATH TESTS
    //----------------------------------------------------------------

    @Test
    public void testConstructorAndMutators() {
        assertEquals("aBcDeF", customer1.getConfirmationCode());
        assertEquals("John Doe", customer1.getCustomerName());
        assertEquals(true, customer1.getAlohaClub());
        assertEquals(60.0, customer1.getPriceBooked(), .001);
    }

    //----------------------------------------------------------------
    //            Preconditions/Exceptions
    //----------------------------------------------------------------

    @Test(expected = IllegalArgumentException.class)
    public void testNullName() {
        new CustomerConfirmation(null, "abcdef", true, 60.0);       
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyName() {
        new CustomerConfirmation("", "abcdef", true, 60.0);       
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConfirmationCodeAlphaNumeric() {
        new CustomerConfirmation("John Doe", "1b-de?", true, 60.0);       
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConfirmationCodeLengthTooSmall() {
        new CustomerConfirmation("John Doe", "abcde", true, 60.0);       
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConfirmationCodeLengthTooBig() {
        new CustomerConfirmation("John Doe", "abcdefg", true, 60.0);       
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPriceBelowZero() {
        new CustomerConfirmation("John Doe", "abcdef", true, -1.0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPriceEqualsZero() {
        new CustomerConfirmation("John Doe", "abcdef", true, 0.0);
    }
}

