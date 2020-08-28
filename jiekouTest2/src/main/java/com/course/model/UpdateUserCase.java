package com.course.model;

import lombok.Data;

@Data
public class UpdateUserCase {
    private int id;
    private int uesrid;
    private String userName;
    private int sex;
    private int age;
    private int permission;
    private int isDelete;
    private int expected;
}
