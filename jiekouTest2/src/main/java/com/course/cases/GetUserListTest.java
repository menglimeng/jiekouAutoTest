package com.course.cases;

import com.course.config.UserUrlConfig;
import com.course.model.GetuserListCase;
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
import java.util.List;

public class GetUserListTest {
    @Test(dependsOnGroups = "loginTrue",description = "查询用户信息列表")
    public void GetUserListCase() throws IOException {
        SqlSession sqlSession = DataBaseUtils.sqlSession();
        GetuserListCase getuserListCase = sqlSession.selectOne("getUserListCase",1);
        System.out.println(getuserListCase.toString());
        System.out.println(UserUrlConfig.getUserListUrl);
        //获取请求结果
        JSONArray resultJson = getJsonResult(getuserListCase);
        //结果校验
        List<User> userList = sqlSession.selectList(getuserListCase.getExpected(),getuserListCase);
        for (User u:userList){
            System.out.println("获取的user"+u.toString());
        }
        JSONArray userJsonList = new JSONArray(userList);
        Assert.assertEquals(userJsonList.length(),resultJson.length());
        for (int i = 0;i < resultJson.length();i++){
            JSONObject expect = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userJsonList.get(i);
            Assert.assertEquals(expect.toString(),actual.toString());
        }
    }

    private JSONArray getJsonResult(GetuserListCase getuserListCase) throws IOException {
        HttpPost httpPost = new HttpPost(UserUrlConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("userName",getuserListCase.getUsername());
        param.put("sex",getuserListCase.getSex());
        param.put("age",getuserListCase.getAge());
        httpPost.setHeader("context-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(entity);
        UserUrlConfig.httpClient.setCookieStore(UserUrlConfig.store);
        String result;
        HttpResponse response = UserUrlConfig.httpClient.execute(httpPost);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        JSONArray jsonArray = new JSONArray(result);
        return jsonArray;
    }
}
