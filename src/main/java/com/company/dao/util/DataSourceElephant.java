package com.company.dao.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DataSourceElephant implements Closeable {

    private Connection connection;

    private DataSourceElephant() {

    }

    @Value("${db.elephant.url}")
    private String URL;
    @Value("${db.elephant.username}")
    private String USER;
    @Value("${db.elephant.password}")
    private String PASSWORD;

    private static final Logger log = LogManager.getLogger(DataSourceElephant.class);

    @Autowired
    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                log.info("Create connection in to ElephantSQL - {}", connection);
            } catch (ClassNotFoundException | SQLException e) {
                log.error("Connection ERROR (ElephantSQL) - {}", e);
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Connection ERROR (ElephantSQL) - {}", e);
                throw new RuntimeException(e);
            }
        }
    }
}
