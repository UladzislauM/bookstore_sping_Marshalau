package com.company;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
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
    public NamedParameterJdbcTemplate namedJdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataSource());
    }
    @Bean
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig();
//        config.set properties()
        return new HikariDataSource(config);
    }

//    public Properties properties(){
//        try {
//
//            Resource resource = new ClassPathResource("/application.properties");
////            Properties properties = PropertiesLoaderUtils.loadAllProperties(resource);
//        }catch (IOException e){
//            throw new RuntimeException(e);
//        }
//
//    }
}
