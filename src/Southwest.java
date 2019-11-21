import java.util.ArrayList;

public class Southwest implements Airline {

    private ArrayList<Passenger> passengers;

    public Southwest () {
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
        return 100;
    }

    @Override
    public String toString() {
        return "Southwest Airlines is proud to offer flights to Purdue University. \n" +
                "We are happy to offer free in flight WiFi, as well as our amazing snacks. \n" +
                "In addition, we offer flights much cheaper than other airlines, and offer two free checked bags. \n" +
                "We hope you choose Southwest for your next flight.";
    }

    public String getGate() {
        Gate gate = new Gate();
        return gate.toString();
    }
}

