import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Arrays;

public class MovieSeatApp extends JDialog{
    private JPanel mainPanel;
    private JComboBox movieBox;
    private JButton a5Button;
    private JButton a10Button;
    private JButton a1Button;
    private JButton a3Button;
    private JButton a6Button;
    private JButton a2Button;
    private JButton a4Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a7Button;
    private JButton b1Button;
    private JButton b2Button;
    private JButton b3Button;
    private JButton b4Button;
    private JButton b5Button;
    private JButton b6Button;
    private JButton b7Button;
    private JButton b8Button;
    private JButton b9Button;
    private JButton b10Button;
    private JButton c1Button;
    private JButton c2Button;
    private JButton c3Button;
    private JButton c4Button;
    private JButton c5Button;
    private JButton c6Button;
    private JButton c7Button;
    private JButton c8Button;
    private JButton c9Button;
    private JButton c10Button;
    private JTextField firstnameTextField;
    private JTextField lastnameTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField movieTextField;
    private JComboBox rowComboBox;
    private JComboBox columnComboBox;
    private JButton reserveButton;
    private JButton d1Button;
    private JButton d2Button;
    private JButton d3Button;
    private JButton d4Button;
    private JButton d5Button;
    private JButton d6Button;
    private JButton d7Button;
    private JButton d8Button;
    private JButton d9Button;
    private JButton d10Button;
    private JButton d11Button;
    private JButton d12Button;
    private JButton e1Button;
    private JButton e2Button;
    private JButton e3Button;
    private JButton e4Button;
    private JButton e5Button;
    private JButton e6Button;
    private JButton e7Button;
    private JButton e8Button;
    private JButton e9Button;
    private JButton e10Button;
    private JButton e11Button;
    private JButton e12Button;

    public MovieSeatApp(JFrame parent) {
        super(parent);
        setTitle("Online Cinema Seat Reservation");
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(1300, 575));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // initially set movie
        movieTextField.setText(movieBox.getSelectedItem().toString());

        // set seat buttons
        for (JButton jButton : Arrays.asList(a1Button, a2Button, a3Button, a4Button, a5Button,
                                             a6Button, a7Button, a8Button, a9Button, a10Button,
                                             b1Button, b2Button, b3Button, b4Button, b5Button,
                                             b6Button, b7Button, b8Button, b9Button, b10Button,
                                             c1Button, c2Button, c3Button, c4Button, c5Button,
                                             c6Button, c7Button, c8Button, c9Button, c10Button,
                                             d1Button, d2Button, d3Button, d4Button, d5Button, d6Button,
                                             d7Button, d8Button, d9Button, d10Button, d11Button, d12Button,
                                             e1Button, e2Button, e3Button, e4Button, e5Button, e6Button,
                                             e7Button, e8Button, e9Button, e10Button, e11Button, e12Button)) {
            triggerBtn(jButton);
        }

        // get movie list from db
        getMovieListFromDB();

        // get reserved seat from db

        movieBox.addActionListener(e -> {
            for (JButton jButton : Arrays.asList(a1Button, a2Button, a3Button, a4Button, a5Button,
                    a6Button, a7Button, a8Button, a9Button, a10Button,
                    b1Button, b2Button, b3Button, b4Button, b5Button,
                    b6Button, b7Button, b8Button, b9Button, b10Button,
                    c1Button, c2Button, c3Button, c4Button, c5Button,
                    c6Button, c7Button, c8Button, c9Button, c10Button,
                    d1Button, d2Button, d3Button, d4Button, d5Button, d6Button,
                    d7Button, d8Button, d9Button, d10Button, d11Button, d12Button,
                    e1Button, e2Button, e3Button, e4Button, e5Button, e6Button,
                    e7Button, e8Button, e9Button, e10Button, e11Button, e12Button)) {
                jButton.setEnabled(true);
            }
            movieTextField.setText(movieBox.getSelectedItem().toString());
            getReservedSeatFromDB(movieBox.getSelectedItem().toString());
        });

        reserveButton.addActionListener(e -> reserveSeat());

