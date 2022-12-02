package jm.task.core.jdbc.util;

import org.hibernate.engine.jdbc.connections.internal.ConnectionCreatorBuilder;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static String URL = "jdbc:mysql://localhost:3306/kata";

    private static String NAME = "root";
    private static String PASS = "root";

    private static Connection connection;


    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(URL, NAME, PASS);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
