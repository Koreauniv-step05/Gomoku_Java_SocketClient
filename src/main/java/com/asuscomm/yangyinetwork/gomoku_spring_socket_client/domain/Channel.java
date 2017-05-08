package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.domain;

import java.util.ArrayList;
import java.util.List;

import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.domain.Channel.Status.NEEDS_MORE_USER;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.domain.Channel.Status.NEEDS_ONLY_OBSERVER;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.consts.GAME_BOARD.BLACK_STONE;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.consts.GAME_BOARD.NONE_STONE;
import static com.asuscomm.yangyinetwork.gomoku_spring_socket_client.game.consts.GAME_BOARD.WHITE_STONE;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public class Channel {
    List<User> userList;
    User lastUser;
    String id;

    public interface Status {
        String NEEDS_MORE_USER = "NEEDS_MORE_USER";
        String NEEDS_ONLY_OBSERVER = "NEEDS_ONLY_OBSERVER";
        String ALREADY_STARTED = "ALREADY_STARTED";
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

    public void addUser(User user) {
        this.userList.add(user);
        this.lastUser = user;
    }
    public void addUser() {
        this.addUser(new User(newStoneType()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String status() {
        if(userList.size() >= 2) {
            return NEEDS_ONLY_OBSERVER;
        } else {
            return NEEDS_MORE_USER;
        }
    }

    public int newStoneType() {
        int target = BLACK_STONE;
        for (User user:
                userList) {
            if(user.getStoneType() == target) {
                target = nextTarget(target);
            }
        }
        return target;
    }

    private int nextTarget(int target) {
        if ( target == BLACK_STONE ) {
            return WHITE_STONE;
        }
        return NONE_STONE;
    }

    public User getLastUser() {
        return lastUser;
    }

    public void setLastUser(User lastUser) {
        this.lastUser = lastUser;
    }
}
