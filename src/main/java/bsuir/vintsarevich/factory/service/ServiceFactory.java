package bsuir.vintsarevich.factory.service;

import bsuir.vintsarevich.buisness.account.service.IAccountService;
import bsuir.vintsarevich.buisness.account.service.impl.AccountService;
import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.buisness.admin.service.impl.AdminService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.client.service.impl.ClientService;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.buisness.order.service.impl.OrderService;
import bsuir.vintsarevich.buisness.orderproduct.service.IOrderProductService;
import bsuir.vintsarevich.buisness.orderproduct.service.impl.OrderProductService;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.buisness.product.service.impl.ProductService;
import bsuir.vintsarevich.buisness.review.service.IReviewService;
import bsuir.vintsarevich.buisness.review.service.impl.ReviewService;
import bsuir.vintsarevich.buisness.staff.service.IStaffService;
import bsuir.vintsarevich.buisness.staff.service.impl.StaffService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final IAdminService adminService = new AdminService();
    private final IProductService producteService = new ProductService();
    private final IClientService clientService = new ClientService();
    private final IOrderProductService orderProductService = new OrderProductService();
    private final IAccountService accountService = new AccountService();
    private final IOrderService orderService = new OrderService();
    private final IReviewService reviewService = new ReviewService();
    private final IStaffService staffService = new StaffService();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public IAdminService getAdminService() {
        return adminService;
    }

    public IOrderProductService getOrderProductService() {
        return orderProductService;
    }

    public IProductService getProducteService() {
        return producteService;
    }

    public IClientService getClientService() {
        return clientService;
    }

    public IAccountService getAccountService() {
        return accountService;
    }

    public IOrderService getOrderService() {
        return orderService;
    }

    public IReviewService getReviewService() {
        return reviewService;
    }

    public IStaffService getStaffService() {
        return staffService;
    }
}
