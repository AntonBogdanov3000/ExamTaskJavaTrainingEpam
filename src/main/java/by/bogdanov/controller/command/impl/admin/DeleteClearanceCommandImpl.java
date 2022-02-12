package by.bogdanov.controller.command.impl.admin;

import by.bogdanov.controller.command.Command;
import by.bogdanov.service.ClearanceService;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class DeleteClearanceCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(DeleteCarCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("path");
        String clearanceId = request.getParameter("delClear");
        logger.info("Prepare to delete clearance id: " + clearanceId);
        ClearanceService clearanceService = ServiceFactory.getInstance().getClearanceService();
        try{
            clearanceService.deleteClearance(Integer.parseInt(clearanceId));
        }catch (ServiceException e){
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return page;
    }
}
