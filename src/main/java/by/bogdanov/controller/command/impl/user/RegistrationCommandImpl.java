package by.bogdanov.controller.command.impl.user;

import by.bogdanov.controller.command.Command;
import by.bogdanov.controller.command.validators.LoginValidator;
import by.bogdanov.controller.command.validators.TextValidator;
import by.bogdanov.entity.User;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import by.bogdanov.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class RegistrationCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(RegistrationCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        LoginValidator loginValidator = new LoginValidator();
        TextValidator textValidator = new TextValidator();
        HttpSession session = request.getSession();

        String page;
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = new User();


        if(loginValidator.checkLogin(request.getParameter("login"))){
            request.setAttribute("incorrectLogin", "Current login is in use");
            return "registration.jsp";
        }
        if(!textValidator.checkText(request.getParameter("name"))){
            request.setAttribute("incorrectSymbolName", "Incorrect symbol to use in name");
            return "registration.jsp";
        }
        if(!textValidator.checkText(request.getParameter("lastname"))){
            request.setAttribute("incorrectSymbolLastName", "Incorrect symbol to use in lastname");
            return "registration.jsp";
        }

        if(request.getParameter("name").isEmpty() || request.getParameter("lastname").isEmpty() ||
        request.getParameter("password").isEmpty() || request.getParameter("login").isEmpty() ||
        request.getParameter("telephone").isEmpty()) {
            request.setAttribute("nullDataForUser", "Empty enter is not valid");
            return "registration.jsp";
        }
        else{
         user.setName(request.getParameter("name"));
         request.getSession().setAttribute("userName", user.getName());
         user.setLastName(request.getParameter("lastname"));
         user.setPassword(request.getParameter("password"));
         user.setLogin(request.getParameter("login"));
         user.setTelephone(request.getParameter("telephone"));
         user.setRole(Integer.parseInt(request.getParameter("role")));
         logger.info("!!" + request.getParameter("path") + " role " +
                 request.getParameter("role"));
         page = request.getParameter("path");
        }
        try {
            if(user.getRole() == 2){
                page = "AdminPage.jsp";
            }
            /*User checkUser = (User) session.getAttribute("f5ForUser");
            if(checkUser != null && checkUser.equals(user)){
                return null;
            }*/
            userService.createUser(user);
            logger.info("New user " +user.getLogin()+ " has been created");
        }catch (ServiceException e){
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        session.setAttribute("f5ForUser", user);
        return page;
    }

}
