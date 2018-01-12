package bsuir.vintsarevich.buisness.product.service.impl;

import bsuir.vintsarevich.buisness.product.service.IProducteService;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.service.ServiceLogicException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.Part;
import java.util.List;

public class ProducteService implements IProducteService {

    private static final int BUFFER_LENGTH = 1024;
    private static final int RECIPE_NEED = 1;
    private static final int RECIPE_NOT_NEED = 0;
    private static Logger logger = Logger.getLogger(ProducteService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public List<Product> getAllProducts() throws ServiceException {
        return null;
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
