package com.course.testng;

import org.testng.annotations.Test;

/**
 * test2依赖test1,test1执行成功才会执行test2,test1执行失败test2则不会执行
 */
public class DependTest {
    @Test
    public void test1(){
        System.out.println("test1 run");
//        throw new RuntimeException();
    }
    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("test2 run");
    }
}
