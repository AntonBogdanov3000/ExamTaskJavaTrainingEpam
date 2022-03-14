package by.bogdanov.controller.command.impl.admin;

import by.bogdanov.controller.command.Command;
import by.bogdanov.entity.User;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import by.bogdanov.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class UpdateUserCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(UpdateUserCommandImpl.class);

    @Override

    public String execute(HttpServletRequest request) {
        String page = request.getParameter("path");
        HttpSession session = request.getSession();
        User user ;
        UserService userService = ServiceFactory.getInstance().getUserService();

        try {

                int userId = Integer.parseInt(request.getParameter("updUser"));
                request.setAttribute("updUser",userId);

                user = userService.readUserById(userId);
                request.setAttribute("name", user.getName());
                request.setAttribute("lastname", user.getLastName());
                request.setAttribute("role", user.getRole());
                request.setAttribute("tel", user.getTelephone());
                request.setAttribute("login", user.getLogin());
                //request.setAttribute("password", user.getPassword());

            if(request.getParameter("upd").isEmpty()) {
                user.setName(request.getParameter("name"));
                user.setLastName(request.getParameter("lastname"));
                user.setRole(Integer.parseInt(request.getParameter("role")));
                user.setTelephone(request.getParameter("tel"));
                user.setLogin(request.getParameter("login"));
                //user.setPassword(request.getParameter("password"));
                userService.updateUser(user);
            }
            } catch (ServiceException e){
            logger.debug(e.getMessage());
                e.printStackTrace();
            }
        return page;
    }
}