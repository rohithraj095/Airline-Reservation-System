
import java.io.Serializable;
import java.util.ArrayList;

public interface Airline extends Serializable {
    int getCurrentPassengers();
    int getMaxPassengers();
    String getGate();
    String getName();
    ArrayList<Passenger> passengerList();
}
