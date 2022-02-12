package by.bogdanov.service.impl;

import by.bogdanov.dao.DaoEnum;
import by.bogdanov.dao.DaoException;
import by.bogdanov.dao.OperationDao;
import by.bogdanov.dao.TransactionFactory;
import by.bogdanov.dao.mysql.TransactionFactoryImpl;
import by.bogdanov.entity.Operation;
import by.bogdanov.service.OperationService;
import by.bogdanov.service.ServiceException;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class OperationServiceImpl extends ServiceImpl implements OperationService {

    private final Logger logger = LogManager.getLogger(OperationServiceImpl.class);

    @Override
    public List<Operation> readAllOperations() throws ServiceException {
        List<Operation> operationList;
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();

            OperationDao operationDao = transaction.createDao(DaoEnum.OPERATION_DAO);
            operationList = operationDao.readAll();
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return operationList;
    }

    @Override
    public Operation readOperationById(int id) throws ServiceException {
        Operation operation;
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            OperationDao operationDao = transaction.createDao(DaoEnum.OPERATION_DAO);
            operation = operationDao.readById(id);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return operation;
    }

    @Override
    public void createOrderOperation(int order_id, int operation_id) throws ServiceException {
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            OperationDao operationDao = transaction.createDao(DaoEnum.OPERATION_DAO);
            operationDao.createOrderOperation(order_id, operation_id);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void createClearanceOperation(int clear_id, int operation_id) throws ServiceException {
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            OperationDao operationDao = transaction.createDao(DaoEnum.OPERATION_DAO);
            operationDao.createClearanceOperation(clear_id, operation_id);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
    }

}
