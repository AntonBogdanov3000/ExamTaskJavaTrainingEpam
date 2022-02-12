package by.bogdanov.service;

import by.bogdanov.entity.Order;
import java.util.List;

public interface OrderService extends Service{
    List<Order> readOrdersByUserId(int id) throws ServiceException;
    List<Order> readOrdersByManagerId(int id) throws ServiceException;
    List<Order> readAllOrders() throws ServiceException;
    Order readOrderById(int id) throws ServiceException;
    void createOrder(Order order) throws ServiceException;
    void updateOrder(Order order) throws ServiceException;
    void deleteOrder(Order order) throws ServiceException;

}
