package bsuir.vintsarevich.dao;

import bsuir.vintsarevich.exception.service.ServiceException;

public interface ICloseDB {
    void closeConnections() throws ServiceException;
}
