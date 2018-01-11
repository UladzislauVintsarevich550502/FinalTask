package com.epam.service;

import com.epam.service.exception.ServiceException;

public interface ICloseDB {

    void closeConnections() throws ServiceException;
}
