package bsuir.vintsarevich;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.service.ServiceLogicException;
import bsuir.vintsarevich.factory.service.ServiceFactory;

public class Main {
    public static void main(String[] args) throws ServiceException {
        IClientService clientService = ServiceFactory.getInstance().getClientService();
        try {
            clientService.signUp("Владислав","Винцаревич","vladick","Qwe123","vlad@gmail.com");
        } catch (ServiceLogicException e) {
            e.printStackTrace();
        }
    }
}
