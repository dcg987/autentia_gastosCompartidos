package com.autentia.utils;

import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Singleton
public class ConnectionBBDD {

    private final String url = "jdbc:postgresql://localhost:5432/autentia";
    private final String user = "postgres";
    private final String password = "autentia2005";
    private Connection conn;

    public Connection connect(){

        conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.print(e.getMessage());
        }
        return conn;
    }

}
