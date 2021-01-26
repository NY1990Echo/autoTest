package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
    private String url;
    private String getCookiesPath;
    private String getWithCookiesPath;
    private ResourceBundle bundle;
    //用来存储cookies信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        //读取配置文件，获取url和path
        bundle = ResourceBundle.getBundle("uri", Locale.CHINA);
        url = bundle.getString("test.url");
        getCookiesPath =bundle.getString("getCookies.uri");
        getWithCookiesPath = bundle.getString("getWithCookies.uri");
    }
    @Test
    public void testGetCookies() throws IOException {
        HttpGet get = new HttpGet(this.url+this.getCookiesPath);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("返回result是："+result);

        //获取cookies信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for(Cookie cookie:cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("返回的cookies信息为：cookie name="+name+",cookie value="+value);

        }

    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {
        HttpGet get = new HttpGet(this.url+this.getWithCookiesPath);
        DefaultHttpClient client = new DefaultHttpClient();
        //设置cookies信息
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(get);

        //获取响应的状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode="+statusCode);
        if (statusCode==200){
           String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }
    }

}