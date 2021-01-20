package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXml {
    @Test
    public void test1(){
        System.out.printf("MultiThreadOnXml的test1方法运行Thread id:%s%n",Thread.currentThread().getId());
    }
    @Test
    public void test2(){
        System.out.printf("MultiThreadOnXml的test2方法运行Thread id:%s%n",Thread.currentThread().getId());
    }
    @Test
    public void test3(){
        System.out.printf("MultiThreadOnXml的test3方法运行Thread id:%s%n",Thread.currentThread().getId());
    }
}
