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
    public List<Product> getProductsWithPrescription() throws ServiceException {
        return null;
    }

    @Override
    public List<Product> getAscSortedByPriceProducts() throws ServiceException {
        return null;
    }

    @Override
    public List<Product> getDescSortedByPriceProducts() throws ServiceException {
        return null;
    }

    @Override
    public Product getProductById(String id) throws ServiceException {
        return null;
    }

    @Override
    public List<Product> getProductsByProducer(String producer) throws ServiceException {
        return null;
    }

    @Override
    public void addProduct(String type, String name, Integer weight, Double cost, String status, String description,
                           Part image, String webPath) throws ServiceException, ServiceLogicException {
        LOGGER.log(Level.DEBUG, "ProductService: addProduct start");
        Product product = new Product();
        IProductDao productDao = daoFactory.getProductDao();
        try {
            Validator.isNull(name, type, status);
            Validator.isEmptyString(name, type, status);
            product.setType(type);
            product.setName(name);
            product.setWeight(weight);
            product.setCost(cost);
            product.setStatus(status);
            product.setDescription(description);
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
    public List<Product> getProductByName(String name) throws ServiceException {
        return null;
    }

    @Override
    public void editProduct(String idProduct, String name, String producer, String price, String prescroption, Part part, String image, String availability) throws ServiceException, ServiceLogicException {

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
