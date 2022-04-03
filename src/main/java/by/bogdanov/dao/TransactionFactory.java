package by.bogdanov.dao;

import java.sql.Connection;

public interface TransactionFactory {
     Transaction createTransaction();
     void close();
     Connection getConnection();
}
