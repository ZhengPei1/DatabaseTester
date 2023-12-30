package View;

import View.SubPanels.DeletePanel;
import View.SubPanels.FilterPanel;
import View.SubPanels.InsertPanel;
import View.SubPanels.ModifyPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class MainFrame extends JFrame {

	// script file section
	public FileTree fileTree = new FileTree();

    // script file section
    private JPanel scriptFilePanel = new JPanel();
    public JButton reselectDatabaseButton = new JButton("Reselect Database");
    public JButton settingButton = new JButton("Settings");
    public JButton directorySelectionButton = new JButton("Script File directory");
    public JButton saveButton = new JButton("Save Script");
    public JButton createScript = new JButton("Create Script");
    public JButton exportScript = new JButton("Export Script");


    //
    private JPanel advancedPanel = new JPanel();
    private JLabel advancedPrompt = new JLabel("programmer section", JLabel.CENTER);
    public JButton runAllButton = new JButton("Run All");
    public JButton runSelectedButton = new JButton("Run Selected");
    public JTextArea scriptArea = new JTextArea();


    //
    private JPanel newbiePanel = new JPanel();
    private JLabel newbiePrompt = new JLabel("non-programmer section", JLabel.CENTER);
    private JTabbedPane tabbedPane = new JTabbedPane();
    public InsertPanel insertPanel = new InsertPanel();
    public DeletePanel deletePanel = new DeletePanel();
    public ModifyPanel modifyPanel = new ModifyPanel();
    public FilterPanel filterPanel = new FilterPanel();
    public JTextArea executionOutput = new JTextArea();

            
    private GridBagConstraints constraints = new GridBagConstraints();
    public MainFrame(){
        // initialize JFrame
        setTitle("DatabaseTester");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setContentPane(new JLabel(new ImageIcon("resources/background.jpg")));
        setLayout(new GridBagLayout()); // 5 x 1

        try {
            setIconImage(ImageIO.read(new File("resources/programIcon.png")));
        } catch (IOException e) {
            System.out.println("can not load icon");
        }

        // initialize different sections
        initializeScriptFileSection();
        defineConstrains(0.2, 1, 0, 0, 1, 1);
        add(scriptFilePanel, constraints);

        //
        initializeAdvancedSection();
        defineConstrains(0.4, 1, 1, 0, 2, 1);
        add(advancedPanel, constraints);

        //
        initializeNewbieSection();
        defineConstrains(0.4, 1, 3, 0, 2, 1);
        add(newbiePanel, constraints);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //
    private void initializeScriptFileSection(){
        scriptFilePanel.setLayout(new GridBagLayout()); // 2 x 10

        // add components related to script file
        reselectDatabaseButton.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
        defineConstrains(0.5, 0.1, 0, 0, 1, 1);
        scriptFilePanel.add(reselectDatabaseButton, constraints);

        settingButton.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
        defineConstrains(0.5, 0.1, 1, 0, 1, 1);
        scriptFilePanel.add(settingButton, constraints);

        defineConstrains(1, 0.7, 0, 1, 2, 5);
        JScrollPane scrollPane = new JScrollPane(fileTree);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        try {
            Scanner fileReader = new Scanner(new File("resources/lastSelectedDirectory.txt"));
            String path;
            if(fileReader.hasNextLine()){
                path = fileReader.nextLine();
                fileTree.update(new File(path));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can not find resources/lastSelectedDirectory.txt");
        }
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scriptFilePanel.add(scrollPane, constraints);

        defineConstrains(0.5, 0.1, 0, 7, 1, 1);
        directorySelectionButton.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
        scriptFilePanel.add(directorySelectionButton, constraints);

        defineConstrains(0.5, 0.1, 1, 7, 1, 1);
        saveButton.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
        scriptFilePanel.add(saveButton, constraints);

        defineConstrains(0.5, 0.1, 0, 8, 1, 1);
        createScript.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
        scriptFilePanel.add(createScript, constraints);

        defineConstrains(0.5, 0.1, 1, 8, 1, 1);
        exportScript.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
        scriptFilePanel.add(exportScript, constraints);
    }

    //
    private void initializeAdvancedSection(){
        advancedPanel.setLayout(new GridBagLayout()); //9 x 9

        defineConstrains(1, 0.125, 0, 0, 9, 1);
        advancedPrompt.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 26));
        advancedPanel.add(advancedPrompt, constraints);

        defineConstrains(0.125, 0.4375, 0, 1, 1, 4);
        runAllButton.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
        advancedPanel.add(runAllButton, constraints);

        defineConstrains(0.125, 0.4375, 0, 5, 1, 4);
        runSelectedButton.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
        advancedPanel.add(runSelectedButton, constraints);

        defineConstrains(0.875, 0.875, 1, 1, 8, 8);
        scriptArea.setFont(new Font("Serif", Font.BOLD , 16));
        JScrollPane scrollPane = new JScrollPane(scriptArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        advancedPanel.add(scrollPane, constraints);
    }

    //
    private void initializeNewbieSection(){
        newbiePanel.setLayout(new GridBagLayout()); // 1 x 8

        defineConstrains(1, 0.125, 0, 0, 1, 1);
        newbiePrompt.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 26));
        newbiePanel.add(newbiePrompt, constraints);

        tabbedPane.addTab("Insert Data", insertPanel);
        tabbedPane.addTab("Modify Data", new JPanel());
        tabbedPane.addTab("Delete Data", new JPanel());
        tabbedPane.addTab("Filter/Request", new JPanel());

        defineConstrains(1, 0.5, 0, 1, 1, 4);
        newbiePanel.add(tabbedPane, constraints);

        defineConstrains(1, 0.375, 0, 5, 1, 2);
        executionOutput.setFont(new Font("Serif", Font.BOLD , 16));
        executionOutput.setEditable(false);
        executionOutput.setBackground(Color.gray);
        JScrollPane scrollPane = new JScrollPane(executionOutput);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        newbiePanel.add(scrollPane, constraints);

    }


    private void defineConstrains(double weightx, double weighty, int gridx, int gridy, int gridwidth, int gridheight){
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        constraints.fill = GridBagConstraints.BOTH;
    }
}
