package com.course.cases;

import com.course.config.UserUrlConfig;
import com.course.model.AddUserCase;
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


public class AddUserTest {

    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口接口")
    public void addUserTureCase() throws IOException, InterruptedException {
        SqlSession sqlSession = DataBaseUtils.sqlSession();
        AddUserCase addUserCase = sqlSession.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(UserUrlConfig.addUserUrl);
        //获取请求，返回结果
        String result = getResult(addUserCase);
        //结果比对
        Thread.sleep(3000);
        User user = sqlSession.selectOne("addUser",addUserCase);
        System.out.println(user.toString());
        Assert.assertEquals(addUserCase.getExpected(),result);
    }

    private String getResult(AddUserCase addUserCase) throws IOException {
        //获取接口链接
        HttpPost httpPost =  new HttpPost(UserUrlConfig.addUserUrl);
        //写入参数
        JSONObject param = new JSONObject();
        param.put("userName",addUserCase.getUserName());
        param.put("password",addUserCase.getPassword());
        param.put("sex",addUserCase.getSex());
        param.put("age",addUserCase.getAge());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());
        //设置请求头信息
        httpPost.setHeader("content-type","application/json");
        //将参数写入请求信息中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        //设置cookies
        UserUrlConfig.httpClient.setCookieStore(UserUrlConfig.store);
        //存放返回结果
        String result;
        HttpResponse httpResponse = UserUrlConfig.httpClient.execute(httpPost);
        System.out.println(httpResponse);
        result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        return result;
    }
}
