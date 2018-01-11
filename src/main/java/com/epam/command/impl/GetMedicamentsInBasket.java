package com.epam.command.impl;//package com.epam.command.impl;
//
//import com.epam.entity.Medicament;
//import com.epam.service.IMedicamentService;
//import com.epam.service.IOrderService;
//import com.epam.service.exception.ServiceException;
//import com.epam.service.exception.ServiceLogicException;
//import com.epam.service.factory.ServiceFactory;
//import com.epam.servlet.RequestEnum;
//import org.apache.log4j.Logger;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//public class GetMedicamentsInBasket implements com.epam.command.ICommand {
//    private static Logger logger = Logger.getLogger(GetMedicamentsInBasket.class);
//    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
//    private JspPageName jspPageName = JspPageName.BASKET;
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            HttpSession session = request.getSession();
//            String idUser = session.getAttribute(RequestEnum.USER_ID.getValue()).toString();
//
//            IOrderService orderService = serviceFactory.getOrderServiceImpl();
//            List<Medicament> medicaments = orderService.getMedicamentsInBasket(idUser);
//
//            logger.info(request.getHeader("User-Agent") + " successfully getting meds in basket ");
//            request.setAttribute("medicaments",medicaments);
//        } catch (ServiceException e) {
//            logger.error(e.getMessage());
//        } catch (ServiceLogicException e){
//            logger.error(e.getMessage());
//        }
//        return jspPageName.getPath();
//    }
//}
