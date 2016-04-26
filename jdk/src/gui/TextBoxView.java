package gui;

import gamemodel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 4/18/16.
 */
public class TextBoxView extends ComponentView
{
    ScreenTextBox textBox;
    JTextField textField;

    public TextBoxView(ScreenView parent, ScreenTextBox textBox, boolean editing)
    {
        super(parent, textBox, editing);

        // add text field
        textField = new JTextField();
        textField.addActionListener(new TextBoxListener());

        textField.setBounds((int) textBox.getPosition().getX() * parent.IMAGE_WIDTH,
                            (int) textBox.getPosition().getY() * parent.IMAGE_HEIGHT,
                            textBox.getWidth()  * parent.IMAGE_WIDTH,
                            textBox.getHeight() * parent.IMAGE_HEIGHT);

        textField.setEditable(true);
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
