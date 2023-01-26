// Imports for the date
import java.text.SimpleDateFormat;  
import java.util.Date;

// Import for ArrayList
import java.util.ArrayList;

//Import for File
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Makes a flight with seats, date of flight, origin airport, destination, and the 
 * flight number. Also includes a base price for seats.
 *
 * @author Roman Gofman
 * @version 4/28/2020
 */
public class Flight {

    /**---------------------------- Instance Variables -----------------------------------*/

    /** The flight's number */
    private String flightNumber;

    /** The origin airport code */
    private String origin;

    /** The destination airport code */
    private String destination;

    /** The date of the flight*/
    private String date;

    /** The full jagged array */
    private Seat[][] seat;

    /** The date of the flight*/
    private double basePrice;

    /**-----------------------------------Constants---------------------------------------*/

    /** The first row value of the plane */
    public static final int FIRST_ROW = 1;
    
    /** The first column value of the plane */
    public static final char FIRST_COLUMN = 'A';
    
    /** The total rows in the plane */
    public static final int TOTAL_ROWS = 44;

    /** The total rows in the plane */
    public static final int TOTAL_COLUMNS = 8;
    
    /** TThe index of the aisle on the plane */
    public static final int AISLE_INDEX = 2;

    /**--------------------------------- Constructor---------------------------------------*/

    /**
     * Constructor for objects of class Flight
     * 
     * @param   flightNumber    the flights number; must use alphanumeric characters
     * @param   origin          the airport code of the origin; must be three uppercase letters
     * @param   destination     the airport code of the destination; must be three uppercase letters
     * @param   basePrice       base price of a seat on a flight; must be grater than zero
     */
    public Flight(String flightNumber,
    String origin,
    String destination,
    double basePrice) {
        // Validation
        if (flightNumber == null || !flightNumber.matches("[A-Za-z0-9]+")){
            throw new IllegalArgumentException("flightNumber must be made up of only alphanumeric characters.");
        }
        if (origin == null || !origin.matches("[A-Z]{3}")){
            throw new IllegalArgumentException("origin must be exactly three uppercase letters.");
        }
        if (destination == null || !destination.matches("[A-Z]{3}")){
            throw new IllegalArgumentException("destination must be exactly three uppercase letters.");
        }
        if (basePrice <= 0){
            throw new IllegalArgumentException("Base price must be greater than zero.");
        }
        
        //Set up date
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date todaysDate = new Date();
       
        this.date = formatter.format(todaysDate);
        //Initialization
        this.origin = origin;
        this.flightNumber = flightNumber;
        this.destination = destination;        
        this.basePrice = basePrice;
        this.seat = new Seat[TOTAL_ROWS][];
        
        // Fill in seat array
        String seatNumber;
        for (int row = 0; row < TOTAL_ROWS; row++){
            if (row < FirstClass.NUM_ROWS) {
                seat[row] = new Seat[FirstClass.NUM_COLUMNS];
                for (int column = 0; column < FirstClass.NUM_COLUMNS; column++){
                    seatNumber = toSeatNumber(row, column);
                    seat[row][column] = new FirstClass(seatNumber, null, this);
                }
            }else if (row >= FirstClass.NUM_ROWS && row < Comfort.NUM_ROWS + FirstClass.NUM_ROWS) {
                seat[row] = new Seat[Comfort.NUM_COLUMNS];
                for (int column = 0; column< Comfort.NUM_COLUMNS; column++){
                    seatNumber = toSeatNumber(row, column);
                    seat[row][column] = new Comfort(seatNumber, null, this);
                }
            }else{
                seat[row] = new Seat[Economy.NUM_COLUMNS];
                for (int column = 0; column< Economy.NUM_COLUMNS; column++){
                    seatNumber = toSeatNumber(row, column);
                    seat[row][column] = new Economy(seatNumber, null, this);
                }
            }
        }
    }

    /**-------------------------------------- Accessors ------------------------------------*/

    /**
     * Returns the string flightNumber.
     *
     * @return    flightNumber    the flights number
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Returns the string origin.
     *
     * @return    origin    the airport code of the origin;
     */
    public String getOrigin() {
        return this.origin;
    }

    /**
     * Returns the string destination.
     *
     * @return    the airport code of the destination
     */
    public String getDestination() {
        return this.destination;
    }

