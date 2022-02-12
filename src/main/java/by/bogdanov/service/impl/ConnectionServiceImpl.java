package by.bogdanov.service.impl;

import by.bogdanov.dao.DaoException;
import by.bogdanov.dao.pool.ConnectionPool;
import by.bogdanov.service.ConnectionService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ConnectionServiceImpl implements ConnectionService {

    private final Logger logger = LogManager.getLogger(ConnectionServiceImpl.class);

    private final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/carshop";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "Americana22";
    private final int DB_POOL_SIZE = 32;
    private final int DB_START_SIZE = 10;
    private final int DB_CHECK_CONNECTION_TIMEOUT = 30;


   public void startConnection(){
        try {
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS ,DB_URL, DB_USER,
                    DB_PASSWORD, DB_START_SIZE, DB_POOL_SIZE, DB_CHECK_CONNECTION_TIMEOUT);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
    }
}

