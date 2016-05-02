package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Contains ScreenView for a screen being played.
 * @author  Ata Deniz Aydin
 * @version 12/04/16
 */
public class ScreenPlayController extends JPanel implements ScreenController
{
    PlayableScreen screen;
    GamePlayer player;
    ScreenView view;

    public ScreenPlayController(GamePlayer player, PlayableScreen screen)
    {
        this.player = player;
        this.screen = screen;

        view = new ScreenView(this, screen);
        add(view);

        // setPreferredSize(new Dimension(504, 504));
        // view.setFocusable(true);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                view.setFocusable(true);
                view.requestFocusInWindow();
            }
        });
    }

    public GamePlayer getPlayer()
    {
        return player;
    }

    public void setScreen(PlayableScreen screen)
    {
        this.screen = screen;
        view.setScreen(screen);
    }
}
