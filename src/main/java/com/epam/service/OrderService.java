package com.epam.service;

import com.epam.dao.IOrderDao;
import com.epam.dao.exception.DaoException;
import com.epam.dao.factory.DaoFactory;
import com.epam.entity.Order;
import com.epam.service.exception.ServiceException;
import com.epam.service.exception.ServiceLogicException;
import com.epam.service.utils.Validator;
import com.epam.service.utils.exception.ValidatorException;
import org.apache.log4j.Logger;

public class OrderService implements IOrderService {

    private static final int IN_BASKET = 1;
    private static final int PAID = 2;
    private static Logger logger = Logger.getLogger(OrderService.class);
    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public void addToBasket(String idUser, String idMedicament,
                            String number, String dosage) throws ServiceException, ServiceLogicException {
        logger.debug("OrderService.addToBasket");
        IOrderDao orderDao = daoFactory.getIOrderDao();
        Order order = new Order();
        try {
            Validator.isNull(idUser, idMedicament, number, dosage);
            Validator.isEmptyString(idUser, idMedicament, number, dosage);
            Validator.matchNumber(number, dosage);

            order.setIdUser(Integer.parseInt(idUser));
            order.setIdMedicament(Integer.parseInt(idMedicament));
            order.setIdOrderStatus(IN_BASKET);
            order.setNumber(Integer.parseInt(number));
            order.setDosage(Integer.parseInt(dosage));

            orderDao.addOrder(order);

        } catch (ValidatorException e) {
            throw new ServiceException(e);
        } catch (NumberFormatException e) {
            throw new ServiceException("number format exception", e);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        logger.debug("OrderService.addToBasket - success");
    }

    @Override
    public void checkout(String idUser, String idMedicament,
                         String number, String dosage) throws ServiceException, ServiceLogicException {
        logger.debug("OrderService.checkout");
        IOrderDao orderDao = daoFactory.getIOrderDao();
        Order order = new Order();
        try {
            Validator.isNull(idUser, idMedicament, number, dosage);
            Validator.isEmptyString(idUser, idMedicament, number, dosage);
            Validator.matchNumber(number, dosage);

            order.setIdUser(Integer.parseInt(idUser));
            order.setIdMedicament(Integer.parseInt(idMedicament));
            order.setIdOrderStatus(PAID);
            order.setNumber(Integer.parseInt(number));
            order.setDosage(Integer.parseInt(dosage));

            orderDao.addOrder(order);

        } catch (ValidatorException e) {
            throw new ServiceException(e);
        } catch (NumberFormatException e) {
            throw new ServiceException("number format exception", e);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        logger.debug("OrderService.checkout - success");
    }

//    @Override
//    public List<Medicament> getMedicamentsInBasket(String idUser) throws ServiceException, ServiceLogicException {
//        logger.debug("OrderService.getMedicamentsInBasket()");
//        List<Medicament> medicaments = null;
//        IOrderDao orderDao = daoFactory.getIOrderDao();
//        try {
//            Validator.isNull(idUser);
//            Validator.isEmptyString(idUser);
//            int id = Integer.parseInt(idUser);
//            medicaments = orderDao.getMedicamentsInBasket(id);
//        } catch (DaoException e) {
//            throw new ServiceException(e);
//        } catch (ValidatorException e){
//            throw new ServiceException(e);
//        }
//        logger.debug("OrderService.getMedicamentsInBasket() - success.");
//        return medicaments;
//    }
}
