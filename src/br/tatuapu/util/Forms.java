package br.tatuapu.util;

import java.awt.*;
import javax.swing.JTextField;

/**
 *
 * @author tatuapu
 */
public class Forms {
    
    /**
     * to clear all textFields(JTextField) of an container
     * to use, use getContentPane(). Ex.: Forms.clearTextFields(getContentPane());
     * @param container 
     */
    public static void clearTextFields(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField) {
                JTextField f = (JTextField) c;
                f.setText("");
            } else if (c instanceof Container) {
                clearTextFields((Container) c);
            }
        }
    }
}
