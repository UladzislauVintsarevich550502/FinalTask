package bsuir.vintsarevich.buisness.database.impl;

import bsuir.vintsarevich.buisness.database.ICloseDB;
import bsuir.vintsarevich.connectionpool.ConnectionPool;
import bsuir.vintsarevich.connectionpool.ICloseConnectionPool;
import bsuir.vintsarevich.exception.dao.ConnectionException;
import bsuir.vintsarevich.exception.service.ServiceException;
import org.apache.log4j.Logger;

public class CloseDB implements ICloseDB {
    private static Logger logger = Logger.getLogger(CloseDB.class);

    public void closeConnections() throws ServiceException {
        logger.debug("Service.closeConnection()");

        try {
            ICloseConnectionPool pool = ConnectionPool.getInstance();
            pool.releasePool();
        } catch (ConnectionException e) {
            throw new ServiceException(e);
        }

        logger.debug("Service.closeConnection() - success");
    }
}
