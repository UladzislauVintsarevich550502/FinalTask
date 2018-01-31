package bsuir.vintsarevich.buisness.client.service;

import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.service.ServiceLogicException;

import java.util.List;

public interface IClientService {
    Client signUp(String name, String surname,
                  String login, String password, String email) throws ServiceException, ServiceLogicException;
    Client signIn(String clientLogin, String clientPassword);
    List<Client> getAllClients() throws ServiceException;

    boolean deleteClient(Integer id) throws ServiceException;
}
