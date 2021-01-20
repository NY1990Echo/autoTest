package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class NewMultiThreadOnXml {
    @Test
    public void test1(){
        System.out.printf("NewMultiThreadOnXml的test1方法执行的ThreadID:%s%n",Thread.currentThread().getId());
    }
    @Test
    public void test2(){
        System.out.printf("NewMultiThreadOnXml的test2方法执行的ThreadID:%s%n",Thread.currentThread().getId());
    }
    @Test
    public void test3(){
        System.out.printf("NewMultiThreadOnXml的test3方法执行的ThreadID:%s%n",Thread.currentThread().getId());
    }
}
