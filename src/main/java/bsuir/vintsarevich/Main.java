package bsuir.vintsarevich;

import bsuir.vintsarevich.buisness.admin.dao.IAdminDao;
import bsuir.vintsarevich.buisness.admin.dao.impl.AdminDAO;
import bsuir.vintsarevich.buisness.admin.service.IAdminService;
import bsuir.vintsarevich.buisness.admin.service.impl.AdminService;
import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.client.service.impl.ClientService;
import bsuir.vintsarevich.buisness.order.service.IOrderService;
import bsuir.vintsarevich.buisness.orderproduct.service.IOrderProductService;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.entity.Admin;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.service.ServiceLogicException;
import bsuir.vintsarevich.factory.service.ServiceFactory;

public class Main {
    public static void main(String[] args) throws ServiceException {
        IClientService clientService = new ClientService();
        try {
            clientService.signUp("Винцаревич","Владислав","venchick","Qwe123","vlad@gmail.com");
        } catch (ServiceLogicException e) {
            e.printStackTrace();
        }
    }
}
