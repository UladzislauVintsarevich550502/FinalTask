package bsuir.vintsarevich;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;

public class Main {
    public static void main(String[] args) throws ServiceException {
        IClientService clientService = ServiceFactory.getInstance().getClientService();
        System.out.println(clientService.signIn("vladick", "Qwe123"));
    }
}
