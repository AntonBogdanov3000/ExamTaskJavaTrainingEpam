package by.bogdanov.controller.command.impl;

import by.bogdanov.controller.command.Command;
import by.bogdanov.controller.command.validators.LoginValidator;
import by.bogdanov.entity.User;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import by.bogdanov.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class LoginCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(LoginCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user;
        UserService userService = ServiceFactory.getInstance().getUserService();
        LoginValidator loginValidator = new LoginValidator();

        String password = request.getParameter("password");
        String login = request.getParameter("login");
        int idManager;

        if(password.isEmpty() || login.isEmpty() || !loginValidator.checkLogin(login)){
            request.setAttribute("errorLoginMessage", "Incorrect Login or Password");
            return "LogInPage.jsp";
        }

        try {
            user = userService.readUserByLogin(login);
            request.getSession().setAttribute("userName", user.getName());
            logger.info("User id: " + user.getId() + " " + user.getLogin() + " is login");
            
            if(user.getRole()==2){
                idManager = user.getId();
            } else{
                idManager = 0;
            }
            request.getSession().setAttribute("idManager", idManager);
            switch (user.getRole()){
                case 1: return "welcomePage.jsp";
                case 2: return "ManagerPage.jsp";
                case 3: return "AdminPage.jsp";
            }

        }catch (ServiceException e){
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        catch (NullPointerException e){
            logger.debug(e.getMessage());
        }
        return null;
    }
}

