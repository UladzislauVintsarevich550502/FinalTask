package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.database.ICloseDB;
import bsuir.vintsarevich.buisness.database.impl.CloseDB;
import bsuir.vintsarevich.command.ICloseDBCommand;
import bsuir.vintsarevich.exception.service.ServiceException;
import org.apache.log4j.Logger;

public class CloseDBCommand implements ICloseDBCommand {
    private static Logger logger = Logger.getLogger(CloseDBCommand.class);

    @Override
    public void closeDB() {
        try {
            ICloseDB closeDB = new CloseDB();
            closeDB.closeConnections();
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}
