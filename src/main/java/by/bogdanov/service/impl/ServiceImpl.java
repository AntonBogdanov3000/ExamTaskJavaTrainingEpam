package by.bogdanov.service.impl;

import by.bogdanov.dao.Transaction;
import by.bogdanov.service.Service;

abstract public class ServiceImpl implements Service {

    protected Transaction transaction = null;

    public void setTransaction(Transaction transaction) {
       this.transaction = transaction;
    }
}
