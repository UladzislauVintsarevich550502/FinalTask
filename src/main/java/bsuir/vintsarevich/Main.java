package bsuir.vintsarevich;

        import bsuir.vintsarevich.buisness.order.service.IOrderService;
        import bsuir.vintsarevich.buisness.orderproduct.service.IOrderProductService;
        import bsuir.vintsarevich.buisness.product.service.IProductService;
        import bsuir.vintsarevich.exception.service.ServiceException;
        import bsuir.vintsarevich.factory.service.ServiceFactory;
        import org.omg.IOP.IOR;

public class Main {
    public static void main(String[] args) throws ServiceException {
        IProductService productService = ServiceFactory.getInstance().getProducteService();
        IOrderService orderService=ServiceFactory.getInstance().getOrderService();
        IOrderProductService orderProductService=ServiceFactory.getInstance().getOrderProductService();
        orderProductService.deleteOrderProduct(7,9);
    }
}
