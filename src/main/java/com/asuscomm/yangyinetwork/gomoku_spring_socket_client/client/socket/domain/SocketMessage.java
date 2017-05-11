package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.socket.domain;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public class SocketMessage<T> {
    private String command;
    private String message;
    private T content;

    public SocketMessage() {
    }

    public SocketMessage(String command, String message, T content) {
        this.command = command;
        this.message = message;
        this.content = content;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SocketMessage{" +
                "command='" + command + '\'' +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
