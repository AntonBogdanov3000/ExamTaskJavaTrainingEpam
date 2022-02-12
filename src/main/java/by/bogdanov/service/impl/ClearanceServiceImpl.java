package by.bogdanov.service.impl;

import by.bogdanov.dao.ClearanceDao;
import by.bogdanov.dao.DaoEnum;
import by.bogdanov.dao.DaoException;
import by.bogdanov.dao.TransactionFactory;
import by.bogdanov.dao.mysql.TransactionFactoryImpl;
import by.bogdanov.entity.Clearance;
import by.bogdanov.service.ClearanceService;
import by.bogdanov.service.ServiceException;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class ClearanceServiceImpl extends ServiceImpl implements ClearanceService {

    private final Logger logger = LogManager.getLogger(ClearanceServiceImpl.class);

    @Override
    public Clearance getClearanceById(int id) throws ServiceException {
        Clearance clearance;
        try {
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            ClearanceDao clearanceDao = transaction.createDao(DaoEnum.CLEARANCE_DAO);
            clearance = clearanceDao.readById(id);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return clearance;
    }

    @Override
    public List<Clearance> getAllClearance() throws ServiceException {
        List<Clearance> clearanceList;
        try {
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            ClearanceDao clearanceDao = transaction.createDao(DaoEnum.CLEARANCE_DAO);
            clearanceList = clearanceDao.readAll();
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return clearanceList;
    }

    @Override
    public void createClearance(Clearance clearance) throws ServiceException {
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            ClearanceDao clearanceDao = transaction.createDao(DaoEnum.CLEARANCE_DAO);
            clearanceDao.create(clearance);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteClearance(int clearId) throws ServiceException {
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            ClearanceDao clearanceDao = transaction.createDao(DaoEnum.CLEARANCE_DAO);
            clearanceDao.delete(clearId);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
    }
}
