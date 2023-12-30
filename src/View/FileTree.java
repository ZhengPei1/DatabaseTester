package View;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;

// File tree for script files
public class FileTree extends JPanel {

    private JTree tree;

    public FileTree(){

    }


    // using recursion to find root node and all the sub nodes
    private DefaultMutableTreeNode findNode(DefaultMutableTreeNode parentNode, File directory){

        File[] allFiles = directory.listFiles();
        for(File file: allFiles){

            // if the file is a non-empty directory, perform recursion based on this directory
            if(file.isDirectory() && file.listFiles() != null){
                parentNode.add(findNode(new DefaultMutableTreeNode(file.getName()), file));
            }
            else{
                parentNode.add(new DefaultMutableTreeNode(file.getName()));
            }

        }
        return parentNode;

    }

    // update method - called when a new file tree is created
    public void update(File rootDirectory){

        if(tree == null){
            removeAll();

            // set up the layout and tree
            setLayout(new GridLayout(1, 1));
            tree = new JTree(findNode(new DefaultMutableTreeNode(rootDirectory.getAbsolutePath()), rootDirectory));
            tree.setRowHeight(30);

            // expand the tree if it contains less than 5 files
            if(tree.getRowCount()<=5){
                // expand all the nodes
                for (int i = 0; i < tree.getRowCount(); i++)
                    tree.expandRow(i);
            }

            add(tree);

            revalidate();
            repaint();
        }else{
            // update the tree by reestablishing the root node
            DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
            model.setRoot(findNode(new DefaultMutableTreeNode(rootDirectory.getAbsolutePath()), rootDirectory));

            // expand all the nodes
            for (int i = 0; i < tree.getRowCount(); i++)
                tree.expandRow(i);
        }

    }

    // getters and setters
    public JTree getTree() {
        return tree;
    }

    public void setTree(JTree tree) {
        this.tree = tree;
    }
}









