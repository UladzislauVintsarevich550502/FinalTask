package bsuir.vintsarevich.command;


import bsuir.vintsarevich.command.impl.*;
import bsuir.vintsarevich.enumeration.CommandName;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final static CommandProvider instance = new CommandProvider();
    private static final Logger LOGGER = Logger.getLogger(CommandProvider.class);
    private Map<CommandName, ICommand> repository = new HashMap<>();

    private CommandProvider() {
        repository.put(CommandName.INDEX, new Index());
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.SIGN_UP, new SignUp());
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
        repository.put(CommandName.ADD_PRODUCT, new AddProduct());
        repository.put(CommandName.BASKET, new Basket());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand iCommand = repository.get(CommandName.WRONG_REQUEST);
        String command = request.getRequestURI().replace("/", "");
        command = command.replace(".do", "");
        try {
            CommandName commandName = CommandName.valueOf(command.toUpperCase().replace('-', '_'));
            LOGGER.log(Level.DEBUG, "Command name:" + commandName.toString());
            iCommand = repository.get(commandName);
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.DEBUG, "No such support command name");
            request.setAttribute("wrongAction", e.getMessage());
        }
        return iCommand;
    }
}
