package bsuir.vintsarevich.buisness.stock.dao.impl;

import bsuir.vintsarevich.buisness.product.dao.impl.ProductDAO;
import bsuir.vintsarevich.buisness.stock.dao.IStockDao;
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

public class StockDAO implements IStockDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class);
    private String GET_STOCK_PRODUCTS = "SELECT * FROM epamcafe.product JOIN epamcafe.productstock ON epamcafe.product.productId = epamcafe.productstock.productId";
    private ConnectionPool connectionPool;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    @Override
    public List<Product> getStockProducts() throws DaoException {
        LOGGER.log(Level.DEBUG, "stock DAO: Start get stock products");
        List<Product> products;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_STOCK_PRODUCTS);
            resultSet = statement.executeQuery();
            products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(createProductByResultSet(resultSet));
            }
            LOGGER.log(Level.INFO, products);
        } catch (SQLException e) {
            return null;
        } catch (ConnectionException e) {
            throw new DaoException(this.getClass() + ":" + e.getMessage());
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
            throw new DaoException(this.getClass() + ":" + e.getMessage());
        }
        return product;
    }
}