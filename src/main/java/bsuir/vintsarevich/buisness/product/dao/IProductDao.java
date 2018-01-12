package bsuir.vintsarevich.buisness.product.dao;

import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.exception.dao.DaoException;

import java.util.List;

public interface IProductDao {
    boolean addProduct(Product product) throws DaoException;

    boolean deleteProduct();

    Product getProductById(int id) throws DaoException;

    List<Product> getAllProducts() throws DaoException;

    List<Product> getAscSortedByPriceProducts() throws DaoException;

    List<Product> getDescSortedByPriceProducts() throws DaoException;

    List<Product> getProductsByProducer(String producer) throws DaoException;

    List<Product> getProductsByName(String name) throws DaoException;

    boolean editProduct(Product product) throws DaoException;

    List<Product> getProductsByPrescription(int prescriptionStatus) throws DaoException;
}
