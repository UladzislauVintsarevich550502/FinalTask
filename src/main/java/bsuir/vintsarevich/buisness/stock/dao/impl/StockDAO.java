package bsuir.vintsarevich.buisness.stock.dao.impl;

import bsuir.vintsarevich.buisness.product.dao.impl.ProductDAO;
import bsuir.vintsarevich.buisness.stock.dao.IStockDao;
import bsuir.vintsarevich.connectionpool.ConnectionPool;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.entity.Staff;
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

public class StockDAO implements IStockDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class);
    private String GET_STOCK_PRODUCTS = "SELECT * FROM epamcafe.product JOIN epamcafe.productstock ON epamcafe.product.productId = epamcafe.productstock.productId";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;
    private Product productEntity;
    private List<Product> products;

    @Override
    public List<Product> getStockProducts() throws DaoException {
        LOGGER.log(Level.DEBUG, "stock DAO: Start get stock products");
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = null;
            statement = connection.prepareStatement(GET_STOCK_PRODUCTS);
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
            throw new DaoException("Error of query to database(getSTOCKproducts)", e);
        } catch (ConnectionException e) {
            throw new DaoException("Error with connection with database" + e);
        } finally {
            if (connectionPool != null) {
                connectionPool.putBackConnection(connection, statement, resultSet);
            }
        }
        LOGGER.log(Level.DEBUG, "product DAO: Finish get stock products");
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