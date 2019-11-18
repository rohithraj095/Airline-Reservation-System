import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

/**
 * CountdownClient
 *
 * Client program for the countdown
 *
 * @author Rohith Rajashekarbabu, lab- B13
 * @version November 07, 2019
 *
 *
 * A client used to connect to a {@code CountdownServer} instance.
 *
 * <p>Purdue University -- CS18000 -- Fall 2019 -- Network I/O -- Homework</p>
 */
public final class ReservationClient {
    /**
     * Determines whether or not the specified {@code String} is parsable as a non-negative {@code int}.
     *
     * @param string the {@code String} to be used in the operation
     * @return {@code true}, if the specified {@code String} is parsable as a non-negative {@code int} and
     * {@code false} otherwise
     */
    private static boolean isParsable(String string) {
        return string.chars()
                .mapToObj(Character::isDigit)
                .reduce(Boolean::logicalAnd)
                .orElse(Boolean.FALSE);
    } //isParsable

    /**
     * Connects to a {@code CountdownServer} instance and sends requests.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        String hostname;
        String portString;
        int port;
        Socket socket;
        BufferedWriter socketWriter = null;
        BufferedReader socketReader = null;
        String request;
        String response;

        try {

            hostname = JOptionPane.showInputDialog(null,
                    "What is the hostname you'd like to connect to?",
                    "Hostname?",
                    JOptionPane.INFORMATION_MESSAGE);

            if (hostname == null) {
                System.out.println();

                System.out.println("Goodbye!");

                return;
            } //end if

            portString = JOptionPane.showInputDialog(null,
                    "What is the port you'd like to connect to?",
                    "Port?",
                    JOptionPane.INFORMATION_MESSAGE);

            if (portString == null) {
                System.out.println();

                System.out.println("Goodbye!");

                return;
            } else if (!isParsable(portString)) {
                System.out.println();

                System.out.println("Error: the specified port must be a non-negative integer!");

                System.out.println();

                System.out.println("Goodbye!");

                return;
            } else {
                port = Integer.parseInt(portString);
            } //end if

            socket = new Socket(hostname, port);

            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println();

            int result = JOptionPane.showConfirmDialog(null,
                    "Welcome to the Purdue University Airline Reservation Management System!", "Purdue University Flight Reservation System",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

            System.out.print("Enter your request: ");

            request = userInputReader.readLine();

            while (request != null) {
                socketWriter.write(request);

                socketWriter.newLine();

                socketWriter.flush();

                response = socketReader.readLine();

                System.out.println();

                System.out.printf("Response from the server: %s%n", response);

                System.out.println();

                request = userInputReader.readLine();
            } //end while

            System.out.println();

            System.out.println("Goodbye!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                userInputReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } //end try catch

            if (socketWriter != null) {
                try {
                    socketWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } //end try catch
            } //end if

            if (socketReader != null) {
                try {
                    socketReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } //end try catch
            } //end if
        } //end try catch finally
    } //main
}