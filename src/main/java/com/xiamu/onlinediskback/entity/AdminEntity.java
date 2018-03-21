package com.xiamu.onlinediskback.entity;

/**
 * 管理员实体
 * by fuyb
 */
public class AdminEntity {
    private String admin_name;
    private String admin_pass;

    public AdminEntity() {
    }

    @Override
    public String toString() {
        return "AdminEntity{" +
                "admin_name='" + admin_name + '\'' +
                ", admin_pass='" + admin_pass + '\'' +
                '}';
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_pass() {
        return admin_pass;
    }

    public void setAdmin_pass(String admin_pass) {
        this.admin_pass = admin_pass;
    }
}
