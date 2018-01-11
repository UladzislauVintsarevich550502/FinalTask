package com.epam.service;

import com.epam.dao.IMedicamentDao;
import com.epam.dao.exception.DaoException;
import com.epam.dao.factory.DaoFactory;
import com.epam.entity.Medicament;
import com.epam.service.exception.ServiceException;
import com.epam.service.exception.ServiceLogicException;
import com.epam.service.utils.Validator;
import com.epam.service.utils.exception.ValidatorException;
import org.apache.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MedicamentService implements IMedicamentService {

    private static final int BUFFER_LENGTH = 1024;
    private static final int RECIPE_NEED = 1;
    private static final int RECIPE_NOT_NEED = 0;
    private static Logger logger = Logger.getLogger(MedicamentService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public List<Medicament> getAllMedicaments() throws ServiceException {
        List<Medicament> medicaments = null;
        try {
            logger.debug("MedicamentService.getAllMedicaments()");
            IMedicamentDao iMedicamentDao = daoFactory.getIMedicamentDao();
            medicaments = iMedicamentDao.getAllMedicaments();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        logger.debug("MedicamentService.getAllMedicaments() - success");
        return medicaments;
    }

    @Override
    public List<Medicament> getMedicamentsWithPrescription() throws ServiceException {
        List<Medicament> medicaments = null;
        try {
            logger.debug("MedicamentService.getAllMedicamentsWithPrescription()");
            IMedicamentDao iMedicamentDao = daoFactory.getIMedicamentDao();
            medicaments = iMedicamentDao.getMedicamentsByPrescription(RECIPE_NEED);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        logger.debug("MedicamentService.getAllMedicamentsWithPrescription() - success");
        return medicaments;
    }


    @Override
    public List<Medicament> getAscSortedByPriceMedicaments() throws ServiceException {
        logger.debug("MedicamentService.getAscSortedByPriceMedicaments()");
        List<Medicament> medicaments = null;
        IMedicamentDao iMedicamentDao = daoFactory.getIMedicamentDao();
        try {
            medicaments = iMedicamentDao.getAscSortedByPriceMedicaments();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        logger.debug("MedicamentService.getAscSortedByPriceMedicaments() - success.");
        return medicaments;
    }

    @Override
    public List<Medicament> getDescSortedByPriceMedicaments() throws ServiceException {
        logger.debug("MedicamentService.getDescSortedByPriceMedicaments()");
        List<Medicament> medicaments = null;
        IMedicamentDao iMedicamentDao = daoFactory.getIMedicamentDao();
        try {
            medicaments = iMedicamentDao.getDescSortedByPriceMedicaments();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        logger.debug("MedicamentService.getDescSortedByPriceMedicaments() - success.");
        return medicaments;
    }

    @Override
    public Medicament getMedicamentById(String id) throws ServiceException {
        logger.debug("MedicamentService.getMedicamentById()");
        Medicament medicament = null;
        IMedicamentDao iMedicamentDao = daoFactory.getIMedicamentDao();
        try {
            Validator.isNull(id);
            Validator.isEmptyString(id);
            int idMedicament = Integer.parseInt(id);
            medicament = iMedicamentDao.getMedicamentById(idMedicament);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (NumberFormatException e) {
            throw new ServiceException("number format exception" + e);
        } catch (ValidatorException e) {
            throw new ServiceException(e);
        }
        logger.debug("MedicamentService.getMedicamentById() - success.");
        return medicament;
    }

    @Override
    public List<Medicament> getMedicamentsByProducer(String producer) throws ServiceException {
        logger.debug("MedicamentService.getMedicamentsByProducer()");
        List<Medicament> medicaments = null;
        IMedicamentDao iMedicamentDao = daoFactory.getIMedicamentDao();
        try {
            Validator.isNull(producer);
            Validator.isEmptyString(producer);
            Validator.matchProperName(producer);
            medicaments = iMedicamentDao.getMedicamentsByProducer(producer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (ValidatorException e) {
            throw new ServiceException(e);
        }
        logger.debug("MedicamentService.getMedicamentsByProducer() - success.");
        return medicaments;
    }

    @Override
    public void addMedicament(String name, String producer,
                              String price, String prescription,
                              Part part, String webInfPath, String availability) throws ServiceException, ServiceLogicException {

        logger.debug("MedicamentService.addMedicament");
        Medicament medicament = new Medicament();
        IMedicamentDao medicamentDao = daoFactory.getIMedicamentDao();
        try {
            Validator.isNull(name, producer, price, prescription, webInfPath, availability);
            Validator.isEmptyString(name, producer, price, prescription, webInfPath, availability);
            Validator.matchProperName(producer);
            medicament.setName(name);
            medicament.setProducer(producer);
            medicament.setPrice(Float.parseFloat(price));
            medicament.setPrescription(Boolean.parseBoolean(prescription));
            medicament.setAvailability(Boolean.parseBoolean(availability));
            String imageName = getImageName(part);
            if (!imageName.isEmpty()) {
                medicament.setImage(imageName);
            }
            medicamentDao.addMedicament(medicament);
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            if (!fileName.isEmpty()) {
                uploadImage(part, fileName, webInfPath);
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
        }
        logger.debug("MedicamentService.addMedicament - success");
    }

    @Override
    public List<Medicament> getMedicamentByName(String name) throws ServiceException {
        logger.debug("MedicamentService.getMedicamentByName()");
        List<Medicament> medicaments = null;
        IMedicamentDao iMedicamentDao = daoFactory.getIMedicamentDao();
        try {
            Validator.isNull(name);
            Validator.isEmptyString(name);
            Validator.matchProperName(name);
            medicaments = iMedicamentDao.getMedicamentsByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (ValidatorException e) {
            throw new ServiceException(e);
        }
        logger.debug("MedicamentService.getMedicamentByName() - success.");
        return medicaments;
    }

    @Override
    public void editMedicament(String idMedicament, String name, String producer,
                               String price, String prescription,
                               Part part, String webInfPath,
                               String availability) throws ServiceException, ServiceLogicException {
        logger.debug("MedicamentService.editMedicament");
        Medicament medicament = new Medicament();
        IMedicamentDao medicamentDao = daoFactory.getIMedicamentDao();
        try {
            Validator.isNull(idMedicament, name, producer, price, prescription, webInfPath, availability);
            Validator.isEmptyString(idMedicament, name, producer, price, prescription, webInfPath, availability);
            Validator.matchProperName(producer);
            medicament.setId(Integer.parseInt(idMedicament));
            medicament.setName(name);
            medicament.setProducer(producer);
            medicament.setPrice(Float.parseFloat(price));
            medicament.setPrescription(Boolean.parseBoolean(prescription));
            medicament.setAvailability(Boolean.parseBoolean(availability));
            String imageName = getImageName(part);
            if (!imageName.isEmpty()) {
                medicament.setImage(imageName);
            }
            medicamentDao.editMedicament(medicament);
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            if (!fileName.isEmpty()) {
                uploadImage(part, fileName, webInfPath);
            }
        } catch (ValidatorException e) {
            throw new ServiceException(e);
        } catch (NumberFormatException e) {
            throw new ServiceException("number format exception", e);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        logger.debug("MedicamentService.editMedicament - success");
    }

    private void uploadImage(Part filePart, String fileName, String webInfPath) throws ServiceException, ServiceLogicException {
        try {
            logger.debug("MedicamentService.uploadImage()");
            File dir = new File(webInfPath + "images" + File.separator + "medicaments");
            if (!dir.exists()) {
                Path path = Paths.get(webInfPath + "images" + File.separator + "medicaments");
                Files.createDirectories(path);
            }
            File file = new File(dir, fileName);
            InputStream fileContent = filePart.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[BUFFER_LENGTH];
            int len = fileContent.read(buffer);
            while (len != -1) {
                fileOutputStream.write(buffer, 0, len);
                len = fileContent.read(buffer);
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    System.out.println("error with closing file" + e);
                }
            }
        } catch (IOException e) {
            throw new ServiceLogicException("error with upload of image", e);
        }
        logger.debug("MedicamentService.uploadImage() - success");
    }

    private String getImageName(Part filePart) {
        logger.debug("MedicamentService.getImageName()" + filePart);
        String name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        logger.debug("MedicamentService.getImageName() - success");
        return name;
    }
}
