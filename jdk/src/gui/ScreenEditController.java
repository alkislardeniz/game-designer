package gui;

import javax.swing.*;
import gamemodel.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel containing ScreenView that allows to edit an individual screen.
 * Created by Ata Deniz Aydin on 4/12/16.
 */
public class ScreenEditController extends JPanel implements ScreenController
{
    Game game;
    PlayableScreen screen;
    ScreenView screenView;
    EditScrollPaneRight scrollPaneRight;
    EditScrollPaneLeft scrollPaneLeft;
    EditScreenOptions screenOptions;
    ComponentListener listener;

    public ScreenEditController(PlayableScreen screen)
    {
        this.screen = screen;
        game = screen.getParent();

        listener = new ComponentListener();

        screenView = new ScreenView(this, screen);
        add(screenView);

        scrollPaneRight = new EditScrollPaneRight(this);
        scrollPaneLeft = new EditScrollPaneLeft(this);
        screenOptions = new EditScreenOptions(this);

        setLayout(new BorderLayout());

        add(screenView, BorderLayout.CENTER);
        add(scrollPaneRight, BorderLayout.EAST);
        add(scrollPaneLeft, BorderLayout.WEST);
        add(screenOptions, BorderLayout.SOUTH);

        //setPreferredSize (new Dimension (504, 264));

        // have screenView at the center of the panel,
        // and a pane of components to add to the left
        screenView.addMouseListener(new ComponentListener());
        setFocusable(true);
    }

    public GamePlayer getPlayer()
    {
        return null;
    }

    // update whether to show grid
    public void updateShowGrid(boolean shouldShowGrid)
    {
        screenView.setShowGrid(shouldShowGrid);
    }

    // receive
    public void setSelectedComponent(ScreenComponent comp)
    {
        listener.setComponent(comp);
    }

    private class ComponentListener extends MouseAdapter
    {
        ScreenComponent comp;

        public void setComponent(ScreenComponent comp)
        {
            this.comp = comp;
        }

        @Override
        public void mousePressed (MouseEvent e)
        {
            Point objectAddDeletePos = e.getPoint();

            int rX = ((int) objectAddDeletePos.getX()) - ((int) objectAddDeletePos.getX() % screenView.IMAGE_HEIGHT);
            int rY = ((int) objectAddDeletePos.getY()) - ((int) objectAddDeletePos.getY() % screenView.IMAGE_HEIGHT);

            if (comp != null)
            {
                comp.setPosition(new Point(rX, rY));
                screen.addComponent(comp);
                comp.accept(screenView);
            }

            repaint();
        }
    }
}
