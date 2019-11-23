import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private static String description;


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
                return;
            } //end if

            portString = JOptionPane.showInputDialog(null,
                    "What is the port you'd like to connect to?",
                    "Port?",
                    JOptionPane.INFORMATION_MESSAGE);

            if (portString == null) {
                return;
            } else if (!isParsable(portString)) {
                return;
            } else {
                port = Integer.parseInt(portString);
            } //end if

            socket = new Socket(hostname, port);

            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println();





            JFrame jf = new JFrame("Purdue University Flight Reservation System");
            JLabel main1 = new JLabel("Welcome to the Purdue University Flight Reservation Management System!");
            JPanel buttonPane1 = new JPanel();

            JPanel title1 = new JPanel();


            ImageIcon i =  new ImageIcon("src/Purdue_Logo.png");

            JButton ok1 = new JButton("Next");
            JButton cancel1 = new JButton("Exit");

            buttonPane1.setLayout(new FlowLayout());

            buttonPane1.add(ok1);
            buttonPane1.add(cancel1);

            title1.add(main1, BorderLayout.CENTER);




            jf.add(title1, BorderLayout.PAGE_START);
            jf.add(new JLabel(i));
            jf.add(buttonPane1, BorderLayout.PAGE_END);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setSize(600, 300);
            jf.setVisible(true);



            cancel1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource () == cancel1)
                        System.exit(0);
                }
            });


            ok1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource () == ok1){

                        jf.setVisible(false);


                        JFrame jf2 = new JFrame("Purdue University Flight Reservation System");
                        JLabel main2 = new JLabel("Do you want to book a flight today?");
                        JPanel buttonPane2 = new JPanel();

                        JPanel title2 = new JPanel();

                        JButton ok2 = new JButton("Yes, I want to book a flight.");
                        JButton cancel2 = new JButton("Exit");

                        buttonPane2.setLayout(new FlowLayout());

                        buttonPane2.add(ok2);
                        buttonPane2.add(cancel2);

                        title2.add(main2, BorderLayout.CENTER);


                        jf2.add(title2, BorderLayout.PAGE_START);
                        jf2.add(buttonPane2, BorderLayout.PAGE_END);
                        jf2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        jf2.setSize(600, 300);
                        jf2.setVisible(true);


                        cancel2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(e.getSource () == cancel2)
                                    System.exit(0);
                            }
                        });




                        ok2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(e.getSource () == ok2) {

                                    jf2.setVisible(false);


                                    String[] messageStrings = {"Delta", "Southwest", "Alaska"};

                                    JComboBox list = new JComboBox(messageStrings);

                                    list.setSelectedIndex(0);
                                    JLabel msglabel = new JLabel(description);
                                    list.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if(e.getSource() == list) {
                                                JComboBox cb = (JComboBox)e.getSource();
                                                String msg = (String)cb.getSelectedItem();
                                                //assert msg != null;
                                                if(msg.equals("Delta"))
                                                    msglabel.setText("delta air");
                                                else if(msg.equals("Alaska"))
                                                    msglabel.setText("Alaska air");
                                                else
                                                    msglabel.setText("Southwest");
                                            }
                                        }
                                    });



                                    JFrame jf5 = new JFrame("Purdue University Flight Reservation System");
                                    JLabel main5 = new JLabel("Choose a flight from the drop down menu.");
                                    JPanel buttonPane5 = new JPanel();

                                    JPanel title5 = new JPanel();

                                    JPanel title6 = new JPanel();

                                    JButton ok5 = new JButton("Choose this flight");
                                    JButton cancel5 = new JButton("Exit");

                                    buttonPane5.setLayout(new FlowLayout());

                                    title6.add(list);
                                    title6.add(msglabel);

                                    buttonPane5.add(ok5);
                                    buttonPane5.add(cancel5);

                                    title5.add(main5, BorderLayout.CENTER);




                                    jf5.add(title5, BorderLayout.PAGE_START);
                                    jf5.add(title6, BorderLayout.CENTER);
                                    //jf2.add(title4, BorderLayout.AFTER_LAST_LINE);
                                    jf5.add(buttonPane5, BorderLayout.PAGE_END);
                                    jf5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    jf5.setSize(600, 300);
                                    jf5.setVisible(true);


                                    cancel5.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if(e.getSource () == cancel5)
                                                System.exit(0);
                                        }


                                    });

                                    ok5.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if(e.getSource () == ok5) {

                                                JFrame frame = new JFrame("Purdue University Flight Reservation System");
                                                JLabel main = new JLabel("Please enter your information below.");
                                                JPanel buttonPane = new JPanel();
                                                JPanel fieldsPanel = new JPanel();
                                                JPanel title = new JPanel();
                                                JLabel fName = new JLabel("What is your first name?");
                                                JLabel lName = new JLabel("What is your last name?");
                                                JLabel age = new JLabel("What is your age?");
                                                JTextField fNameText = new JTextField("");
                                                JTextField lNameText = new JTextField("");
                                                JTextField ageText = new JTextField("");
                                                JButton ok = new JButton("Next");
                                                JButton cancel = new JButton("Exit");

                                                fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
                                                buttonPane.setLayout(new FlowLayout());

                                                title.add(main, BorderLayout.CENTER);
                                                fieldsPanel.add(fName);
                                                fieldsPanel.add(fNameText);
                                                fieldsPanel.add(lName);
                                                fieldsPanel.add(lNameText);
                                                fieldsPanel.add(age);
                                                fieldsPanel.add(ageText);
                                                buttonPane.add(ok);
                                                buttonPane.add(cancel);
                                                frame.add(title, BorderLayout.PAGE_START);
                                                frame.add(fieldsPanel);
                                                frame.add(buttonPane, BorderLayout.PAGE_END);

                                                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                frame.setSize(600, 300);
                                                frame.setVisible(true);

                                            }
                                        }


                                    });

                            }}
                        });


                    }
                }
            });




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