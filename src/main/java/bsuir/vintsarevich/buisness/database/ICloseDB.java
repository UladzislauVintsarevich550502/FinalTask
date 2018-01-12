package bsuir.vintsarevich.buisness.database;

import bsuir.vintsarevich.exception.service.ServiceException;

public interface ICloseDB {

    void closeConnections() throws ServiceException;
}
