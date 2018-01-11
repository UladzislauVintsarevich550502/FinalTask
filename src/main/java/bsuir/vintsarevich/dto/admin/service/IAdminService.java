package bsuir.vintsarevich.dto.admin.service;

import bsuir.vintsarevich.entity.Admin;
import bsuir.vintsarevich.exception.service.ServiceException;

import java.util.List;

public interface IAdminService {

    void updateLocale(String AdminLogin, String locale) throws ServiceException;

    Admin getAdminByLogin(String AdminLogin) throws ServiceException;

    Admin signIn(String adminLogin, String adminPassword);

    List<Admin> getAllAdmins() throws ServiceException;

}
