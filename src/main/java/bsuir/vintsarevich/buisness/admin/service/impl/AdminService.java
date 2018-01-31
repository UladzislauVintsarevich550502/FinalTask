package bsuir.vintsarevich.buisness.admin.service.impl;

import bsuir.vintsarevich.buisness.admin.dao.IAdminDao;
import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.entity.Admin;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.validation.ValidatorException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import bsuir.vintsarevich.utils.Hasher;
import bsuir.vintsarevich.utils.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

public class AdminService implements IAdminService {

    private static final Logger LOGGER = Logger.getLogger(AdminService.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public void updateLocale(String AdminLogin, String locale) throws ServiceException {

    }

    @Override
    public boolean addAdmin(String adminLogin, String adminPassword) {
        LOGGER.log(Level.DEBUG, "Admin service: start addAdmin");
        IAdminDao adminDao = daoFactory.getAdminDao();
        Admin admin;
        try {
            Validator.isEmptyString(adminLogin, adminPassword);
            Validator.isNull(adminLogin, adminPassword);
            adminPassword = Hasher.hashBySha1(adminPassword);
            admin = new Admin(adminLogin, adminPassword);
            if (adminDao.addAdmin(admin)) {
                return true;
            }
        } catch (ValidatorException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        } catch (DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Admin service: finish addAdmin");
        return false;

    }

    @Override
    public boolean deleteAdmin(Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product DAO: Delete admin start");
        IAdminDao adminDao = daoFactory.getAdminDao();
        try {
            adminDao.deleteAdmin(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "ProductService: finish admin client");
        return true;
    }

    @Override
    public Admin getAdminByLogin(String AdminLogin) throws ServiceException {
        return null;
    }

    @Override
    public Admin signIn(String adminLogin, String adminPassword) {
        LOGGER.log(Level.DEBUG, "Admin service: start SignIn");
        Admin admin = null;
        IAdminDao adminDao = daoFactory.getAdminDao();
        try {
            Validator.isEmptyString(adminLogin, adminPassword);
            Validator.isNull(adminLogin, adminPassword);
            adminPassword = Hasher.hashBySha1(adminPassword);
            admin = adminDao.signIn(adminLogin, adminPassword);
        } catch (DaoException | ValidatorException e) {
            return null;
        }
        LOGGER.log(Level.DEBUG, "Admin service: finish SignIn");
        return admin;
    }

    @Override
    public List<Admin> getAllAdmins() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product Service: Start get all admins");
        List<Admin> admins;
        try {
            IAdminDao adminDao = daoFactory.getAdminDao();
            admins = adminDao.getAllAdmins();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Product Service: Finish get all admins");
        return admins;
    }
}