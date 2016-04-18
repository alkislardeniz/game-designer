package gui;

import gamemodel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by admin on 4/12/16.
 */
public class ScreenView extends JPanel implements ComponentVisitor
{
    ScreenController parent;
    PlayableScreen screen;
    ArrayList<ComponentView> comps;
    ObjectView movable;
    ImageIcon bg;
    boolean editing;

    // final int JUMP = IMAGE_HEIGHT;  // increment for image movement
    int IMAGE_HEIGHT = 24;
    int IMAGE_WIDTH  = 24;

    private boolean showGrid, deleteObject;

    public ScreenView(ScreenController parent, PlayableScreen screen, boolean editing)
    {
        // ComponentView view;

        final int WIDTH = screen.getWidth() * IMAGE_WIDTH;
        final int HEIGHT = screen.getHeight() * IMAGE_HEIGHT;

        setPreferredSize (new Dimension (WIDTH, HEIGHT));

        deleteObject = false;
        showGrid = true;

        // initialize fields
        this.parent = parent;
        this.screen = screen;
        this.editing = editing;
        comps = new ArrayList<ComponentView>();

        // Perhaps use a GridBagLayout or absolute layout
        // paintComponentOn() should automatically resize according to IMAGE_HEIGHT and IMAGE_WIDTH

        for (ScreenComponent comp : screen.getComponents())
            // add comp's specific component view to comps and set movable
            comp.accept(this);

        addKeyListener(new DirectionListener());

        setFocusable (true);
    }

    // TODO include methods to add, get and modify different component views

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);

        // g.drawImage(bg.getImage(), 0, 0, null); // make this the screen's property

        for (ComponentView comp : comps)
        {
            // different for labels, objects, etc.
            comp.paintComponentOn(g);
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

    public void visit(ScreenButton comp)
    {
        comps.add(new ButtonView(this, comp, editing));
    }

    public void visit(ScreenLabel comp)
    {
        comps.add(new LabelView(this, comp, editing));
    }

    public void visit(ScreenObject comp)
    {
        ObjectView view = new ObjectView(this, comp, editing);
        comps.add(view);
        // if (comp == screen.getMovable())
            movable = view;
    }

    public void visit(ScreenTextBox comp)
    {
        comps.add(new TextBoxView(this, comp, editing));
    }

    class DirectionListener implements KeyListener
    {
        public void keyPressed (KeyEvent event)
        {
            movable.move(event.getKeyCode());
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
