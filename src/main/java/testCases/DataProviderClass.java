package testCases;


import objectRepository.Mail;
import org.testng.annotations.DataProvider;

public class DataProviderClass {



    @DataProvider
    public Object[][] loginData(){
        return new Object[][]{
                {"automationTest@protonmail.com", "test123456"}
        };
    }

    @DataProvider
    public Object[][] testDataForMail(){
        return new Object[][]{
                {new Mail("aibar.abilchanov@mail.ru", "From Aibar", "Test automation engineers in da house!!! ")}
        };
    }
}
