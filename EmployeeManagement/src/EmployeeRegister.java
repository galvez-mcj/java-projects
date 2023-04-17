import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class EmployeeRegister extends JDialog{
    private JTextField lastNameTextField;
    private JPanel mainPanel;
    private JTextField firstNameTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailAddressTextField;
    private JPasswordField passwordField;
    private JPasswordField confPasswordField;
    private JButton registerButton;
    private JButton cancelButton;
    private JLabel logo;

    public User user;

    public EmployeeRegister(JFrame parent) {
        super(parent);
        setTitle("Employee Registration Form");
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(480, 430));
        setModal(true);
        setLocationRelativeTo(parent);

        // initialize logo
        ImageIcon logoIcon = new ImageIcon("images/register.png");
        logo.setIcon(logoIcon);

        registerButton.addActionListener(e -> registerEmployee());
        cancelButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void registerEmployee() {
        String lastName = lastNameTextField.getText();
        String firstName = firstNameTextField.getText();
        String phone = phoneNumberTextField.getText();
        String email = emailAddressTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String confPass = String.valueOf(confPasswordField.getPassword());

        // check if all fields are filled
        if (lastName.isEmpty() || firstName.isEmpty() || phone.isEmpty() ||
            email.isEmpty() || password.isEmpty() || confPass.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill out all the fields.",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // check if passwords match
        if (!password.equals(confPass)) {
            JOptionPane.showMessageDialog(this,
                    "Passwords don't match.",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // passed form requirements
        // add to database
        user = addToDatabase(lastName, firstName, phone, email, password);

        // more checks if user not added to db
        if (user != null) {
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Something went wrong. Please try again later.",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private User addToDatabase(String lastName, String firstName, String phone, String email, String password) {
        User user = null;

        // connect to db
        // retrieve data from dot-env
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();
        final String DB_URL = dotenv.get("DB_URL");
        final String USERNAME = dotenv.get("USER_NAME");
        final String PASSWORD = dotenv.get("PASSWORD");

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // connected

            Statement statement = connection.createStatement();
            String sql = "INSERT INTO employees (lastname, firstname, phone, email, password)" +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, password);

            // insert the rows to the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.setLastName(lastName);
                user.setFirstName(firstName);
                user.setPhone(phone);
                user.setEmail(email);
                user.setPassword(password);
            }

            // close connection
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }


    public static void main(String[] args) {
        EmployeeRegister regForm = new EmployeeRegister(null);
        User user = regForm.user;
        if ( user != null ) {
            System.out.println("Employee successfully registered!");
        } else {
            System.out.println("Something went wrong.");
        }
    }

}
