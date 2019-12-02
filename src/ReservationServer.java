import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
    } //ReservationServer

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
        String format = "ReservationServer[%s]";

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
        } //ClientHandler


        public ArrayList readPassList(String airlineChoice) throws IOException {
            FileReader fr = new FileReader("Reservations.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            ArrayList passList = new ArrayList();
            while (!line.equals(airlineChoice)) {
                line = bfr.readLine();
            } //now on the line that says what airline it is
            line = bfr.readLine();
            line = bfr.readLine(); //skipped the line with the numbers
            while (line != null && !line.isBlank()) {
                passList.add(line);
                line = bfr.readLine();
            }
            bfr.close();
            return passList;
        }


        public void addPassenger(Airline airline, Passenger passenger) throws IOException {
            ArrayList alaskaPass = readPassList("Alaska");
            ArrayList deltaPass = readPassList("Delta");
            ArrayList southwestPass = readPassList("Southwest");
            FileOutputStream fos = new FileOutputStream("Reservations.txt");
            PrintWriter pw = new PrintWriter(fos);
            int updatedPassNum;

            pw.println("Alaska");
            if (airline.equals(alaska)) {
                updatedPassNum = alaskaPass.size() + 1;
                pw.println(updatedPassNum + "/" + airline.getMaxPassengers());
            } else {
                pw.println(alaskaPass.size() + "/" + airline.getMaxPassengers());
            }
            for (int i = 0; i < alaskaPass.size(); i++) {
                pw.println(alaskaPass.get(i));
            }
            if (airline.equals(alaska)) {
                pw.println(passenger.getfName().substring(0, 1) + ". " + passenger.getlName() + ", " + passenger.getAge());
            }
            pw.println();

            pw.println("Delta");
            if (airline.equals(delta)) {
                updatedPassNum = deltaPass.size() + 1;
                pw.println(updatedPassNum + "/" + airline.getMaxPassengers());
            } else {
                pw.println(deltaPass.size() + "/" + airline.getMaxPassengers());
            }
            for (int i = 0; i < deltaPass.size(); i++) {
                pw.println(deltaPass.get(i));
            }
            if (airline.equals(delta)) {
                pw.println(passenger.getfName().substring(0, 1) + ". " + passenger.getlName() + ", " + passenger.getAge());
            }
            pw.println();

            pw.println("Southwest");
            if (airline.equals(southwest)) {
                updatedPassNum = southwestPass.size() + 1;
                pw.println(updatedPassNum + "/" + airline.getMaxPassengers());
            } else {
                pw.println(southwestPass.size() + "/" + airline.getMaxPassengers());
            }
            for (int i = 0; i < southwestPass.size(); i++) {
                pw.println(southwestPass.get(i));
            }
            if (airline.equals(southwest)) {
                pw.println(passenger.getfName().substring(0, 1) + ". " + passenger.getlName() + ", " + passenger.getAge());
            }

            pw.close();
        }

        public boolean canChooseFlight(String airlineChoice) throws IOException {
            FileReader fr = new FileReader("Reservations.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (!line.equals(airlineChoice)) {
                line = bfr.readLine();
            } //gets to the line with the name of the airline
            line = bfr.readLine(); //read one more line to get to the number of passengers
            String currentPass = line.substring(0, line.indexOf('/'));
            String maxPass = line.substring(line.indexOf("/") + 1);
            bfr.close();
            return (Integer.parseInt(currentPass) < Integer.parseInt(maxPass));
        }
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


                oos.writeObject(delta);
                oos.writeObject(southwest);
                oos.writeObject(alaska);
                oos.flush();


                oos.writeObject(delta);
                oos.writeObject(southwest);
                oos.writeObject(alaska);
                oos.flush();

                Passenger pass = (Passenger) (ois.readObject());

                if(pass.getAirlineName().equals("Delta Airlines")) {
                    delta.addPassengers(pass);
                    addPassenger(delta, pass);
                }
                else if(pass.getAirlineName().equals("Southwest Airlines")) {
                    southwest.addPassengers(pass);
                    addPassenger(southwest, pass);
                }
                else {
                    alaska.addPassengers(pass);
                    addPassenger(alaska, pass);
                }

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

                /*if (true) { //if a certain button is pushed? How to get this input?? This is meant to display passList
                    writer.write(readPassList("Alaska").toString()); //send this back to client?
                    writer.newLine();
                    writer.flush();
                }*/
                /*if (true) { //if they want to check if an airline has seats available
                    boolean goAhead = canChooseFlight("Delta"); //get airline from client, send result back
                }
                writer.close();
                reader.close(); */

            } catch (IOException e) {
                System.out.println(e.getMessage());
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

            } catch (ClassNotFoundException e) {
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
            String format = "ReservationRequestHandler[%s]";

            return String.format(format, this.clientSocket);
        } //toString
    }


}