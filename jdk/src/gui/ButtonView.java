package gui;

import gamemodel.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Created by admin on 4/17/16.
 */
public class ButtonView extends ComponentView
{
    ScreenButton button;
    JButton jButton;

    public ButtonView(ScreenButton button, boolean editing)
    {
        super(button, editing);
        this.button = button;

        jButton = new JButton(button.getText());
        jButton.addActionListener(null);
    }

    // only update position
    public void paintComponentOn(Graphics g, ScreenView scr)
    {

    }

    private class ButtonListener implements ActionListener
    {
        GamePlayer player;

        public ButtonListener(GamePlayer player)
        {
            this.player = player;
        }

        public void actionPerformed(ActionEvent e)
        {
            button.clicked(player);
        }
    }
}