        // should always be last!!!!
        setVisible(true);
    }

    private void getReservedSeatFromDB(String movie) {
        int seat_code = 0;

        // hard coded seat code
        switch (movie) {
            case "Leonor Will Never Die" -> seat_code = 100;
            case "Everything Everywhere All at Once" -> seat_code = 200;
        }

        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();
        final String DB_URL = dotenv.get("DB_URL");
        final String USERNAME = dotenv.get("USER_NAME");
        final String PASSWORD = dotenv.get("PASSWORD");

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

            // get all seats from seats table
            String sql = "SELECT * FROM seat WHERE seat_code=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, seat_code);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                do {
                    disableButton(resultSet.getString("seat_row"), resultSet.getString("seat_col"));
                } while (resultSet.next());
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void disableButton(String seatRow, String seatCol) {
        String buttonName = seatRow + seatCol;
        for (JButton jButton : Arrays.asList(a1Button, a2Button, a3Button, a4Button, a5Button,
                a6Button, a7Button, a8Button, a9Button, a10Button,
                b1Button, b2Button, b3Button, b4Button, b5Button,
                b6Button, b7Button, b8Button, b9Button, b10Button,
                c1Button, c2Button, c3Button, c4Button, c5Button,
                c6Button, c7Button, c8Button, c9Button, c10Button,
                d1Button, d2Button, d3Button, d4Button, d5Button, d6Button,
                d7Button, d8Button, d9Button, d10Button, d11Button, d12Button,
                e1Button, e2Button, e3Button, e4Button, e5Button, e6Button,
                e7Button, e8Button, e9Button, e10Button, e11Button, e12Button)) {
            //jButton.setEnabled(true);
            String btnText = jButton.getText().toString();
            if(btnText.equals(buttonName)) {
                jButton.setEnabled(false);
            }
        }
    }

    private void getMovieListFromDB() {
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();
        final String DB_URL = dotenv.get("DB_URL");
        final String USERNAME = dotenv.get("USER_NAME");
        final String PASSWORD = dotenv.get("PASSWORD");

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM cinema";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                do {
                    movieBox.addItem(resultSet.getString("title"));
                } while (resultSet.next());
            }
            // close connection
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reserveSeat() {
        String row = rowComboBox.getSelectedItem().toString();
        String column = columnComboBox.getSelectedItem().toString();
        String movie = movieTextField.getText();

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String phone = phoneNumberTextField.getText();
        String email = emailTextField.getText();

        // check all fields are filled out
        if (firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty() || email.isEmpty() || movie == "---" || row == "-" || column == "-") {
            JOptionPane.showMessageDialog(this,
                                          "Fill out all fields",
                                          "Details Incomplete",
                                          JOptionPane.ERROR_MESSAGE);
        } else {
            Seat seat = new Seat(row, column);
            Patron patron = new Patron(lastname, firstname, phone, email);
            // add to database
            addToDatabase(movie, seat, patron);
            disableButton(row, column);
        }
    }

    private void addToDatabase(String movie, Seat seat, Patron patron) {
        int seat_code = 0;

        // hard coded seat code
        switch (movie) {
            case "Leonor Will Never Die" -> seat_code = 100;
            case "Everything Everywhere All at Once" -> seat_code = 200;
        }

        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();
        final String DB_URL = dotenv.get("DB_URL");
        final String USERNAME = dotenv.get("USER_NAME");
        final String PASSWORD = dotenv.get("PASSWORD");

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO patron (lastname, firstname, phone, email)" +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, patron.getLastname());
            preparedStatement.setString(2, patron.getFirstname());
            preparedStatement.setString(3, patron.getPhone());
            preparedStatement.setString(4, patron.getEmail());
            preparedStatement.executeUpdate();

            // get patron ID to store to seat table
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int patronID = 0;
            if (rs.next()) {
                patronID = rs.getInt(1);
                // only add if patron created
                addSeatToDatabase(seat_code, seat, patronID);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Something went wrong. Try again later",
                        "Try Again",
                        JOptionPane.ERROR_MESSAGE);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Something went wrong. Try again later.",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addSeatToDatabase(int seat_code, Seat seat, int patronID) {
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();
        final String DB_URL = dotenv.get("DB_URL");
        final String USERNAME = dotenv.get("USER_NAME");
        final String PASSWORD = dotenv.get("PASSWORD");

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String sql2 = "INSERT INTO seat (seat_code, seat_row, seat_col, patron_id)" +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1, seat_code);
            preparedStatement.setString(2, seat.getRow());
            preparedStatement.setString(3, seat.getColumn());
            preparedStatement.setInt(4, patronID);
            int addedRow = preparedStatement.executeUpdate();

            if (addedRow > 0) {
                JOptionPane.showMessageDialog(this,
                        "Successfully reserved your seat!",
                        "Reservation Successful",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Something went wrong. Seat not reserved.",
                        "Try Again",
                        JOptionPane.ERROR_MESSAGE);
            }

            // close connection
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void triggerBtn(JButton myBtn) {
        String row = myBtn.getText().toString().substring(0,1);
        String column = myBtn.getText().toString().substring(1);

        myBtn.addActionListener(e -> {
            rowComboBox.setSelectedItem(row);
            columnComboBox.setSelectedItem(column);
        });
    }

    public static void main(String[] args) {
        MovieSeatApp app = new MovieSeatApp(null);
    }
}
