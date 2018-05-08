package br.unirio.calls.configuration;

// import java.io.IOException;
// import java.io.InputStream;
// import java.util.Properties;

public class Configuration {
    // private static Properties configuration = null;

    public static final String DATABASE_DRIVER_KEY = "DATABASE_DRIVER";
    public static final String DATABASE_HOST_KEY = "DATABASE_HOST";
    public static final String DATABASE_PORT_KEY = "DATABASE_PORT";
    public static final String DATABASE_NAME_KEY = "DATABASE_NAME";
    public static final String DATABASE_USER_KEY = "DATABASE_USER";
    public static final String DATABASE_PASSWORD_KEY = "DATABASE_PASSWORD";

    private static String valueOrDefault(String value, String defaultValue) {
        return value != null && !value.isEmpty() ? value : defaultValue;
    }

    public static String getDatabaseDriver() {
        return valueOrDefault(System.getenv(DATABASE_DRIVER_KEY), "mysql");
    }

    public static String getDatabaseHost() {
        return valueOrDefault(System.getenv(DATABASE_HOST_KEY), "localhost");
    }

    public static String getDatabasePort() {
        return valueOrDefault(System.getenv(DATABASE_PORT_KEY), "3306");
    }

    public static String getDatabaseName() {
        return valueOrDefault(System.getenv(DATABASE_NAME_KEY), "UnirioChamadas");
    }

    public static String getDatabaseUser() {
        return valueOrDefault(System.getenv(DATABASE_USER_KEY), "root");
    }

    public static String getDatabasePassword() {
        return valueOrDefault(System.getenv(DATABASE_PASSWORD_KEY), "secret");
    }
}