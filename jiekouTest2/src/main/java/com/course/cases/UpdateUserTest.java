package com.course.cases;

import com.course.config.UserUrlConfig;
import com.course.model.UpdateUserCase;
import com.course.model.User;
import com.course.utils.DataBaseUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserTest {
    @Test(dependsOnGroups = "loginTrue",description = "修改用户成功案例")
    public void UpadteUserTureCase() throws IOException, InterruptedException {
        SqlSession sqlSession = DataBaseUtils.sqlSession();
        //Thread.sleep(10000);
        UpdateUserCase updateUserCase = sqlSession.selectOne("updateUserCase",1);
        System.out.println(updateUserCase.toString());
        System.out.println(UserUrlConfig.updateUrl);

        int result = getResult(updateUserCase);
        System.out.println(result);
        User user = sqlSession.selectOne("getUpdateUserInfo",updateUserCase);
        System.out.println(user);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }



    @Test(dependsOnGroups = "loginTrue",description = "删除用户成功案例")
    public void DeleteUserCase() throws IOException {
        SqlSession sqlSession = DataBaseUtils.sqlSession();
        UpdateUserCase updateUserCase = sqlSession.selectOne("deleteUserCase",2);
        System.out.println(updateUserCase.toString());
        System.out.println(UserUrlConfig.updateUrl);

        int result = getResult(updateUserCase);
        System.out.println(result);
        User user = sqlSession.selectOne("getUpdateUserInfo",updateUserCase);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }
    private int getResult(UpdateUserCase updateUserCase) throws IOException {

        HttpPost httpPost = new HttpPost(UserUrlConfig.updateUrl);
        JSONObject param = new JSONObject();
       /* param.put("id",updateUserCase.getId()+"");*/
        param.put("id",updateUserCase.getUserId()+"");
        param.put("username",updateUserCase.getUserName()+"");
        param.put("sex",updateUserCase.getSex()+"");
        param.put("age",updateUserCase.getAge()+"");
        param.put("permission",updateUserCase.getPermission()+"");
        param.put("isDelete",updateUserCase.getIsDelete()+"");
        httpPost.setHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        UserUrlConfig.httpClient.setCookieStore(UserUrlConfig.store);
        String result;
        HttpResponse response = UserUrlConfig.httpClient.execute(httpPost);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        return Integer.parseInt(result);
    }
}
