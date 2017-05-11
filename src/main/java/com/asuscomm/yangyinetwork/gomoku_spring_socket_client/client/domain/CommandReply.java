package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.client.domain;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public class CommandReply<T> extends Command<T> {
    public CommandReply() {
        super();
    }

    public CommandReply(String command, String message, T content) {
        super(command, message, content);
    }

    @Override
    public String getCommand() {
        return super.getCommand();
    }

    @Override
    public void setCommand(String command) {
        super.setCommand(command);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void setMessage(String message) {
        super.setMessage(message);
    }

    @Override
    public T getContent() {
        return super.getContent();
    }

    @Override
    public void setContent(T content) {
        super.setContent(content);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
