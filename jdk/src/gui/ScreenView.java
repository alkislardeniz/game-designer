package gui;

import gamemodel.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by admin on 4/12/16.
 */
public class ScreenView extends JPanel
{
    PlayableScreen screen;
    ArrayList<ComponentView> comps;
    ObjectView movable;
    ImageIcon bg;

    // perhaps make these constants for Screen
    private final int WIDTH = 504, HEIGHT = 504;
    private final int IMAGE_HEIGHT = 24;
    private final int IMAGE_WIDTH = IMAGE_HEIGHT;
    private final int JUMP = IMAGE_HEIGHT;  // increment for image movement

    private boolean showGrid, deleteObject;

    public ScreenView(PlayableScreen screen)
    {
        deleteObject = false;
        showGrid = true;

        // initialize screen, comps, movable, bg

        // Perhaps we can use GridBagLayout for components

        // add ComponentViews for each component of the screen
        for (ComponentView comp : comps)
            comp.addComponent(this);

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

}
