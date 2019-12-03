import java.io.Serializable;
import java.util.ArrayList;

/**
 * Airline
 * <p>
 * Has aspects of an airline
 *
 * @author Rohith Rajashekarbabu, Cassandra Jessica Deckowitz, lab- B13
 * @version December 03, 2019
 */

public interface Airline extends Serializable {
    int getCurrentPassengers();

    int getMaxPassengers();

    String getGate();

    String getName();

    ArrayList<Passenger> passengerList();
}
