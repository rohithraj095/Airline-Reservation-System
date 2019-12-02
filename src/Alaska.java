import java.util.ArrayList;

public class Alaska implements Airline {

    private ArrayList<Passenger> passengers;
    private Gate gate;

    public Alaska() {
        passengers = new ArrayList<>();
        gate = new Gate();
    }

    public void addPassengers(Passenger passenger) {
        if(passenger != null && getCurrentPassengers() < getMaxPassengers())
            passengers.add(passenger);
    }

    @Override
    public int getCurrentPassengers() {
        return passengers.size();
    }

    @Override
    public int getMaxPassengers() {
        return 100;
    }

    public boolean canAddPassenger() {
        return (passengers.size() < 100);
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
