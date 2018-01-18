package bsuir.vintsarevich;

import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;

public class Main {
    public static void main(String[] args) throws ServiceException {
        IProductService producteService = ServiceFactory.getInstance().getProducteService();
        System.out.println(producteService.getAllProducts());
    }
}
