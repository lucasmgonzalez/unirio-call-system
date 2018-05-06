package br.unirio.calls.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.unirio.calls.configuration.Configuration;

public abstract class AbstractDao {

    private static final String COULD_NOT_ESTABLISH_CONNECTION = "Could not establish connection with database";

    private static String buildUrl(String driver, String host, String database) {
        return "jdbc:" + driver + "://" + host + "/" + database;
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jbdc.Driver").newInstance();

            String url = buildUrl(Configuration.getDatabaseDriver(), Configuration.getDatabaseHost(),
                    Configuration.getDatabaseName());

            return DriverManager.getConnection(url, Configuration.getDatabaseUser(),
                    Configuration.getDatabasePassword());
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