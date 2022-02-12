package by.bogdanov.controller.command.impl.user;

import by.bogdanov.controller.command.Command;
import by.bogdanov.entity.User;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import by.bogdanov.service.UserService;
import by.bogdanov.service.VehicleService;
import by.bogdanov.entity.Vehicle;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class ReadUserCasCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(ReadUserCasCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user;
        List<Vehicle> vehicleList;
        String page = request.getParameter("path");
        VehicleService vehicleService = ServiceFactory.getInstance().getVehicleService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        try{
        user = userService.readUserByLogin(request.getParameter("login"));
        vehicleList = vehicleService.readVehicleByUserId(user.getId());
        request.setAttribute("vehicleList",vehicleList);
        }catch (ServiceException e){
            logger.debug(e.getMessage());
        }
        return page;
    }
}
