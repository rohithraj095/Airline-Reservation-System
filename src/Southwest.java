import java.util.ArrayList;

public class Southwest implements Airline {

    private ArrayList<Passenger> passengers;

    public Southwest (Passenger passenger) {

        passengers = new ArrayList<>();

        if(passenger != null)
            passengers.add(passenger);
    }
}
