package com.epam.framework.webServicesTesting;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class RestAssured {

        Response response;

        @BeforeTest
        public void initTest() {
             response = get("http://jsonplaceholder.typicode.com/users");
        }

        @Test
        public void checkStatusCode() {
            Assert.assertEquals(response.getStatusCode(), 200);
        }

        @Test
        public void checkResponseHeader() {
            Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");
        }

        @Test
        public void checkResponseBody() {
            Assert.assertEquals(response.body().path("id.size()"), 10);
        }
    }

