package bsuir.vintsarevich.buisness.stock.dao;

import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.entity.Staff;
import bsuir.vintsarevich.exception.dao.DaoException;

import java.util.List;

public interface IStockDao {

    List<Product> getStockProducts() throws DaoException;
}
