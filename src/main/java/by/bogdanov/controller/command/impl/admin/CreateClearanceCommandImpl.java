package by.bogdanov.controller.command.impl.admin;

import by.bogdanov.controller.command.Command;
import by.bogdanov.dao.mysql.ClearanceDaoImpl;
import by.bogdanov.entity.Clearance;
import by.bogdanov.service.ClearanceService;
import by.bogdanov.service.OperationService;
import by.bogdanov.service.ServiceException;
import by.bogdanov.service.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class CreateClearanceCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(CreateClearanceCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("path");
        ClearanceService clearanceService = ServiceFactory.getInstance().getClearanceService();
        OperationService operationService = ServiceFactory.getInstance().getOperationService();
        Clearance clearance = new Clearance();


        String s = request.getParameter("operation");
        String delimiter = s.substring(4,s.indexOf('|'));

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        try{
            clearance.setOperation(operationService.readOperationById((Integer.parseInt(delimiter))));
            clearance.setOperation_id(Integer.parseInt(delimiter));
            clearance.setName(request.getParameter("name"));

            if(clearance.getName().isEmpty()){
                request.setAttribute("nullEnter","Enter name of the clearance");
                return "ClearanceCreatePage.jsp";
            }
            clearance.setDiscount(Integer.parseInt(request.getParameter("discount")));
            clearance.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));
            clearance.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));

            if(clearance.getEndDate().before(clearance.getStartDate())){
                request.setAttribute("IncorrectDate","Incorrect End Date");
            return "ClearanceCreatePage.jsp";
            }
            clearanceService.createClearance(clearance);
            operationService.createClearanceOperation(ClearanceDaoImpl.id, Integer.parseInt(delimiter));

        } catch (ServiceException | ParseException e){
            request.setAttribute("IncorrectDate","Choose date");
            logger.debug(e.getMessage());
            return "ClearanceCreatePage.jsp";

        } catch (NumberFormatException e){
            request.setAttribute("wrongDiscount","Enter must be a digit");
            return "ClearanceCreatePage.jsp";
        }
        return page;
    }
}
