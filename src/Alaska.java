import java.util.ArrayList;

/**
 * Alaska
 * <p>
 * Has aspects of an Alaska Airlines
 *
 * @author Rohith Rajashekarbabu, Cassandra Jessica Deckowitz, lab- B13
 * @version December 03, 2019
 */

public class Alaska implements Airline {

    private ArrayList<Passenger> passengers;
    private Gate gate;
    private int maxSize = 50;

    public Alaska() {
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
        return "Alaska Airlines is proud to serve the strong and knowledgeable Boilermakers from Purdue University. \n" +
                "We primarily fly westward, and often have stops in Alaska and California. \n" +
                "We have first class amenities, even in coach class. \n" +
                "We provide fun snacks, such as pretzels and goldfish. \n" +
                "We also have comfortable seats and free WiFi. \n" +
                "We hope you choose Alaska Airlines for your next trip!";
    }

    public String getName() {
        return "Alaska Airlines";
    }

    public String getGate() {
        return gate.toString();
    }
}
