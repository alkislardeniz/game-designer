package gui;

import gamemodel.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Created by fatma on 4/17/16.
 */
public class ButtonView extends ComponentView
{
    ScreenButton button;
    JButton jButton;

    public ButtonView(ScreenView parent, ScreenButton button, boolean editing)
    {
        super(parent, button, editing);
        this.button = button;

        // show button if not playing or visible
        if (button.getVisible() || parent.parent.getPlayer() == null)
        {
            jButton = new JButton(button.getText());
            jButton.addActionListener(new ButtonListener(parent.parent.getPlayer()));
            jButton.setEnabled(true);
            parent.add(jButton);
        }
    }

    // only update position
    public void paintComponentOn(Graphics g)
    {
        // jButton.repaint(); // is this necessary?
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
