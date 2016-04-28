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

            jButton.setBounds((int) button.getPosition().getX() * parent.IMAGE_WIDTH,
                              (int) button.getPosition().getY() * parent.IMAGE_HEIGHT,
                              button.getWidth()  * parent.IMAGE_WIDTH,
                              button.getHeight() * parent.IMAGE_HEIGHT);

            jButton.setVisible(true);
            jButton.setEnabled(true);
            parent.add(jButton);
        }
    }

    public void paintComponentOn(Graphics g)
    {
        return;
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
