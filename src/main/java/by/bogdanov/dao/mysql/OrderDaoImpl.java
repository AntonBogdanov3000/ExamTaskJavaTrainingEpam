package by.bogdanov.dao.mysql;

import by.bogdanov.dao.DaoException;
import by.bogdanov.dao.OrderDao;
import by.bogdanov.entity.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class OrderDaoImpl implements OrderDao {

    private final Logger logger = LogManager.getLogger(OrderDaoImpl.class);

    private Connection connection;
    public OrderDaoImpl(Connection connection){
        this.connection = connection;
    }
    public OrderDaoImpl(){}
    public static int id;

    private static final String SQL_SELECT_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SQL_READ_ORDER_BY_ID = "SELECT user_id, total_price, vehicle_id, manager_id, date FROM orders WHERE id=?";
    private static final String SQL_DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE id=?";
    private static final String SQL_CREATE_ORDER = "INSERT INTO orders(user_id, date, total_price, vehicle_id, manager_id) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders SET user_id=?, date=?, total_price=? WHERE id=?";
    private static final String SQL_READ_ORDERS_BY_USER_ID = "SELECT id, total_price, vehicle_id, manager_id, date FROM orders WHERE user_id=?";

    @Override
    public List<Order> readAll() throws DaoException {
        List<Order> orderList = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ORDERS);
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setPrice(resultSet.getInt("total_price"));
                order.setDate(resultSet.getDate("date"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setVehicleId(resultSet.getInt("vehicle_id"));
                order.setManagerId(resultSet.getInt("manager_id"));
                orderList.add(order);
            }
            connection.close();
            logger.info("OrderList contains " + orderList.size() + " orders");
        } catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
        return orderList;
    }

    @Override
    public Order readById(int id) throws DaoException {
        Order order = new Order();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_READ_ORDER_BY_ID);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
            order.setUserId(resultSet.getInt("user_id"));
            order.setPrice(resultSet.getInt("total_price"));
            order.setVehicleId(resultSet.getInt("vehicle_id"));
            order.setDate(resultSet.getDate("date"));
            order.setManagerId(resultSet.getInt("manager_id"));
            order.setId(id);
            }
            connection.close();
            logger.info("Order read " + id);
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
        return order;
    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER_BY_ID);
            statement.setInt(1,id);
            statement.executeUpdate();
            connection.close();
            logger.info("Order deleted " + id);
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void create(Order order) throws DaoException {
        java.sql.Date sqlDate = new java.sql.Date(order.getDate().getTime());
        try{
            PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ORDER,Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getUserId());
            statement.setDate(2, sqlDate);
            statement.setInt(3, order.getPrice());
            statement.setInt(4, order.getVehicleId());
            statement.setInt(5, order.getManagerId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()){
                id = rs.getInt(1);
                logger.info("Order created " + id);
            }
            connection.close();
        }catch (SQLException e){
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(Order order) throws DaoException {
        java.sql.Date sqlDate = new java.sql.Date(order.getDate().getTime());
        try{
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER);
            statement.setLong(1,order.getUserId());
            statement.setDate(2,sqlDate);
            statement.setInt(3,order.getPrice());
            statement.setLong(4,order.getId());
            statement.executeUpdate();
            connection.close();
            logger.info("Order " + order.getId() + " updated");
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> readByUserId(int id) throws DaoException {
        List<Order> orderList = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_READ_ORDERS_BY_USER_ID);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setPrice(resultSet.getInt("total_price"));
                order.setVehicleId(resultSet.getInt("vehicle_id"));
                order.setDate(resultSet.getDate("date"));
                order.setManagerId(resultSet.getInt("manager_id"));
                order.setUserId(id);
                orderList.add(order);
            }
            connection.close();
            logger.info("User " + id + " have " + orderList.size() + " orders ");
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
        return orderList;
    }

}
