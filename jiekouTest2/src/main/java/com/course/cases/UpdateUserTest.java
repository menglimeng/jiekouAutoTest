package com.course.cases;

import com.course.config.UserUrlConfig;
import com.course.model.UpdateUserCase;
import com.course.utils.DataBaseUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserTest {
    @Test(dependsOnGroups = "loginTrue",description = "修改用户成功案例")
    public void UpadteUserTureCase() throws IOException {
        SqlSession sqlSession = DataBaseUtils.sqlSession();
        UpdateUserCase updateUserCase = sqlSession.selectOne("updateUserCase",1);
        System.out.println(updateUserCase.toString());
        System.out.println(UserUrlConfig.updateUrl);
    }
    @Test(dependsOnGroups = "loginTrue",description = "删除用户成功案例")
    public void DeleteUserCase() throws IOException {
        SqlSession sqlSession = DataBaseUtils.sqlSession();
        UpdateUserCase updateUserCase = sqlSession.selectOne("deleteUserCase",2);
        System.out.println(updateUserCase.toString());
        System.out.println(UserUrlConfig.updateUrl);
    }
}