    /**
     * Returns the string date today.
     *
     * @return    date  today's date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Method getBasePrice returns the base price.
     *
     * @return basePrice    the base price for a seat on a flight
     */
    public double getBasePrice(){
        return this.basePrice;
    }

    /**
     * Method getSeatArray returns the array of seats
     *
     * @return the total array of seats
     */
    public Seat[][] getSeatArray(){
        return this.seat;
    }

    /**
     * Calculates the number of passengers on the flight.
     * 
     * @return passengerCount   the number of passengers
     */
    public int getPassengerCount() {
        int passengerCount = 0;
        for (Seat[] seatObject: seat){
            for (Seat seat: seatObject){
                if (seat.isCustomerConfirmed()){
                    passengerCount += 1;
                }
            }
        }
        return passengerCount;
    }

    /**
     * Resturns a string of the seat toString() method for each seat in the 2d array.
     * 
     * @return  manifest group of formatted toString()
     */
    public String getManifest() {
        String manifest = "\nManifest:";
        for (Seat[] seatObject: seat) {
            for (Seat seat: seatObject) {
                if (seat.getCustomerConfirmation() != null){
                    manifest += "\n" + seat.toString();
                }
            }
        }
        return manifest;
    }

    /**
     * Takes in the row and column and returning the seat that is there
     * 
     * @param   row     the row index value
     * @param   column  the column index value
     * @return  Seat object in the 2d array
     */
    public Seat getSeat(int row, int column) {
        row -= FIRST_ROW;
        column -= FIRST_ROW;
        return seat[row][column];
    }

    /**
     * Method getSeat gets the seat object given a certain seat number
     *
     * @param   seatNum   String; seat number
     * @return  Seat object in the 2d array
     */
    public Seat getSeat(String seatNum) {
        int row = seatToRow(seatNum) - Seat.FIRST_ROW;
        int column = seatToColumn(seatNum) - Seat.FIRST_ROW;
        return seat[row][column];
    }

    /**
     * Method getAllSeatsForConfirmationCode gets seats with a confirmation code
     *
     * @param   customerConfirmation  String; customer confirmation code
     * @return array of seats with a given confirmation code
     */
    public Seat[] getAllSeatsForConfirmationCode(String customerConfirmation) {
        ArrayList<Seat> arrayList = new ArrayList<Seat>();
        for (Seat[] seatObject : seat) {
            for (Seat seat : seatObject) {
                if (seat.getCustomerConfirmation().getConfirmationCode().equals(customerConfirmation)) {
                    arrayList.add(seat);
                }
            }
        }
        Seat[] seatArray = arrayList.toArray(new Seat[0]);
        return seatArray;
    }

    /**
     * Method getEmptySeats sorts through the 2d array to get empty ones in an array.
     *
     * @return an array of empty seats
     */
    public Seat[] getEmptySeats(){
        ArrayList<Seat> emptySeats = new ArrayList<Seat>();
        for (Seat[] seatObject : seat) {
            for (Seat seat : seatObject) {
                if (seat.getCustomerConfirmation() == null) {
                    emptySeats.add(seat);
                }
            }
        }
        Seat[] emptySeatsArray = emptySeats.toArray(new Seat[0]);
        return emptySeatsArray;
    }

    /**
     * Method getEmptySeats gets array given a class name
     *
     * @param seatClass     class name to parse through
     * @return array of empty seats in that plane section
     */
    public Seat[] getEmptySeats(Class<? extends Seat> seatClass){
        ArrayList<Seat> emptySeats = new ArrayList<Seat>();
        if (seatClass == FirstClass.class){
            for (int row = 0; row < FirstClass.NUM_ROWS; row++){
                for (int column = 0; column < FirstClass.NUM_COLUMNS; column++){
                    if (seat[row][column].getCustomerConfirmation() == null) {
                        emptySeats.add(seat[row][column]);
                    }
                }
            }
        }else if (seatClass == Comfort.class){
            for (int row = FirstClass.NUM_ROWS; row < FirstClass.NUM_ROWS + Comfort.NUM_ROWS; row++){
                for (int column = 0; column < Comfort.NUM_COLUMNS; column++){
                    if (seat[row][column].getCustomerConfirmation() == null) {
                        emptySeats.add(seat[row][column]);
                    }
                }
            }
        }else{
            for (int row = FirstClass.NUM_ROWS + Comfort.NUM_ROWS; row < TOTAL_ROWS; row++){
                for (int column = 0; column < Economy.NUM_COLUMNS; column++){
                    if (seat[row][column].getCustomerConfirmation() == null) {
                        emptySeats.add(seat[row][column]);
                    }
                }
            }
        }
        Seat[] emptySeatsArray = emptySeats.toArray(new Seat[0]);
        return emptySeatsArray;
    }
    
