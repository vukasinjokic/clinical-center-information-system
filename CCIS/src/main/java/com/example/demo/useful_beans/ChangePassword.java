package com.example.demo.useful_beans;

public class ChangePassword {

    private String id;
    private String old;
    private String newPass;


    public ChangePassword(){}

    public ChangePassword(String old, String new_pass) {
        this.old = old;
        this.newPass = new_pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNew_pass() {
        return newPass;
    }

    public void setNew_pass(String new_pass) {
        this.newPass = new_pass;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }
}
