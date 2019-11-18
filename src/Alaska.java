import java.util.ArrayList;

public class Alaska implements Airline {

    private ArrayList<Passenger> passengers;

    public Alaska(Passenger passenger) {

        passengers = new ArrayList<>();

        if(passenger != null)
            passengers.add(passenger);
    }
}
