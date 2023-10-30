package com.nimesa.assignment.configurations;

import com.nimesa.assignment.utils.EnvironmentVariables;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {

    @Bean
    @Profile("dev")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        System.out.println(EnvironmentVariables.SPRING_DATASOURCE_URL);
        dataSource.setUrl(EnvironmentVariables.SPRING_DATASOURCE_URL);
        dataSource.setUsername(EnvironmentVariables.SPRING_DATASOURCE_USERNAME);
        dataSource.setPassword(EnvironmentVariables.SPRING_DATASOURCE_PASSWORD);
        dataSource.setDriverClassName(EnvironmentVariables.SPRING_DATASOURCE_DRIVER_CLASS_NAME);
        return dataSource;
    }
}
