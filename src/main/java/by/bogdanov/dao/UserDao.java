package by.bogdanov.dao;

import by.bogdanov.entity.User;
import java.util.List;

public interface UserDao extends BaseDao<User>{
    List<User> readAll() throws DaoException;
    User readById(int id) throws DaoException;
    void delete(int id) throws DaoException;
    void create(User user) throws DaoException;
    void update(User user) throws DaoException;
    User findUserByLogin(String login) throws DaoException;
}
