package ru.job4j.jdbc;

import ru.job4j.io.*;
import java.sql.*;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("data/app.properties");
        config.load();
        Class.forName(config.value("hibernate.connection.driver_class"));
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String password = config.value("hibernate.connection.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
