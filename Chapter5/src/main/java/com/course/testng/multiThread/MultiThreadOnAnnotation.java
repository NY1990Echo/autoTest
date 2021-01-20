package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnAnnotation {
    //invocationCount是运行次数，threadPoolSizee是线程池大小
    @Test(invocationCount = 10,threadPoolSize = 3)
    public void test(){
        System.out.println(1);
        System.out.printf("Thread Id:%s%n",Thread.currentThread().getId());
    }
}
