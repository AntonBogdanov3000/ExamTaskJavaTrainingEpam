package by.bogdanov.controller.command.impl.manager;

import by.bogdanov.controller.command.Command;
import by.bogdanov.dao.mysql.OrderDaoImpl;
import by.bogdanov.entity.Operation;
import by.bogdanov.entity.Order;
import by.bogdanov.entity.User;
import by.bogdanov.entity.Vehicle;
import by.bogdanov.service.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class CreateOrderCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(CreateOrderCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("path");

        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        VehicleService vehicleService = ServiceFactory.getInstance().getVehicleService();
        OperationService operationService = ServiceFactory.getInstance().getOperationService();

        Order order = new Order();
        Vehicle vehicle;
        User user;
        Operation operation;
        List<Operation> operationList = new ArrayList<>();

        try {
            int idManager = (int) request.getSession().getAttribute("idManager");
            user = userService.readUserByLogin(request.getParameter("login"));
            String car = request.getParameter("cars");
            String carDelimeter = car.substring(0,car.indexOf('|'));
            vehicle = vehicleService.readById(Integer.parseInt(carDelimeter));
            String s =  request.getParameter("operation");
            String delimiter = s.substring(4,s.indexOf('|'));
            operation = operationService.readOperationById(Integer.parseInt(delimiter));

            operationList.add(operation);

            order.setUserId(user.getId());
            order.setDate(new Date());
            order.setPrice(Integer.parseInt(request.getParameter("price")));
            order.setVehicleId(vehicle.getId());
            order.setVehicle(vehicle);
            order.setManagerId(idManager);
            order.setOperationList(operationList);

            orderService.createOrder(order);
            logger.info("Manager id: " + idManager + "has been created order " + OrderDaoImpl.id);
            operationService.createOrderOperation(OrderDaoImpl.id,operation.getId());
        }catch (ServiceException  e){
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return page;
    }
}
