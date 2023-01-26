import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class FlightTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class FlightTest {
    Flight flight1;
    Seat seat;
    CustomerConfirmation customerConfirmation;
    
    /**
     * Default constructor for test class FlightTest
     */
    public FlightTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        flight1 = new Flight("123ABC", "SEA", "JFK", 90.99);
        seat = new FirstClass("2C", customerConfirmation, flight1);
        customerConfirmation = new CustomerConfirmation("John Doe", "JOD123", false, 69.99);
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
    public void tearDown() {
        flight1 = null;
        seat = null;
        customerConfirmation = null;
    }

    //----------------------------------------------------------------
    //            HAPPY PATH TESTS
    //----------------------------------------------------------------

    @Test
    public void testConstructorAndGets() {
        assertEquals("JFK", flight1.getDestination());
        assertEquals("123ABC", flight1.getFlightNumber());
        assertEquals("SEA", flight1.getOrigin());
        assertEquals("04/28/2020", flight1.getDate());//check dayly
        assertEquals(90.99, flight1.getBasePrice(), .001);
    }

    @Test
    public void testGetPassengerCount() {
        assertEquals(50, flight1.getPassengerCount());
    }

    @Test
    public void testGetSeat() {
        assertEquals(flight1.getSeatArray()[1][1], flight1.getSeat(2,2));
        assertEquals(flight1.getSeatArray()[1][1], flight1.getSeat("2B"));
    }

    @Test
    public void testGetAllSeatsForConfirmtionCode() {
        assertEquals(6, flight1.getAllSeatsForConfirmationCode("ABCDEF").length);
    }

    @Test
    public void testGetEmptySeats() {
        assertEquals(262, flight1.getEmptySeats().length);
        assertEquals(18, flight1.getEmptySeats(FirstClass.class).length);
        assertEquals(36, flight1.getEmptySeats(Comfort.class).length);
        assertEquals(208, flight1.getEmptySeats(Economy.class).length);
    }
    
     @Test
    public void testToSeatNumber() {
        assertEquals("1A", flight1.toSeatNumber(0, 0));
        assertEquals("32C", flight1.toSeatNumber(31, 2));
        assertEquals("60E", flight1.toSeatNumber(59, 4));
    }

    @Test
    public void testSeatToRow() {
        assertEquals(0, flight1.seatToRow("1A"));
        assertEquals(31, flight1.seatToRow("32C"));
        assertEquals(59, flight1.seatToRow("60E"));
    }

    @Test
    public void testSeatToColumn() {
        assertEquals(0, flight1.seatToColumn("1A"));
        assertEquals(2, flight1.seatToColumn("32C"));
        assertEquals(4, flight1.seatToColumn("60E"));
    }
    
    //----------------------------------------------------------------
    //            Precondtions
    //----------------------------------------------------------------

    @Test(expected = IllegalArgumentException.class)
    public void testFlightNumberIsNull() {
        new Flight(null, "SEA", "JFK", 90.99);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFlightNumberIsEmpty(){
        new Flight("", "SEA", "JFK", 90.99);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDestinationTwoLetters(){
        new Flight("123ABC", "SEA", "JK", 90.99);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDestinationLowercase(){
        new Flight("123ABC", "SEA", "jfk", 90.99);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDestinationEmpty(){
        new Flight("123ABC", "SEA", "", 90.99);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDestinationIsNull(){
        new Flight("123ABC", "SEA", null, 90.99);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOriginTwoLetters(){
        new Flight("123ABC", "SE", "JFK", 90.99);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOriginLowercase(){
        new Flight("123ABC", "sea", "JFK", 90.99);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOriginEmpty(){
        new Flight("123ABC", "", "JFK", 90.99);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOriginIsNull(){
        new Flight("123ABC", "SEA", null, 90.99);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeBasePrice(){
        new Flight("123ABC", "SEA", null, -1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testZeroBasePrice(){
        new Flight("123ABC", "SEA", null, 0);
    }
}

