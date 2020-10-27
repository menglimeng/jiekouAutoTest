package com.course.cases;

import com.course.config.UserUrlConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import com.course.utils.DataBaseUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetUserInfoTest {
    @Test(dependsOnGroups = "loginTrue",description = "查询单个用户信息")
    public void GetUserInfoCase() throws IOException {
        SqlSession sqlSession = DataBaseUtils.sqlSession();
        GetUserInfoCase getUserInfoCase = sqlSession.selectOne("getUserInfoCase",1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(UserUrlConfig.getGetUserInfoUrl);
        //获取请求结果
        JSONArray resultJson = getJsonResult(getUserInfoCase);
        //结果比对
        User user = sqlSession.selectOne("getUserInfo",getUserInfoCase);
        List userList = new ArrayList();
        userList.add(user);
        JSONArray jsonArray = new JSONArray(userList);
        if(jsonArray.equals(resultJson)){
            Boolean result = true;
            Assert.assertEquals(getUserInfoCase.getExpected(),result);
        }

    }

    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {
        //获取请求连接
        HttpPost httpPost = new HttpPost(UserUrlConfig.getGetUserInfoUrl);
        //写入参数
        JSONObject param = new JSONObject();
        param.put("userId",getUserInfoCase.getUserId()+"");
        //写入头信息
        httpPost.setHeader("Content-Type","application/json");
        //参数放入请求
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        //获取cookies值
        UserUrlConfig.httpClient.setCookieStore(UserUrlConfig.store);
        //执行请求
        HttpResponse httpResponse = UserUrlConfig.httpClient.execute(httpPost);
        //获取结果
        String result;
        result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");

        JSONArray array = new JSONArray(result);

        return array;
    }
}
