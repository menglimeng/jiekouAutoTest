package com.example.server;

import com.example.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(description = "这是全部的post接口")
public class PostWithCookiesTest {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "携带cookies的登录接口",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username",required = true) String username,
                        @RequestParam(value = "password",required = true) String password){
        if(username.equals("test")&&password.equals("123456")){
            Cookie cookie = new Cookie("loginTest","true");
            response.addCookie(cookie);
            return "恭喜你登录成功";
        }
        return "登录失败";
    }
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "返回用户信息列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request, @RequestBody User user){
        Cookie[] cookie = request.getCookies();
        for(Cookie c : cookie){
            if(c.getName().equals("loginTest")&&c.getValue().equals("true")){
                User user1 = new User();
                user1.setName("wangwu");
                user1.setAge(18);
                user1.setSex("man");
                return user.toString();
            }
            return "参数错误";
        }
        return null;
    }

}
