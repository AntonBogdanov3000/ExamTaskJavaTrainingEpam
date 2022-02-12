package by.bogdanov.dao.mysql;

import by.bogdanov.dao.DaoException;
import by.bogdanov.dao.VehicleDao;
import by.bogdanov.entity.Vehicle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class VehicleDaoImpl implements VehicleDao {

    private final Logger logger = LogManager.getLogger(VehicleDaoImpl.class);
    private Connection connection;

    public VehicleDaoImpl(){}

    public VehicleDaoImpl(Connection connection){
        this.connection = connection;
    }

    private static final String SQL_INSERT_VEHICLE = "INSERT INTO vehicle (owner_id, model, plate, mileage,year) VALUES (?,?,?,?,?)";
    private static final String SQL_READ_VEHICLE_ID = "SELECT model, plate, owner_id, year, mileage FROM vehicle WHERE id=?";
    private static final String SQL_SELECT_ALL_VEHICLES = "SELECT * FROM vehicle";
    private static final String SQL_SELECT_VEHICLE_BY_PLATE = "SELECT id, model, owner_id, year, mileage FROM vehicle WHERE plate=?";
    private static final String SQL_DELETE_VEHICLE_BY_ID = "DELETE FROM vehicle WHERE id=?";
    private static final String SQL_UPDATE_VEHICLE = "UPDATE vehicle SET owner_id=?, model=?, plate=?, mileage=?, year=? WHERE id=?";
    private static final String SQL_READ_VEHICLE_BY_YEAR = "SELECT model, mileage, plate FROM vehicle WHERE year=?";

    @Override
    public List<Vehicle> readAll() throws DaoException {
        List<Vehicle> vehiclesList = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_VEHICLES);
            while (resultSet.next()){
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setOwnerId(resultSet.getInt("owner_id"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setPlate(resultSet.getString("plate"));
                vehicle.setYear(resultSet.getInt("year"));
                vehicle.setMileage(resultSet.getInt("mileage"));
                vehiclesList.add(vehicle);
            }
            connection.close();
            logger.info("VehicleList contains " + vehiclesList.size() + " cars");
        } catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
        return vehiclesList;
    }

    @Override
    public Vehicle readById(int id) throws DaoException {
        Vehicle vehicle = new Vehicle();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_READ_VEHICLE_ID);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
            vehicle.setModel(resultSet.getString("model"));
            vehicle.setPlate(resultSet.getString("plate"));
            vehicle.setOwnerId(resultSet.getInt("owner_id"));
            vehicle.setYear(resultSet.getInt("year"));
            vehicle.setMileage(resultSet.getInt("mileage"));
            vehicle.setId(id);
            }
            connection.close();
            logger.info("Read vehicle " + id);
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
        return vehicle;
    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_VEHICLE_BY_ID);
            statement.setLong(1,id);
            statement.executeUpdate();
            connection.close();
            logger.info("Vehicle id: " + id + " deleted");
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
    }
    @Override
    public void create(Vehicle vehicle) throws DaoException {
        try{
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_VEHICLE);
            statement.setLong(1,vehicle.getOwnerId());
            statement.setString(2,vehicle.getModel());
            statement.setString(3, vehicle.getPlate());
            statement.setInt(4,vehicle.getMileage());
            statement.setInt(5,vehicle.getYear());
            statement.executeUpdate();
            logger.info("Vehicle created " + vehicle.getModel());
            connection.close();
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
    }
    @Override
    public void update(Vehicle vehicle) throws DaoException {
        try{
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_VEHICLE);
            statement.setLong(1,vehicle.getOwnerId());
            statement.setString(2,vehicle.getModel());
            statement.setString(3,vehicle.getPlate());
            statement.setInt(4,vehicle.getMileage());
            statement.setInt(5,vehicle.getYear());
            statement.setLong(6,vehicle.getId());
            statement.executeUpdate();
            connection.close();
            logger.info("Vehicle " + vehicle.getPlate() + " updated");
        }catch (SQLException e){
           logger.debug(e.getMessage());
           throw new DaoException(e);
        }
    }

    @Override
    public List<Vehicle> readByYear(int year) throws DaoException {
        List<Vehicle> list = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_READ_VEHICLE_BY_YEAR);
            statement.setLong(1,year);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Vehicle vehicle = new Vehicle();
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setPlate(resultSet.getString("plate"));
                vehicle.setMileage(resultSet.getInt("mileage"));
                vehicle.setYear(year);
                list.add(vehicle);
            }
            connection.close();
            logger.info("Search for vehicles manufactured in " + year);
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    public Vehicle readByPlate(String plate) throws DaoException {
        Vehicle vehicle = new Vehicle();
        PreparedStatement statement;
        try{
            statement = connection.prepareStatement(SQL_SELECT_VEHICLE_BY_PLATE);
            statement.setString(1,plate);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setOwnerId(resultSet.getInt("owner_id"));
                vehicle.setMileage(resultSet.getInt("mileage"));
                vehicle.setYear(resultSet.getInt("year"));
                vehicle.setPlate(plate);
            }
            connection.close();
            logger.info("Search for vehicle with plates " + plate);
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
        return vehicle;
    }
}
