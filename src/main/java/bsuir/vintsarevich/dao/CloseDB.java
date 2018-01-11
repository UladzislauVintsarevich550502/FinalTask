package bsuir.vintsarevich.dao;

import bsuir.vintsarevich.dao.pool.ICloseConnectionPool;
import bsuir.vintsarevich.dao.pool.impl.ConnectionPool;
import bsuir.vintsarevich.exception.dao.ConnectionException;
import bsuir.vintsarevich.exception.service.ServiceException;
import org.apache.log4j.Logger;


public class CloseDB implements ICloseDB {
    private static Logger logger = Logger.getLogger(CloseDB.class);

    @Override
    public void closeConnections() throws ServiceException {
        logger.debug("Service.closeConnection()");

        try {
            ICloseConnectionPool pool = ConnectionPool.getInstance();
            pool.releasePool();
        }catch (ConnectionException e){
            throw new ServiceException(e);
        }

        logger.debug("Service.closeConnection() - success");
    }
}
