package com.nimesa.assignment.utils;

import java.util.Objects;
import java.util.Optional;

public class EnvironmentVariables {
    public static final String AWS_ACCESS_KEY = getEnvString("AWS_ACCESS_KEY");
    public static final String AWS_SECRET_KEY = getEnvString("AWS_SECRET_KEY");

    private static String getEnvString(String varName){
        return Optional.ofNullable(System.getenv(varName))
                .map(s -> s.trim())
                .orElse("");

    }
}
