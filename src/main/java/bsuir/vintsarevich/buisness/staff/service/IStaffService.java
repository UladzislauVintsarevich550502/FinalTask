package bsuir.vintsarevich.buisness.staff.service;

import bsuir.vintsarevich.entity.Staff;
import bsuir.vintsarevich.exception.service.ServiceException;

import java.util.List;

public interface IStaffService {
    boolean signUp(String staffLogin, String staffPassword) throws ServiceException;

    boolean deleteStaff(Integer id) throws ServiceException;

    Staff signIn(String staffLogin, String staffPassword);

    List<Staff> getAllStaff() throws ServiceException;

    boolean checkPassword(String password, Integer id) throws ServiceException;

    boolean changePassword(String password, Integer id) throws ServiceException;

    boolean findStaffByLogin(String login) throws ServiceException;
}
