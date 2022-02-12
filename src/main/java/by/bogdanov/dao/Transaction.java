package by.bogdanov.dao;

public interface Transaction {
    <T extends BaseDao<?>> T createDao(DaoEnum key);
    void commit() throws DaoException;
    void rollback() throws DaoException;
}
