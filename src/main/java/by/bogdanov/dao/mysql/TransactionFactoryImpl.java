package by.bogdanov.dao.mysql;

import by.bogdanov.dao.DaoException;
import by.bogdanov.dao.Transaction;
import by.bogdanov.dao.TransactionFactory;
import by.bogdanov.dao.pool.ConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class TransactionFactoryImpl implements TransactionFactory {

    private final Logger logger = LogManager.getLogger(TransactionFactoryImpl.class);
    private  Connection connection;


    public TransactionFactoryImpl() throws DaoException {
        connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(true);
        }catch (SQLException e){
            logger.info(e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Transaction createTransaction() {
        return new TransactionImpl(connection);
    }

    @Override
    public void close() {
    try {
        connection.close();
    }catch (SQLException e){
        logger.debug(e.getMessage());
    }
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }
}
