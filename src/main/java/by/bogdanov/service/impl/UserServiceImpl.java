package by.bogdanov.service.impl;

import by.bogdanov.dao.*;
import by.bogdanov.dao.mysql.TransactionFactoryImpl;
import by.bogdanov.entity.User;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.UserService;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UserServiceImpl extends ServiceImpl implements UserService {

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public void createUser(User user) throws ServiceException {
        try {
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER_DAO);
            userDao.create(user);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUser(User user) throws ServiceException {
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER_DAO);
            userDao.delete(user.getId());
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER_DAO);
            userDao.update(user);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public User readUserById(int id) throws ServiceException {
        User user;
        try {
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER_DAO);
            user = userDao.readById(id);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public List<User> readAllUsers() throws ServiceException {
        List<User> userList;
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER_DAO);
            userList = userDao.readAll();
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return userList;
    }

    @Override
    public User readUserByLogin(String login) throws ServiceException {
        User user;
        try {
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            UserDao userDao = transaction.createDao(DaoEnum.USER_DAO);
            user = userDao.findUserByLogin(login);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return user;
    }
 }

