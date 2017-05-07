package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public class Channel {
    List<User> userList;
    String id;
    String status;

    public interface Status {
        String NEEDS_MORE_USER = "NEEDS_MORE_USER";
        String NEEDS_ONLY_OBSERVER = "NEEDS_ONLY_OBSERVER";
        String ALREADY_STARTED = "ALREADY_STARTED";
    }

    public Channel() {
    }

    public Channel(String id, String status) {
        this.userList = new ArrayList<User>();
        this.id = id;
        this.status = status;
    }

    public Channel(List<User> userList, String id, String status) {
        this.userList = userList;
        this.id = id;
        this.status = status;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

