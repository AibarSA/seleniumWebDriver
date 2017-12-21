package TestCases;

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
                {"aibar.abilchanov@mail.ru", "From Aibar", "Test automation engineers in da house!!! "}
        };
    }
}
