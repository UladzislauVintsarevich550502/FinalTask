package bsuir.vintsarevich.validator;

import bsuir.vintsarevich.exception.validation.ValidatorException;
import bsuir.vintsarevich.validator.builder.InformationBuilder;
import bsuir.vintsarevich.utils.Validator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ValidatorTest extends Assert{

    @AfterMethod
    public void clearDataForVerification() {
        InformationBuilder.getDataForVerification().clear();
    }

    @Test(expectedExceptions = ValidatorException.class)
    public void validationNullValuesWithValidationException() throws ValidatorException{
        Object[] inputData = InformationBuilder.setDataWithNullValue();
        Validator.isNull(inputData);
    }

    @Test
    public void validationNullValuesWithoutValidationException() throws ValidatorException{
        Object[] inputData = InformationBuilder.setDataWithoutNullValue();
        Validator.isNull(inputData);
    }

    @Test(expectedExceptions = ValidatorException.class)
    public void validationEmptyValuesWithValidationException() throws ValidatorException{
        String[] inputData = InformationBuilder.setDataWithEmptyString();
        Validator.isEmptyString(inputData);
    }

    @Test
    public void validationEmptyValuesWithoutValidationException() throws ValidatorException{
        String[] inputData = InformationBuilder.setDataWithoutEmptyString();
        Validator.isEmptyString(inputData);
    }

    @Test(expectedExceptions = ValidatorException.class)
    public void validationNamesSurnameWithIncorrectValue() throws ValidatorException{
        String[] inputData = InformationBuilder.setInccorectDataForVerificationNameSurname();
        Validator.matchProperName(inputData);
    }

    @Test
    public void validationNamesSurnameWithoutIncorrectValue() throws ValidatorException{
        String[] inputData = InformationBuilder.setСorectDataForVerificationNameSurname();
        Validator.matchProperName(inputData);
    }

    @Test(expectedExceptions = ValidatorException.class)
    public void validationEmailsWithIncorrectValue() throws ValidatorException{
        String[] inputData = InformationBuilder.setIncorectDataForVerificationEmail();
        Validator.matchEmail(inputData);
    }

    @Test
    public void validationEmailsWithoutIncorrectValue() throws ValidatorException{
        String[] inputData = InformationBuilder.setCorectDataForVerificationEmail();
        Validator.matchEmail(inputData);
    }

    @Test(expectedExceptions = ValidatorException.class)
    public void validationLoginsWithIncorrectValue() throws ValidatorException{
        String[] inputData = InformationBuilder.setIncorectDataForVerificationLogin();
        Validator.matchLogin(inputData);
    }

    @Test
    public void validationLoginsWithoutIncorrectValue() throws ValidatorException{
        String[] inputData = InformationBuilder.setCorectDataForVerificationLogin();
        Validator.matchLogin(inputData);
    }

    @Test(expectedExceptions = ValidatorException.class)
    public void validationPasswordsWithIncorrectValue() throws ValidatorException{
        String[] inputData = InformationBuilder.setIncorectDataForVerificationPassword();
        Validator.matchPassword(inputData);
    }

    @Test
    public void validationPasswordsWithoutIncorrectValue() throws ValidatorException{
        String[] inputData = InformationBuilder.setCorectDataForVerificationPassword();
        Validator.matchPassword(inputData);
    }

    @Test(expectedExceptions = ValidatorException.class)
    public void validationProductNameWithIncorrectValue() throws ValidatorException{
        String[] inputData = InformationBuilder.setIncorectDataForVerificationProductName();
        Validator.matchProductName(inputData);
    }

    @Test
    public void validationProductNameWithoutIncorrectValue() throws ValidatorException{
        String[] inputData = InformationBuilder.setCorectDataForVerificationProductName();
        Validator.matchProductName(inputData);
    }
}