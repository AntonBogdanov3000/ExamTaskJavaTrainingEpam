package by.bogdanov.controller.command.impl;

import by.bogdanov.controller.command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class ChangeLangCommandImpl implements Command {

    private final Logger logger = LogManager.getLogger(ChangeLangCommandImpl.class);
    Locale locale;

    @Override
    public String execute(HttpServletRequest request) {

        String page = request.getParameter("path");
        HttpSession session = request.getSession();

        if(request.getParameter("language").equals("russian")){
            locale = new Locale("ru", "RU");
        }else if(request.getParameter("language").equals("english")) {
            locale = new Locale("en", "EN");
        }else {
            locale = new Locale("de", "DE");
        }
        if(!session.isNew()) {
            session.setAttribute("locale", locale);
        }
        logger.info("User choose " + locale.getLanguage() + " language");
        return page;
    }
}
