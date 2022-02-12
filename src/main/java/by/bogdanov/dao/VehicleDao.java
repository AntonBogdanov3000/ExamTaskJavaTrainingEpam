package by.bogdanov.dao;


import by.bogdanov.entity.Vehicle;
import java.util.List;

public interface VehicleDao extends BaseDao<Vehicle> {
    List<Vehicle> readAll() throws DaoException;
    Vehicle readById(int id) throws DaoException;
    void delete(int id) throws DaoException;
    void create(Vehicle vehicle) throws DaoException;
    void update(Vehicle vehicle) throws DaoException;
    List<Vehicle> readByYear(int year) throws DaoException;
    Vehicle readByPlate(String plate) throws DaoException;
}
