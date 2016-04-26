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
    ScreenComponent comp;
    ScreenObject movable;

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
        this.comp = comp.copy();
    }

    private class ComponentListener extends MouseAdapter
    {
        @Override
        public void mousePressed (MouseEvent e)
        {
            Point objectAddDeletePos = e.getPoint();

            int rX = (int) objectAddDeletePos.getX() / screenView.IMAGE_HEIGHT;
            int rY = (int) objectAddDeletePos.getY() / screenView.IMAGE_HEIGHT;

            if (screenOptions.shouldDelete())
            {
                for (ScreenComponent temp : screen.findComponentsAt(rX, rY))
                    if (temp != null)
                        screenView.removeComponent(temp);

            }
            else if (comp != null)
            {
                comp = comp.copy();
                comp.setPosition(new Point(rX, rY));
                comp.accept(screenView);
            }

            repaint();
        }
    }
}
