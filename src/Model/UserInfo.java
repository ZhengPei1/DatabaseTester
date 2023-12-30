package Model;

import View.InitializationFrame;
import View.MainFrame;

import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class UserInfo {
    private static String portNumber = "";
    private static String databaseName = "";
    private static String databaseUsername = "";
    private static String password = "";
    private static boolean confirmRecord = false;

    //
    public static void loadSettings(InitializationFrame initializationFrame){
        try{
            File settingFile = new File("resources/userinfo.txt");
            Scanner scanner = new Scanner(settingFile);

            StringBuilder settingText = new StringBuilder();
            while(scanner.hasNextLine()){
                settingText.append(scanner.next());
            }
            scanner.close();

            //
            String[] settings = settingText.toString().split(";");
            if(settings.length < 5) return;
            else{
                if(Objects.equals(settings[4], "True")){
                    setPortNumber(settings[0]);
                    initializationFrame.portNumber.setText(portNumber);

                    setDatabaseName(settings[1]);
                    initializationFrame.databaseName.setText(databaseName);
                    initializationFrame.databaseName.setForeground(Color.black);

                    setDatabaseUsername(settings[2]);
                    initializationFrame.username.setText(databaseUsername);

                    setPassword(settings[3]);
                    initializationFrame.password.setText(password);
                    initializationFrame.password.setForeground(Color.black);
                    
                    initializationFrame.confirmStorage.setSelected(true);

                    setConfirmRecord(true);
                }
            }
        }catch (FileNotFoundException e){
            File settingFile = new File("resources/userinfo.txt");
            try {
                settingFile.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return;
        }


    }

    //
    public static void recordSettings(InitializationFrame initializationFrame){

        StringBuilder settingText = new StringBuilder();

        setPortNumber(initializationFrame.portNumber.getText());
        setDatabaseName(initializationFrame.databaseName.getText());
        setDatabaseUsername(initializationFrame.username.getText());
        setPassword(initializationFrame.password.getText());
        // decide what to record
        if(confirmRecord){
            settingText.append(portNumber).append(";").append(databaseName).append(";").append(databaseUsername).append(";").
                    append(password).append(";").append("True").append(";");
        }else{
            settingText.append(";;;;").append("False").append(";");
        }

        try{
            FileWriter writer = new FileWriter("resources/userinfo.txt");
            writer.write(settingText.toString());
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

    }

    // getters and setters
    public static String getPortNumber() {
        return portNumber;
    }

    public static void setPortNumber(String portNumber) {
        UserInfo.portNumber = portNumber.trim();
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static void setDatabaseName(String databaseName) {
        UserInfo.databaseName = databaseName.trim();
    }

    public static String getDatabaseUsername() {
        return databaseUsername;
    }

    public static void setDatabaseUsername(String databaseUsername) {
        UserInfo.databaseUsername = databaseUsername.trim();
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserInfo.password = password.trim();
    }


    public static boolean isConfirmRecord() {
        return confirmRecord;
    }

    public static void setConfirmRecord(boolean confirmRecord) {
        UserInfo.confirmRecord = confirmRecord;
    }
}
