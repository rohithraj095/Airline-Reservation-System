import java.util.ArrayList;

/**
 * Southwest
 * <p>
 * Has aspects of a Southwest Airlines
 *
 * @author Rohith Rajashekarbabu, Cassandra Jessica Deckowitz, lab- B13
 * @version December 03, 2019
 */

public class Southwest implements Airline {

    private ArrayList<Passenger> passengers;
    private Gate gate;
    private int maxSize = 100;

    public Southwest() {
        passengers = new ArrayList<>();
        gate = new Gate();
    }

    public void addPassengers(Passenger passenger) {
        if (passenger != null && getCurrentPassengers() < getMaxPassengers())
            passengers.add(passenger);
    }

    @Override
    public int getCurrentPassengers() {
        return passengers.size();
    }

    @Override
    public int getMaxPassengers() {
        return maxSize;
    }

    public boolean canAddPassenger() {
        return (passengers.size() <= maxSize);
    }

    @Override
    public String toString() {
        return "Southwest Airlines is proud to offer flights to Purdue University. \n" +
                "We are happy to offer free in flight WiFi, as well as our amazing snacks. \n" +
                "In addition, we offer flights much cheaper than other airlines, and offer two free checked bags. \n" +
                "We hope you choose Southwest for your next flight.";
    }

    public String getGate() {
        return gate.toString();
    }

    public ArrayList<Passenger> passengerList() {
        return passengers;
    }

    public String getName() {
        return "Southwest Airlines";
    }
}
