package br.unirio.calls.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.unirio.calls.configuration.Configuration;

public abstract class AbstractDao {

    private static final String COULD_NOT_ESTABLISH_CONNECTION = "Could not establish connection with database";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jbdc.Driver").newInstance();
            return DriverManager.getConnection(Configuration.getDatabaseConnection(), Configuration.getDatabaseUser(),
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