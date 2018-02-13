package bsuir.vintsarevich.buisness.database.impl;

import bsuir.vintsarevich.buisness.database.ICloseDB;
import bsuir.vintsarevich.connectionpool.ConnectionPool;
import bsuir.vintsarevich.connectionpool.ICloseConnectionPool;
import bsuir.vintsarevich.exception.dao.ConnectionException;
import bsuir.vintsarevich.exception.service.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * class CloseDB created for closing connection with database
 */
public class CloseDB implements ICloseDB {
    private static Logger LOGGER = Logger.getLogger(CloseDB.class);

    /**
     * @throws ServiceException
     */
    public void closeConnections() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Service: Close Connection");

        try {
            ICloseConnectionPool pool = ConnectionPool.getInstance();
            pool.releasePool();
        } catch (ConnectionException e) {
            throw new ServiceException(e);
        }

        LOGGER.log(Level.DEBUG, "Service: Close Connection - success");
    }
}
