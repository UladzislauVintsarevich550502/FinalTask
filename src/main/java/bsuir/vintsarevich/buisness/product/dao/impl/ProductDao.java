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
    public static String GET_ALL_PRODUCTS = "SELECT * FROM epamcafe.product;";
    public static String ADD_PRODUCT = "INSERT INTO product (productType,productNameRu,productNameEn,productWeight,productCost,productStatus," +
            "productDescriptionRu,productDescriptionEn,productImage) VALUES(?,?,?,?,?,?,?,?,?);";
    public static String EDIT_PRODUCT = "UPDATE product SET productType=?,productNameRu=?,productNameEn=?,productWeight=?,productCost=?,productStatus=?," +
            "productDescriptionRu=?,productDescriptionEn=?,productImage=? WHERE productId=?;";
    public static String GET_PRODUCT_BY_ID = "SELECT * FROM epamcafe.product WHERE productId=?";
    public static String GET_PRODUCT_BY_TYPE = "SELECT *FROM epamcafe.product WHERE productType=?";
    public static String DELETE_PRODUCT = "DELETE FROM epamcafe.product WHERE productId=?";
    public static String GET_PRODUCT_BY_CLIENT_ID = "SELECT product.productId, product.productType,product.productNameRu," +
            "product.productNameEn,product.productWeight,product.productCost,product.productStatus," +
            "product.productDescriptionRu,productDescriptionEn,product.productImage " +
            "FROM(((client join epamcafe.order ON client.clientId = epamcafe.order.clientId) JOIN orderproducts" +
            " ON epamcafe.order.orderId = orderproducts.orderId) JOIN product " +
            "ON product.productId = orderproducts.productId) WHERE client.clientId = ?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;
    private Product productEntity;
    private List<Product> products;

    @Override
    public List<Product> getProductsByClientId(Integer clientId) throws DaoException {
        LOGGER.log(Level.DEBUG, "product DAO: Start get products by clientId");
        productEntity = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(GET_PRODUCT_BY_CLIENT_ID);
            statement.setInt(1, clientId);
            resultSet = null;
            resultSet = statement.executeQuery();
            products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(createProductByResultSet(resultSet));
            }
            LOGGER.log(Level.INFO, products);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(get Products by Client Id)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "product DAO: finish get products by clientId");
        return products;
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
            statement.setString(2, product.getNameRu());
            statement.setString(3, product.getNameEn());
            statement.setInt(4, product.getWeight());
            statement.setDouble(5, product.getCost());
            statement.setString(6, product.getStatus());
            statement.setString(7, product.getDescriptionRu());
            statement.setString(8, product.getDescriptionEn());
            statement.setString(9, product.getImagePath());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Add product success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Add product finish");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(addProduct)", e);
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
    public List<Product> getAllProducts() throws DaoException {
        LOGGER.log(Level.DEBUG, "Product DAO: Start get all products");
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
        LOGGER.log(Level.DEBUG, "Product DAO: Finish get all products");
        return products;
    }

    @Override
    public boolean editProduct(Product product) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product DAO: Add product start");
        try {
            System.out.println(product);
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(EDIT_PRODUCT);
            statement.setString(1, product.getType());
            statement.setString(2, product.getNameRu());
            statement.setString(3, product.getNameEn());
            statement.setInt(4, product.getWeight());
            statement.setDouble(5, product.getCost());
            statement.setString(6, product.getStatus());
            statement.setString(7, product.getDescriptionRu());
            statement.setString(8, product.getDescriptionEn());
            statement.setString(9, product.getImagePath());
            statement.setInt(10, product.getId());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Edit Product success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Edit Product not success");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(editProduct)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Product DAO: Edit product finish");
        }
    }

    @Override
    public boolean deleteProduct(Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product DAO: Delete product start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = connection.prepareStatement(DELETE_PRODUCT);
            statement.setInt(1, id);
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Delete product success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Delete product finish");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e);
            }
            throw new DaoException("Error of query to database(deleteProduct)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Product DAO: Delete product finish");
        }
    }

    @Override
    public Product getProductById(Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "product DAO: Start get product by ID");
        productEntity = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(GET_PRODUCT_BY_ID);
            statement.setInt(1, id);
            resultSet = null;
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                productEntity = createProductByResultSet(resultSet);
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
        LOGGER.log(Level.DEBUG, "product DAO: get product by ID");
        return productEntity;
    }

    @Override
    public List<Product> getProductByType(String type) throws DaoException {
        LOGGER.log(Level.DEBUG, "product DAO: Start get product by type");
        productEntity = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.retrieve();
            statement = null;
            statement = connection.prepareStatement(GET_PRODUCT_BY_TYPE);
            statement.setString(1, type);
            resultSet = null;
            resultSet = statement.executeQuery();
            products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(createProductByResultSet(resultSet));
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
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "product DAO: finish get product by Type");
        return products;
    }

    private Product createProductByResultSet(ResultSet resultSet) throws DaoException {
        Product product = new Product();
        try {
            product.setId(resultSet.getInt("productId"));
            product.setType(resultSet.getString("productType"));
            product.setNameRu(resultSet.getString("productNameRu"));
            product.setNameEn(resultSet.getString("productNameEn"));
            product.setWeight(resultSet.getInt("productWeight"));
            product.setCost(resultSet.getDouble("productCost"));
            product.setStatus(resultSet.getString("productStatus"));
            product.setDescriptionRu(resultSet.getString("productDescriptionRu"));
            product.setDescriptionEn(resultSet.getString("productDescriptionEn"));
            product.setImagePath(resultSet.getString("productImage"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return product;
    }
}
