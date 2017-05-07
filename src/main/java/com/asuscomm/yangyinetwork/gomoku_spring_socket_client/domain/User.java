package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.domain;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public class User  {
    String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}