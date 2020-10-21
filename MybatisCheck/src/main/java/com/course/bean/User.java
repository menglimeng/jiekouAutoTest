package com.course.bean;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String age;
    private String sex;
    private String password;
    private String permission;
    private String isDelete;
}
