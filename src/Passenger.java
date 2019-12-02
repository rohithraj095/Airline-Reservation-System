import java.io.Serializable;

public class Passenger implements Serializable {

    private String fName;
    private String lName;
    private int age;
    private Airline airline;

    public Passenger(String fName, String lName, int age, Airline airline) {
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.airline = airline;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public int getAge() {
        return age;
    }

    public BoardingPass getBoardingPass(Passenger passenger){
        return new BoardingPass(passenger);
    }

    public String getAirlineName() {
        return airline.getName();
    }

    public String toString(){
        return fName.substring(0,1).toUpperCase() + ". " + lName.toUpperCase() + ", " + age;
    }

    public String getGate(){
        return airline.getGate();
    }

}
