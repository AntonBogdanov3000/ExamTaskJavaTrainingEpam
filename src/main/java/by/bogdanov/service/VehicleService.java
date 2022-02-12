package by.bogdanov.service;

import by.bogdanov.entity.Vehicle;
import java.util.List;

public interface VehicleService extends Service{
    List<Vehicle> readAllVehicles()throws ServiceException;
    List<Vehicle> readVehicleByUserId(int id)throws ServiceException;
    Vehicle readByPlate(String plate) throws ServiceException;
    Vehicle readById(int id) throws ServiceException;
    void createVehicle(Vehicle vehicle) throws ServiceException;
    void deleteVehicle(int id) throws ServiceException;

}
