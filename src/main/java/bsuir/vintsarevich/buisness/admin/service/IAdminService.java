package bsuir.vintsarevich.buisness.admin.service;

import bsuir.vintsarevich.entity.Admin;
import bsuir.vintsarevich.exception.service.ServiceException;

import java.util.List;

public interface IAdminService {
    boolean signUp(String adminLogin, String adminPassword);

    boolean deleteAdmin(Integer id) throws ServiceException;
    Admin signIn(String adminLogin, String adminPassword);
    List<Admin> getAllAdmins() throws ServiceException;
}
