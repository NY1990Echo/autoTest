package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/")
public class MyGetMethod {
//    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @GetMapping("/getCookies")
    @ApiOperation(value = "通过这个方法可以获取到cookies",httpMethod = "get")
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest 装请求信息的类
        //HttpServerletResponse 装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies成功";
    }
    /*
    要求客户端携带cookies访问
    这是一个携带cookies信息才能访问的get请求
     */
//    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @GetMapping("/get/with/cookies")
    @ApiOperation(value = "这是一个携带cookies信息才能访问的get请求",httpMethod = "get")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "你必须携带cookies信息来";
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login") &&
                    cookie.getValue().equals("true")){
                return "恭喜你访问成功！";
            }
        }
        return "这是一个携带cookies信息才能访问的get请求";
    }
    /*
    开发一个需要携带参数才能访问的get请求
    第一种实现方式：url：ip:port/key=value&key=value
    我们来模拟获取商品列表
     */
    @GetMapping("/getProductList")
    @ApiOperation(value = "这是一个需要携带查询参数才能访问的get请求",httpMethod = "get")
    public Map<String,Integer> getProductList(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> productList = new HashMap<>();
        productList.put("鞋",400);
        productList.put("干脆面",1);
        productList.put("衬衫",300);
        return productList;
    }
    /*
    第二种需要携带参数访问的get请求
    url：ip:port/getProductList/10/20
     */
    @GetMapping("/getProductListNew/{start}/{end}")
    @ApiOperation(value = "这是一个需要携带路径参数才能访问的get请求",httpMethod = "get")
    public Map<String,Integer>  getProductListNew(@PathVariable Integer start,@PathVariable Integer end){
        Map<String,Integer> productList = new HashMap<>();
        productList.put("鞋",400);
        productList.put("干脆面",1);
        productList.put("衬衫",300);
        return productList;
    }
}