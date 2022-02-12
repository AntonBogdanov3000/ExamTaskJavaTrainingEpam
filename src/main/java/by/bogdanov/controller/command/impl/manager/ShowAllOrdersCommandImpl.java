package by.bogdanov.controller.command.impl.manager;

import by.bogdanov.controller.command.Command;
import by.bogdanov.entity.Order;
import by.bogdanov.service.OrderService;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class ShowAllOrdersCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(ShowAllOrdersCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("path");
        List<Order> orderList;
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        int idManager = (int) request.getSession().getAttribute("idManager");
        try{
            if(idManager == 0) {
                orderList = orderService.readAllOrders();
            } else{
                logger.info("Manager + " + idManager + "requested his order list");
                orderList = orderService.readOrdersByManagerId(idManager);
            }
            request.setAttribute("orderList", orderList);
        } catch (ServiceException e){
            logger.debug(e.getMessage());
        }
        return page;
    }
}
