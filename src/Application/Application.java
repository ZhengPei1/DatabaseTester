package Application;

import Controller.Controller;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        // use nimbus look and feel for the project
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // open up the controller
        new Controller();
    }
}
