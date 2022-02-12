package by.bogdanov.controller.command.impl;

import by.bogdanov.controller.command.Command;
import by.bogdanov.entity.Operation;
import by.bogdanov.service.OperationService;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class ShowPricesCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(ShowPricesCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("path");
        List<Operation> operationList;
        OperationService operationService = ServiceFactory.getInstance().getOperationService();
        try{
            operationList = operationService.readAllOperations();
            request.getSession().setAttribute("operationList",operationList);
            request.setAttribute("manager", request.getParameter("idManager"));
        }catch (ServiceException e){
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return page;
    }
}
