package by.bogdanov.dao;

public class DaoException extends Exception{
    public DaoException(){
    }

    public DaoException(String message){
        super(message);
    }
    public DaoException(String message, Throwable obj){
        super(message, obj);
    }

    public DaoException(Throwable obj){
        super(obj);
    }
}
