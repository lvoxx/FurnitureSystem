/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package query.connect;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import javax.swing.JFrame;
import main.ConnectDialog;
import notifi.swing.Notification;

/**
 *
 * @author Admin
 */
public class Settings {
    public static final String SOURCE = "config.json";

    public static void WriteDatabase(Database db) {
        try {
            FileWriter writer = new FileWriter(SOURCE);
            // Convert the JSON to a Java object.
            Gson gson = new Gson();
            gson.toJson(db, writer);
            
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Database ReadDatabase() {
        Database db = new Database("null", "null", "null");
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(SOURCE);
            db = gson.fromJson(reader, Database.class);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }

    public static Connection BuildConnect() throws SQLException {
        Database db = Settings.ReadDatabase();
        String urlString = "jdbc:sqlserver://$serverName;databaseName=FurnitureSystem;encrypt=true;trustServerCertificate=true;queryTimeout=5";
        Connection conn = null;
        //System.out.println(urlString);
        try {
            String username = null, password = null;
            if (db != null) {
                urlString = urlString.replace("$serverName", db.getServerName());

                username = db.getUsername();
                password = db.getPassword();
            }
            System.out.println(urlString);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(urlString,username, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    public static void TryGetConnection(JFrame frame, Connection conn) {
        try {
            conn = Settings.BuildConnect();
        } catch (Exception ex) {
            Notification noti = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Lost connection to server");
            noti.showNotification();
        }
        if (conn != null) {
            Notification noti = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Connect to server success");
            noti.showNotification();
        } else {
            Notification noti = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Lost connection to server");
            noti.showNotification();
            ConnectDialog connDia = new ConnectDialog(frame, conn);
            connDia.show();
            connDia.setAlwaysOnTop(true);
        }
    }

}
