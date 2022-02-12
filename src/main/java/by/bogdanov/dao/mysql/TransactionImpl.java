package by.bogdanov.dao.mysql;

import by.bogdanov.dao.*;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class TransactionImpl implements Transaction {

    private final Logger logger = LogManager.getLogger(TransactionImpl.class);
    private Connection connection;

    public TransactionImpl(Connection connection){
        this.connection = connection;
    }

    private TransactionImpl(){}

    @Override
    public <T extends BaseDao<?>> T createDao(DaoEnum key){
        switch (key) {
            case USER_DAO: return (T) new UserDaoImpl(connection);
            case VEHICLE_DAO: return (T) new VehicleDaoImpl(connection);
            case ORDER_DAO: return (T) new OrderDaoImpl(connection);
            case OPERATION_DAO: return (T) new OperationDaoImpl(connection);
            case CLEARANCE_DAO: return (T) new ClearanceDaoImpl(connection);
        }
        logger.info("Created DAO " + key);
        return null;
    }

    @Override
    public void commit() throws DaoException {
     try {
         connection.commit();
     } catch (SQLException e){
         throw new DaoException(e);
     }
    }

    @Override
    public void rollback() throws DaoException {
    try {
        connection.rollback();
    }catch (SQLException e){
        throw new DaoException(e);
    }
    }
}
