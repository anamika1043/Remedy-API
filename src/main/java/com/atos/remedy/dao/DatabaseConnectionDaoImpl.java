package com.atos.remedy.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("DatabaseConnectionDao")
public class DatabaseConnectionDaoImpl implements DatabaseConnectionDao
{
    Logger logger;
    @Value("${spring.datasource.driverClassName}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String datsourceUrl;
    
    public DatabaseConnectionDaoImpl() {
        this.logger = LogManager.getLogger((Class)DatabaseConnectionDaoImpl.class);
    }
    
    @Override
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(this.driver);
            conn = DriverManager.getConnection(this.datsourceUrl);
            this.logger.info("Database connection successful for BBCJIA------" + conn);
        }
        catch (Exception e) {
            this.logger.info("Database connection Failed for BBCJIA------", (Throwable)e);
        }
        return conn;
    }
}
