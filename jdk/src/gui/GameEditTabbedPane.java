package gui;

import gamemodel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by sev on 26.04.2016.
 */
public class GameEditTabbedPane extends JPanel
{
    JTabbedPane pane;
    Game game;

    public GameEditTabbedPane(GameEditController controller)
    {
        game = controller.game;
        pane = new JTabbedPane();

        setPreferredSize(new Dimension(825, 550));

        // add game view to pane
        pane.addTab("Game", controller.gameView);

        pane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        add(pane);
    }

    protected void addScreen(PlayableScreen screen)
    {
        // if there isn't already a tab for screen
        if (pane.indexOfTab(screen.getName()) == -1)
        {
            pane.addTab(screen.getName(), new ScreenEditController(screen));
            pane.setTabComponentAt(pane.indexOfTab(screen.getName()), new ButtonTabComponent(pane));
        }

        pane.setSelectedIndex(pane.indexOfTab(screen.getName()));
    }
}



