/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import query.connect.Database;
import java.sql.*;
import dialog.message.*;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import notifi.swing.Notification;
import query.connect.Settings;

/**
 *
 * @author RAVEN
 */
public class ConnectDialog extends javax.swing.JFrame {

    private JFrame frame;
    private ConnectDialog main;
    protected Connection conn;
    protected Database dbConn;
    
    private String ipAddressStr;
    private String portStr;
    private String usernameStr;
    private String passwordStr;

    /**
     * Creates new form Main
     */
    public ConnectDialog(JFrame frame, Connection conn) {
        main = this;
        this.frame = frame;
        this.conn = conn;
        initComponents();
        alert.setVisible(false);

        this.setLocationRelativeTo(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                MessageDialog dialog = new MessageDialog(main);
                dialog.showMessage("Exit Connection ?", "Incomplete work will not be saved.");
                if (dialog.getMessageType().equals(MessageDialog.MessageType.OK)) {
                    System.exit(0);
                }
            }
        });
    }

    public void tryGetConnection() {
        Database db = new Database("null", "null", "null", "null");
        try {
            db = new Database(ipAddress.getText(), username.getText(), String.copyValueOf(password.getPassword()), port.getText());

            Settings.WriteDatabase(db);
            this.conn = Settings.BuildConnect();
            if (conn == null) {
                alert.setVisible(true);
            } else {
                Notification notif = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Connect to server success");
                notif.showNotification();
                this.dispose();
            }
        } catch (Exception ex) {
            Logger.getLogger(ConnectDialog.class.getName()).log(Level.SEVERE, null, ex);
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

        slide = new swing.PanelSlide();
        jLabel2 = new javax.swing.JLabel();
        ipAddress = new swing.MyTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        port = new swing.MyTextField();
        username = new swing.MyTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        password = new swing.MyPassword();
        connectButton = new swing.MyButton();
        alert = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        slide.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(69, 68, 68));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CONNECT");

        ipAddress.setForeground(new java.awt.Color(102, 102, 102));
        ipAddress.setText("Server IP");
        ipAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ipAddressFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ipAddressFocusLost(evt);
            }
        });
        ipAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipAddressActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("IP Address");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Port");

        port.setForeground(new java.awt.Color(102, 102, 102));
        port.setText("Server Port");
        port.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                portFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                portFocusLost(evt);
            }
        });
        port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portActionPerformed(evt);
            }
        });

        username.setForeground(new java.awt.Color(102, 102, 102));
        username.setText("Server Username");
        username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFocusLost(evt);
            }
        });
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Username");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Password");

        password.setForeground(new java.awt.Color(102, 102, 102));
        password.setText("Password");
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFocusLost(evt);
            }
        });
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });

        connectButton.setBackground(new java.awt.Color(51, 255, 255));
        connectButton.setForeground(new java.awt.Color(0, 0, 0));
        connectButton.setText("Connect to server");
        connectButton.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        connectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                connectButtonMousePressed(evt);
            }
        });
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        alert.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        alert.setForeground(new java.awt.Color(255, 0, 51));
        alert.setText("Failed to connect to server");
        alert.setAlignmentX(0.5F);
        alert.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout slideLayout = new javax.swing.GroupLayout(slide);
        slide.setLayout(slideLayout);
        slideLayout.setHorizontalGroup(
            slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, slideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addGap(15, 15, 15))
            .addGroup(slideLayout.createSequentialGroup()
                .addGroup(slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(slideLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(port, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ipAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(slideLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, slideLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(alert)
                .addGap(114, 114, 114))
        );
        slideLayout.setVerticalGroup(
            slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, slideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ipAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(alert)
                .addGap(32, 32, 32)
                .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ipAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipAddressActionPerformed

    }//GEN-LAST:event_ipAddressActionPerformed

    private void portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        tryGetConnection();
    }//GEN-LAST:event_connectButtonActionPerformed

    private void ipAddressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ipAddressFocusGained
        // TODO add your handling code here:
        ipAddress.setText("");
        ipAddress.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_ipAddressFocusGained

    private void ipAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ipAddressFocusLost
        // TODO add your handling code here:
        ipAddress.setForeground(new Color(102, 102, 102));
        ipAddressStr = ipAddress.getText();
    }//GEN-LAST:event_ipAddressFocusLost

    private void portFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_portFocusGained
        // TODO add your handling code here:
        port.setText("");
        port.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_portFocusGained

    private void portFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_portFocusLost
        // TODO add your handling code here:
        port.setForeground(new Color(102, 102, 102));
        portStr = port.getText();
    }//GEN-LAST:event_portFocusLost

    private void usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusGained
        // TODO add your handling code here:
        username.setText("");
        username.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_usernameFocusGained

    private void usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusLost
        // TODO add your handling code here:
        username.setForeground(new Color(102, 102, 102));
        usernameStr = username.getText();
    }//GEN-LAST:event_usernameFocusLost

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        // TODO add your handling code here:
        password.setText("");
        password.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_passwordFocusGained

    private void passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusLost
        // TODO add your handling code here:
        password.setForeground(new Color(102, 102, 102));
        passwordStr= password.getText();
    }//GEN-LAST:event_passwordFocusLost

    private void connectButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectButtonMousePressed
        tryGetConnection();
    }//GEN-LAST:event_connectButtonMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alert;
    private swing.MyButton connectButton;
    private swing.MyTextField ipAddress;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private swing.MyPassword password;
    private swing.MyTextField port;
    private swing.PanelSlide slide;
    private swing.MyTextField username;
    // End of variables declaration//GEN-END:variables
}
