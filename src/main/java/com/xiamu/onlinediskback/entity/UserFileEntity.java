package com.xiamu.onlinediskback.entity;

import lombok.Data;

@Data
public class UserFileEntity {
    private int id;
    private String fileName;
    private String type;
    private String size;
    private UserEntity userEntity;

    public UserFileEntity() {
    }
}
