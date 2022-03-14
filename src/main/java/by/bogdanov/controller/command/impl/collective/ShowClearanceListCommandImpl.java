package by.bogdanov.controller.command.impl.collective;

import by.bogdanov.controller.command.Command;
import by.bogdanov.entity.Clearance;
import by.bogdanov.service.ClearanceService;
import by.bogdanov.service.OperationService;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class ShowClearanceListCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(ShowClearanceListCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("path");
        List<Clearance> clearanceList;
        ClearanceService clearanceService = ServiceFactory.getInstance().getClearanceService();
        OperationService operationService = ServiceFactory.getInstance().getOperationService();
        try {
            clearanceList = clearanceService.getAllClearance();

            for(Clearance clearance : clearanceList){
                clearance.setOperation(operationService.readOperationById(clearance.getOperation_id()));
            }

            request.setAttribute("clearanceList", clearanceList);
        }catch (ServiceException e){
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return page;
    }
}
