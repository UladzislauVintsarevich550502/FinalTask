package bsuir.vintsarevich.buisness.staff.service.impl;

import bsuir.vintsarevich.buisness.admin.dao.IAdminDao;
import bsuir.vintsarevich.buisness.client.dao.IClientDao;
import bsuir.vintsarevich.buisness.staff.dao.IStaffDao;
import bsuir.vintsarevich.buisness.staff.service.IStaffService;
import bsuir.vintsarevich.entity.Admin;
import bsuir.vintsarevich.entity.Staff;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.validation.ValidatorException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import bsuir.vintsarevich.utils.Hasher;
import bsuir.vintsarevich.utils.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

public class StaffService implements IStaffService {
    private static final Logger LOGGER = Logger.getLogger(StaffService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public boolean signUp(String staffLogin, String staffPassword) {
        LOGGER.log(Level.DEBUG, "Staff service: start addStaff");
        IStaffDao staffDao = daoFactory.getStaffDao();
        IAdminDao adminDao = daoFactory.getAdminDao();
        IClientDao clientDao = daoFactory.getClientDao();
        Staff staff;
        try {
            Validator.isEmptyString(staffLogin, staffPassword);
            Validator.isNull(staffLogin, staffPassword);
            staffPassword = Hasher.hashBySha1(staffPassword);
            if (clientDao.getClientByLogin(staffLogin) == null && !adminDao.findAdminByLogin(staffLogin)) {
                staff = new Staff(staffLogin, staffPassword);
                return staffDao.addStaff(staff);
            }
        } catch (ValidatorException | DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
        }
        LOGGER.log(Level.DEBUG, "Admin service: finish addAdmin");
        return false;
    }

    @Override
    public boolean deleteStaff(Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product DAO: Delete staff start");
        IStaffDao staffDao = daoFactory.getStaffDao();
        try {
            staffDao.deleteStaff(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "ProductService: finish staff client");
        return true;
    }

    @Override
    public Staff signIn(String staffLogin, String staffPassword) {
        LOGGER.log(Level.DEBUG, "Staff service: start SignIn");
        Staff staff;
        IStaffDao staffDao = daoFactory.getStaffDao();
        try {
            Validator.isEmptyString(staffLogin, staffPassword);
            Validator.isNull(staffLogin, staffPassword);
            staffPassword = Hasher.hashBySha1(staffPassword);
            staff = staffDao.signIn(staffLogin, staffPassword);
        } catch (DaoException | ValidatorException e) {
            return null;
        }
        LOGGER.log(Level.DEBUG, "Staff service: finish SignIn");
        return staff;
    }

    @Override
    public List<Staff> getAllStaff() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Staff Service: Start get all staff");
        List<Staff> staff;
        try {
            IStaffDao staffDao = daoFactory.getStaffDao();
            staff = staffDao.getAllStaff();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Staff Service: Finish get all staff");
        return staff;
    }

    @Override
    public boolean checkPassword(String password, Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Staff Service: check password start");
        password = Hasher.hashBySha1(password);
        IStaffDao staffDao = daoFactory.getStaffDao();
        try {
            LOGGER.log(Level.DEBUG, "Staff Service: finish check password");
            return staffDao.checkPassword(password, id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changePassword(String password, Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Staff Service: change password start");
        password = Hasher.hashBySha1(password);
        IStaffDao staffDao = daoFactory.getStaffDao();
        try {
            staffDao.changePassword(password, id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Staff Service: finish change password");
        return true;
    }
}
