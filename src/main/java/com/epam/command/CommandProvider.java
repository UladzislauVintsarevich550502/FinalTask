package com.epam.command;


import com.epam.command.impl.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final static CommandProvider instance = new CommandProvider();
    private static Logger logger = Logger.getLogger(CommandProvider.class);
    private Map<CommandName, ICommand> repository = new HashMap<>();

    private CommandProvider() {
        repository.put(CommandName.INDEX, new Index());
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.SIGN_UP, new SignUp());
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.MEDICAMENT, new GetMedicamentById());
        repository.put(CommandName.MEDICAMENTS_BY_PRODUCER, new GetMedicamentsByProducer());
        repository.put(CommandName.MEDICAMENTS_BY_NAME, new GetMedicamentsByName());
        repository.put(CommandName.MEDICAMENTS_ASC_SORTED_BY_PRICE, new GetAscSortedByPriceMedicaments());
        repository.put(CommandName.MEDICAMENTS_DESC_SORTED_BY_PRICE, new GetDescSortedByPriceMedicaments());
        repository.put(CommandName.NEW_MEDICAMENT, new NewMedicament());
        repository.put(CommandName.ADD_MEDICAMENT, new AddMedicament());
        repository.put(CommandName.EDIT_MEDICAMENT, new EditMedicament());
        repository.put(CommandName.NEW_PRESCRIPTION, new NewPrescription());
        repository.put(CommandName.ADD_PRESCRIPTION, new AddPrescription());
        repository.put(CommandName.ADD_TO_BASKET, new AddToCart());
        repository.put(CommandName.CHECKOUT, new Checkout());
//        repository.put(CommandName.BASKET, new GetMedicamentsInBasket());
        repository.put(CommandName.ADD_USER, new AddUser());
        repository.put(CommandName.GET_USERS, new GetUsers());
        repository.put(CommandName.GET_MEDICAMENTS, new GetMedicaments());


//        repository.put(CommandName.GET_ALL_PHARMACISTS, new GetAllPharmacists());
//        repository.put(CommandName.GET_ALL_DOCTORS, new GetAllDoctors());
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
            iCommand = repository.get(commandName);
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", e.getMessage());
        }
        return iCommand;
    }
}
