package View;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class InitializationFrame extends JFrame{

    private Panel userInputPanel = new Panel();
    private JLabel prompt1 = new JLabel("database port number", JLabel.CENTER);
    private JLabel prompt2 = new JLabel("database name", JLabel.CENTER);
    private JLabel prompt3 = new JLabel("database username", JLabel.CENTER);
    private JLabel prompt4 = new JLabel("database password", JLabel.CENTER);
    private JLabel prompt5 = new JLabel("remember my info", JLabel.CENTER);
    private JLabel prompt6 = new JLabel("<html>contact zhengpei.pz@gmail.com if you have any suggestion <br> or encounters any " +
            "issues while using this program <html>", JLabel.CENTER);

    public JButton confirmationButton = new JButton("Continue");

    public PromptTextField portNumber = new PromptTextField("Input your MySQL port number");
    public PromptTextField username = new PromptTextField("Input your database user name, the default name is generally root");
    public PromptTextField password = new PromptTextField("Input the password of your database");
    public PromptTextField databaseName = new PromptTextField("Input the name of your database");

    public JCheckBox confirmStorage = new JCheckBox();

    private GridBagConstraints constraints = new GridBagConstraints();

    public InitializationFrame(){
        // initialize JFrame
        setTitle("DatabaseTester");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setContentPane(new JLabel(new ImageIcon("resources/background.jpg")));
        setLayout(new GridLayout(2,2));

        try {
            setIconImage(ImageIO.read(new File("resources/programIcon.png")));
        } catch (IOException e) {
            System.out.println("can not load icon");
        }

        // define panel layout
        userInputPanel.setLayout(new GridBagLayout()); // 4 x 5
        userInputPanel.setBackground( new Color(243, 224, 224) );

        // add place holders
        for(int i =0; i < 5; ++i){
            defineConstrains(0.25, 0.2, 3, i, 1, 1);
            userInputPanel.add(new JLabel(), constraints);
        }

        // define TextFields and prompts
        prompt1.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
        defineConstrains(0.25, 0.2, 0, 0, 1, 1);
        userInputPanel.add(prompt1, constraints);

        portNumber.setText("3306");
        defineConstrains(0.5, 0.2, 1, 0, 2, 1);
        userInputPanel.add(portNumber, constraints);

        prompt2.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
        defineConstrains(0.25, 0.2, 0, 1, 1, 1);
        userInputPanel.add(prompt2, constraints);

        defineConstrains(0.5, 0.2, 1, 1, 2, 1);
        databaseName.setForeground(Color.GRAY);
        userInputPanel.add(databaseName, constraints);

        prompt3.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
        defineConstrains(0.25, 0.2, 0, 2, 1, 1);
        userInputPanel.add(prompt3, constraints);

        username.setText("root");
        defineConstrains(0.5, 0.2, 1, 2, 2, 1);
        userInputPanel.add(username, constraints);

        prompt4.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
        defineConstrains(0.25, 0.2, 0, 3, 1, 1);
        userInputPanel.add(prompt4, constraints);

        defineConstrains(0.5, 0.2, 1, 3, 2, 1);
        password.setForeground(Color.GRAY);
        userInputPanel.add(password, constraints);

        prompt5.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
        defineConstrains(0.25, 0.25, 0, 4, 1, 1);
        userInputPanel.add(prompt5, constraints);

        defineConstrains(0.5, 0.25, 1, 4, 1, 1);
        userInputPanel.add(confirmStorage, constraints);

        defineConstrains(0.25, 0.25, 2, 4, 1, 1);
        confirmationButton.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
        userInputPanel.add(confirmationButton, constraints);

        prompt6.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));

        // add elements onto the frame
        add(new JLabel()); //placeholder
        add(userInputPanel);
        add(new JLabel()); //placeholder
        add(prompt6);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    private void defineConstrains(double weightx, double weighty, int gridx, int gridy, int gridwidth, int gridheight){
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        constraints.fill = GridBagConstraints.HORIZONTAL;
    }


}
