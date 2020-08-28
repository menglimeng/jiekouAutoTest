package com.course.model;

import lombok.Data;

@Data
public class AddUserCase {
    private int id;
    private String userName;
    private int age;
    private int sex;
    private String password;
    private int permission;
    private int isDelete;
    private String expected;
}
