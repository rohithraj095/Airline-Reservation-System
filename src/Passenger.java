public class Passenger {

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

    public Airline getAirline(){
        return airline;
    }

    public String toString(){
        return fName.substring(0,1).toUpperCase() + ". " + lName.toUpperCase() + ", " + age;
    }
}
