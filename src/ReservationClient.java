import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ReservationClient
 * <p>
 * Clientside for ReservationClient
 *
 * @author Rohith Rajashekarbabu, Cassandra Jessica Deckowitz, lab- B13
 * @version December 03, 2019
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
        final Socket socket;
        final BufferedWriter socketWriter;
        final BufferedReader socketReader;
        final ObjectInputStream ois;
        final ObjectOutputStream oos;
        String request;
        String response;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();


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

            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            oos = new ObjectOutputStream(socket.getOutputStream());

            ois = new ObjectInputStream(socket.getInputStream());


            JFrame jf = new JFrame("Purdue University Flight Reservation System");
            JLabel main1 = new JLabel("Welcome to the Purdue University Flight Reservation Management System!");
            JPanel buttonPane1 = new JPanel();

            JPanel title1 = new JPanel();


            ImageIcon i = new ImageIcon("Purdue_Logo.png");

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
            jf.setLocation(dim.width / 2 - jf.getSize().width / 2, dim.height / 2 - jf.getSize().height / 2);
            jf.setVisible(true);


            cancel1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == cancel1) {
                        //System.exit(0);
                        jf.dispose();
                        JOptionPane.showMessageDialog(null,
                                "Thank you for using the Purdue University Airline Management System!",
                                "Thank You!",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }
            });


            //BufferedWriter finalSocketWriter = socketWriter;
            //BufferedReader finalSocketReader = socketReader;
            ok1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == ok1) {

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
                        jf2.setLocation(dim.width / 2 - jf2.getSize().width / 2, dim.height / 2 - jf2.getSize().height / 2);
                        jf2.setVisible(true);


                        cancel2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (e.getSource() == cancel2) {
                                    //System.exit(0);
                                    jf2.dispose();
                                    JOptionPane.showMessageDialog(null,
                                            "Thank you for using the Purdue University Airline Management System!",
                                            "Thank You!",
                                            JOptionPane.PLAIN_MESSAGE);
                                }
                            }
                        });


                        ok2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (e.getSource() == ok2) {

                                    jf2.setVisible(false);

                                    ArrayList dobj= new ArrayList<>();
                                    ArrayList sobj= new ArrayList<>();
                                    ArrayList aobj= new ArrayList<>();
                                    String maxDelta = null;
                                    String maxSouthwest = null;
                                    String maxAlaska = null;

                                    try {

                                        dobj = (ArrayList) ois.readObject();
                                        sobj = (ArrayList) (ois.readObject());
                                        aobj = (ArrayList) (ois.readObject());
                                        maxDelta = (String) ois.readObject();
                                        maxSouthwest = (String) ois.readObject();
                                        maxAlaska = (String) ois.readObject();
                                    } catch (IOException | ClassNotFoundException l) {
                                        System.out.println(l.toString());
                                    }


                                    ArrayList finalDobj = dobj;
                                    ArrayList finalSobj = sobj;
                                    ArrayList finalAobj = aobj;
                                    String finalMaxDelta = maxDelta;
                                    String finalMaxSouthwest = maxSouthwest;
                                    String finalMaxAlaska = maxAlaska;

                                    /*

                                    Airline dobj2 = null;
                                    Airline sobj2 = null;
                                    Airline aobj2 = null;

                                    try {

                                        dobj2 = (Delta) (ois.readObject());
                                        sobj2 = (Southwest) (ois.readObject());
                                        aobj2 = (Alaska) (ois.readObject());
                                    } catch (IOException | ClassNotFoundException l) {
                                        System.out.println(l.toString());
                                    }


                                    Airline finalDobj2 = dobj2;
                                    Airline finalSobj2 = sobj2;
                                    Airline finalAobj2 = aobj2;


                                    ArrayList<String> messageStringsAL = new ArrayList<String>();
                                    messageStringsAL.add("Delta");
                                    messageStringsAL.add("Southwest");
                                    messageStringsAL.add("Alaska");
                                    if(finalDobj){}


                                     */

                                    ArrayList<String> messageStringsAL = new ArrayList<>();
                                    messageStringsAL.add("Delta");
                                    messageStringsAL.add("Southwest");
                                    messageStringsAL.add("Alaska");

                                    if(finalDobj.size() >= Integer.parseInt(maxDelta))
                                        messageStringsAL.remove("Delta");
                                    if(finalSobj.size() >= Integer.parseInt(maxSouthwest))
                                        messageStringsAL.remove("Southwest");
                                    if(finalAobj.size() >= Integer.parseInt(maxAlaska))
                                        messageStringsAL.remove("Alaska");

                                    JComboBox list = new JComboBox(messageStringsAL.toArray());

                                    list.setSelectedIndex(0);
                                    JTextArea msglabel = new JTextArea(35, 40);
                                    msglabel.setText("Delta Airlines is proud to be one of the five premier Airlines at Purdue University. \n" +
                                            "We are extremely proud to offer exceptional services, with free limited WiFi for all customers. \n" +
                                            "Passengers who use T-Mobile as a cell phone carrier get additional benefits. \n" +
                                            "We are also happy to offer power outlets in each seat for passenger use. \n" +
                                            "We hope you choose to fly Delta as your next Airline.");
                                    msglabel.setWrapStyleWord(true);
                                    JLabel msglabel2 = new JLabel(description);
                                    list.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (e.getSource() == list) {
                                                JComboBox cb = (JComboBox) e.getSource();
                                                String msg = (String) cb.getSelectedItem();
                                                //assert msg != null;
                                                if (msg.equals("Delta")) {
                                                    msglabel.setText("Delta Airlines is proud to be one of the five premier Airlines at Purdue University. \n" +
                                                            "We are extremely proud to offer exceptional services, with free limited WiFi for all customers. \n" +
                                                            "Passengers who use T-Mobile as a cell phone carrier get additional benefits. \n" +
                                                            "We are also happy to offer power outlets in each seat for passenger use. \n" +
                                                            "We hope you choose to fly Delta as your next Airline.");
                                                } else if (msg.equals("Alaska"))
                                                    msglabel.setText("Alaska Airlines is proud to serve the strong and knowledgeable Boilermakers from Purdue University. \n" +
                                                            "We primarily fly westward, and often have stops in Alaska and California. \n" +
                                                            "We have first class amenities, even in coach class. \n" +
                                                            "We provide fun snacks, such as pretzels and goldfish. \n" +
                                                            "We also have comfortable seats and free WiFi. \n" +
                                                            "We hope you choose Alaska Airlines for your next trip!");
                                                else
                                                    msglabel.setText("Southwest Airlines is proud to offer flights to Purdue University. \n" +
                                                            "We are happy to offer free in flight WiFi, as well as our amazing snacks. \n" +
                                                            "In addition, we offer flights much cheaper than other airlines, and offer two free checked bags. \n" +
                                                            "We hope you choose Southwest for your next flight.");
                                            }
                                        }
                                    });


                                    JFrame jf5 = new JFrame("Purdue University Flight Reservation System");
                                    JLabel main5 = new JLabel("Choose a flight from the drop down menu.");
                                    JPanel buttonPane5 = new JPanel();
                                    //JPanel

                                    JPanel title5 = new JPanel();

                                    JPanel title6 = new JPanel();

                                    JPanel title7 = new JPanel();

                                    JButton ok5 = new JButton("Choose this flight");
                                    JButton cancel5 = new JButton("Exit");

                                    buttonPane5.setLayout(new FlowLayout());


                                    //title6.add(msglabel);
                                    msglabel.setEditable(false);
                                    title7.add(msglabel);

                                    buttonPane5.add(ok5);
                                    buttonPane5.add(cancel5);

                                    title5.add(main5, BorderLayout.CENTER);
                                    title5.add(list, BorderLayout.AFTER_LINE_ENDS);

                                    list.addKeyListener(new KeyListener() {
                                        @Override
                                        public void keyTyped(KeyEvent e) {

                                        }

                                        @Override
                                        public void keyPressed(KeyEvent e) {
                                            if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                                                String nameOfAir = null;

                                                Object[] passlist = null;

                                                if (list.getSelectedItem().equals("Delta")) {
                                                    nameOfAir = "Delta Airlines. " + finalDobj.size() + ":" + finalMaxDelta;
                                                    passlist = finalDobj.toArray();
                                                } else if (list.getSelectedItem().equals("Southwest")) {
                                                    nameOfAir = "Southwest Airlines. " + finalSobj.size() + ":" + finalMaxSouthwest;
                                                    passlist = finalSobj.toArray();
                                                } else if(list.getSelectedItem().equals("Alaska")){
                                                    nameOfAir = "Alaska Airlines. " + finalAobj.size() + ":" + finalMaxAlaska;
                                                    passlist = finalAobj.toArray();
                                                }

                                                JFrame jf20 = new JFrame("Purdue University Flight Reservation System");

                                                JLabel main20 = new JLabel(nameOfAir);
                                                JPanel buttonPane20 = new JPanel();


                                                JList list5 = new JList(passlist);
                                                JScrollPane scrollableList = new JScrollPane(list5);

                                                JPanel title20 = new JPanel();
                                                JPanel title21 = new JPanel();

                                                JButton cancel20 = new JButton("Exit");

                                                buttonPane20.setLayout(new FlowLayout());

                                                buttonPane20.add(cancel20);

                                                title20.add(main20);
                                                title21.add(scrollableList);


                                                jf20.add(title20, BorderLayout.PAGE_START);
                                                jf20.add(title21, BorderLayout.CENTER);
                                                jf20.add(buttonPane20, BorderLayout.PAGE_END);
                                                jf20.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                jf20.setSize(600, 300);
                                                jf20.setVisible(true);

                                                list5.addKeyListener(new KeyListener() {
                                                    @Override
                                                    public void keyTyped(KeyEvent e) {

                                                    }

                                                    @Override
                                                    public void keyPressed(KeyEvent e) {
                                                        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                                            jf20.setVisible(false);
                                                        }
                                                    }

                                                    @Override
                                                    public void keyReleased(KeyEvent e) {

                                                    }
                                                });

                                                cancel20.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        if (e.getSource() == cancel20)
                                                            jf20.setVisible(false);
                                                    }


                                                });
                                            }
                                        }

                                        @Override
                                        public void keyReleased(KeyEvent e) {

                                        }
                                    });


                                    //jf5.add(title6, BorderLayout.AFTER_LINE_ENDS);
                                    jf5.add(title5, BorderLayout.NORTH);
                                    jf5.add(title7, BorderLayout.CENTER);
                                    //jf2.add(title4, BorderLayout.AFTER_LAST_LINE);
                                    jf5.add(buttonPane5, BorderLayout.PAGE_END);
                                    jf5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    jf5.setSize(600, 300);
                                    jf5.setLocation(dim.width / 2 - jf5.getSize().width / 2, dim.height / 2 - jf5.getSize().height / 2);
                                    jf5.setVisible(true);


                                    cancel5.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (e.getSource() == cancel5) {
                                                //System.exit(0);
                                                jf5.dispose();
                                                JOptionPane.showMessageDialog(null,
                                                        "Thank you for using the Purdue University Airline Management System!",
                                                        "Thank You!",
                                                        JOptionPane.PLAIN_MESSAGE);
                                            }
                                        }


                                    });

                                    ok5.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (e.getSource() == ok5) {

                                                jf5.setVisible(false);


                                                JFrame jf200 = new JFrame("Purdue University Flight Reservation System");

                                                String whatName;

                                                if (list.getSelectedIndex() == 0) {
                                                    whatName = "Delta Airlines";
                                                } else if (list.getSelectedIndex() == 1) {
                                                    whatName = "Southwest Airlines";
                                                } else {
                                                    whatName = "Alaska Airlines";
                                                }

                                                String sure = "Are you sure you want to book a flight on " + whatName + "?";
                                                JLabel main200 = new JLabel(sure);
                                                JPanel buttonPane200 = new JPanel();

                                                JPanel title200 = new JPanel();
                                                title200.setFocusable(true);
                                                title200.requestFocusInWindow();

                                                JButton ok200 = new JButton("Yes, I want this flight.");
                                                JButton diff200 = new JButton("No, I want a different flight.");
                                                JButton cancel200 = new JButton("Exit");

                                                buttonPane200.setLayout(new FlowLayout());

                                                buttonPane200.add(ok200);
                                                buttonPane200.add(diff200);
                                                buttonPane200.add(cancel200);

                                                title200.add(main200, BorderLayout.CENTER);


                                                title200.addKeyListener(new KeyListener() {
                                                    @Override
                                                    public void keyTyped(KeyEvent e) {

                                                    }

                                                    @Override
                                                    public void keyPressed(KeyEvent e) {
                                                        if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                                                            String nameOfAir = null;

                                                            Object[] passlist = null;

                                                            if (list.getSelectedItem().equals("Delta")) {
                                                                nameOfAir = "Delta Airlines. " + finalDobj.size() + ":" + finalMaxDelta;
                                                                passlist = finalDobj.toArray();
                                                            } else if (list.getSelectedItem().equals("Southwest")) {
                                                                nameOfAir = "Southwest Airlines. " + finalSobj.size() + ":" + finalMaxSouthwest;
                                                                passlist = finalSobj.toArray();
                                                            } else if(list.getSelectedItem().equals("Alaska")){
                                                                nameOfAir = "Alaska Airlines. " + finalAobj.size() + ":" + finalMaxAlaska;
                                                                passlist = finalAobj.toArray();
                                                            }

                                                            JFrame jf20 = new JFrame("Purdue University Flight Reservation System");

                                                            JLabel main20 = new JLabel(nameOfAir);
                                                            JPanel buttonPane20 = new JPanel();


                                                            JList list5 = new JList(passlist);
                                                            JScrollPane scrollableList = new JScrollPane(list5);

                                                            JPanel title20 = new JPanel();
                                                            JPanel title21 = new JPanel();

                                                            JButton cancel20 = new JButton("Exit");

                                                            buttonPane20.setLayout(new FlowLayout());

                                                            buttonPane20.add(cancel20);

                                                            title20.add(main20);
                                                            title21.add(scrollableList);


                                                            jf20.add(title20, BorderLayout.PAGE_START);
                                                            jf20.add(title21, BorderLayout.CENTER);
                                                            jf20.add(buttonPane20, BorderLayout.PAGE_END);
                                                            jf20.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                            jf20.setSize(600, 300);
                                                            jf20.setVisible(true);

                                                            list5.addKeyListener(new KeyListener() {
                                                                @Override
                                                                public void keyTyped(KeyEvent e) {

                                                                }

                                                                @Override
                                                                public void keyPressed(KeyEvent e) {
                                                                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                                                        jf20.setVisible(false);
                                                                    }
                                                                }

                                                                @Override
                                                                public void keyReleased(KeyEvent e) {

                                                                }
                                                            });

                                                            cancel20.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (e.getSource() == cancel20)
                                                                        jf20.setVisible(false);
                                                                }


                                                            });
                                                        }
                                                    }

                                                    @Override
                                                    public void keyReleased(KeyEvent e) {

                                                    }
                                                });


                                                jf200.add(title200, BorderLayout.PAGE_START);
                                                jf200.add(buttonPane200, BorderLayout.PAGE_END);
                                                jf200.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                jf200.setSize(600, 300);
                                                jf200.setLocation(dim.width / 2 - jf200.getSize().width / 2, dim.height / 2 - jf200.getSize().height / 2);
                                                jf200.setVisible(true);


                                                cancel200.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        if (e.getSource() == cancel200) {
                                                            //System.exit(0);
                                                            jf200.dispose();
                                                            JOptionPane.showMessageDialog(null,
                                                                    "Thank you for using the Purdue University Airline Management System!",
                                                                    "Thank You!",
                                                                    JOptionPane.PLAIN_MESSAGE);
                                                        }
                                                    }
                                                });

                                                diff200.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        if (e.getSource() == diff200) {
                                                            jf200.setVisible(false);
                                                            jf5.setVisible(true);
                                                        }
                                                    }
                                                });


                                                ok200.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        if (e.getSource() == ok200) {


                                                            jf200.setVisible(false);


                                                            JFrame frame = new JFrame("Purdue University Flight Reservation System");
                                                            JLabel main = new JLabel("Please enter your information below.");
                                                            JPanel buttonPane = new JPanel();
                                                            JPanel fieldsPanel = new JPanel();
                                                            fieldsPanel.setFocusable(true);
                                                            fieldsPanel.requestFocusInWindow();
                                                            JPanel title = new JPanel();
                                                            JLabel fName = new JLabel("What is your first name?");
                                                            JLabel lName = new JLabel("What is your last name?");
                                                            JLabel age = new JLabel("What is your age?");
                                                            JTextField fNameText = new JTextField();
                                                            JTextField lNameText = new JTextField();
                                                            JTextField ageText = new JTextField();
                                                            JButton ok = new JButton("Next");
                                                            JButton cancel = new JButton("Exit");

                                                            fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
                                                            buttonPane.setLayout(new FlowLayout());


                                                            fieldsPanel.addKeyListener(new KeyListener() {
                                                                @Override
                                                                public void keyTyped(KeyEvent e) {

                                                                }

                                                                @Override
                                                                public void keyPressed(KeyEvent e) {
                                                                    if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                                                                        String nameOfAir = null;

                                                                        Object[] passlist = null;

                                                                        if (list.getSelectedItem().equals("Delta")) {
                                                                            nameOfAir = "Delta Airlines. " + finalDobj.size() + ":" + finalMaxDelta;
                                                                            passlist = finalDobj.toArray();
                                                                        } else if (list.getSelectedItem().equals("Southwest")) {
                                                                            nameOfAir = "Southwest Airlines. " + finalSobj.size() + ":" + finalMaxSouthwest;
                                                                            passlist = finalSobj.toArray();
                                                                        } else if(list.getSelectedItem().equals("Alaska")){
                                                                            nameOfAir = "Alaska Airlines. " + finalAobj.size() + ":" + finalMaxAlaska;
                                                                            passlist = finalAobj.toArray();
                                                                        }

                                                                        JFrame jf20 = new JFrame("Purdue University Flight Reservation System");

                                                                        JLabel main20 = new JLabel(nameOfAir);
                                                                        JPanel buttonPane20 = new JPanel();


                                                                        JList list5 = new JList(passlist);
                                                                        JScrollPane scrollableList = new JScrollPane(list5);

                                                                        JPanel title20 = new JPanel();
                                                                        JPanel title21 = new JPanel();

                                                                        JButton cancel20 = new JButton("Exit");

                                                                        buttonPane20.setLayout(new FlowLayout());

                                                                        buttonPane20.add(cancel20);

                                                                        title20.add(main20);
                                                                        title21.add(scrollableList);


                                                                        jf20.add(title20, BorderLayout.PAGE_START);
                                                                        jf20.add(title21, BorderLayout.CENTER);
                                                                        jf20.add(buttonPane20, BorderLayout.PAGE_END);
                                                                        jf20.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                                        jf20.setSize(600, 300);
                                                                        jf20.setVisible(true);

                                                                        list5.addKeyListener(new KeyListener() {
                                                                            @Override
                                                                            public void keyTyped(KeyEvent e) {

                                                                            }

                                                                            @Override
                                                                            public void keyPressed(KeyEvent e) {
                                                                                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                                                                    jf20.setVisible(false);
                                                                                }
                                                                            }

                                                                            @Override
                                                                            public void keyReleased(KeyEvent e) {

                                                                            }
                                                                        });

                                                                        cancel20.addActionListener(new ActionListener() {
                                                                            @Override
                                                                            public void actionPerformed(ActionEvent e) {
                                                                                if (e.getSource() == cancel20)
                                                                                    jf20.setVisible(false);
                                                                            }


                                                                        });
                                                                    }
                                                                }

                                                                @Override
                                                                public void keyReleased(KeyEvent e) {

                                                                }
                                                            });


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
                                                            frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
                                                            frame.setVisible(true);


                                                            cancel.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (e.getSource() == cancel) {
                                                                        //System.exit(0);
                                                                        frame.dispose();
                                                                        JOptionPane.showMessageDialog(null,
                                                                                "Thank you for using the Purdue University Airline Management System!",
                                                                                "Thank You!",
                                                                                JOptionPane.PLAIN_MESSAGE);
                                                                    }
                                                                }


                                                            });

                                                            ok.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (e.getSource() == ok) {

                                                                        if (!(fNameText.getText().matches("[a-zA-Z-]+") && lNameText.getText().matches("[a-zA-Z-]+") && ageText.getText().matches("[0-9]+") && Integer.parseInt(ageText.getText()) >= 0)) {
                                                                            JOptionPane.showMessageDialog(null,
                                                                                    "Make sure your name contains only alphabets and hyphens and your age is only digits and above 0",
                                                                                    "Error",
                                                                                    JOptionPane.WARNING_MESSAGE);
                                                                            frame.repaint();
                                                                        } else {
                                                                            int age = Integer.parseInt(ageText.getText());

                                                                            String info = "Are all the details you entered correct? \n" +
                                                                                    "The passenger's name is " + fNameText.getText() + " " + lNameText.getText() +
                                                                                    " and their age is " + age + ". \nIf all the information shown in correct, " +
                                                                                    "select the Yes button below, otherwise, select the No button.";

                                                                            int cont = JOptionPane.showConfirmDialog(null,
                                                                                    info,
                                                                                    "Confirm Info",
                                                                                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

                                                                            if (cont == JOptionPane.NO_OPTION) {
                                                                                frame.repaint();
                                                                            } else {
                                                                                frame.setVisible(false);

                                                                                //Passenger passenger = new Passenger(fNameText.getText(), lNameText.getText(), age, );

                                                                                String request = fNameText.getText() + lNameText.getText() + age;

                                                                                Passenger passenger = null;
                                                                                Airline deltobj = null;
                                                                                Airline swobj = null;
                                                                                Airline alaskobj = null;

                                                                                try {

                                                                                    String airline;

                                                                                    if (list.getSelectedIndex() == 0) {
                                                                                        airline = "Delta Airlines";
                                                                                    } else if (list.getSelectedIndex() == 1) {
                                                                                        airline = "Southwest Airlines";
                                                                                    } else {
                                                                                        airline = "Alaska Airlines";
                                                                                    }

                                                                                    int test = 1;


                                                                                    //socketWriter.write(test);
                                                                                    //String s = socketReader.readLine();

                                                                                    //socketWriter.flush();

                                                                /*


                                                                if(list.getSelectedIndex() == 0){
                                                                    socketWriter.write("Delta");
                                                                    socketWriter.flush();
                                                                    airlineobj = (Delta) (ois.readObject());
                                                                }
                                                                else if(list.getSelectedIndex() == 1){
                                                                    socketWriter.write("Southwest");
                                                                    socketWriter.flush();
                                                                    airlineobj = (Southwest) (ois.readObject());
                                                                    }
                                                                else{
                                                                    socketWriter.write("Alaska");
                                                                    socketWriter.flush();
                                                                    airlineobj = (Alaska) (ois.readObject());
                                                                }


                                                                 */

                                                                                    deltobj = (Delta) (ois.readObject());
                                                                                    swobj = (Southwest) (ois.readObject());
                                                                                    alaskobj = (Alaska) (ois.readObject());


                                                                                    if (list.getSelectedIndex() == 0) {
                                                                                        passenger = new Passenger(fNameText.getText(), lNameText.getText(), age, deltobj);
                                                                                    } else if (list.getSelectedIndex() == 1) {
                                                                                        passenger = new Passenger(fNameText.getText(), lNameText.getText(), age, swobj);
                                                                                    } else {
                                                                                        passenger = new Passenger(fNameText.getText(), lNameText.getText(), age, alaskobj);
                                                                                    }


                                                                                    //String response = socketReader.readLine();

                                                                                    //System.out.println();

                                                                                    String bp = passenger.getBoardingPass(passenger).toString();

                                                                                    oos.writeObject(passenger);
                                                                                    oos.flush();

                                                                                    //socketWriter.write("get");

                                                                                    //System.out.println(bp);

                                                                                    //System.out.println();

                                                                                } catch (IOException | ClassNotFoundException f) {
                                                                                    System.out.println(f.toString());
                                                                                }

                                                                                boolean docont = true;

                                                                                ArrayList passlist= new ArrayList<>();


                                                                                try {

                                                                                    //deltobj = (Delta) (ois.readObject());
                                                                                    //swobj = (Southwest) (ois.readObject());
                                                                                    //alaskobj = (Alaska) (ois.readObject());
                                                                                    passlist = (ArrayList) ois.readObject();

                                                                                } catch (IOException | ClassNotFoundException h) {
                                                                                    System.out.println(h.toString());
                                                                                }


                                                                                JFrame jf20 = new JFrame("Purdue University Flight Reservation System");

                                                                                String show = "Flight data displaying for " + passenger.getAirlineName() + ". " + "Enjoy your flight! " + "Flight is now boarding at Gate " + passenger.getGate();
                                                                                JLabel main20 = new JLabel(show);
                                                                                JLabel main21 = new JLabel(passenger.getBoardingPass(passenger).toString());
                                                                                JPanel buttonPane20 = new JPanel();



                                                                                /*if (list.getSelectedIndex() == 0) {
                                                                                    passlist = deltobj.passengerList().toArray();
                                                                                } else if (list.getSelectedIndex() == 1) {
                                                                                    passlist = swobj.passengerList().toArray();
                                                                                } else {
                                                                                    passlist = alaskobj.passengerList().toArray();
                                                                                }

                                                                                 */

                                                                                /*DefaultListModel listModel = new DefaultListModel();

                                                                                listModel.addElement(passenger.toString());

                                                                                for (int a = 0; a < passlist.length; a++) {
                                                                                    listModel.addElement(passlist[a].toString());
                                                                                }

                                                                                 */


                                                                                JList list5 = new JList(passlist.toArray());

                                                                                JScrollPane scrollableList = new JScrollPane(list5);
                                                                                JTextArea passbp = new JTextArea(35, 40);
                                                                                passbp.setText(passenger.getBoardingPass(passenger).toString());
                                                                                passbp.setWrapStyleWord(true);
                                                                                passbp.setEditable(false);

                                                                                JPanel title20 = new JPanel();
                                                                                JPanel title21 = new JPanel();

                                                                                JButton ok20 = new JButton("Refresh Flight Status");
                                                                                JButton cancel20 = new JButton("Exit");

                                                                                buttonPane20.setLayout(new FlowLayout());

                                                                                buttonPane20.add(ok20);
                                                                                buttonPane20.add(cancel20);

                                                                                title20.add(main20);
                                                                                title21.add(scrollableList);


                                                                                jf20.add(title20, BorderLayout.PAGE_START);
                                                                                jf20.add(title21, BorderLayout.CENTER);
                                                                                jf20.add(passbp, BorderLayout.AFTER_LINE_ENDS);
                                                                                jf20.add(buttonPane20, BorderLayout.PAGE_END);
                                                                                jf20.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                                                                jf20.setSize(600, 300);
                                                                                jf20.setLocation(dim.width / 2 - jf20.getSize().width / 2, dim.height / 2 - jf20.getSize().height / 2);
                                                                                jf20.setVisible(true);

                                                                                cancel20.addActionListener(new ActionListener() {
                                                                                    @Override
                                                                                    public void actionPerformed(ActionEvent e) {
                                                                                        if (e.getSource() == cancel20) {
                                                                                            //System.exit(0);
                                                                                            jf20.dispose();
                                                                                            JOptionPane.showMessageDialog(null,
                                                                                                    "Thank you for using the Purdue University Airline Management System!",
                                                                                                    "Thank You!",
                                                                                                    JOptionPane.PLAIN_MESSAGE);
                                                                                        }
                                                                                    }

                                                                                });

                                                                                ok20.addActionListener(new ActionListener() {
                                                                                    @Override
                                                                                    public void actionPerformed(ActionEvent e) {
                                                                                        if (e.getSource() == ok20) {


                                                                                            Object[] passlist = null;

                                                                                            try {
                                                                                                passlist = (Object[]) ois.readObject();
                                                                                                //System.out.println(deltobj.passengerList().toString());
                                                                                            } catch (IOException | ClassNotFoundException h) {
                                                                                                System.out.println(h.toString());
                                                                                            }


                                                                                            list5.setListData(passlist);
                                                                                            jf20.repaint();

                                                                            /*

                                                                            Object[] passlist = null;


                                                                            if(list.getSelectedIndex() == 0){
                                                                                passlist = deltobj.passengerList().toArray();
                                                                            }
                                                                            else if(list.getSelectedIndex() == 1){
                                                                                passlist = swobj.passengerList().toArray();
                                                                            }
                                                                            else{
                                                                                passlist = alaskobj.passengerList().toArray();
                                                                            }

                                                                            JList list5 = new JList(passlist);

                                                                            JScrollPane scrollableList = new JScrollPane(list5);

                                                                            title21.remove(scrollableList);

                                                                            title21.add(scrollableList);

                                                                            jf20.remove(title21);

                                                                            jf20.add(title21, BorderLayout.CENTER);
                                                                            */


                                                                                        }
                                                                                    }

                                                                                });

                                                                            }


                                                                        }
                                                                    }
                                                                }


                                                            });

                                                        }
                                                    }


                                                });

                                            }
                                        }
                                    });

                                }
                            }
                        });


                    }
                }
            });


            //end while

            //System.out.println();

            //System.out.println("Goodbye!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                userInputReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } //end try catch

            /*if (socketWriter != null) {
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
            }

             */

            //end if

        } //end try catch finally
    } //main
    public class ResponseListener {
    }
}