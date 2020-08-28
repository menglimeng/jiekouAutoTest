package com.course.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private int age;
    private int sex;
    private String password;
    private int permission;
    private int isDelete;
}
