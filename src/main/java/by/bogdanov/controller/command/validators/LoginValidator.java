package by.bogdanov.controller.command.validators;

import by.bogdanov.entity.User;
import by.bogdanov.service.UserService;
import by.bogdanov.service.impl.UserServiceImpl;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginValidator {

    private final Logger logger = LogManager.getLogger(LoginValidator.class);

    public boolean checkLogin(String login){
        UserService userService = new UserServiceImpl();
        List<User> userList;
        try{
            userList = userService.readAllUsers();
            for(User user : userList){
                if(user.getLogin().equals(login)){
                    return true;
                }
            }
        }catch (Exception e){
         logger.debug(e.getMessage());
        }
        return false;

    }
}
