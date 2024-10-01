package ru.job4j.jdbc;

import java.io.*;
import java.sql.*;
import java.util.*;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        Class.forName(properties.getProperty("jdbc.driver"));
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        connection = DriverManager.getConnection(url, username, password);
    }

    public void createTable(String tableName) {
        String sql = String.format("CREATE TABLE %s()", tableName);
        query(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("DROP TABLE %s", tableName);
        query(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("ALTER TABLE %s ADD %s %s",
                tableName, columnName, type);
        query(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s",
                tableName, columnName);
        query(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName, columnName, newColumnName);
        query(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private void query(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException sqlException) {
            throw new IllegalStateException(sqlException);
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();

        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("Company");
            System.out.println(tableEditor.getTableScheme("Company"));
            tableEditor.addColumn("Company", "name", "VARCHAR(255)");
            System.out.println(tableEditor.getTableScheme("Company"));
            tableEditor.renameColumn("Company", "name", "new_name");
            System.out.println(tableEditor.getTableScheme("Company"));
            tableEditor.dropColumn("Company", "new_name");
            System.out.println(tableEditor.getTableScheme("Company"));
            tableEditor.dropTable("Company");
        }
    }
}
