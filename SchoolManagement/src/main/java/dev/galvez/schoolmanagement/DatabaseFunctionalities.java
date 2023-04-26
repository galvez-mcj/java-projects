/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.galvez.schoolmanagement;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Christina
 */
public class DatabaseFunctionalities {
    
    private static Connection conn;
    private static PreparedStatement pst;
    private static ResultSet rs;
    
    /**
     * connectDB
     * starts the database connection
     * @return connection string
     */      
    public static Connection connectDB() {
        Dotenv dotenv = Dotenv.configure().load();
        final String DB_URL = dotenv.get("DB_URL");
        final String USERNAME = dotenv.get("USER_NAME");
        final String PASSWORD = dotenv.get("PASSWORD");
        
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error connecting to database.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        } 
        return conn;
    }
    
    /**
     * 
     */
    
    
}
