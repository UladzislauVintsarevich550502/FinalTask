package bsuir.vintsarevich.buisness.stock.service;

import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.entity.Staff;
import bsuir.vintsarevich.exception.service.ServiceException;

import java.util.List;

public interface IStockService {

    List<Product> getStockProducts() throws ServiceException;

}
