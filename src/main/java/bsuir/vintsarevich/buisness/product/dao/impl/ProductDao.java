package bsuir.vintsarevich.buisness.product.dao.impl;

import bsuir.vintsarevich.buisness.product.dao.IProductDao;
import bsuir.vintsarevich.connectionpool.ConnectionPool;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.exception.dao.ConnectionException;
import bsuir.vintsarevich.exception.dao.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDao.class);
    public static String GET_ALL_PRODUCTS = "SELECT * FROM epamcafe.menu;";
    public static String ADD_PRODUCT = "INSERT INTO menu (typeProduct,dishName,weight,cost,status,description," +
            "picturePath) VALUES(?,?,?,?,?,?,?);";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;
    private Product productEntity;
    private List<Product> products;

    public ProductDao() {
    }


    @Override
    public boolean addProduct(Product product) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product DAO: Add product start");
        try {
            System.out.println(product);
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(ADD_PRODUCT);
            statement.setString(1, product.getType());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getWeight());
            statement.setDouble(4, product.getCost());
            statement.setString(5, product.getStatus());
            statement.setString(6, product.getDescription());
            statement.setString(7, product.getImagePath());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Add medicament success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Add medicament finish");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(addMedicament)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Product DAO: Add product finish");
        }
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
        LOGGER.log(Level.DEBUG, "product DAO: Start get all products");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(GET_ALL_PRODUCTS);
            resultSet = null;
            resultSet = statement.executeQuery();
            productEntity = null;
            products = new ArrayList<>();
            while (resultSet.next()) {
                productEntity = createProductByResultSet(resultSet);
                products.add(productEntity);
            }
            LOGGER.log(Level.INFO, products);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(getAllproducts)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "product DAO: Finish get all products");
        return products;
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

    private Product createProductByResultSet(ResultSet resultSet) throws DaoException {
        Product product = new Product();
        try {
            product.setId(resultSet.getInt("productId"));
            product.setType(resultSet.getString("typeProduct"));
            product.setName(resultSet.getString("dishName"));
            product.setWeight(resultSet.getInt("weight"));
            product.setCost(resultSet.getDouble("cost"));
            product.setStatus(resultSet.getString("status"));
            product.setDescription(resultSet.getString("description"));
            product.setImagePath(resultSet.getString("picturePath"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return product;
    }
}
