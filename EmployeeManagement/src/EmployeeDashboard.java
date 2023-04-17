import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class EmployeeDashboard extends JFrame{
    private JPanel mainPanel;
    private JButton editButton;
    private JTextField lastNameTextField;
    private JTextField firstNameTextField;
    private JTextField phoneTextField;
    private JTextField emailTextField;
    private JLabel userFullName;
    private JButton logoutButton;
    private JButton saveButton;

    public User user;

    public EmployeeDashboard(JFrame parent, User user) {
        setTitle("Employee Dashboard");
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(480, 450));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        fillDashboard(parent, user);

        logoutButton.addActionListener(e -> {
            dispose();
            EmployeeLogin login = new EmployeeLogin(null);
        });

        editButton.addActionListener(e -> {
            lastNameTextField.setEditable(true);
            firstNameTextField.setEditable(true);
            phoneTextField.setEditable(true);
            emailTextField.setEditable(true);

        });

        saveButton.addActionListener(e -> updateEmployee(user));
    }

    private void updateEmployee(User user) {
        String lastName = lastNameTextField.getText();
        String firstName = firstNameTextField.getText();
        String phone = phoneTextField.getText();
        String email = emailTextField.getText();
        int ID = user.getID();

        this.user = updateDB(ID, lastName, firstName, phone, email);

        if ( this.user != null ) {
            JOptionPane.showMessageDialog(this,
                    "Successfully updated profile!",
                    "Update Complete",
                    JOptionPane.INFORMATION_MESSAGE);

            // disable fields
            lastNameTextField.setEditable(false);
            firstNameTextField.setEditable(false);
            phoneTextField.setEditable(false);
            emailTextField.setEditable(false);

        } else {
            JOptionPane.showMessageDialog(this,
                    "Something went wrong. Please try again later.",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private User updateDB(int ID, String lastName, String firstName, String phone, String email) {
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
            String sql = "UPDATE employees SET lastname=?, firstname=?, phone=?, email=?" +
                    "WHERE ID=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, email);
            preparedStatement.setInt(5, ID);

            // insert the rows to the table
            int updatedRow = preparedStatement.executeUpdate();
            if (updatedRow > 0) {
                user = new User();
                user.setLastName(lastName);
                user.setFirstName(firstName);
                user.setPhone(phone);
                user.setEmail(email);
            }

            // close connection
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    private void fillDashboard(JFrame parent, User user) {
        if (user != null) {
            userFullName.setText(user.getFirstName() + " " + user.getLastName());
            lastNameTextField.setText(user.getLastName());
            firstNameTextField.setText(user.getFirstName());
            phoneTextField.setText(user.getPhone());
            emailTextField.setText(user.getEmail());

            setLocationRelativeTo(null);
            setVisible(true);
        } else {
            parent.dispose();
        }
    }



    public static void main(String[] args) {
//        EmployeeDashboard dashboard = new EmployeeDashboard(null);
//        User user = dashboard.user;
    }

}
