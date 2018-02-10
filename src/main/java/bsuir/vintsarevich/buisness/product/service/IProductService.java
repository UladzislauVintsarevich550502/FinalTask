package bsuir.vintsarevich.buisness.product.service;

import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.service.ServiceLogicException;

import javax.servlet.http.Part;
import java.util.List;

public interface IProductService {
    List<Product> getAllProducts() throws ServiceException;

    List<Product> getProductByType(String type) throws ServiceException;

    boolean deleteProduct(Integer id) throws ServiceException;

    List<Product> getProductByOrderId(Integer orderId) throws ServiceException;

    Product getProductById(Integer id) throws ServiceException;

    boolean addProduct(String type, String nameRu, String nameEn, Integer weight, Double cost, String status,
                       String descriptionRu, String descriptionEn, Part image, String webPath) throws ServiceException, ServiceLogicException;

}
