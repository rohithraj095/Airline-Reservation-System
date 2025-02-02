import java.util.ArrayList;

/**
 * Delta
 * <p>
 * Has aspects of an Delta Airlines
 *
 * @author Rohith Rajashekarbabu, Cassandra Jessica Deckowitz, lab- B13
 * @version December 03, 2019
 */

public class Delta implements Airline {

    private ArrayList<Passenger> passengers;
    private Gate gate;
    private int maxSize = 100;

    public Delta() {
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

    public ArrayList<Passenger> passengerList() {
        return passengers;
    }

    @Override
    public String toString() {
        return "Delta Airlines is proud to be one of the five premier Airlines at Purdue University. \n" +
                "We are extremely proud to offer exceptional services, with free limited WiFi for all customers. \n" +
                "Passengers who use T-Mobile as a cell phone carrier get additional benefits. \n" +
                "We are also happy to offer power outlets in each seat for passenger use. \n" +
                "We hope you choose to fly Delta as your next Airline.";
    }

    public String getGate() {
        return gate.toString();
    }

    public String getName() {
        return "Delta Airlines";
    }
}
