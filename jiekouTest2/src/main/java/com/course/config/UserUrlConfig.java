package com.course.config;

import org.apache.http.impl.client.DefaultHttpClient;

import java.net.CookieStore;

public class UserUrlConfig {
    public static String loginUrl;
    public static String updateUrl;
    public static String getUserListUrl;
    public static String getGetUserInfoUrl;
    public static String addUserUrl;

    public static DefaultHttpClient httpClient;
    public static CookieStore store;
}
