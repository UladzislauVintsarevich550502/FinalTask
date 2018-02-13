package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.database.ICloseDB;
import bsuir.vintsarevich.buisness.database.impl.CloseDB;
import bsuir.vintsarevich.command.ICloseDBCommand;
import bsuir.vintsarevich.exception.service.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * class CloseDBCommand created to close connection with database
 */
public class CloseDBCommand implements ICloseDBCommand {
    private static final Logger LOGGER = Logger.getLogger(CloseDBCommand.class);


    @Override
    public void closeDB() {
        try {
            ICloseDB closeDB = new CloseDB();
            closeDB.closeConnections();
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, "Problem with closing Data Base:" + e.getMessage());
        }
    }
}
