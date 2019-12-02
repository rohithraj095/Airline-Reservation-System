import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * CountdownServer
 *
 * Server program for the countdown
 *
 * @author Rohith Rajashekarbabu, lab- B13
 * @version November 07, 2019
 *
 *
 * A server used to obtain the years, months, and days until a date requested by a client.
 *
 * <p>Purdue University -- CS18000 -- Fall 2019 -- Network I/O -- Homework</p>
 */
public final class ReservationServer {
    /**
     * The server socket of this server.
     */
    private ServerSocket serverSocket;
    static Alaska alaska = new Alaska();
    static Delta delta = new Delta();
    static Southwest southwest = new Southwest();

    /**
     * Constructs a newly allocated {@code CountdownServer} object.
     *
     * @throws IOException if an I/O exception occurs
     */

    public static void main(String[] args) {
        ReservationServer server;

        try {
            server = new ReservationServer();
        } catch (IOException e) {
            e.printStackTrace();

            return;
        } //end try catch

        server.serveClients();
    } //main

    public ReservationServer() throws IOException {
        this.serverSocket = new ServerSocket(0);
    } //CountdownServer

    /**
     * Serves the clients that connect to this server.
     */
    public void serveClients() {
        Socket clientSocket;
        ClientHandler handler;
        Thread handlerThread;
        int connectionCount = 0;

        System.out.printf("<Now serving clients on port %d...>%n", this.serverSocket.getLocalPort());

        while (true) {
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();

                break;
            } //end try catch


            handler = new ClientHandler(clientSocket);

            handlerThread = new Thread(handler);

            handlerThread.start();

            System.out.printf("<Client %d connected...>%n", connectionCount);

            connectionCount++;
        } //end while
    } //serveClients

    /**
     * Returns the hash code of this server.
     *
     * @return the hash code of this server
     */
    @Override
    public int hashCode() {
        int result = 23;

        result = result * 31 * Objects.hashCode(this.serverSocket);

        return result;
    } //hashCode

    public static Delta getDelta(){
        return delta;
    }

    public static Southwest getSouthwest(){
        return southwest;
    }

    public static Alaska getAlaska(){
        return alaska;
    }

    /**
     * Determines whether or not the specified object is equal to this server. {@code true} is returned if and only if
     * the specified object is an instance of {@code CountdownServer} and its server socket is equal to this server's.
     *
     * @param object the object to be used in the comparisons
     * @return {@code true}, if the specified object is equal to this server and {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof ReservationServer) {
            return Objects.equals(this.serverSocket, ((ReservationServer) object).serverSocket);
        } else {
            return false;
        } //end if
    } //equals

    /**
     * Returns the {@code String} representation of this server. The returned {@code String} consists of this server's
     * server socket surrounded by this class' name and square brackets ("[]").
     *
     * @return the {@code String} representation of this server
     */
    @Override
    public String toString() {
        String format = "CountdownServer[%s]";

        return String.format(format, this.serverSocket);
    } //toString



    public static final class ClientHandler implements Runnable {
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

                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

                Delta delta = ReservationServer.getDelta();
                Southwest southwest = ReservationServer.getSouthwest();
                Alaska alaska = ReservationServer.getAlaska();

                //writer.flush();

                //writer.write(delta.getGate());
                //writer.flush();

                //String airlineName = reader.readLine();

                /*

                if(airlineName.equals("Delta")){
                    oos.writeObject(delta);
                }
                else if(airlineName.equals("Southwest")){
                    oos.writeObject(southwest);
                }
                else if(airlineName.equals("Alaska")){
                    oos.writeObject(alaska);
                }


                 */


                oos.writeObject(delta);
                oos.writeObject(southwest);
                oos.writeObject(alaska);
                oos.flush();


                oos.writeObject(delta);
                oos.writeObject(southwest);
                oos.writeObject(alaska);
                oos.flush();

                Passenger pass = (Passenger) (ois.readObject());

                if(pass.getAirlineName().equals("Delta Airlines"))
                    delta.addPassengers(pass);
                else if(pass.getAirlineName().equals("Southwest Airlines"))
                    southwest.addPassengers(pass);
                else
                    alaska.addPassengers(pass);

                for (int a = 0; a < delta.passengerList().size(); a++) {
                    System.out.println(delta.passengerList().get(a).toString());
                }

                        oos.writeObject(delta);
                        oos.writeObject(southwest);
                        oos.writeObject(alaska);
                        oos.flush();

                while(true) {
                    oos.writeObject(delta);
                    oos.writeObject(southwest);
                    oos.writeObject(alaska);
                    oos.flush();
                }

                //Delta delt = (Delta) (ois.readObject());
                //System.out.println(delt.getGate());


                //

                //String s = reader.readLine();
                //System.out.println(s);
                //oos.writeObject(southwest);
                //oos.writeObject(alaska);


                /*



                 */


                //oos.flush();

                //oos.close();

                //System.out.println(s);

                //String time = "";


                    /*
                    try {
                        time = getTimeRemaining(s);
                    } catch (DateTimeParseException e) {
                        time = "Error: the specified event date is incorrectly formatted!";
                    }
                    */


                    //writer.write(s);

                    //writer.newLine();
                    //writer.flush();

                    //s = reader.readLine();


                //writer.close();
                //reader.close();

            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.toString());
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


}