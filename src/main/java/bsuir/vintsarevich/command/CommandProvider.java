package bsuir.vintsarevich.command;


import bsuir.vintsarevich.command.impl.SignIn;
import bsuir.vintsarevich.command.impl.SignUp;
import bsuir.vintsarevich.enums.CommandName;
import bsuir.vintsarevich.enums.RequestEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private static Logger logger = Logger.getLogger(CommandProvider.class);

    private final static CommandProvider instance = new CommandProvider();
    private Map<CommandName, ICommand> repository = new HashMap<>();

    private CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.SIGN_UP, new SignUp());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand iCommand = repository.get(CommandName.WRONG_REQUEST);
        String command = request.getRequestURI().replace("/","");
        command = command.replace(".do","");
        try {
            CommandName commandName = CommandName.valueOf(command.toUpperCase().replace('-', '_'));
            iCommand = repository.get(commandName);
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", e.getMessage());
            logger.error(((String) request.getSession().getAttribute(RequestEnum.SIGN_IN.getValue())), e);
        }
        return iCommand;
    }
}
