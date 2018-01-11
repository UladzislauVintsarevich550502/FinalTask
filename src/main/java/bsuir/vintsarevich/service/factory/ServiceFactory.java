package bsuir.vintsarevich.service.factory;

import bsuir.vintsarevich.dto.admin.service.IAdminService;
import bsuir.vintsarevich.dto.admin.service.impl.AdminService;
import bsuir.vintsarevich.dto.client.service.IClientService;
import bsuir.vintsarevich.dto.client.service.impl.ClientService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final IClientService clientService = new ClientService();
    private final IAdminService adminService = new AdminService();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public IClientService getClientService() {
        return clientService;
    }
    public IAdminService getAdminService() {
        return adminService;
    }

}
