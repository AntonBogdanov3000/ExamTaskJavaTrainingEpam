package by.bogdanov.controller.command.impl.manager;

import by.bogdanov.controller.command.Command;
import by.bogdanov.entity.Operation;
import by.bogdanov.entity.Order;
import by.bogdanov.service.OperationService;
import by.bogdanov.service.OrderService;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class UpdateOrderCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(UpdateOrderCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("path");
        Operation operation;
        Order order;
        int orderId;

        OperationService operationService = ServiceFactory.getInstance().getOperationService();
        OrderService orderService = ServiceFactory.getInstance().getOrderService();

        try{
              String s = request.getParameter("operation");
              String delimiter = s.substring(4,s.indexOf('|'));

              operation = operationService.readOperationById(Integer.parseInt(delimiter));
              order = orderService.readOrderById(Integer.parseInt(request.getParameter("order")));


            order.setPrice(order.getPrice() + operation.getOperationPrice());
            orderId = order.getId();
            logger.info("Prepare to update order id: " + orderId);
            orderService.updateOrder(order);
            operationService.createOrderOperation(orderId,operation.getId());
        }catch (ServiceException e){
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return page;
    }
}
