package com.company.dao.util;

import java.sql.Connection;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;

//public enum CustomConnectionPool{
//    INSTANCE;
//
//    private BlockingDeque<Connection> freeConnections;
//    private Queue<Connection> givenAwayConnections;
//}

public class ConfigurationManager {
    private Properties props;
    
    public ConfigurationManager(){
        props = new Properties();
    }
    
    
    public String getProperty(String key){
        return getProperty(key);
    }
}
