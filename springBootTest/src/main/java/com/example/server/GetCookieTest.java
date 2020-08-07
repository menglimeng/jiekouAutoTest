package com.example.server;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
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

    /**
     * 携带参数的第一种写法  ip:port/url?value=param&value=param
     * @param start 数组起始位置
     * @param end 数组结束位置
     * @return
     */
    @RequestMapping(value = "/getWithParamTest1",method = RequestMethod.GET)
    public Map<String,Integer> getWithParam(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> paramList = new HashMap<>();
        paramList.put("苹果",5);
        paramList.put("香蕉",2);
        paramList.put("脆桃",10);
        return paramList;
    }

    /**
     * 携带get参数第二种实现方式 ip:port/url/{start}/{end}
     * @param start 数组起始位置
     * @param end 数组结束位置
     * @return
     */
    @RequestMapping(value ="/getWithParamTest2/{start}/{end}",method = RequestMethod.GET)
    public Map getWithParamTest2(@PathVariable Integer start,@PathVariable Integer end){
        Map<String,Integer> paramList = new HashMap<>();
        paramList.put("test1",1);
        paramList.put("test2",2);
        paramList.put("test3",3);
        return paramList;
    }
}
