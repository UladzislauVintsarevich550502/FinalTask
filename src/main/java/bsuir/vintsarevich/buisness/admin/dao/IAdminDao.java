package bsuir.vintsarevich.buisness.admin.dao;

import bsuir.vintsarevich.entity.Admin;
import bsuir.vintsarevich.exception.dao.DaoException;

import java.util.List;

public interface IAdminDao {
    boolean addAdmin(Admin admin) throws DaoException;

    boolean deleteAdmin(Integer id) throws DaoException;

    Admin signIn(String login, String password) throws DaoException;

    List<Admin> getAllAdmins() throws DaoException;

    boolean findAdminByLogin(String login) throws DaoException;
}
