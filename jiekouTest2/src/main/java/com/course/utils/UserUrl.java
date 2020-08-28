package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class UserUrl {
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);
    public static String getUrl(InterfaceName name){
        String address = bundle.getString("test.url");
        String uri = "";
        if(name == InterfaceName.addUserCase){
            uri = bundle.getString("addUserInfo.uri");
        }
        if (name == InterfaceName.getUserInfoCase){
            uri = bundle.getString("getUserListInfo.uri");
        }
        if(name == InterfaceName.updateUserCase){
            uri = bundle.getString("updateUserInfo.uri");
        }
        if(name == InterfaceName.getuserListCase){
            uri = bundle.getString("getUserListInfo.uri");
        }
        if(name == InterfaceName.loginCase){
            uri = bundle.getString("login.uri");
        }
        String testUrl;
        testUrl = address + uri;
        return testUrl;
    }
}
