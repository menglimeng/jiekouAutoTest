package com.example.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
public class GetCookieTest {
    /**
     * 获取cookies
     * @param response 装响应信息
     */
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    public void getCookies(HttpServletResponse response){
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
    }

    /**
     * 携带cookies信息访问get请求
     * @param request 装请求类信息
     * @return
     */
    @RequestMapping(value = "/getWithCookies",method = RequestMethod.GET)
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies =request.getCookies();
        if(Objects.isNull(cookies)){
            return "当前无cookies信息，访问失败";
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "访问成功";
            }
        }
        return null;
    }
}
