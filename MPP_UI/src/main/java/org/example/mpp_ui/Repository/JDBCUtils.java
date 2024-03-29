package org.example.mpp_ui.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private final Properties jdbcProp;
    private static final Logger logger = LogManager.getLogger();
    private Connection instance = null;
    public JDBCUtils(Properties props){
        jdbcProp = props;
    }
    private Connection getNewConnection(){
        logger.traceEntry();

        String url = jdbcProp.getProperty("jdbc.url");
        logger.info("trying to connect to database ... {}", url);
        try{
            return DriverManager.getConnection(url);
        }catch(SQLException e){
            logger.error(e);
            return null;
        }
    }
    public Connection getConnection(){
        logger.traceEntry();
        try{
            if (instance==null || instance.isClosed()){
                instance = getNewConnection();
            }
        }catch(SQLException e){
            logger.error(e);
        }
        logger.traceExit(instance);
        return instance;
    }
}
