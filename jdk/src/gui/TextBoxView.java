package gui;

import gamemodel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a ScreenTextBox graphically.
 * @author  Ata Deniz Aydin
 * @version 22/04/16
 */
public class TextBoxView extends ComponentView
{
    ScreenTextBox textBox;
    JTextField textField;

    public TextBoxView(ScreenView parent, ScreenTextBox textBox, boolean editing)
    {
        super(parent, textBox, editing);

        this.textBox = textBox;

        // add text field
        textField = new JTextField();
        textField.addActionListener(new TextBoxListener());

        textField.setBounds((int) textBox.getPosition().getX() * parent.IMAGE_WIDTH,
                            (int) textBox.getPosition().getY() * parent.IMAGE_HEIGHT,
                            textBox.getWidth()  * parent.IMAGE_WIDTH,
                            textBox.getHeight() * parent.IMAGE_HEIGHT);

        textField.setEditable(true);
        parent.add(textField);
    }

    // draw component on screen
    public void paintComponentOn(Graphics g)
    {
        return;
    }

    private class TextBoxListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // setText() automatically ignores erroneous input
            textBox.setText(textField.getText());
        }
    }
}
