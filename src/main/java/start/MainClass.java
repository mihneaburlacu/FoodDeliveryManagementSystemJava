package start;

import businessLogic.Controller;
import presentation.AdministratorView;
import presentation.ClientView;
import presentation.LogView;

public class MainClass {
    public static void main(String[] args) {
        LogView logView = new LogView();
        ClientView clientView = new ClientView();
        AdministratorView administratorView = new AdministratorView();
        Controller controller = new Controller(logView, clientView, administratorView);
        logView.setVisible(true);
        clientView.setVisible(false);
        administratorView.setVisible(false);
    }


}
