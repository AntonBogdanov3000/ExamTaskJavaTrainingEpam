package by.bogdanov.service;

public class ServiceException extends Exception{
    public ServiceException(){}

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable obj){
        super(message,obj);
    }

    public ServiceException(Throwable obj){
        super(obj);
    }
}
