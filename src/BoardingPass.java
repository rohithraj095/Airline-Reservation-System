/**
 * BoardingPass
 * <p>
 * Has aspects of an BoardingPass
 *
 * @author Rohith Rajashekarbabu, Cassandra Jessica Deckowitz, lab- B13
 * @version December 03, 2019
 */

public class BoardingPass {

    private Passenger passenger;

    public BoardingPass(Passenger passenger) {
        this.passenger = passenger;
    }

    public String toString() {
        return "BOARDING PASS FOR FLIGHT 18000 WITH " + passenger.getAirlineName() + ". \n" +
                "PASSENGER FIRST NAME " + passenger.getfName().toUpperCase() + ". \n" +
                "PASSENGER LAST NAME " + passenger.getlName().toUpperCase() + ". \n" +
                "PASSENGER AGE " + passenger.getAge() + ". \n" +
                "You can now begin boarding at gate " + passenger.getGate() + ".";
    }
}
