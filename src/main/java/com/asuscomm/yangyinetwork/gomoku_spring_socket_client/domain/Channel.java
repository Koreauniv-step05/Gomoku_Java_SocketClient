package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.domain;

import java.util.ArrayList;
import java.util.List;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.domain.Channel.Status.NEEDS_MORE_USER;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.domain.Channel.Status.NEEDS_ONLY_OBSERVER;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public class Channel {
    List<User> userList;
    String id;

    public interface Status {
        String NEEDS_MORE_USER = "NEEDS_MORE_USER";
        String NEEDS_ONLY_OBSERVER = "NEEDS_ONLY_OBSERVER";
    }

    public Channel() {
    }

    public Channel(String id) {
        this.userList = new ArrayList<User>();
        this.id = id;
    }

    public Channel(List<User> userList, String id) {
        this.userList = userList;
        this.id = id;
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
        if(userList.size() >= 2) {
            return NEEDS_ONLY_OBSERVER;
        } else {
            return NEEDS_MORE_USER;
        }
    }
}


