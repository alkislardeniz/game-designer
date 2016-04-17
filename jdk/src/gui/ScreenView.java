package gui;

import gamemodel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by admin on 4/12/16.
 */
public class ScreenView extends JPanel
{
    ScreenController parent;
    PlayableScreen screen;
    ArrayList<ComponentView> comps;
    ObjectView movable;
    ImageIcon bg;

    // perhaps make these constants for Screen
    // final int WIDTH = 504, HEIGHT = 504;
    // final int JUMP = IMAGE_HEIGHT;  // increment for image movement
    int IMAGE_HEIGHT = 24;
    int IMAGE_WIDTH  = 24;

    private boolean showGrid, deleteObject;

    public ScreenView(ScreenController parent, PlayableScreen screen, boolean editing)
    {
        ComponentView view;

        deleteObject = false;
        showGrid = true;

        // initialize fields
        this.parent = parent;
        this.screen = screen;
        comps = new ArrayList<ComponentView>();
        for (ScreenComponent comp : screen.getComponents())
        {
            // TODO add comp's specific component view to comps and set movable
            view = ComponentView.getView(comp, editing);
            comps.add(view);
            if (comp == screen.getMovable())
                movable = (ObjectView) view;
        }

        // Perhaps we can use GridBagLayout for components

        setPreferredSize (new Dimension (WIDTH, HEIGHT));
        setLayout (new BorderLayout());
        setFocusable (true);
    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent (g);

        g.drawImage(bg.getImage(), 0, 0, null); // make this the screen's property

        for (ComponentView comp : comps)
        {
            // different for labels, objects, etc.
            comp.paintComponentOn(g, this);
        }

        if (showGrid)
        {
            int xP = 0;
            int yP = 0;

            // draws horizontal lines
            for (int i = 0; i <= 21; i++)
            {
                g.drawLine (xP, yP, xP + (IMAGE_HEIGHT * 21), yP);
                yP += IMAGE_HEIGHT;
            }
            yP = 0;

            // draws vertical lines
            for (int i = 0; i <= 21; i++)
            {
                g.drawLine (xP, yP, xP, yP + (IMAGE_WIDTH * 21));
                xP += IMAGE_WIDTH;
            }
        }
    }

    class DirectionListener implements KeyListener
    {
        public void keyPressed (KeyEvent event)
        {
            setFocusable(true);
            movable.move(event.getKeyCode(), IMAGE_WIDTH, IMAGE_HEIGHT);
            setFocusable(false);
            repaint();
        }

        public void keyReleased(KeyEvent event)
        {
            movable.stopMoving();
            repaint();
        }

        public void keyTyped (KeyEvent event) {}
    }
}
