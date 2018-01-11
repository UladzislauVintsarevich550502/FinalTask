package com.epam.command.impl;

import com.epam.command.ICloseDBCommand;
import com.epam.service.CloseDBServiceImpl;
import com.epam.service.ICloseDB;
import com.epam.service.exception.ServiceException;
import org.apache.log4j.Logger;

public class CloseDBCommand implements ICloseDBCommand {
    private static Logger logger = Logger.getLogger(CloseDBCommand.class);

    @Override
    public void closeDB() {
        try {
            ICloseDB closeDB = new CloseDBServiceImpl();
            closeDB.closeConnections();
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}
