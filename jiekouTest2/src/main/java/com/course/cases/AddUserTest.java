package com.course.cases;

import com.course.config.UserUrlConfig;
import com.course.model.AddUserCase;
import com.course.utils.DataBaseUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;
import java.io.IOException;


public class AddUserTest {

    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口接口")
    public void addUserTureCase() throws IOException {
        SqlSession sqlSession = DataBaseUtils.sqlSession();
        AddUserCase addUserCase = sqlSession.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(UserUrlConfig.addUserUrl);
    }
}
