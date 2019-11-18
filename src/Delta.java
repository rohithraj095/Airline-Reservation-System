import java.util.ArrayList;

public class Delta implements Airline {

    private ArrayList<Passenger> passengers;

    public Delta (Passenger passenger) {

        passengers = new ArrayList<>();

        if(passenger != null)
            passengers.add(passenger);
    }
}
