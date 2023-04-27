/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dev.galvez.schoolmanagement;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Christina
 */
public class CreateUser extends javax.swing.JFrame {

    /**
     * Creates new form User
     */
    public CreateUser() {
        initComponents();
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        conn = DatabaseFunctionalities.connectDB();
        LoadUsers();
    }
    
    /**
     * Start a MySQL connection
     */
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel usersTable;
    
    /**
     * Fetch data from database and display to GUI
     */
    public final void LoadUsers() {
        
        try {
            String sql = "SELECT * FROM user";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            usersTable = (DefaultTableModel) usersTableGUI.getModel();
            usersTable.setRowCount(0);
            
            while (rs.next()) {
                Vector rowContent = new Vector();
                for (int i = 0; i < colCount; i++) {
                    rowContent.add(rs.getString("userID"));
                    rowContent.add(rs.getString("lastName"));
                    rowContent.add(rs.getString("firstName"));
                    rowContent.add(rs.getString("phoneNumber"));
                    rowContent.add(rs.getString("email"));
                    rowContent.add(rs.getString("userType"));
                }
                usersTable.addRow(rowContent);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Disable Fields
     * @param isEnabled
     */
    public void enableFields(boolean isEnabled) {
        lastNameField.setEnabled(isEnabled);
        firstNameField.setEnabled(isEnabled);
        phoneField.setEnabled(isEnabled);
        emailField.setEnabled(isEnabled);
        userTypeComboBox.setEnabled(isEnabled);
        passwordField.setEnabled(isEnabled);
    }
    
    /**
     * Clear Fields
     */
    public void clearFields() {
        lastNameField.setText("");
        firstNameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        passwordField.setText("");
        userTypeComboBox.setSelectedIndex(0);
        lastNameField.requestFocus();
    }
    
    public static Long tryParse(String text) {
        try {
          return Long.parseLong(text);
        } catch (NumberFormatException e) {
          return 0L;
        }
      }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        userTypeLabel = new javax.swing.JLabel();
        userTypeComboBox = new javax.swing.JComboBox<>();
        lastNameLabel = new javax.swing.JLabel();
        lastNameField = new javax.swing.JTextField();
        firstNameLabel = new javax.swing.JLabel();
        firstNameField = new javax.swing.JTextField();
        phoneLabel = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        saveBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersTableGUI = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 123, 255));

        jLabel8.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Create User Account");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel8)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        userTypeLabel.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        userTypeLabel.setText("User Type");

        userTypeComboBox.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        userTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Faculty", "Student" }));

        lastNameLabel.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        lastNameLabel.setText("Last Name");

        lastNameField.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N

        firstNameLabel.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        firstNameLabel.setText("First Name");

        firstNameField.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N

        phoneLabel.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        phoneLabel.setText("Phone Number");

        phoneField.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N

        emailLabel.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        emailLabel.setText("Email");

        emailField.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N

        passwordLabel.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        passwordLabel.setText("Password");

        passwordField.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N

        saveBtn.setBackground(new java.awt.Color(40, 167, 69));
        saveBtn.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(220, 53, 69));
        deleteBtn.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        editBtn.setBackground(new java.awt.Color(0, 123, 255));
        editBtn.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        clearBtn.setBackground(new java.awt.Color(108, 117, 125));
        clearBtn.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        usersTableGUI.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        usersTableGUI.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Last Name", "First Name", "Phone Number", "Email", "User Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        usersTableGUI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersTableGUIMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(usersTableGUI);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(phoneLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(firstNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lastNameLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(userTypeLabel)
                                    .addGap(18, 18, 18)
                                    .addComponent(userTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emailLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(passwordLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(emailField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(passwordField)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userTypeLabel)
                            .addComponent(userTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lastNameLabel)
                            .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstNameLabel)
                            .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phoneLabel)
                            .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailLabel)
                            .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordLabel)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editBtn)
                            .addComponent(clearBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveBtn)
                            .addComponent(deleteBtn))))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        String userType = userTypeComboBox.getSelectedItem().toString();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String phoneStr = phoneField.getText();
        long phone = tryParse(phoneStr);
        String email = emailField.getText();
        String password = String.valueOf(passwordField.getPassword());
        
        usersTable = (DefaultTableModel) usersTableGUI.getModel(); 
        int selectedIndex = usersTableGUI.getSelectedRow();

        
        // search if record exists
        if (selectedIndex > 0) {
            int id = Integer.parseInt(usersTable.getValueAt(selectedIndex, 0).toString());
            updateUser(userType, lastName, firstName, phone, email, id);
            passwordField.setEnabled(true);
        } else {
            if (userType.isEmpty() || lastName.isEmpty() || firstName.isEmpty() || email.isEmpty() || password.isEmpty() || phone == 0L) {
                JOptionPane.showMessageDialog(this,
                        "Please fill out all the fields.",
                        "Try Again",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                addUser(userType, lastName, firstName, phone, email, password);
            }
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        String[] options = {"Confirm", "Cancel"};
        int choice = JOptionPane.showOptionDialog(null, 
                                                    "Are you sure you want to delete this record?",
                                                    "Delete Confirmation",
                                                    JOptionPane.DEFAULT_OPTION, 
                                                    JOptionPane.INFORMATION_MESSAGE, 
                                                    null, 
                                                    options, 
                                                    options[0]);
        
        if (choice == 0) {
            // delete
            usersTable = (DefaultTableModel) usersTableGUI.getModel(); 
            int selectedIndex = usersTableGUI.getSelectedRow();
            int id = Integer.parseInt(usersTable.getValueAt(selectedIndex, 0).toString());
            deleteUser(id);
            enableFields(true);
            usersTableGUI.clearSelection();
            editBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            saveBtn.setEnabled(true);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        enableFields(true);
        passwordField.setEnabled(false);    
        saveBtn.setEnabled(true);
    }//GEN-LAST:event_editBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // TODO add your handling code here:
        clearFields();
        enableFields(true);
        usersTableGUI.clearSelection();
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        saveBtn.setEnabled(true);
    }//GEN-LAST:event_clearBtnActionPerformed

    private void usersTableGUIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableGUIMouseClicked
        // TODO add your handling code here:
        usersTable = (DefaultTableModel) usersTableGUI.getModel(); 
        int selectedIndex = usersTableGUI.getSelectedRow();
        
        //String id = usersTable.getValueAt(selectedIndex, 0).toString();
        // set all fields
        lastNameField.setText(usersTable.getValueAt(selectedIndex, 1).toString());
        firstNameField.setText(usersTable.getValueAt(selectedIndex, 2).toString());
        phoneField.setText(usersTable.getValueAt(selectedIndex, 3).toString());
        emailField.setText(usersTable.getValueAt(selectedIndex, 4).toString());
        userTypeComboBox.setSelectedItem(usersTable.getValueAt(selectedIndex, 5).toString());

        // disable the fields
        enableFields(false);
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        saveBtn.setEnabled(false);
    }//GEN-LAST:event_usersTableGUIMouseClicked

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CreateUser().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JButton saveBtn;
    private javax.swing.JComboBox<String> userTypeComboBox;
    private javax.swing.JLabel userTypeLabel;
    private javax.swing.JTable usersTableGUI;
    // End of variables declaration//GEN-END:variables

    private void addUser(String userType, String lastName, String firstName, long phone, String email, String password) {
        try {
            String sql = "INSERT INTO user (userType, lastName, firstName, phoneNumber, email, password) VALUES (?, ?, ?, ?, ?, ?)";

            pst = conn.prepareStatement(sql);
            pst.setString(1, userType);
            pst.setString(2, lastName);
            pst.setString(3, firstName);
            pst.setLong(4, phone);
            pst.setString(5, email);
            pst.setString(6, password);
            int rows = pst.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this,
                    "User Successfully Added.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                // clear all fields
                clearFields();
                LoadUsers();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateUser(String userType, String lastName, String firstName, long phone, String email, int id) {
        try {          
            String sql = "UPDATE user SET userType=?, lastName=?, firstName=?, phoneNumber=?, email=?"
                    + "WHERE userID=?";

            pst = conn.prepareStatement(sql);
            pst.setString(1, userType);
            pst.setString(2, lastName);
            pst.setString(3, firstName);
            pst.setLong(4, phone);
            pst.setString(5, email);
            pst.setInt(6, id);
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                JOptionPane.showMessageDialog(this,
                    "User Successfully Updated.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                LoadUsers();
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteUser(int id) {
        try {
            String sql = "DELETE FROM user WHERE userID=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                JOptionPane.showMessageDialog(this,
                    "User Successfully Deleted.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                LoadUsers();
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}