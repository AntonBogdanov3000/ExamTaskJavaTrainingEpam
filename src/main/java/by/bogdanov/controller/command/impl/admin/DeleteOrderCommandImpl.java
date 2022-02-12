package by.bogdanov.controller.command.impl.admin;

import by.bogdanov.controller.command.Command;
import by.bogdanov.entity.Order;
import by.bogdanov.service.OrderService;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class DeleteOrderCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(DeleteOrderCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("path");
        HttpSession session = request.getSession();
        Order order;
        int orderId = Integer.parseInt(request.getParameter("order"));
        logger.info("Prepare to delete order id: " + orderId);
        if(!session.isNew()){
            try{
                OrderService orderService = ServiceFactory.getInstance().getOrderService();
                order = orderService.readOrderById(orderId);
                orderService.deleteOrder(order);
            }catch (ServiceException e){
                logger.debug(e.getMessage());
                e.printStackTrace();
            }
        }
        return page;
    }
}
