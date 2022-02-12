package by.bogdanov.controller.command.impl.manager;

import by.bogdanov.controller.command.Command;
import by.bogdanov.controller.command.validators.LoginValidator;
import by.bogdanov.entity.User;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import by.bogdanov.service.UserService;
import by.bogdanov.entity.Vehicle;
import by.bogdanov.service.VehicleService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetClientCarsCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(GetClientCarsCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page = request.getParameter("path");
        UserService userService = ServiceFactory.getInstance().getUserService();
        VehicleService vehicleService = ServiceFactory.getInstance().getVehicleService();
        User user;
        List<Vehicle> vehicleList;
        try{
            LoginValidator loginValidator = new LoginValidator();
            if(!loginValidator.checkLogin(request.getParameter("login"))){
                request.setAttribute("wrongLogin", "incorrect customer");
                return "CreateOrderPage.jsp";
            }else {
                user = userService.readUserByLogin(request.getParameter("login"));
                if(user.getRole() != 1){
                    request.setAttribute("wrongLogin", "incorrect customer");
                    return "CreateOrderPage.jsp";
                }
                vehicleList = vehicleService.readVehicleByUserId(user.getId());
                request.setAttribute("customerCars", vehicleList);
                request.setAttribute("customer", user);
            }
        }catch (ServiceException e){
            logger.debug(e.getMessage());
        }
        return page;
    }
}
