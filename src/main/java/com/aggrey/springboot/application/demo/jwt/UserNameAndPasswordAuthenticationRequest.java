package com.aggrey.springboot.application.demo.jwt;

public class UserNameAndPasswordAuthenticationRequest {

    private String username;
    private String password;

    public UserNameAndPasswordAuthenticationRequest(){
    }

    public UserNameAndPasswordAuthenticationRequest(String userName, String passWord) {
        this.username = userName;
        this.password = passWord;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
