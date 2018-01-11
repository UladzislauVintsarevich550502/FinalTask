package com.epam.service;

import com.epam.dao.IPrescriptionDao;
import com.epam.dao.exception.DaoException;
import com.epam.dao.factory.DaoFactory;
import com.epam.entity.Prescription;
import com.epam.service.exception.ServiceException;
import com.epam.service.exception.ServiceLogicException;
import com.epam.service.utils.Validator;
import com.epam.service.utils.exception.ValidatorException;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class PrescriptionService implements IPrescriptionService {

    private static Logger logger = Logger.getLogger(PrescriptionService.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Prescription getPrescriptionById(String id) throws ServiceException {
        logger.debug("PrescriptionService.getPrescriptionById()");
        Prescription prescription = null;
        IPrescriptionDao prescriptionDao = daoFactory.getIPrescriptionDao();
        try {
            Validator.isNull(id);
            Validator.isEmptyString(id);
            int idPrescription = Integer.parseInt(id);
            prescription = prescriptionDao.getPrescriptionById(idPrescription);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (NumberFormatException e) {
            throw new ServiceException("number format exception" + e);
        } catch (ValidatorException e) {
            throw new ServiceException(e);
        }
        logger.debug("PrescriptionService.getPrescriptionById() - success.");
        return prescription;
    }

    @Override
    public List<Prescription> getPrescriptionsByUserId(String idUser) throws ServiceException {
        return null;
    }

    @Override
    public void addPrescription(String idDoctor, String idUser,
                                String idMedicament, String dateOfCompletion) throws ServiceException, ServiceLogicException {
        logger.debug("PrescriptionService.addPrescription");
        Prescription prescription = new Prescription();
        IPrescriptionDao prescriptionDao = daoFactory.getIPrescriptionDao();
        try {
            Validator.isNull(idDoctor, idUser, idMedicament, dateOfCompletion);
            Validator.isEmptyString(idDoctor, idUser, idMedicament, dateOfCompletion);
            Validator.matchDate(dateOfCompletion);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateOfIssue = new java.util.Date();
            java.util.Date dateOfCompl = dateFormat.parse(dateOfCompletion);

            if (dateOfCompl.after(dateOfIssue)) {

                prescription.setDoctorId(Integer.parseInt(idDoctor));
                prescription.setUserId(Integer.parseInt(idUser));
                prescription.setIdMedicament(Integer.parseInt(idMedicament));
                prescription.setDateOfIssue(Date.valueOf(dateFormat.format(dateOfIssue).toString()));
                prescription.setDateOfCompletion(Date.valueOf(dateFormat.format(dateOfCompl)));

                prescriptionDao.addPrescription(prescription);

            } else {
                throw new ServiceLogicException();
            }


        } catch (ValidatorException e) {
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        } catch (NumberFormatException e) {
            logger.debug(e.getMessage());
            throw new ServiceException("number format exception", e);
        } catch (DaoException e) {
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            throw new ServiceException(e);
        }
        logger.debug("MedicamentService.addMedicament - success");
    }
}
