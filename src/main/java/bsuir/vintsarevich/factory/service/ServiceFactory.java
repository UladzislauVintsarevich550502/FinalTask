package bsuir.vintsarevich.factory.service;

import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.buisness.admin.service.impl.AdminService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.client.service.impl.ClientService;
import bsuir.vintsarevich.buisness.product.service.IProducteService;
import bsuir.vintsarevich.buisness.product.service.impl.ProducteService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final IAdminService adminService = new AdminService();
    private final IProducteService producteService = new ProducteService();
    private final IClientService clientService = new ClientService();


    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public IAdminService getAdminService() {
        return adminService;
    }

    public IProducteService getProducteService() {
        return producteService;
    }

    public IClientService getClientService() {
        return clientService;
    }
}
