package domain;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */
public class Data<T> {
    private String command;
    private String message;
    private T content;

    public Data() {
    }

    public Data(String command, String message, T content) {
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
        return "domain.Data{" +
                "command='" + command + '\'' +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
