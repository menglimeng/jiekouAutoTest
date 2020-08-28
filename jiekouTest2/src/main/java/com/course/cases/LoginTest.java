package com.course.cases;

import com.course.config.UserUrlConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.DataBaseUtils;
import com.course.utils.UserUrl;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    @BeforeTest(groups = "loginTrue",description = "测试准备工作,获取HttpClient对象")
    public void beforeTest(){
        UserUrlConfig.addUserUrl = UserUrl.getUrl(InterfaceName.addUserCase);
        UserUrlConfig.getGetUserInfoUrl = UserUrl.getUrl(InterfaceName.getuserListCase);
        UserUrlConfig.getUserListUrl = UserUrl.getUrl(InterfaceName.getuserListCase);
        UserUrlConfig.loginUrl = UserUrl.getUrl(InterfaceName.loginCase);
        UserUrlConfig.updateUrl = UserUrl.getUrl(InterfaceName.updateUserCase);
        UserUrlConfig.httpClient = new DefaultHttpClient();
    }
    @Test(groups = "loginTrue",description = "用户成功登陆接口")
    public void loginTrueCase() throws IOException {
        SqlSession sqlSession = DataBaseUtils.sqlSession();
        LoginCase loginCase = sqlSession.selectOne("LoginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(UserUrlConfig.loginUrl);
    }
    @Test(groups = "loginFalse",description = "登陆失败测试用例")
    public void loginFalseCase() throws IOException {
        SqlSession sqlSession = DataBaseUtils.sqlSession();
        LoginCase loginCase = sqlSession.selectOne("LoginCase",2);
        System.out.println(loginCase.toString());
        System.out.println(UserUrlConfig.loginUrl);
    }
}
