package bsuir.vintsarevich.buisness.order.service;

public interface IOrderService {
    boolean addOrder(String orderStatus, Double orderCost, Integer clientId);
}
