package com.epam.framework.dataProvider;


import com.epam.framework.business_objects.User;
import com.epam.framework.pages.Mail;
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
