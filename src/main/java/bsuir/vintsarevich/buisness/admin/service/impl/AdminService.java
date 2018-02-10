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
    public boolean signUp(String adminLogin, String adminPassword) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Admin service: start signUp");
        Admin admin;
        try {
            Validator.isEmptyString(adminLogin, adminPassword);
            Validator.isNull(adminLogin, adminPassword);
            adminPassword = Hasher.hashBySha1(adminPassword);
            if (daoFactory.getClientDao().getClientByLogin(adminLogin) == null) {
                admin = new Admin(adminLogin, adminPassword);
                return (daoFactory.getAdminDao().addAdmin(admin));
            }
        } catch (ValidatorException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Admin service: finish signUp");
        return false;

    }

    @Override
    public boolean deleteAdmin(Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product DAO: Delete admin start");
        try {
            LOGGER.log(Level.DEBUG, "ProductService: finish delete admin");
            return daoFactory.getAdminDao().deleteAdmin(id);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public Admin signIn(String adminLogin, String adminPassword) {
        LOGGER.log(Level.DEBUG, "Admin service: start SignIn");
        try {
            Validator.isEmptyString(adminLogin, adminPassword);
            Validator.isNull(adminLogin, adminPassword);
            adminPassword = Hasher.hashBySha1(adminPassword);
            LOGGER.log(Level.DEBUG, "Admin service: finish SignIn");
            return daoFactory.getAdminDao().signIn(adminLogin, adminPassword);
        } catch (DaoException | ValidatorException e) {
            return null;
        }
    }

    @Override
    public List<Admin> getAllAdmins() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product Service: Start get all admins");
        try {
            LOGGER.log(Level.DEBUG, "Product Service: Finish get all admins");
            IAdminDao adminDao = daoFactory.getAdminDao();
            return adminDao.getAllAdmins();
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public boolean checkPassword(String password, Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Admin Service: check password start");
        password = Hasher.hashBySha1(password);
        try {
            LOGGER.log(Level.DEBUG, "Admin Service: finish check password");
            return daoFactory.getAdminDao().checkPassword(password, id);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }

    @Override
    public boolean changePassword(String password, Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Admin Service: change password start");
        password = Hasher.hashBySha1(password);
        try {
            LOGGER.log(Level.DEBUG, "Admin Service: finish change password");
            return daoFactory.getAdminDao().changePassword(password, id);
        } catch (DaoException e) {
            throw new ServiceException(this.getClass() + ":" + e.getMessage());
        }
    }
}
