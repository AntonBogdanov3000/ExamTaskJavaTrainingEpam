package by.bogdanov.controller.command.impl.collective;

import by.bogdanov.controller.command.Command;
import javax.servlet.http.HttpServletRequest;


public class LogoutCommandImpl implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("path");
        if(!request.getSession().isNew()) {
            request.getSession().invalidate();
        }
        return page;
    }
}
