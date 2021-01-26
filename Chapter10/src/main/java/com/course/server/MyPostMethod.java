package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/")
@RequestMapping("/v1")
public class MyPostMethod {
    //这个变量用来装cookies信息
    private static Cookie cookie;
    @PostMapping("/login")
    @ApiOperation(value = "登陆接口，成功后获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String username,
                        @RequestParam(value = "passWord",required = true) String password){
        if (username.equals("niyang") && password.equals("123456")){
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登陆成功！";
        }
        return "用户名或密码错误！";
    }
    @PostMapping("/getUserList")
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User user){
        User user1 = new User();
        //获取cookies
        Cookie[] cookies = request.getCookies();
        //验证cookies是否合法
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login")
                    && cookie.getValue().equals("true")
                    && user.getUsername().equals("niyang")
                    && user.getPassword().equals("123456")){
                user1.setName("lisi");
                user1.setAge(19);
                user1.setSex("男");
                return user1.toString();
            }
        }
        return "参数不合法";
    }
}
