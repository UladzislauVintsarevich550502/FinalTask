package bsuir.vintsarevich.buisness.product.service;

import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.service.ServiceLogicException;

import javax.servlet.http.Part;
import java.util.List;

public interface IProductService {
    List<Product> getAllProducts() throws ServiceException;

    List<Product> getProductsWithPrescription() throws ServiceException;

    List<Product> getAscSortedByPriceProducts() throws ServiceException;

    List<Product> getDescSortedByPriceProducts() throws ServiceException;

    Product getProductById(String id) throws ServiceException;

    List<Product> getProductsByProducer(String producer) throws ServiceException;

    void addProduct(String type, String name, Integer weight,
                    Double cost, String status, String description, Part image, String webPath) throws ServiceException, ServiceLogicException;

    List<Product> getProductByName(String name) throws ServiceException;

    void editProduct(String idProduct, String name, String producer, String price,
                     String prescroption, Part part,
                     String image, String availability) throws ServiceException, ServiceLogicException;

}
