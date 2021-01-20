package com.course.testng;

import org.testng.annotations.Test;

public class TimeOutTest {
    @Test(timeOut = 3000) //单位是毫秒值
    public void testTimeOutSuccess() throws InterruptedException{
        Thread.sleep(2000);
    }
}
