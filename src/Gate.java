import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Gate
 * <p>
 * Has aspects of a Gate for airlines
 *
 * @author Rohith Rajashekarbabu, Cassandra Jessica Deckowitz, lab- B13
 * @version December 03, 2019
 */

public class Gate implements Serializable {

    private int randNum;
    private ArrayList<String> alphas;
    private String alpha;

    public Gate() {

        Random rand = new Random();

        alphas = new ArrayList<String>();

        alphas.add("A");
        alphas.add("B");
        alphas.add("C");

        int randAlpha = rand.nextInt(alphas.size());

        alpha = alphas.get(randAlpha);

        //alphas.remove(randAlpha);

        randNum = rand.nextInt(18);
        randNum++;
    }

    public String toString() {
        return "" + alpha + "" + randNum;
    }
}
