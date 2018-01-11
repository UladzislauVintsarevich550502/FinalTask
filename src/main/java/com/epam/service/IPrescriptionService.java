package com.epam.service;

import com.epam.entity.Prescription;
import com.epam.service.exception.ServiceException;
import com.epam.service.exception.ServiceLogicException;

import java.util.List;

public interface IPrescriptionService {

    Prescription getPrescriptionById(String id) throws ServiceException;

    List<Prescription> getPrescriptionsByUserId(String idUser) throws ServiceException;

    void addPrescription(String idDoctor, String idUser,
                         String idMedicament, String dateOfCompletion) throws ServiceException, ServiceLogicException;
}
