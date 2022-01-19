/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filecopier.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cenebeli
 */
public class DatabaseResource {
    static Properties props = new Properties();

    public DatabaseResource() {
        new DatabaseResource("config.properties");

    }

    public DatabaseResource(String fileName) {
        try {
            InputStream inputStream = this.getClass().getClassLoader()
                    .getResourceAsStream(fileName);
            props.load(inputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getLocalConnection() {
        Connection localCon = null;
        String url = "//";
        String localServerName = props.getProperty("ws.dbserver");
        String localDatabasePort = props.getProperty("ws.dbport");
        String sInstance = props.getProperty("sqlinstance");
        url = url + ";databaseName=";
        String localDatabaseName = props.getProperty("ws.dbname");
        String localUserName = props.getProperty("ws.dbuser");
        String localUserpwd = props.getProperty("ws.dbpwd");
        url = url + ";selectMethod=";
        url = url + "cursor" + ";";
        boolean isInstance = true;
        // Connection connection1 = java.sql.DriverManager.getConnection("jdbc:sqlserv er://"server":1433;Database=BusinessDatabase;User=Matt; Password=testpass");
//        try {
//            boolean test = sInstance.equals("default");
//        } catch (NullPointerException e) {
//            isInstance = false;
//        }

        if (sInstance.equalsIgnoreCase("default")) {
            isInstance = false;
        }

        try {
            java.lang.Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            if (!isInstance) {
                localCon = java.sql.DriverManager.getConnection("jdbc:sqlserver://" + localServerName + ":" + localDatabasePort + ";Database=" + localDatabaseName + ";User=" + localUserName + "; Password=" + localUserpwd);
            } else {
                localCon = java.sql.DriverManager.getConnection("jdbc:sqlserver://" + localServerName + "\\" + sInstance + ";databaseName=" + localDatabaseName + ";", localUserName, localUserpwd);
            }
//            System.out.println("Connected to Database Server: " + localServerName);
        } catch (Exception e) {
            System.out.println("Error Trace in getConnection() : " + e.getMessage());
        }

        return localCon;
    }
    
}
