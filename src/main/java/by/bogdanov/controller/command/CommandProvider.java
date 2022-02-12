package by.bogdanov.controller.command;

import by.bogdanov.controller.command.impl.*;
import by.bogdanov.controller.command.impl.admin.*;
import by.bogdanov.controller.command.impl.manager.CreateOrderCommandImpl;
import by.bogdanov.controller.command.impl.manager.GetClientCarsCommandImpl;
import by.bogdanov.controller.command.impl.manager.ShowAllOrdersCommandImpl;
import by.bogdanov.controller.command.impl.manager.UpdateOrderCommandImpl;
import by.bogdanov.controller.command.impl.user.AddCarCommandImpl;
import by.bogdanov.controller.command.impl.user.ReadUserCasCommandImpl;
import by.bogdanov.controller.command.impl.user.ReadUserOrdersCommand;
import by.bogdanov.controller.command.impl.user.RegistrationCommandImpl;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class CommandProvider {

    private final Logger logger = LogManager.getLogger(CommandProvider.class);
    private final Map<CommandName,Command> listCommand = new HashMap<>();


    public CommandProvider(){
        listCommand.put(CommandName.REGISTRATION, new RegistrationCommandImpl());
        listCommand.put(CommandName.LOGIN, new LoginCommandImpl());
        listCommand.put(CommandName.READ_USER_CAR, new ReadUserCasCommandImpl());
        listCommand.put(CommandName.READ_USER_ORDER, new ReadUserOrdersCommand());
        listCommand.put(CommandName.ADD_CAR, new AddCarCommandImpl());
        listCommand.put(CommandName.LOGOUT, new LogoutCommandImpl());
        listCommand.put(CommandName.SHOW_PRICES, new ShowPricesCommandImpl());
        listCommand.put(CommandName.SHOW_ORDERS, new ShowAllOrdersCommandImpl());
        listCommand.put(CommandName.CREATE_ORDER, new CreateOrderCommandImpl());
        listCommand.put(CommandName.UPDATE_ORDER, new UpdateOrderCommandImpl());
        listCommand.put(CommandName.SHOW_CLIENTS, new ShowClientsCommandImpl());
        listCommand.put(CommandName.SHOW_ALL_CLEARANCE, new ShowClearanceListCommandImpl());
        listCommand.put(CommandName.SHOW_ALL_CARS, new ShowAllCarsCommandImpl());
        listCommand.put(CommandName.DELETE_USER, new DeleteUserCommandImpl());
        listCommand.put(CommandName.UPDATE_USER, new UpdateUserCommandImpl());
        listCommand.put(CommandName.CHANGE_LANG, new ChangeLangCommandImpl());
        listCommand.put(CommandName.DELETE_ORDER, new DeleteOrderCommandImpl());
        listCommand.put(CommandName.CREATE_CLEARANCE, new CreateClearanceCommandImpl());
        listCommand.put(CommandName.DELETE_CLEARANCE, new DeleteClearanceCommandImpl());
        listCommand.put(CommandName.DELETE_CAR, new DeleteCarCommandImpl());
        listCommand.put(CommandName.GET_CLIENT_CARS, new GetClientCarsCommandImpl());
    }
    public Command getCommand(String text){
        logger.info("Command " + text);
        return listCommand.get(CommandName.valueOf(text));
    }
}
