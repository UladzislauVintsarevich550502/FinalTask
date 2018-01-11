package com.epam.service.factory;

import com.epam.service.MedicamentService;
import com.epam.service.OrderService;
import com.epam.service.PrescriptionService;
import com.epam.service.UserService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService iUserService = new UserService();
    private final MedicamentService iMedicamentService = new MedicamentService();
    private final PrescriptionService iPrescriptionService = new PrescriptionService();
    private final OrderService iOrderService = new OrderService();


    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserServiceImpl() {
        return iUserService;
    }

    public MedicamentService getMedicamentServiceImpl() {
        return iMedicamentService;
    }

    public PrescriptionService getPrescriptionServiceImpl() {
        return iPrescriptionService;
    }

    public OrderService getOrderServiceImpl() {
        return iOrderService;
    }


}
