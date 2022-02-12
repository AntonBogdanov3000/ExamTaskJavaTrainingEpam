package by.bogdanov.dao.mysql;

import by.bogdanov.dao.DaoException;
import by.bogdanov.dao.UserDao;
import by.bogdanov.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserDaoImpl implements UserDao {

    private Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private Connection connection;
    public static int id;

    public UserDaoImpl(Connection connection){
        this.connection = connection;
    }
    public UserDaoImpl(){}

    private static final String SQL_INSERT_USER = "INSERT INTO users(name,lastname,password,login,telephone,role) VALUES(?,?,?,?,?,?)";
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_SELECT_USER_ID = "SELECT name, lastname, login, role, telephone, password FROM users WHERE id=?";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT id, name, lastname, password, telephone, role  FROM users WHERE login=?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";
    private static final String SQL_UPDATE_USER = "UPDATE users SET name=?, lastname=?, password=?, login=?, telephone=?, role=? WHERE id=?";

    @Override
    public List<User> readAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setRole(resultSet.getInt("role"));
                user.setLastName(resultSet.getString("lastname"));
                user.setPassword(resultSet.getString("password"));
                user.setLogin(resultSet.getString("login"));
                user.setTelephone(resultSet.getString("telephone"));
                userList.add(user);
            }
            connection.close();
            logger.info("UserList contains " + userList.size() + " users");
        } catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
        return userList;
    }

    @Override
    public User readById(int id) throws DaoException {
        User user = new User();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_ID);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setLogin(resultSet.getString("login"));
                user.setRole(resultSet.getInt("role"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setPassword(resultSet.getString("password"));
                user.setId(id);
            }
            connection.close();
            logger.info("User read " + user.getId());
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public void delete(int id) throws DaoException {
    try {
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
        statement.setLong(1,id);
        statement.executeUpdate();
        connection.close();
        logger.info("User " + id + " deleted");
    }catch (SQLException e){
        logger.debug(e.getMessage());
        throw new DaoException(e);
    }
    }

    @Override
    public void create(User user) throws DaoException {
     try{
         PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER,Statement.RETURN_GENERATED_KEYS);
         statement.setString(1,user.getName());
         statement.setString(2,user.getLastName());
         statement.setString(3,user.getPassword());
         statement.setString(4,user.getLogin());
         statement.setString(5,user.getTelephone());
         statement.setInt(6,user.getRole());
         statement.executeUpdate();
         ResultSet rs = statement.getGeneratedKeys();
         while (rs.next()){
             id = rs.getInt(1);
             logger.info("User created " + id);
         }
         connection.close();
     }catch (SQLException e){
         logger.debug(e.getMessage());
         throw new DaoException(e);
     }
    }

    @Override
    public void update(User user) throws DaoException {
        try{
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1,user.getName());
            statement.setString(2,user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getTelephone());
            statement.setInt(6,user.getRole());
            statement.setInt(7, user.getId());
            statement.executeUpdate();
            connection.close();
            logger.info("User " + user.getId() + " updated");
        }catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public User findUserByLogin(String login) throws DaoException {
        User user = new User();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            statement.setString(1,login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setPassword(resultSet.getString("password"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setRole(resultSet.getInt("role"));
                user.setLogin(login);
            }
            connection.close();
            logger.info("Search for User with login " + login);
        } catch (SQLException e){
            logger.debug(e.getMessage());
            throw new DaoException(e);
        }
        return user;
    }
}
