package bsuir.vintsarevich.factory.service;

import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.buisness.admin.service.impl.AdminService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.client.service.impl.ClientService;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.buisness.product.service.impl.ProductService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final IAdminService adminService = new AdminService();
    private final IProductService producteService = new ProductService();
    private final IClientService clientService = new ClientService();


    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
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
}
