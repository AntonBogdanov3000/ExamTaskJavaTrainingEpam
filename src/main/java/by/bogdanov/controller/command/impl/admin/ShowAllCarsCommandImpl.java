package by.bogdanov.controller.command.impl.admin;

import by.bogdanov.controller.command.Command;
import by.bogdanov.entity.Vehicle;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import by.bogdanov.service.VehicleService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class ShowAllCarsCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(ShowAllCarsCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("path");
        VehicleService vehicleService = ServiceFactory.getInstance().getVehicleService();
        List<Vehicle> adminVehicleList;
        try{
            adminVehicleList = vehicleService.readAllVehicles();
            request.setAttribute("adminVehicleList", adminVehicleList);
        }catch (ServiceException e){
            logger.debug(e.getMessage());
          e.printStackTrace();
        }
        return page;
    }
}
