package by.bogdanov.service.impl;

import by.bogdanov.dao.*;
import by.bogdanov.dao.mysql.TransactionFactoryImpl;
import by.bogdanov.entity.Operation;
import by.bogdanov.entity.Order;
import by.bogdanov.entity.User;
import by.bogdanov.service.OrderService;
import by.bogdanov.service.ServiceException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class OrderServiceImpl extends ServiceImpl implements OrderService {

    private final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    @Override
    public List<Order> readOrdersByUserId(int id) throws ServiceException {
        List<Order> orderList;
        List<Order> result = new ArrayList<>();
        List<Operation> operationList;
        User user;
        try {
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();

            UserDao userDao = transaction.createDao(DaoEnum.USER_DAO);
            OrderDao orderDao = transaction.createDao(DaoEnum.ORDER_DAO);
            VehicleDao vehicleDao = transaction.createDao(DaoEnum.VEHICLE_DAO);
            OperationDao operationDao = transaction.createDao(DaoEnum.OPERATION_DAO);
            user = userDao.readById(id);
            orderList = orderDao.readAll();
            for(Order order : orderList){
                if(order.getUserId() == user.getId()){
                    operationList = operationDao.readByOrderId(order.getId());
                    order.setOperationList(operationList);
                    order.setVehicle(vehicleDao.readById(order.getVehicleId()));
                    result.add(order);
                }
            }
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Order> readOrdersByManagerId(int id) throws ServiceException {
        List<Order> orderList;
        List<Order> result = new ArrayList<>();
        List<Operation> operationList;

        try {
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            OrderDao orderDao = transaction.createDao(DaoEnum.ORDER_DAO);
            VehicleDao vehicleDao = transaction.createDao(DaoEnum.VEHICLE_DAO);
            OperationDao operationDao = transaction.createDao(DaoEnum.OPERATION_DAO);
            orderList = orderDao.readAll();

            for(Order order : orderList){
                if(order.getManagerId() == id){
                    operationList = operationDao.readByOrderId(order.getId());
                    order.setOperationList(operationList);
                    order.setVehicle(vehicleDao.readById(order.getVehicleId()));
                    result.add(order);
                }
            }
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public void createOrder(Order order) throws ServiceException {
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            OrderDao orderDao = transaction.createDao(DaoEnum.ORDER_DAO);
            orderDao.create(order);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateOrder(Order order) throws ServiceException {
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            OrderDao orderDao = transaction.createDao(DaoEnum.ORDER_DAO);
            orderDao.update(order);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteOrder(Order order) throws ServiceException {
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            OrderDao orderDao = transaction.createDao(DaoEnum.ORDER_DAO);
            orderDao.delete(order.getId());
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> readAllOrders() throws ServiceException {
        List<Order> orderList;
        List<Operation> operationList;
        try {
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            OrderDao orderDao = transaction.createDao(DaoEnum.ORDER_DAO);
            VehicleDao vehicleDao = transaction.createDao(DaoEnum.VEHICLE_DAO);
            OperationDao operationDao = transaction.createDao(DaoEnum.OPERATION_DAO);

            orderList = orderDao.readAll();

            for (Order order : orderList){
                operationList = operationDao.readByOrderId(order.getId());
                order.setVehicle(vehicleDao.readById(order.getVehicleId()));
                order.setOperationList(operationList);
            }

        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return orderList;
    }

    @Override
    public Order readOrderById(int id) throws ServiceException {
        Order order;
        try {
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            OrderDao orderDao = transaction.createDao(DaoEnum.ORDER_DAO);
            order = orderDao.readById(id);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return order;
    }
}
