package bsuir.vintsarevich;

import bsuir.vintsarevich.exception.service.ServiceException;

public class Main {
    public static void main(String[] args) throws ServiceException {
        double result = Math.rint(100.0 * 1.2 * 3) / 100.0;
        System.out.println(result);
    }
}
