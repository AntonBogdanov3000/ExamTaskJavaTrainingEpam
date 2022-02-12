package by.bogdanov.controller;

import by.bogdanov.controller.command.Command;
import by.bogdanov.controller.command.CommandProvider;
import by.bogdanov.service.ServiceFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ControllerServlet extends HttpServlet {

 private final Logger logger = LogManager.getLogger(ControllerServlet.class);

    public void init() {
        ServiceFactory.getInstance().getConnectionService().startConnection();
        logger.info("Servlet start");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("name", request.getParameter("name"));
        processRequest(request, response);

    }

    public CommandProvider provider = new CommandProvider();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jspPage;
        String commandName;

        HttpSession session = request.getSession();

        if (request.getParameter("command").isEmpty()) {
            String s = "/WEB-INF/jsp/" + request.getParameter("path");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(s);
            requestDispatcher.forward(request, response);
        } else {
            commandName = request.getParameter("command");
            logger.info("Use a command " + commandName);
            Command command = provider.getCommand(commandName);
            String result = command.execute(request);
            jspPage = "/WEB-INF/jsp/" + result;
            if (result == null) {
                response.getWriter().write("Error");
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jspPage);
                dispatcher.forward(request, response);
            }
        }
    }
}

