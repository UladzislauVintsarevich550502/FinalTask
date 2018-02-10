package bsuir.vintsarevich.buisness.stock.service.impl;

import bsuir.vintsarevich.buisness.stock.service.IStockService;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

public class StockService implements IStockService {
    private static final Logger LOGGER = Logger.getLogger(StockService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<Product> getStockProducts() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product Service: Start get stock products");
        try {
            LOGGER.log(Level.DEBUG, "Product Service: Finish get stock products");
            return daoFactory.getStockDao().getStockProducts();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
