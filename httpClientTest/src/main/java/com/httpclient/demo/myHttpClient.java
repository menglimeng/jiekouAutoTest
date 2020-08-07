package com.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class myHttpClient {
    @Test
    public void test1() throws IOException {
        String result;
        HttpGet get = new HttpGet("http://baidu.com");
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpReponse =client.execute(get);
        result = EntityUtils.toString(httpReponse.getEntity(),"utf-8");
        System.out.println(result);



    }
}
