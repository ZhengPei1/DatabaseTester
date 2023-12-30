package View.SubPanels;

import View.PromptTextField;

import javax.swing.*;
import java.awt.*;

public class InsertPanel extends JPanel {

    public JButton confirmationButton = new JButton("Confirm");

    private JLabel prompt1 = new JLabel("Table Name ", JLabel.CENTER);
    private JLabel prompt2 = new JLabel("Column Names: ", JLabel.CENTER);
    private JLabel prompt3 = new JLabel("Values: ", JLabel.CENTER);

    public PromptTextField tableName = new PromptTextField("Input table name");
    public PromptTextField columnNames = new PromptTextField("Input column names, use ',' as separator");
    public PromptTextField values = new PromptTextField("Input values, use ',' as separator");

    public InsertPanel() {
        super();
        setLayout(new GridLayout(4, 2));

        // customize components
        prompt1.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 26));
        prompt2.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 26));
        prompt3.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 26));

        tableName.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
        tableName.setForeground(Color.gray);
        columnNames.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
        columnNames.setForeground(Color.gray);
        values.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
        values.setForeground(Color.gray);
        confirmationButton.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));

        // add components
        add(prompt1);
        add(tableName);
        add(prompt2);
        add(columnNames);
        add(prompt3);
        add(values);
        add(confirmationButton);

    }
}
