package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {
    @Test(dataProvider="person",priority = 1)
    public void test1(String name,int age){
        System.out.println("name="+name+";age="+age);
    }
    @DataProvider(name = "person")
    public Object[][] dataProvider(){
        Object[][] o = new Object[][]{
                {"zhangsan",30},
                {"lisi",40}
        };
        return o;
    }

    @Test(dataProvider = "newPerson",priority = 0)
    public void test2(String name,int age){
        System.out.println("test2方法的name="+name+";age="+age);
    }
    @Test(dataProvider = "newPerson",priority = 2)
    public void test3(String name,int age){
        System.out.println("test3方法的name="+name+";age="+age);
    }
    @DataProvider(name = "newPerson")
    public Object[][] newDataProvider(Method method){
        Object[][] result = null;
        if (method.getName().equals("test2")){
            result = new Object[][]{
                    {"zhangsan",30}
            };
        }else if (method.getName().equals("test3")) {
            result = new Object[][]{
                    {"lisi", 40}
            };
        }
        return result;
    }
}
