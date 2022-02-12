package by.bogdanov.dao;

import by.bogdanov.entity.Clearance;
import java.util.List;

public interface ClearanceDao extends BaseDao<Clearance>{
    List<Clearance> readAll() throws DaoException;
    Clearance readById(int id) throws DaoException;
    void delete(int id) throws DaoException;
    void create(Clearance clearance) throws DaoException;
    void update(Clearance clearance) throws DaoException;

}
