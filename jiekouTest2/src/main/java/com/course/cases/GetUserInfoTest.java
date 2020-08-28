package com.course.cases;

import com.course.config.UserUrlConfig;
import com.course.model.GetUserInfoCase;
import com.course.utils.DataBaseUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserInfoTest {
    @Test(dependsOnGroups = "loginTrue",description = "查询单个用户信息")
    public void GetUserInfoCase() throws IOException {
        SqlSession sqlSession = DataBaseUtils.sqlSession();
        GetUserInfoCase getUserInfoCase = sqlSession.selectOne("getUserInfoCase",1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(UserUrlConfig.getGetUserInfoUrl);
    }
}
