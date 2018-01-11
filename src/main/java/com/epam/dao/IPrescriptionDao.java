package com.epam.dao;

import com.epam.dao.exception.DaoException;
import com.epam.entity.Prescription;

import java.util.List;

public interface IPrescriptionDao {
    boolean addPrescription(Prescription prescription) throws DaoException;

    boolean deletePriscription();

    boolean createPrescription();

    Prescription getPrescriptionById(int id) throws DaoException;

    List<Prescription> getPrescriptionsByDoctorLogin(String doctorLogin);

    boolean changeStatus(Prescription prescription);

}
