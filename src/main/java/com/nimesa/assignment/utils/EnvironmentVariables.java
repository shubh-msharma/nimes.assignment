package com.nimesa.assignment.utils;

import java.util.Optional;

public class EnvironmentVariables {
    public static final String AWS_ACCESS_KEY = getEnvString("AWS_ACCESS_KEY");
    public static final String AWS_SECRET_KEY = getEnvString("AWS_SECRET_KEY");
    public static final String DB_HOST = getEnvString("DB_HOST");
    public static final String DB_PORT = getEnvString("DB_PORT");
    public static final String DB_NAME = getEnvString("DB_NAME");
    public static final String SPRING_DATASOURCE_USERNAME = getEnvString("SPRING_DATASOURCE_USERNAME");
    public static final String SPRING_DATASOURCE_PASSWORD = getEnvString("SPRING_DATASOURCE_PASSWORD");
    public static final String SPRING_DATASOURCE_DRIVER_CLASS_NAME = getEnvString("SPRING_DATASOURCE_DRIVER_CLASS_NAME");
    public static final String SPRING_DATASOURCE_URL = getDbUrl();

    private static String getEnvString(String varName){
        return Optional.ofNullable(System.getenv(varName))
                .map(s -> s.trim())
                .orElse("");

    }

    private static String getDbUrl() {
        return "jdbc:postgresql://"+DB_HOST+":"+DB_PORT+"/" + DB_NAME;
    }
}
