package com.course.controller;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Log4j2
@RestController
@Api(value = "v1",description = "用户管理测试")
public class UserController {
    @Autowired
    private SqlSessionTemplate template;
    @ApiOperation(value = "登录接口",httpMethod = "POST")
    @RequestMapping(value = "/v1/login",method = RequestMethod.POST)
    public boolean login(HttpServletResponse servletResponse , @RequestBody User user){
        int i = template.selectOne("login",user);
        Cookie cookie = new Cookie("login","true");
        servletResponse.addCookie(cookie);
        log.info("查询到的结果是"+i);
        if(i==1){
            log.info("登录的用户是"+user.getUsername());
            return true;
        }
        return false;
    }
    @ApiOperation(value = "新增用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/v1/addUser",method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest servletRequest,@RequestBody User user){
        boolean x = verifyCookies(servletRequest);
        int result = 0;
        if(Objects.nonNull(x)){
            result = template.insert("addUser",user);
        }
        if(result == 0){
            log.info("增加失败");
            return false;
        }
        return true;
    }
    @ApiOperation(value = "获取用户信息列表",httpMethod = "POST")
    @RequestMapping(value = "/v1/getUserList",method = RequestMethod.POST)
    public List<User> getUserListInfo(HttpServletRequest servletRequest,@RequestBody User user){
        boolean x = verifyCookies(servletRequest);
        if(x==true){
            List<User> users = template.selectList("getUserList",user);
            log.info("获取到的用户数是"+users.size());
            return users;
        }else {
            return null;
        }
    }
    @ApiOperation(value = "更新用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/v1/updateUser",method = RequestMethod.POST)
    public int updateUser(HttpServletRequest servletRequest,@RequestBody User user){
        boolean x = verifyCookies(servletRequest);
        int i = 0;
        if(x==true){
            i = template.update("updateUser",user);
        }
        log.info("更新数据条数为:"+i);
        return i;

    }

    private boolean verifyCookies(HttpServletRequest servletRequest) {
        Cookie[] cookies = servletRequest.getCookies();
        if(Objects.isNull(cookies)){
            log.info("cookie为空");
            return false;
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                log.info("cookie验证通过");
                return true;
            }
        }
        return false;
    }
}
