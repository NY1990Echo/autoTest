package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethods {
    @Test(groups = "coupon",enabled = false)
    public void method1(){
        System.out.println("这是coupon组的method1方法");
    }
    @Test(groups = "coupon")
    public void method2(){
        System.out.println("这是coupon组的method2方法");
    }
    @Test(groups = "activity")
    public void method3(){
        System.out.println("这是activity组的method3方法");
    }
    @Test(groups = "activity")
    public void method4(){
        System.out.println("这是activity组的method4方法");
    }
    @BeforeGroups("coupon")
    public void beforeGroupsOnCoupon(){
        System.out.println("coupon组方法运行之前执行beforeGroups");
    }
    @AfterGroups("coupon")
    public void afterGroupsOnCoupon(){
        System.out.println("coupon组方法运行之后执行afterGroups");
    }
    @BeforeGroups("activity")
    public void beforeGroupsOnActivity(){
        System.out.println("activity组方法运行之前执行beforeGroups");
    }
    @AfterGroups("activity")
    public void afterGroupsOnActivity(){
        System.out.println("activity组方法运行之后执行afterGroups");
    }
}
