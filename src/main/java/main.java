import controller.GameController;
import controller.GetInput;
import service.Socketclient;
import service.SocketclientImpl;

/**
 * Created by jaeyoung on 2017. 4. 28..
 */
public class main {
    public static void main(String[] args) {
        new GameController(new SocketclientImpl(), new GetInput());    }
}
