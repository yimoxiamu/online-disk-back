package com.xiamu.onlinediskback.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserEntity {
    private int id;
    private String name;
    private String pass;
    private String email;
    private int staus;
    private String size;
    private String use;
    private String vip;
    private String pic;
    private String msg;
}
