package bsuir.vintsarevich.buisness.product.service.impl;

import bsuir.vintsarevich.buisness.product.dao.IProductDao;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.exception.dao.DaoException;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.exception.service.ServiceLogicException;
import bsuir.vintsarevich.exception.validation.ValidatorException;
import bsuir.vintsarevich.factory.dao.DaoFactory;
import bsuir.vintsarevich.utils.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProductService implements IProductService {

    private static Logger LOGGER = Logger.getLogger(ProductService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private static final int BUFFER_LENGTH = 1024;

    @Override
    public List<Product> getAllProducts() throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product Service: Start get all products");
        List<Product> products;
        try {
            IProductDao productDao = daoFactory.getProductDao();
            products = productDao.getAllProducts();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Product Service: Finish get all products");
        return products;
    }

    @Override
    public void addProduct(String type, String nameRu, String nameEn, Integer weight, Double cost, String status,
                           String descriptionRu, String descriptionEn, Part image, String webPath) throws ServiceException, ServiceLogicException {
        LOGGER.log(Level.DEBUG, "ProductService: addProduct start");
        Product product = new Product();
        IProductDao productDao = daoFactory.getProductDao();
        try {
            Validator.isNull(nameEn, nameRu, type, status);
            Validator.isEmptyString(nameEn, nameRu, type, status);
            product.setType(type);
            product.setNameRu(nameRu);
            product.setNameEn(nameEn);
            product.setWeight(weight);
            product.setCost(cost);
            product.setStatus(status);
            product.setDescriptionRu(descriptionRu);
            product.setDescriptionEn(descriptionEn);
            String imageName = getImageName(image);
            System.out.println(imageName);
            if (!imageName.isEmpty()) {
                product.setImagePath(imageName);
            }
            productDao.addProduct(product);
            String fileName = Paths.get(image.getSubmittedFileName()).getFileName().toString();
            if (!fileName.isEmpty()) {
                System.out.println(webPath);
                uploadImage(image, fileName, webPath);
            }
        } catch (ValidatorException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
            throw new ServiceException(e);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
            throw new ServiceException("number format exception", e);
        } catch (DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "ProductService: addProduct finish");
    }

    @Override
    public List<Product> getProductByType(String type) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: start get product by type");
        List<Product> products;
        try {
            IProductDao productDao = daoFactory.getProductDao();
            products = productDao.getProductByType(type);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "Product Service: Finish get products by type");
        return products;
    }

    @Override
    public boolean deleteProduct(Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "Product DAO: Delete product start");
        IProductDao productDao = daoFactory.getProductDao();
        try {
            productDao.deleteProduct(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "ProductService: finish delete products");
        return true;
    }

    @Override
    public Product getProductById(Integer id) throws ServiceException {
        LOGGER.log(Level.DEBUG, "ProductService: start get product by ID");
        Product product;
        IProductDao productDao = daoFactory.getProductDao();
        try {
            product = productDao.getProductById(id);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
            throw new ServiceException("number format exception", e);
        } catch (DaoException e) {
            LOGGER.log(Level.DEBUG, e.getMessage());
            throw new ServiceException(e);
        }
        LOGGER.log(Level.DEBUG, "ProductService: finish get product by ID");
        return product;
    }

    private void uploadImage(Part filePart, String fileName, String webInfPath) throws ServiceException, ServiceLogicException {
        try {
            LOGGER.log(Level.DEBUG, "ProductServer: upload start");
            File dir = new File(webInfPath + "images" + File.separator + "products");
            if (!dir.exists()) {
                Path path = Paths.get(webInfPath + "images" + File.separator + "products");
                Files.createDirectories(path);
            }
            File file = new File(dir, fileName);
            InputStream fileContent = filePart.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[BUFFER_LENGTH];
            int len = fileContent.read(buffer);
            while (len != -1) {
                fileOutputStream.write(buffer, 0, len);
                len = fileContent.read(buffer);
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    System.out.println("error with closing file" + e);
                }
            }
        } catch (IOException e) {
            throw new ServiceLogicException("error with upload of image", e);
        }
        LOGGER.log(Level.DEBUG, "ProductServer: upload finish");
    }

    private String getImageName(Part filePart) {
        LOGGER.log(Level.DEBUG, "MedicamentServiceImpl.getImageName()" + filePart);
        String name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        LOGGER.log(Level.DEBUG, "MedicamentServiceImpl.getImageName() - success");
        return name;
    }
}
