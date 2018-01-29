package bsuir.vintsarevich.factory.dao;

import bsuir.vintsarevich.buisness.admin.dao.IAdminDao;
import bsuir.vintsarevich.buisness.admin.dao.impl.AdminDAO;
import bsuir.vintsarevich.buisness.client.dao.IClientDao;
import bsuir.vintsarevich.buisness.client.dao.impl.ClientDAO;
import bsuir.vintsarevich.buisness.order.dao.IOrderDao;
import bsuir.vintsarevich.buisness.order.dao.impl.OrderDao;
import bsuir.vintsarevich.buisness.product.dao.IProductDao;
import bsuir.vintsarevich.buisness.product.dao.impl.ProductDao;
import bsuir.vintsarevich.buisness.review.dao.IReviewDao;
import bsuir.vintsarevich.buisness.review.dao.impl.ReviewDao;

public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();
    private final IClientDao clientDao = new ClientDAO();
    private final IProductDao productDao = new ProductDao();
    private final IAdminDao adminDao = new AdminDAO();
    private final IOrderDao orderDao = new OrderDao();
    private final IReviewDao reviewDao = new ReviewDao();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
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

    public IOrderDao getOrderDao() {
        return orderDao;
    }

    public IReviewDao getReviewDao() {
        return reviewDao;
    }
}
