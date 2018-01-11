package bsuir.vintsarevich.dao.factory;

import bsuir.vintsarevich.dto.admin.dao.IAdminDao;
import bsuir.vintsarevich.dto.admin.dao.impl.AdminDAO;
import bsuir.vintsarevich.dto.client.dao.IClientDao;
import bsuir.vintsarevich.dto.client.dao.impl.ClientDAO;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private final IClientDao clientDao = new ClientDAO();
    private final IAdminDao adminDao = new AdminDAO();

    private DaoFactory() {}
    public static DaoFactory getInstance() {
        return instance;
    }

    public IClientDao getClientDao() {
        return clientDao;
    }

    public IAdminDao getAdminDao() {
        return adminDao;
    }
}
