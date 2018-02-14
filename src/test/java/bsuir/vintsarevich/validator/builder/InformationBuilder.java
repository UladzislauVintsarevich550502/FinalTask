package bsuir.vintsarevich.validator.builder;

import java.util.LinkedList;
import java.util.List;

public class InformationBuilder {
    private static List dataForVerification = new LinkedList();

    public  static List getDataForVerification(){
        return dataForVerification;
    }

    public static Object[] setDataWithNullValue() {
        dataForVerification.add("Test");
        dataForVerification.add(null);
        dataForVerification.add("Java");
        return dataForVerification.toArray();
    }

    public static Object[] setDataWithoutNullValue() {
        dataForVerification.add("Hello");
        dataForVerification.add("");
        dataForVerification.add("World");
        return dataForVerification.toArray();
    }

    public static String[] setDataWithEmptyString() {
        dataForVerification.add("");
        dataForVerification.add("Hello");
        dataForVerification.add("123");
        return (String[])dataForVerification.toArray(new String[dataForVerification.size()]);
    }

    public static String[] setDataWithoutEmptyString() {
        dataForVerification.add("Hello");
        dataForVerification.add("World");
        dataForVerification.add("122331");
        return (String[])dataForVerification.toArray(new String[dataForVerification.size()]);
    }

    public static String[] setInccorectDataForVerificationNameSurname() {
        dataForVerification.add("Vintsarevich");
        dataForVerification.add("Владислав");
        dataForVerification.add("плохо");
        return (String[])dataForVerification.toArray(new String[dataForVerification.size()]);
    }

    public static String[] setСorectDataForVerificationNameSurname() {
        dataForVerification.add("Vintsarevich");
        dataForVerification.add("Владислав");
        dataForVerification.add("Александрович");
        return (String[])dataForVerification.toArray(new String[dataForVerification.size()]);
    }

    public static String[] setIncorectDataForVerificationEmail() {
        dataForVerification.add("vladick@mail.ru");
        dataForVerification.add("@");
        dataForVerification.add("vlad007xixixi@gmail.com");
        return (String[])dataForVerification.toArray(new String[dataForVerification.size()]);
    }

    public static String[] setCorectDataForVerificationEmail() {
        dataForVerification.add("vladick@mail.ru");
        dataForVerification.add("vlad007xixixi@mail.ru");
        dataForVerification.add("vlad007xixixi@gmail.com");
        return (String[])dataForVerification.toArray(new String[dataForVerification.size()]);
    }

    public static String[] setIncorectDataForVerificationLogin() {
        dataForVerification.add("15Login");
        dataForVerification.add("loginвс15");
        dataForVerification.add("1...");
        return (String[])dataForVerification.toArray(new String[dataForVerification.size()]);
    }

    public static String[] setCorectDataForVerificationLogin() {
        dataForVerification.add("vladick123");
        dataForVerification.add("login");
        dataForVerification.add("myLogin");
        return (String[])dataForVerification.toArray(new String[dataForVerification.size()]);
    }

    public static String[] setIncorectDataForVerificationPassword() {
        dataForVerification.add("sxwx");
        dataForVerification.add("dcdвсвс");
        dataForVerification.add("12xwx./4");
        return (String[])dataForVerification.toArray(new String[dataForVerification.size()]);
    }

    public static String[] setCorectDataForVerificationPassword() {
        dataForVerification.add("Password123");
        dataForVerification.add("passWord3");
        dataForVerification.add("hELLOword");
        return (String[])dataForVerification.toArray(new String[dataForVerification.size()]);
    }

    public static String[] setIncorectDataForVerificationProductName() {
        dataForVerification.add("Coca-cola");
        dataForVerification.add("Яблочный сок");
        dataForVerification.add("ватрfшка");
        return (String[])dataForVerification.toArray(new String[dataForVerification.size()]);
    }

    public static String[] setCorectDataForVerificationProductName() {
        dataForVerification.add("Coca-cola");
        dataForVerification.add("Яблочный сок");
        dataForVerification.add("Ватрушка");
        return (String[])dataForVerification.toArray(new String[dataForVerification.size()]);
    }
}
