package com.edutech.progressive.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static Properties properties = new Properties();

    public static void loadProperties(){
        try(InputStream input = DatabaseConnectionManager.class.getClassLoader().getResourceAsStream("application.properties")){

            if(input == null){
                throw new RuntimeException("application.properties file not found");
            }


        }catch(Exception e){
            throw new RuntimeException("Failed to load database properties",e);
        }
    }
    public static Connection getConnection(){
        try{
            if(properties.isEmpty()){
                loadProperties();
            }
            String url = properties.getProperty("spring.datasource.url");
            String username = properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");
            String driver = properties.getProperty("spring.datasource.driver-class-name");

            return DriverManager.getConnection(url, username, password);

        }catch(Exception e){
            throw new RuntimeException("Database connection failed",e);
        }
    }
}
