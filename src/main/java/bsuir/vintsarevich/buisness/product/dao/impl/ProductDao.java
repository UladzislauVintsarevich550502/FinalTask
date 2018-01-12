package bsuir.vintsarevich.buisness.product.dao.impl;

import bsuir.vintsarevich.buisness.product.dao.IProductDao;
import bsuir.vintsarevich.connectionpool.ConnectionPool;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.exception.dao.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ProductDao implements IProductDao {
    public static String GET_ALL_PRODUCTS = "SELECT * FROM pharmacy.Product;";
    public static String GET_SORTED_BY_PRICE_PRODUCTS_ASC = "SELECT * FROM pharmacy.Product ORDER BY price ASC;";
    public static String GET_SORTED_BY_PRICE_PRODUCTS_DESC = "SELECT * FROM pharmacy.Product ORDER BY price DESC;";
    public static String GET_PRODUCT_BY_ID = "SELECT * FROM pharmacy.Product WHERE idProduct=?;";
    public static String ADD_PRODUCT = "INSERT INTO Product (name,producer,price,prescription,image,availability) VALUES(?,?,?,?,?,?);";
    public static String GET_PRODUCTS_BY_PRODUCER = "SELECT * FROM pharmacy.Product WHERE producer=?;";
    public static String GET_PRODUCT_BY_NAME = "SELECT * FROM pharmacy.Product WHERE name LIKE ?;";
    public static String EDIT_PRODUCT = "UPDATE pharmacy.Product SET (name, producer, price, prescroption, image, availability) VALUES(?,?,?,?,?,?) WHERE idProduct=?;";
    public static String GET_PRODUCTS_BY_PRESCRIPTION = "SELECT * FROM pharmacy.Product WHERE prescription=?;";
    private static Logger logger = Logger.getLogger(ProductDao.class);
    ConnectionPool connectionPool;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement statement;
    Product product;
    List<Product> products;

    public ProductDao() {
    }


    @Override
    public boolean addProduct(Product product) throws DaoException {
        return false;
    }

    @Override
    public boolean deleteProduct() {
        return false;
    }

    @Override
    public Product getProductById(int id) throws DaoException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws DaoException {
        return null;
    }

    @Override
    public List<Product> getAscSortedByPriceProducts() throws DaoException {
        return null;
    }

    @Override
    public List<Product> getDescSortedByPriceProducts() throws DaoException {
        return null;
    }

    @Override
    public List<Product> getProductsByProducer(String producer) throws DaoException {
        return null;
    }

    @Override
    public List<Product> getProductsByName(String name) throws DaoException {
        return null;
    }

    @Override
    public boolean editProduct(Product product) throws DaoException {
        return false;
    }

    @Override
    public List<Product> getProductsByPrescription(int prescriptionStatus) throws DaoException {
        return null;
    }
}
