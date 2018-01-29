package bsuir.vintsarevich.buisness.product.dao;

import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.exception.dao.DaoException;

import java.util.List;

public interface IProductDao {
    List<Product> getProductsByClientId(Integer clientId) throws DaoException;
    boolean addProduct(Product product) throws DaoException;
    List<Product> getAllProducts() throws DaoException;
    boolean editProduct(Product product) throws DaoException;
    boolean deleteProduct(Integer id) throws DaoException;
    Product getProductById(Integer id) throws DaoException;
    List<Product> getProductByType(String type) throws DaoException;
}