    /**-------------------------------------- Other Methods --------------------------------*/

    /**
     * Changes a row and a column into a seat number
     * 
     * @param   row; row on plane
     * @param   column; column on plane
     * @return  seat number
     */
    public static String toSeatNumber(int row, int column) {
        column += Seat.FIRST_COLUMN;
        row += FIRST_ROW;
        char charColumn = (char) column;        
        return "" + row + charColumn;
    }

    /**
     * Method seatToRow changes a seat number into a row
     *
     * @param seatNum String; seat number
     * @return int;the row on the plane with the seat
     */
    public static int seatToRow(String seatNum) {
        String rowString = seatNum.substring(0, seatNum.length() - FIRST_ROW);
        return Integer.parseInt(rowString) - FIRST_ROW;
    }

    /**
     * Method seatToColumn changes a seat number into a column
     *
     * @param   seatNum String; seat number
     * @return  the row on the plane with the seat
     */
    public static int seatToColumn(String seatNum) {
        String colString = seatNum.substring(seatNum.length() - FIRST_ROW);
        char charColumn = colString.charAt(0);
        return charColumn - FIRST_COLUMN;
    }

    /**
     * Method seatMap writes to a file a seat map of available and unavailable seats
     *
     * 
     */
    public void seatMap() {
        String resultFirstClass = "\n\n-----First Class-----\n\n   A  B          C  D";
        int counter;
        for (int row = 0; row < FirstClass.NUM_ROWS; row++) {
            resultFirstClass += "\n" + String.format("%2d" ,row + FirstClass.FIRST_ROW);
            counter = 0;
            for (int column = 0; column < FirstClass.NUM_COLUMNS; column++) {
                counter += 1;
                if (seat[row][column].getCustomerConfirmation() != null) {
                    resultFirstClass += " X ";
                } else {
                    resultFirstClass += " _ ";
                }
                if (counter == AISLE_INDEX){
                    resultFirstClass += "        ";
                }
            }
        }

        String resultComfort = "\n\n----Comfort  Class----\n\n   A B     C D     E F";
        for (int row = FirstClass.NUM_ROWS; row < Comfort.NUM_ROWS + FirstClass.NUM_ROWS; row++) {
            resultComfort += "\n" + String.format("%2d" ,row + FirstClass.FIRST_ROW);
            counter = 0;
            for (int column = 0; column < Comfort.NUM_COLUMNS; column++) {
                counter += 1;
                if (seat[row][column].getCustomerConfirmation() != null) {
                    resultComfort += " X";
                } else {
                    resultComfort += " _";
                }
                if (counter == AISLE_INDEX|| counter == AISLE_INDEX * AISLE_INDEX){
                    resultComfort += "    ";
                }
            }
        }

        String resultEconomy = "\n\n----Economy  Class----\n\n   A B   C D E F   G H";
        for (int row = Comfort.NUM_ROWS + FirstClass.NUM_ROWS; row < TOTAL_ROWS; row++) {
            resultEconomy += "\n" + String.format("%2d" ,row + FirstClass.FIRST_ROW);
            counter = 0;
            for (int column = 0; column < Economy.NUM_COLUMNS; column++) {
                counter += 1;
                if (seat[row][column].getCustomerConfirmation() != null) {
                    resultEconomy += " X";
                } else {
                    resultEconomy += " _";
                }
                if (counter == AISLE_INDEX - FIRST_ROW || counter == AISLE_INDEX){
                    resultEconomy += "  ";
                }
            }
        }
        
        //Write to file
        try {
            FileWriter writer = new FileWriter("SeatMap.txt");
            writer.write("Flight #" + this.flightNumber + " from " + this.origin + " to " + this.destination + ", " + this.date + resultFirstClass + resultComfort + resultEconomy);
            writer.close();
        } catch (IOException e) {

        }
    }

    /**
     * Returns all useful data from this class in an appealing manor
     * 
     * @return  the customer's name and confirmation code with other strings for organization
     */
    public String toString() {
        return "Flight Number: " + this.flightNumber + "\nOrigin: " + this.origin + "\nDestination: " + 
        this.destination + "\nDate: " + getDate() + "\n" + getManifest();
    }
}