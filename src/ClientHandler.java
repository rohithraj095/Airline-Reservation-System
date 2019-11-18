import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Objects;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.Period;


/**
 * CountdownRequestHandler
 * <p>
 * RequestHandler program for the countdown
 *
 * @author Rohith Rajashekarbabu, lab- B13
 * @version November 07, 2019
 * <p>
 * <p>
 * A handler for requests made to a countdown server.
 *
 * <p>Purdue University -- CS18000 -- Fall 2019 -- Network I/O -- Homework</p>
 */
public final class ClientHandler implements Runnable {
    /**
     * The client socket of this request handler.
     */
    private Socket clientSocket;

    /**
     * Constructs a newly allocated {@code CountdownRequestHandler} object with the specified client socket.
     *
     * @param clientSocket the client socket to be used in construction
     * @throws NullPointerException if the specified client socket is {@code null}
     */
    public ClientHandler(Socket clientSocket) throws NullPointerException {
        Objects.requireNonNull(clientSocket, "the specified client socket is null");

        this.clientSocket = clientSocket;
    } //CountdownRequestHandler

    /**
     * Returns the time remaining until the specified event date. The returned {@code String} is of the form
     * {@code xYyMzD}, where {@code x}, {@code y}, and {@code z} are the number of years, months, and days until the
     * specified date.
     *
     * @param eventDate the event date to be used in the operation
     * @return the time remaining until the specified event date
     * @throws DateTimeParseException if the specified event date is not parsable
     */
    private String getTimeRemaining(String eventDate) throws DateTimeParseException {
        LocalDate now = LocalDate.now();
        LocalDate event = LocalDate.parse(eventDate);
        Period period = Period.between(now, event);
        int years;
        int months;
        int days;

        years = period.getYears();

        months = period.getMonths();

        days = period.getDays();

        if (years < 0) {
            years = 0;
        } //end if

        if (months < 0) {
            months = 0;
        } //end if

        if (days < 0) {
            days = 0;
        } //end if

        return String.format("%dY%dM%dD", years, months, days);
    } //getTimeRemaining

    /**
     * Handles the requests sent by the client connected to this request handler's client socket.
     */
    @Override
    public void run() {

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            writer.flush();

            String s = reader.readLine();

            String time = "";

            while (!s.equals(null)) {

                try {
                    time = getTimeRemaining(s);
                } catch (DateTimeParseException e) {
                    time = "Error: the specified event date is incorrectly formatted!";
                }

                writer.write(time);

                writer.newLine();
                writer.flush();

                s = reader.readLine();
            }


            writer.close();
            reader.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    } //run

    /**
     * Returns the hash code of this request handler.
     *
     * @return the hash code of this request handler
     */
    @Override
    public int hashCode() {
        int result = 23;

        result = result * 31 * Objects.hashCode(this.clientSocket);

        return result;
    } //hashCode

    /**
     * Determines whether or not the specified object is equal to this request handler. {@code true} is returned if and
     * only if the specified object is an instance of {@code CountdownRequestHandler} and its client socket is equal to
     * this request handler's.
     *
     * @param object the object to be used in the comparisons
     * @return {@code true}, if the specified object is equal to this request handler and {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof ClientHandler) {
            return Objects.equals(this.clientSocket, ((ClientHandler) object).clientSocket);
        } else {
            return false;
        } //end if
    } //equals

    /**
     * Returns the {@code String} representation of this request handler. The returned {@code String} consists of this
     * request handler's client socket surrounded by this class' name and square brackets ("[]").
     *
     * @return the {@code String} representation of this request handler
     */
    @Override
    public String toString() {
        String format = "CountdownRequestHandler[%s]";

        return String.format(format, this.clientSocket);
    } //toString
}