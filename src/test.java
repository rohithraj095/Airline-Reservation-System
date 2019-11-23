import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test {

    private static String description;

    public static void main(String[] args){
        /*
        JFrame jf = new JFrame("P U F R S");
        JButton button = new JButton("Push");
        JButton button1 = new JButton("exit");
        jf.setSize(640, 480);
        jf.add(button, BorderLayout.SOUTH);
        jf.add(button1, BorderLayout.SOUTH);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true);


        final JFrame frame = new JFrame("JTextField Demo");

        JLabel lblFName = new JLabel("First Name:");
        JTextField tfFName = new JTextField(20);
        lblFName.setLabelFor(tfFName);

        JLabel lblLName = new JLabel("Last Name:");
        JTextField tfLName = new JTextField(20);
        lblLName.setLabelFor(tfLName);

        JPanel panel = new JPanel();
        panel.setLayout(new SpringLayout());

        panel.add(lblFName);
        panel.add(tfFName);
        panel.add(lblLName);
        panel.add(tfLName);

        //SpringUtilities.makeCompactGrid(panel,
                //2, 2,  //rows, cols
                //6, 6,  //initX, initY
                //6, 6); //xPad, yPad

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.getContentPane().add(panel);
        frame.setVisible(true);


        JFrame jf = new JFrame("Purdue University Flight Reservation System");
        JPanel jp = new JPanel();
        JPanel jp2 = new JPanel();

        JLabel lblFName = new JLabel("What is your first name?");
        JTextField tfFName = new JTextField(20);
        lblFName.setLabelFor(tfFName);

        JLabel lblLName = new JLabel("What is your last name?");
        JTextField tfLName = new JTextField(20);
        lblLName.setLabelFor(tfLName);

        jp.add(lblFName);
        jp.add(tfFName);
        jp.add(lblLName);
        jp.add(tfLName);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(200, 300);
        jf.getContentPane().add(jp);
        //jf.getContentPane().add(jp2);
        jf.setVisible(true);




        //ImageIcon icon = createImageIcon("images/middle.gif");


        ImageIcon icon = new ImageIcon("images/middle.gif",
                "a pretty but meaningless splat");

        JLabel label1 = new JLabel("Image and Text",
                icon,
                JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setHorizontalTextPosition(JLabel.CENTER);



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



         */

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



        JFrame jf2 = new JFrame("Purdue University Flight Reservation System");
        JLabel main2 = new JLabel("Choose a flight from the drop down menu.");
        JPanel buttonPane2 = new JPanel();

        JPanel title2 = new JPanel();

        JPanel title3 = new JPanel();

        JPanel title4 = new JPanel();

        JButton ok2 = new JButton("Choose this flight");
        JButton cancel2 = new JButton("Exit");

        buttonPane2.setLayout(new FlowLayout());

        title3.add(list);
        title3.add(msglabel);

        buttonPane2.add(ok2);
        buttonPane2.add(cancel2);

        title2.add(main2, BorderLayout.CENTER);




        jf2.add(title2, BorderLayout.PAGE_START);
        jf2.add(title3, BorderLayout.CENTER);
        //jf2.add(title4, BorderLayout.AFTER_LAST_LINE);
        jf2.add(buttonPane2, BorderLayout.PAGE_END);
        jf2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf2.setSize(600, 300);
        jf2.setVisible(true);





    }
}
