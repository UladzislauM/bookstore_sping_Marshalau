package com.company;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class ContextConfiguration {
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        Properties properties = properties();
        String urlKey = null;
        String userKey = null;
        String passwordKey = null;
        String driverClassNameKey = null;
        String typeOfConnection = properties.getProperty("db");
        switch (typeOfConnection) {
            case "elephant":
                urlKey = "db.elephant.url";
                userKey = "db.elephant.user";
                passwordKey = "db.elephant.password";
                driverClassNameKey = "db.elephant.driver-Class-Name";
                break;
            case "local":
                urlKey = "db.local.url";
                userKey = "db.local.user";
                passwordKey = "db.local.password";
                driverClassNameKey = "db.local.driver-Class-Name";
                break;
            default:
                urlKey = "db.elephant.url";
                userKey = "db.elephant.user";
                passwordKey = "db.elephant.password";
                driverClassNameKey = "db.elephant.driver-Class-Name";
        }
        String url = properties.getProperty(urlKey);
        String user = properties.getProperty(userKey);
        String password = properties.getProperty(passwordKey);
        String driverClassName = properties.getProperty(driverClassNameKey);

        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        return new HikariDataSource(config);
    }

    @Bean
    public Properties properties() {
        Resource resource = new ClassPathResource("/application.properties");
        try {
            return PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
