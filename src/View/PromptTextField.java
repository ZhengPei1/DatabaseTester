package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/*
Full credit to: https://stackoverflow.com/questions/11200585/adding-a-prompt-text-property-to-jtextfield
                user3033626
 */

public class PromptTextField extends JTextField {

    public PromptTextField(String promptText) {
        super(promptText);

        setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));

        addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if(getText().isEmpty()) {
                    setText(promptText);
                    setForeground(Color.GRAY);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if(getText().equals(promptText)) {
                    setForeground(Color.BLACK);
                    setText("");
                }
            }
        });

    }
}
