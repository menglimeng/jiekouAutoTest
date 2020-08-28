package com.course.cases;

import com.course.config.UserUrlConfig;
import com.course.model.GetuserListCase;
import com.course.utils.DataBaseUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserListTest {
    @Test(dependsOnGroups = "login",description = "查询用户信息列表")
    public void GetUserListCase() throws IOException {
        SqlSession sqlSession = DataBaseUtils.sqlSession();
        GetuserListCase getuserListCase = sqlSession.selectOne("getUserListCase","1");
        System.out.println(getuserListCase.toString());
        System.out.println(UserUrlConfig.getUserListUrl);
    }
}
