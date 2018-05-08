package br.unirio.calls.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import br.unirio.calls.configuration.Configuration;

@Service("SqlConnectionManager")
public class SqlConnectionManager {
    private static final String COULD_NOT_ESTABLISH_CONNECTION = "Could not establish connection with database";

    private static SqlConnectionManager instance;

    private Connection connection;

    private SqlConnectionManager() {

    }

    public SqlConnectionManager getInstance() {
        if (instance == null) {
            instance = new SqlConnectionManager();
        }

        return instance;
    }

    private static String buildUrl(String driver, String host, String database) {
        return "jdbc:" + driver + "://" + host + "/" + database;
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jbdc.Driver").newInstance();
            if (this.connection == null) {
                String url = buildUrl(Configuration.getDatabaseDriver(), Configuration.getDatabaseHost(),
                        Configuration.getDatabaseName());

                this.connection = DriverManager.getConnection(url, Configuration.getDatabaseUser(),
                        Configuration.getDatabasePassword());
            }
            return connection;
        } catch (SQLException e) {
            System.out.println(COULD_NOT_ESTABLISH_CONNECTION + " - SQL ERROR");
            System.out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println(COULD_NOT_ESTABLISH_CONNECTION + " - Driver not found");
            System.out.println(e.getMessage());
            return null;
        } catch (InstantiationException e) {
            System.out.println(COULD_NOT_ESTABLISH_CONNECTION + " - Error on driver instatiation");
            System.out.println(e.getMessage());
            return null;
        } catch (IllegalAccessException e) {
            System.out.println(COULD_NOT_ESTABLISH_CONNECTION + " - Illegal access");
            System.out.println(e.getMessage());
            return null;
        }
    }
}