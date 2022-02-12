package by.bogdanov.dao.mysql;

import by.bogdanov.dao.ClearanceDao;
import by.bogdanov.dao.DaoException;
import by.bogdanov.entity.Clearance;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ClearanceDaoImpl implements ClearanceDao {

    private final Logger logger = LogManager.getLogger(ClearanceDaoImpl.class);

    private Connection connection;
    public ClearanceDaoImpl(Connection connection){
        this.connection = connection;
    }
    public ClearanceDaoImpl(){}
    public static int id;

    private static final String SQL_SELECT_ALL_CLEARANCE = "SELECT * FROM clearance";
    private static final String SQL_READ_CLEARANCE_BY_ID = "SELECT name, start_date, end_date, discount FROM clearance WHERE id=?";
    private static final String SQL_DELETE_CLEARANCE_BY_ID = "DELETE FROM clearance WHERE id=?";
    private static final String SQL_CREATE_CLEARANCE = "INSERT INTO clearance(name, start_date, end_date, operation_id, discount) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE_CLEARANCE = "UPDATE clearance SET name=?, start_date=?, end_date=? ,discount=? WHERE id=?";

    @Override
    public List<Clearance> readAll() throws DaoException {
        List<Clearance> clearanceList = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CLEARANCE);
            while (resultSet.next()){
                Clearance clearance = new Clearance();
                clearance.setId(resultSet.getInt("id"));
                clearance.setOperation_id(resultSet.getInt("operation_id"));
                clearance.setName(resultSet.getString("name"));
                clearance.setStartDate(resultSet.getDate("start_date"));
                clearance.setEndDate(resultSet.getDate("end_date"));
                clearance.setDiscount(resultSet.getInt("discount"));
                clearanceList.add(clearance);
                connection.close();
            }
            logger.info("ClearanceList contains " + clearanceList.size());
        } catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
        return clearanceList;
    }

    @Override
    public Clearance readById(int id) throws DaoException {
        Clearance clearance = new Clearance();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_READ_CLEARANCE_BY_ID);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                clearance.setName(resultSet.getString("name"));
                clearance.setStartDate(resultSet.getDate("start_date"));
                clearance.setEndDate(resultSet.getDate("end_date"));
                clearance.setDiscount(resultSet.getInt("discount"));
                clearance.setId(id);
            }
            connection.close();
            logger.info("Clearance read by id = " + id );
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
        return clearance;
    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CLEARANCE_BY_ID);
            statement.setInt(1,id);
            statement.executeUpdate();
            connection.close();
            logger.info("Clearance deleted id " + id);
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void create(Clearance clearance) throws DaoException {
        java.sql.Date sqlDateStart = new java.sql.Date(clearance.getStartDate().getTime());
        java.sql.Date sqlDateEnd = new java.sql.Date(clearance.getEndDate().getTime());
        try{
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_CLEARANCE,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,clearance.getName());
            statement.setDate(2,sqlDateStart);
            statement.setDate(3,sqlDateEnd);
            statement.setInt(4,clearance.getOperation_id());
            statement.setInt(5,clearance.getDiscount());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()){
                id = rs.getInt(1);
                logger.info("Created new clearance " + id);
            }
            connection.close();
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Clearance clearance) throws DaoException {
        java.sql.Date sqlDateStart = new java.sql.Date(clearance.getStartDate().getTime());
        java.sql.Date sqlDateEnd = new java.sql.Date(clearance.getEndDate().getTime());
        try{
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CLEARANCE);
            statement.setString(1,clearance.getName());
            statement.setDate(2,sqlDateStart);
            statement.setDate(3,sqlDateEnd);
            statement.setInt(4,clearance.getDiscount());
            statement.executeUpdate();
            connection.close();
            logger.info("Clearance " + clearance.getId() + " was updated");
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
    }
}
