package by.bogdanov.dao;

import by.bogdanov.entity.Order;
import java.util.List;

public interface OrderDao extends BaseDao<Order>{
    List<Order> readAll() throws DaoException;
    Order readById(int id) throws DaoException;
    void delete(int id) throws DaoException;
    void create(Order order) throws DaoException;
    void update(Order order) throws DaoException;
    List<Order> readByUserId(int id) throws DaoException;
}
