package by.bogdanov.service.impl;

import by.bogdanov.dao.*;
import by.bogdanov.dao.mysql.TransactionFactoryImpl;
import by.bogdanov.entity.User;
import by.bogdanov.entity.Vehicle;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.VehicleService;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class VehicleServiceImpl extends ServiceImpl implements VehicleService {

    private final Logger logger = LogManager.getLogger(VehicleServiceImpl.class);

    @Override
    public List<Vehicle> readAllVehicles() throws ServiceException {
        List<Vehicle> vehicleList;
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            VehicleDao vehicleDao = transaction.createDao(DaoEnum.VEHICLE_DAO);
            vehicleList = vehicleDao.readAll();
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return vehicleList;
    }

    @Override
    public List<Vehicle> readVehicleByUserId(int id) throws ServiceException {
        List<Vehicle> vehicleList;
        List<Vehicle> result = new ArrayList<>();
        User user;
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            VehicleDao vehicleDao = transaction.createDao(DaoEnum.VEHICLE_DAO);
            UserDao userDao = transaction.createDao(DaoEnum.USER_DAO);
            user = userDao.readById(id);
            vehicleList = vehicleDao.readAll();
            for(Vehicle car : vehicleList){
                if(car.getOwnerId() == user.getId()){
                    result.add(car);
                }
            }
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Vehicle readByPlate(String plate) throws ServiceException {
        Vehicle vehicle;
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            VehicleDao vehicleDao = transaction.createDao(DaoEnum.VEHICLE_DAO);
            vehicle = vehicleDao.readByPlate(plate);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return vehicle;
    }

    @Override
    public Vehicle readById(int id) throws ServiceException {
        Vehicle vehicle;
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            VehicleDao vehicleDao = transaction.createDao(DaoEnum.VEHICLE_DAO);
            vehicle = vehicleDao.readById(id);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        return vehicle;
    }

    @Override
    public void createVehicle(Vehicle vehicle) throws ServiceException {
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            VehicleDao vehicleDao = transaction.createDao(DaoEnum.VEHICLE_DAO);
            vehicleDao.create(vehicle);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteVehicle(int id) throws ServiceException {
        try{
            TransactionFactory factory = new TransactionFactoryImpl();
            transaction = factory.createTransaction();
            VehicleDao vehicleDao = transaction.createDao(DaoEnum.VEHICLE_DAO);
            vehicleDao.delete(id);
        }catch (DaoException e){
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
    }
}
