package by.bogdanov.dao;

import by.bogdanov.entity.Entity;
import java.util.List;

public interface BaseDao <T extends Entity>{
    List<T> readAll() throws DaoException;
    T readById(int id) throws DaoException;
    void delete(int id) throws DaoException;
    void create(T t) throws DaoException;
    void update(T t) throws DaoException;
}
