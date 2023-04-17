import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EmployeeLogin extends JDialog{
    private JPanel mainPanel;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JLabel logo;
    private JButton loginButton;
    private JButton cancelButton;
    private JButton regButton;
    private JLabel noAccountLabel;

    public User user;

    public EmployeeLogin(JFrame parent) {
        super(parent);
        setTitle("Employee Login Form");
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(480, 400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // initialize logo
        ImageIcon logoIcon = new ImageIcon("images/login.png");
        logo.setIcon(logoIcon);

        loginButton.addActionListener(e -> loginEmployee());
        cancelButton.addActionListener(e -> dispose());

        regButton.addActionListener(e -> {
            dispose();
            EmployeeRegister regForm = new EmployeeRegister(null);
        });

        setVisible(true);
    }

    private void loginEmployee() {
        String email = emailTextField.getText();
        String password = String.valueOf(passwordField.getPassword());

        // check if all fields filled out
        if ( email.isEmpty() || password.isEmpty() ) {
            JOptionPane.showMessageDialog(this,
                    "Please fill out all the fields.",
                    "Try Again",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // check in db if credentials exists
        user = retrieveFromDB(email, password);

        // more checks if user is authenticated
        if ( user != null ) {
            JOptionPane.showMessageDialog(this,
                    String.format("Welcome back, %s %s", user.getFirstName(), user.getLastName()),
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
            EmployeeDashboard dashboard = new EmployeeDashboard(null, user);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Wrong credentials. Please try again.",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private User retrieveFromDB(String email, String password) {
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

            // query db
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM employees WHERE email=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            // execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setID(resultSet.getInt("ID"));
                user.setLastName(resultSet.getString("lastname"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
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
        EmployeeLogin loginForm = new EmployeeLogin(null);
        User user = loginForm.user;
        loginForm.dispose();
    }
}
