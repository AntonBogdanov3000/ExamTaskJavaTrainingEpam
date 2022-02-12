package by.bogdanov.controller.command.impl.admin;

import by.bogdanov.controller.command.Command;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import by.bogdanov.service.VehicleService;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class DeleteCarCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(DeleteCarCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("path");
        VehicleService vehicleService = ServiceFactory.getInstance().getVehicleService();
        int idCar = Integer.parseInt(request.getParameter("delCar"));
        logger.info("Prepare to delete car id: " + idCar);
        try {
            vehicleService.deleteVehicle(idCar);
        }catch (ServiceException e){
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return page;
    }
}
