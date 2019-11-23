
import java.io.Serializable;

public interface Airline extends Serializable {
    int getCurrentPassengers();
    int getMaxPassengers();
    String getGate();
}
