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

    private static Logger logger = Logger.getLogger(AdminService.class);

    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public void updateLocale(String AdminLogin, String locale) throws ServiceException {

    }

    @Override
    public Admin getAdminByLogin(String AdminLogin) throws ServiceException {
        return null;
    }

    @Override
    public Admin signIn(String adminLogin, String adminPassword) {
        logger.log(Level.DEBUG, "AdminService.signIn()");
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
        logger.debug("adminService.signIn() - success. ");
        return admin;
    }

    @Override
    public List<Admin> getAllAdmins() throws ServiceException {
        return null;
    }
}
