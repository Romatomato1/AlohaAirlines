/**
 * A Main method to run the other methods
 *
 * @author Roman Gofman
 * @version 4/28/2020
 */
public class AlohaAirlines {
    /**
     * Method main implements and tests classes together.
     *
     * @param args A parameter
     */
    public static void main(String[] args) {
        Flight flight4 = new Flight("123ABC", "SEA", "JFK", 90.99);
        for (int row = Flight.FIRST_ROW; row < FirstClass.NUM_ROWS + Flight.FIRST_ROW; row ++){ 
            for (int column = Flight.FIRST_ROW; column < FirstClass.NUM_COLUMNS + Flight.FIRST_ROW; column++){
                if (row % 2 == 0 & column % 2 == 0){
                    flight4.getSeat(row,column).confirm("Joe Smith", "ABSDEF", true);
                }
            }
        }
        
        for (int row = Flight.FIRST_ROW; row < Comfort.NUM_ROWS + Flight.FIRST_ROW; row ++){ 
            for (int column = Flight.FIRST_ROW; column < Comfort.NUM_COLUMNS + Flight.FIRST_ROW; column++){
                if (row % 3 == 0){
                    flight4.getSeat(row + FirstClass.NUM_ROWS,column).confirm("Joe Comfort", "BCDEFG", false);
                }
            }
        }
        
        for (int row = Flight.FIRST_ROW; row < Economy.NUM_ROWS + Flight.FIRST_ROW; row ++){ 
            for (int column = Flight.FIRST_ROW; column < Economy.NUM_COLUMNS + Flight.FIRST_ROW; column++){
                if (row % 3.5 == 0){
                    flight4.getSeat(row + FirstClass.NUM_ROWS + Comfort.NUM_ROWS,column).confirm("Joeseph Average", "CD34EF", false);
                }
            }
        }
        
        System.out.print(flight4.toString());
        flight4.seatMap();
    }
}
//https://howtodoinjava.com/java/date-time/java-get-current-datetime-examples