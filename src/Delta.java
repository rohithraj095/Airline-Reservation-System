import java.util.ArrayList;

public class Delta implements Airline {

    private ArrayList<Passenger> passengers;

    public Delta () {
        passengers = new ArrayList<>();
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
        return 200;
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
        Gate gate = new Gate();
        return gate.toString();
    }
}
