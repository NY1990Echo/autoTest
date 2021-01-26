package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private String getCookiesPath;
    private String postWithCookiesPath;
    private ResourceBundle bundle;
    //用来存储cookies信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("uri", Locale.CHINA);
        url = bundle.getString("test.url");
        getCookiesPath = bundle.getString("getCookies.uri");
        postWithCookiesPath = bundle.getString("postWithCookiesPath.uri");
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
    public void testPostMethod() throws IOException {
        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(this.url+this.postWithCookiesPath);
        //声明一个Client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //添加参数
        JSONObject param = new JSONObject();
        param.put("name","niyang");
        param.put("age","30");
        //设置请求头信息,设置Header
        post.setHeader("Content-Type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置Cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //将返回的响应结果字符串转化为json对象
        JSONObject resultJson = new JSONObject(result);

        //获取到结果值
        String success = (String)resultJson.get("message");
        String status = (String)resultJson.get("status");
        //具体判断返回结果的值
        Assert.assertEquals("success",success);
        Assert.assertEquals("1",status);

    }
}
