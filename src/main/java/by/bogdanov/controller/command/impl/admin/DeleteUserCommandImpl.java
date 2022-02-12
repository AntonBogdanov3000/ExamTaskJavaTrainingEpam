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


public class DeleteUserCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(DeleteUserCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("path");
        HttpSession session = request.getSession();
        User user;
        int userId = Integer.parseInt(request.getParameter("delUser"));
        logger.info("Prepare to delete user id: " + userId);
        if(!session.isNew()){
            try{
                UserService userService = ServiceFactory.getInstance().getUserService();
                user = userService.readUserById(userId);
                userService.deleteUser(user);
            }catch (ServiceException e){
                logger.debug(e.getMessage());
                e.printStackTrace();
            }
        }
        return page;
    }
}
