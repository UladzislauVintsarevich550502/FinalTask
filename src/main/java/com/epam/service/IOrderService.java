package com.epam.service;

import com.epam.service.exception.ServiceException;
import com.epam.service.exception.ServiceLogicException;

public interface IOrderService {
    void addToBasket(String idUser, String idMedicament,
                     String number, String dosage) throws ServiceException, ServiceLogicException;


    void checkout(String idUser, String idMedicament,
                  String number, String dosage) throws ServiceException, ServiceLogicException;

//    List<Medicament> getMedicamentsInBasket(String idUser) throws ServiceException,ServiceLogicException;
}
