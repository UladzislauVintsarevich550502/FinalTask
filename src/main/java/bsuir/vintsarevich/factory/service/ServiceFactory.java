package bsuir.vintsarevich.factory.service;

import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.buisness.admin.service.impl.AdminService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.client.service.impl.ClientService;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.buisness.order.service.impl.OrderService;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.buisness.product.service.impl.ProductService;
import bsuir.vintsarevich.buisness.review.service.IReviewService;
import bsuir.vintsarevich.buisness.review.service.impl.ReviewService;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private final IAdminService adminService = new AdminService();
    private final IProductService producteService = new ProductService();
    private final IClientService clientService = new ClientService();
    private final IOrderService orderService = new OrderService();
    private final IReviewService reviewService = new ReviewService();


    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public IAdminService getAdminService() {
        return adminService;
    }

    public IProductService getProducteService() {
        return producteService;
    }

    public IClientService getClientService() {
        return clientService;
    }

    public IOrderService getOrderService() {
        return orderService;
    }

    public IReviewService getReviewService() {
        return reviewService;
    }
}
