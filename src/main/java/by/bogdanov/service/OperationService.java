package by.bogdanov.service;

import by.bogdanov.entity.Operation;
import java.util.List;

public interface OperationService extends Service{
    List<Operation> readAllOperations() throws ServiceException;
    Operation readOperationById(int id) throws ServiceException;
    void createOrderOperation(int order_id, int operation_id) throws ServiceException;
    void createClearanceOperation(int clear_id , int operation_id) throws ServiceException;
}
