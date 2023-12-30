package Controller;

import Daos.NewbieDaoImplementation;
import Daos.ProgrammerDaoImplementation;
import Model.UserInfo;
import Service.DatabaseConnection;

import Service.SQLFileReader;
import View.InitializationFrame;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;


public class Controller {

    private static DatabaseConnection databaseConnection;
    private static InitializationFrame initializationFrame = new InitializationFrame();
    private static MainFrame mainFrame = new MainFrame();
    private static NewbieDaoImplementation newbieDaoImplementation = new NewbieDaoImplementation();

    public Controller(){
        setUpInitializationFrame();
        setUpMainFrame();
        UserInfo.loadSettings(initializationFrame);
        initializationFrame.setVisible(true);
    }

    private void setUpInitializationFrame(){
        initializationFrame.confirmationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // establish connection
                String url = "jdbc:mysql://127.0.0.1:" + initializationFrame.portNumber.getText() + "/" + initializationFrame.databaseName.getText();
                String username = initializationFrame.username.getText();
                String password = initializationFrame.password.getText();

                databaseConnection = new DatabaseConnection(url, username, password);

                if(databaseConnection.establishConnection()){
                    // go to main frame
                    initializationFrame.setVisible(false);
                    mainFrame.setVisible(true);

                    // store settings
                    UserInfo.setConfirmRecord(initializationFrame.confirmStorage.isSelected());
                    UserInfo.recordSettings(initializationFrame);

                }
            }
        });
    }

    private void setUpMainFrame(){
        // run all button
        mainFrame.runAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rawText = mainFrame.scriptArea.getText();
                SQLFileReader sqlFileReader = new SQLFileReader(rawText);
                mainFrame.executionOutput.append("\n" + sqlFileReader.executeQuery(databaseConnection.getConnection()));
            }
        });

        // run selected button
        mainFrame.runSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rawText = mainFrame.scriptArea.getSelectedText();
                SQLFileReader sqlFileReader = new SQLFileReader(rawText);
                mainFrame.executionOutput.append("\n" + sqlFileReader.executeQuery(databaseConnection.getConnection()));
            }
        });

        // reselect database button
        mainFrame.reselectDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                initializationFrame.setVisible(true);
            }
        });

        // button for selecting script file directory
        mainFrame.directorySelectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                File file;

                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    mainFrame.fileTree.update(file);
                } else {
                    return;
                }

                try {
                    FileWriter fileWriter = new FileWriter("resources/lastSelectedDirectory.txt");
                    fileWriter.write(file.getAbsolutePath());
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        // button for confirmation of insert statement
        mainFrame.insertPanel.confirmationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    String[] columnNames = mainFrame.insertPanel.columnNames.getText().split(",");
                    String[] values = mainFrame.insertPanel.values.getText().split(",");

                    String result = newbieDaoImplementation.insert(mainFrame.insertPanel.tableName.getText(), columnNames, values, databaseConnection.getConnection());
                    mainFrame.executionOutput.append("\n" + result);

                }catch (Exception exception){
                    mainFrame.executionOutput.append("\n" + exception.getMessage());
                }
            }
        });
    }
}
