package com.epam.framework.dataProvider;


import com.epam.framework.business_objects.Letter;
import com.epam.framework.business_objects.User;
import org.testng.annotations.DataProvider;

public class DataProviderClass {



    @DataProvider
    public Object[][] loginData(){
        return new Object[][]{
                {User.MAIN_USER}
        };
    }

    @DataProvider
    public Object[][] dataForLetter(){
        return new Object[][]{
                {new Letter("abilchanov.aibar@mail.ru","From Aibar",  "Test Automation in da house!!!")}
        };
    }

}
