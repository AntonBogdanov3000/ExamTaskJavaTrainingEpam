package by.bogdanov.dao;

import java.sql.Connection;

public interface TransactionFactory {
    public Transaction createTransaction();
    public void close();
    public Connection getConnection();
}
