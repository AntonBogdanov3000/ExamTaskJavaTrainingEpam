package by.bogdanov.dao;

import by.bogdanov.entity.Operation;
import java.util.List;

public interface OperationDao extends BaseDao<Operation>{
    List<Operation> readAll() throws DaoException;
    Operation readById(int id) throws DaoException;
    void delete(int id) throws DaoException;
    void create(Operation operation) throws DaoException;
    void update(Operation operation) throws DaoException;
    void createOrderOperation(int order_id, int operation_id) throws DaoException;
    void createClearanceOperation(int clear_id, int operation_id) throws DaoException;
    Operation readByPrice(int price) throws DaoException;
    List<Operation> readByOrderId(int id) throws DaoException;
}
