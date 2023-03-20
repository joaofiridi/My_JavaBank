package org.academiadecodigo.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMaster {

    private Connection connection = null;

    String dbUrl = "jdbc:mysql://localhost:3306/javabank";
    String user = "root";
    String pass = "";

    public Connection getConnection (){
        try{

            if (connection == null) {
                connection = DriverManager.getConnection(dbUrl, user, pass);
            }
        } catch (SQLException ex) {
            System.out.println("Failure to connect to database: " + ex.getMessage());
        }
        return connection;
    }

    public void close (){
        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException ex){
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }
}
