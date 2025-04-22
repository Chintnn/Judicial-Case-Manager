package main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//public class LoginFrame extends JFrame {

//    private final JTextField txtUsername = new JTextField(15);
//    private final JPasswordField txtPassword = new JPasswordField(15);

//    public LoginFrame() {
//        setTitle("Judicial Case Manager - Login");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(350, 200);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // Form Panel
//        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
//        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
//        formPanel.add(new JLabel("Username:"));
//        formPanel.add(txtUsername);
//        formPanel.add(new JLabel("Password:"));
//        formPanel.add(txtPassword);
//
//        JButton btnLogin = new JButton("Login");
//        btnLogin.addActionListener(this::handleLogin);
//
//        formPanel.add(new JLabel()); // empty for spacing
//        formPanel.add(btnLogin);
//        setSize(850,480);
//        setLocation(450,200);
//
//        setVisible(true);
//
//
//        add(formPanel, BorderLayout.CENTER);
//    }
//
//    private void handleLogin(ActionEvent e) {
//        String username = txtUsername.getText().trim();
//        String password = new String(txtPassword.getPassword());
//
//        // Hardcoded check (can be replaced with DB auth logic)
//        if (username.equals("admin") && password.equals("admin123")) {
//            SwingUtilities.invokeLater(() -> {
//                new AdminDashboard().setVisible(true);
//                dispose(); // Close login window
//            });
//        } else {
//            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
//        }
//    }





    public class LoginFrame extends JFrame implements ActionListener{
        JLabel label1,label2,label3,label4;
        JTextField Jtext1;
        JPasswordField JPass1;
        JButton button1,button2,button3;
        public LoginFrame(){

            super("Judicial Management System");
            setResizable(false);
            setLayout(null);





            label1=new JLabel("Welcome to the Indian Judicial System");
            label1.setBounds(100,50,800,40);
            label1.setForeground(Color.BLACK);
            label1.setFont(new Font("AvantGarde", Font.ITALIC,38));
            add(label1);
            label2=new JLabel("Username");
            label2.setBounds(150,195,375,30);
            label2.setForeground(Color.WHITE);
            label2.setFont(new Font("AvantGarde", Font.BOLD,20));
            add(label2);

            Jtext1=new JTextField(15);
            Jtext1.setBounds(325,190,230,30);
            Jtext1.setFont(new Font("Ralway", Font.BOLD,14));
            add(Jtext1);

            label3=new JLabel("Password");
            label3.setBounds(150,250,375,30);
            label3.setForeground(Color.WHITE);
            label3.setFont(new Font("AvantGarde", Font.BOLD,20));
            add(label3);

            label4=new JLabel("Admin Login");
            label4.setBounds(345,100,375,30);
            label4.setForeground(Color.BLACK);
            label4.setFont(new Font("AvantGarde", Font.BOLD,30));
            add(label4);

            JPass1=new JPasswordField(15);
            JPass1.setBounds(325,250,230,30);
            JPass1.setFont(new Font("Arial", Font.BOLD,14));
            add(JPass1);

            button1 =new JButton("Login");
            button1.setForeground(Color.black);
            button1.setFont(new Font("arial", Font.BOLD,14));
            button1.setBounds(300,300,100,30);
//            button1.addActionListener(this);
//            JButton btnLogin = new JButton("Login");
      button1.addActionListener(this::handleLogin);
            add(button1);

            button2 =new JButton("Clear");
            button2.setForeground(Color.WHITE);
            button2.setBackground(Color.BLACK);
            button2.setFont(new Font("arial", Font.BOLD,14));
            button2.setBounds(430,300,100,30);
            button2.addActionListener(this);
            add(button2);

//        button3 =new JButton("Sign-Up");
//        button3.setForeground(Color.WHITE);
//        button3.setBackground(Color.BLACK);
//        button3.setFont(new Font("arial", Font.BOLD,14));
//        button3.setBounds(300,350,230,30);
//        button3.addActionListener(this);
//        add(button3);
//            ImageIcon i=new ImageIcon(ClassLoader.getSystemResource("./Icons/th.jpeg"));
//            Image i1=i.getImage().getScaledInstance(850,480, Image.SCALE_DEFAULT);
//
//            ImageIcon i2=new ImageIcon(i1);
//            JLabel imagee=new JLabel(i2);
//            imagee.setBounds(0,0,850,480);
//            add(imagee);
            ImageIcon i=new ImageIcon(ClassLoader.getSystemResource("./Icons/th (2).jpeg"));
            Image i1=i.getImage().getScaledInstance(850,480, Image.SCALE_DEFAULT);

            ImageIcon i2=new ImageIcon(i1);
            JLabel imagee=new JLabel(i2);
            imagee.setBounds(0,0,850,480);
            add(imagee);

            setSize(850,450);
            setLocation(450,200);
            setVisible(true);

        }
        private void handleLogin(ActionEvent e) {
            String username = Jtext1.getText().trim();
            String password = new String(JPass1.getPassword());

            // Hardcoded check (can be replaced with DB auth logic)
            if (username.equals("admin") && password.equals("admin123")) {
                SwingUtilities.invokeLater(() -> {
                    new AdminDashboard().setVisible(true);
                    dispose(); // Close login window
                });
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
//        public static void main(String[] args) {
//            new Login();
//        }

        //Case Management tab-panel-> caseHistory, CaseManagement Panel,
        //law and legal tab->Appeal , bail,Evidence ,Hearing ,Settlement
        //person tab->PersonManagement,CourtSTaff
        //Infrastructure->CourtPanel, CourtRoom

        @Override
        public void actionPerformed(ActionEvent e) {
            try{

                if(e.getSource()==button1){

                }
                else if(e.getSource()==button2){
                    Jtext1.setText("");
                    JPass1.setText("");
                }
                else{

                }

            }
            catch(Exception E){
                System.out.println(E.getMessage());;
            }
        }



//    public static void main(String[] args) {
//        new LoginFrame();
//    }
}
