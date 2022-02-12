package by.bogdanov.controller.command.impl.user;

import by.bogdanov.controller.command.Command;
import by.bogdanov.entity.User;
import by.bogdanov.entity.Vehicle;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import by.bogdanov.service.UserService;
import by.bogdanov.service.VehicleService;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class AddCarCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(AddCarCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        Vehicle vehicle = new Vehicle();
        User user;
        VehicleService vehicleService = ServiceFactory.getInstance().getVehicleService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String page = request.getParameter("path");
        try{
        String model = request.getParameter("model");
        String plate = request.getParameter("plate");
        if(model.isEmpty() || plate.isEmpty()){
            throw new ServiceException();
        }
        int year = Integer.parseInt(request.getParameter("year"));
        int mileage = Integer.parseInt(request.getParameter("mileage"));

            user = userService.readUserByLogin(request.getParameter("login"));
            vehicle.setOwnerId(user.getId());
            vehicle.setModel(model);
            vehicle.setPlate(plate);
            vehicle.setYear(year);
            vehicle.setMileage(mileage);
            vehicleService.createVehicle(vehicle);
            logger.info("Car " + vehicle.getPlate() + " was added to garage user : " + user.getLogin());
        }catch (ServiceException e){
            logger.debug(e.getMessage());
            request.setAttribute("nullData", "Null enter");
            return "AddCarPage.jsp";

        }catch (NumberFormatException e){
            request.setAttribute("unCorrectYear","Must be a number");
            return "AddCarPage.jsp";
        }
        return page;
    }
}
