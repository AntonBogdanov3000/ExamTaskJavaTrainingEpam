package by.bogdanov.service;

import by.bogdanov.entity.Clearance;
import java.util.List;

public interface ClearanceService extends Service {

Clearance getClearanceById(int id) throws ServiceException;
List<Clearance> getAllClearance() throws ServiceException;
void createClearance(Clearance clearance) throws ServiceException;
void deleteClearance(int clearId) throws ServiceException;
}
