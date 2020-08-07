package com.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class httpClientCookiesForPost {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;
    @BeforeTest
    public void baseURL(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }
    @Test
    public void getCookies() throws IOException {
        String getCookiesUri = bundle.getString("getCookies.uri");
        String getUrl = this.url + getCookiesUri;
        HttpGet get = new HttpGet(getUrl);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(get);
        String result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(result);
        this.store =  httpClient.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for(Cookie cookie:cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("name="+name+",value="+value);
        }
    }
    @Test(dependsOnMethods = {"getCookies"})
    public void postWithCookies() throws IOException {
        String postCookiesUri = bundle.getString("postWithCookies.uri");
        String postUrl = this.url + postCookiesUri;
        //声明一个client对象
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //声明一个post方法
        HttpPost httpPost = new HttpPost(postUrl);
        //添加参数
        JSONObject param = new JSONObject();
        param.put("username","zhangsan");
        param.put("age","18");
        //设置请求头信息，设置header
        httpPost.setHeader("content-type","application/json");
        //将参数添加到方法中
        StringEntity stringEntity = new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(stringEntity);
        //设置cookies信息
        httpClient.setCookieStore(this.store);
        //执行post方法
        HttpResponse response = httpClient.execute(httpPost);
        //获取响应结果
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //处理获取结果
        JSONObject resultJson = new JSONObject(result);
        String success = (String)resultJson.get("zhangsan");
        String status = (String)resultJson.get("status");
        System.out.println(success);
        System.out.println(status);
        Assert.assertEquals("success",success);
        Assert.assertEquals("1",status);
    }
}
