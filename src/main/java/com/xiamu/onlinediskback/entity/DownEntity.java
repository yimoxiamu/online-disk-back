package com.xiamu.onlinediskback.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DownEntity {
    private int id;
    private int fileId;
    private int userId;
    private String time;
    private UserFileEntity userFileEntity;
    private UserEntity userEntity;

    public DownEntity() {
    }
}
