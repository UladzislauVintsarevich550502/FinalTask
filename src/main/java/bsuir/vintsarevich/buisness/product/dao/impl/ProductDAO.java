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

/**
 * class ProductDAO created for working with products
 */
public class ProductDAO implements IProductDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class);
    public static String GET_ALL_PRODUCTS = "SELECT * FROM epamcafe.product;";
    public static String ADD_PRODUCT = "INSERT INTO product (productType,productNameRu,productNameEn,productWeight,productCost," +
            "productDescriptionRu,productDescriptionEn,productImage) VALUES(?,?,?,?,?,?,?,?);";
    public static String EDIT_PRODUCT = "UPDATE product SET productType=?,productNameRu=?,productNameEn=?,productWeight=?,productCost=?," +
            "productDescriptionRu=?,productDescriptionEn=?,productImage=? WHERE productId=?;";
    public static String GET_PRODUCT_BY_ID = "SELECT * FROM epamcafe.product WHERE productId=?";
    public static String GET_PRODUCT_BY_TYPE = "SELECT * FROM epamcafe.product WHERE productType=?";
    public static String DELETE_PRODUCT = "DELETE FROM epamcafe.product WHERE productId=?";
    public static String GET_PRODUCT_BY_ORDERID = "SELECT product.productId, product.productType,product.productNameRu," +
            "product.productNameEn,product.productWeight,product.productCost," +
            "product.productDescriptionRu,productDescriptionEn,product.productImage,orderproducts.productCount " +
            "FROM(((client join epamcafe.order ON client.clientId = epamcafe.order.clientId) JOIN orderproducts" +
            " ON epamcafe.order.orderId = orderproducts.orderId) JOIN product " +
            "ON product.productId = orderproducts.productId) WHERE order.orderId = ?";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    /**
     * @param product
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean addProduct(Product product) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product DAO: Add product start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(ADD_PRODUCT);
            statement.setString(1, product.getType());
            statement.setString(2, product.getNameRu());
            statement.setString(3, product.getNameEn());
            statement.setInt(4, product.getWeight());
            statement.setDouble(5, product.getCost());
            statement.setString(6, product.getDescriptionRu());
            statement.setString(7, product.getDescriptionEn());
            statement.setString(8, product.getImagePath());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Add product success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Add product finish");
                return false;
            }
        } catch (SQLException e) {
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Product DAO: Add product finish");
        }
    }

    /**
     * @param id
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean deleteProduct(Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product DAO: Delete product start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
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
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Product DAO: Delete product finish");
        }
    }

    /**
     * @param id
     * @return Product
     * @throws DaoException
     */
    @Override
    public Product getProductById(Integer id) throws DaoException {
        LOGGER.log(Level.DEBUG, "product DAO: Start get product by ID");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_PRODUCT_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createProductByResultSet(resultSet);
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "product DAO: get product by ID");
        return null;
    }

    /**
     * @return List<Product>
     * @throws DaoException
     */
    @Override
    public List<Product> getAllProducts() throws DaoException {
        LOGGER.log(Level.DEBUG, "product DAO: Start get all products");
        List<Product> products;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_ALL_PRODUCTS);
            resultSet = statement.executeQuery();
            products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(createProductByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "product DAO: Finish get all products");
        return products;
    }

    /**
     * @param type
     * @return List<Product>
     * @throws DaoException
     */
    @Override
    public List<Product> getProductByType(String type) throws DaoException {
        LOGGER.log(Level.DEBUG, "product DAO: Start get product by type");
        List<Product> products = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_PRODUCT_BY_TYPE);
            statement.setString(1, type);
            resultSet = statement.executeQuery();
            products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(createProductByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
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

    /**
     * @param orderId
     * @return List<Product>
     * @throws DaoException
     */
    @Override
    public List<Product> getProductByOrderId(Integer orderId) throws DaoException {
        LOGGER.log(Level.DEBUG, "product DAO: Start get product by orderId");
        List<Product> products = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_PRODUCT_BY_ORDERID);
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();
            products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(createProductByResultSet(resultSet));
                products.get(products.size() - 1).setNumber(resultSet.getInt("orderproducts.productCount"));
            }
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "product DAO: finish get product by orderId");
        return products;
    }

    /**
     * @param product
     * @return boolean
     * @throws DaoException
     */
    @Override
    public boolean editProduct(Product product) throws DaoException {
        LOGGER.log(Level.DEBUG, "Product DAO: Add product start");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(EDIT_PRODUCT);
            statement.setString(1, product.getType());
            statement.setString(2, product.getNameRu());
            statement.setString(3, product.getNameEn());
            statement.setInt(4, product.getWeight());
            statement.setDouble(5, product.getCost());
            statement.setString(6, product.getDescriptionRu());
            statement.setString(7, product.getDescriptionEn());
            statement.setString(8, product.getImagePath());
            statement.setInt(9, product.getId());
            if (statement.executeUpdate() != 0) {
                LOGGER.log(Level.DEBUG, "Edit Product success");
                return true;
            } else {
                LOGGER.log(Level.DEBUG, "Edit Product not success");
                return false;
            }
        } catch (SQLException e) {
            return false;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
            LOGGER.log(Level.DEBUG, "Product DAO: Edit product finish");
        }
    }


    /**
     * @param resultSet
     * @return Product
     * @throws DaoException
     */
    private Product createProductByResultSet(ResultSet resultSet) throws DaoException {
        Product product = new Product();
        try {
            product.setId(resultSet.getInt("productId"));
            product.setType(resultSet.getString("productType"));
            product.setNameRu(resultSet.getString("productNameRu"));
            product.setNameEn(resultSet.getString("productNameEn"));
            product.setWeight(resultSet.getInt("productWeight"));
            product.setCost(resultSet.getDouble("productCost"));
            product.setDescriptionRu(resultSet.getString("productDescriptionRu"));
            product.setDescriptionEn(resultSet.getString("productDescriptionEn"));
            product.setImagePath(resultSet.getString("productImage"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return product;
    }
}
