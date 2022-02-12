package by.bogdanov.service;

import by.bogdanov.entity.User;
import java.util.List;

public interface UserService extends Service{

void createUser(User user) throws ServiceException;
void deleteUser(User user) throws ServiceException;
void updateUser(User user) throws ServiceException;
User readUserById(int id) throws ServiceException;
List<User> readAllUsers()throws ServiceException;
User readUserByLogin(String login) throws ServiceException;
}
