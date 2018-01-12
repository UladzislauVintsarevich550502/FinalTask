package bsuir.vintsarevich.buisness.admin.dao;

import bsuir.vintsarevich.entity.Admin;
import bsuir.vintsarevich.exception.dao.DaoException;

import java.util.List;

public interface IAdminDao {
    boolean addAdmin() throws DaoException;

    boolean deleteAdmin();

    boolean createAdmin();

    Admin signIn(String login, String password) throws DaoException;

    Admin getAdminById(int id) throws DaoException;

    List<Admin> getAllAdmins() throws DaoException;
}
