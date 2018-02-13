package bsuir.vintsarevich;

import bsuir.vintsarevich.exception.validation.ValidatorException;
import bsuir.vintsarevich.utils.Validator;

public class Main {
    public static void main(String[] args) throws ValidatorException {
        Validator.matchProductName("Котлета по-Киевски", "Kiev`s kotlet", "Coca-cola","Кока-кола", "Яблочный сок");
        System.out.println("trvrfvrf");
    }
}
