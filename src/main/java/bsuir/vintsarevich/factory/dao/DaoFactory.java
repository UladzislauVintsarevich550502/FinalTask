package bsuir.vintsarevich.factory.dao;

import bsuir.vintsarevich.buisness.admin.dao.IAdminDao;
import bsuir.vintsarevich.buisness.admin.dao.impl.AdminDAO;
import bsuir.vintsarevich.buisness.client.dao.IClientDao;
import bsuir.vintsarevich.buisness.client.dao.impl.ClientDAO;
import bsuir.vintsarevich.buisness.product.dao.IProductDao;
import bsuir.vintsarevich.buisness.product.dao.impl.ProductDao;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private final IClientDao clientDao = new ClientDAO();
    private final IProductDao productDao = new ProductDao();
    private final IAdminDao adminDao = new AdminDAO();


    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public IClientDao getClientDao() {
        return clientDao;
    }

    public IProductDao getProductDao() {
        return productDao;
    }

    public IAdminDao getAdminDao() {
        return adminDao;
    }
}
