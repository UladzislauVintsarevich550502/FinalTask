package bsuir.vintsarevich.buisness.product.service.impl;

import bsuir.vintsarevich.buisness.product.dao.IProductDao;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.service.ServiceLogicException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.Part;
import java.util.List;

public class ProductService implements IProductService {

    private static Logger LOGGER = Logger.getLogger(ProductService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public List<Product> getAllProducts() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product Service: Start get all products");
        List<Product> products;
        try {
            IProductDao productDao = daoFactory.getProductDao();
            products = productDao.getAllProducts();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Product Service: Finish get all products");
        return products;
    }

    @Override
    public List<Product> getProductsWithPrescription() throws ServiceException {
        return null;
    }

    @Override
    public List<Product> getAscSortedByPriceProducts() throws ServiceException {
        return null;
    }

    @Override
    public List<Product> getDescSortedByPriceProducts() throws ServiceException {
        return null;
    }

    @Override
    public Product getProductById(String id) throws ServiceException {
        return null;
    }

    @Override
    public List<Product> getProductsByProducer(String producer) throws ServiceException {
        return null;
    }

    @Override
    public void addProduct(String name, String producer, String price, String prescroption, Part part, String image, String availability) throws ServiceException, ServiceLogicException {

    }

    @Override
    public List<Product> getProductByName(String name) throws ServiceException {
        return null;
    }

    @Override
    public void editProduct(String idProduct, String name, String producer, String price, String prescroption, Part part, String image, String availability) throws ServiceException, ServiceLogicException {

    }
}
