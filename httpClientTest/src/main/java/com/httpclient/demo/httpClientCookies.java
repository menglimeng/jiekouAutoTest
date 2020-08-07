package com.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class httpClientCookies {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;
    @BeforeTest
    public void clientCookies(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }
    @Test
    public  void getClient() throws IOException {
        String result;
        String uri = bundle.getString("getCookies.uri");
        String lastUrl = this.url+uri;
        HttpGet get = new HttpGet(lastUrl);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(get);
        result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(result);
    }
    @Test
    public void getClientCookies() throws IOException {
        String result;
        String uri = bundle.getString("getCookies.uri");
        String lastUrl = this.url+uri;
        HttpGet get = new HttpGet(lastUrl);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(get);
        result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println(result);

         this.store = httpClient.getCookieStore();
        List<Cookie> cookies = store.getCookies();
        for(Cookie cookie:cookies){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookies的name="+name+" ;value="+value);
        }
    }
    @Test(dependsOnMethods = {"getClientCookies"})
    public void  testGetWithCookies() throws IOException {
        String uri = bundle.getString("getWithCookies.uri");
        String testurl = this.url+uri;
        System.out.println(testurl);
        HttpGet get = new HttpGet(testurl);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.setCookieStore(this.store);
        HttpResponse httpResponse = httpClient.execute(get);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        if(statusCode == 200){
            String result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            System.out.println(result);
        }else{
            System.out.println("访问get请求失败");
        }
    }
}
