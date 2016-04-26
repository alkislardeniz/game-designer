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

    public ScreenView(ScreenController parent, PlayableScreen screen)
    {
        // ComponentView view;

        final int WIDTH = screen.getWidth() * IMAGE_WIDTH;
        final int HEIGHT = screen.getHeight() * IMAGE_HEIGHT;

        deleteObject = false;
        showGrid = true;

        // initialize fields
        this.parent = parent;
        this.screen = screen;
        editing = parent.getPlayer() == null;
        comps = new ArrayList<ComponentView>();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(null);

        // add comp's specific component view to comps and set movable
        for (ScreenComponent comp : screen.getComponents())
            comp.accept(this);

        repaint();

        addKeyListener(new DirectionListener());
        setFocusable(true);
    }

    public void setScreen(PlayableScreen screen)
    {
        this.screen = screen;

        removeAll();

        comps = new ArrayList<ComponentView>();

        for (ScreenComponent comp : screen.getComponents())
            comp.accept(this);

        revalidate();
        repaint();
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

        if (editing)
            screen.addComponent(comp);
    }

    public void visit(ScreenLabel comp)
    {
        comps.add(new LabelView(this, comp, editing));
        if (editing)
            screen.addComponent(comp);
    }

    public void visit(ScreenObject comp)
    {
        ObjectView view = new ObjectView(this, comp, editing);
        comps.add(view);

        if (comp.equals(screen.getMovable()))
            movable = view;

        if (editing)
            screen.addComponent(comp);
    }

    public void visit(ScreenTextBox comp)
    {
        comps.add(new TextBoxView(this, comp, editing));

        if (editing)
            screen.addComponent(comp);
    }

    class DirectionListener implements KeyListener
    {
        public void keyPressed (KeyEvent event)
        {
            // System.out.println("test");
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

    public boolean getShowGrid()
    {
        return showGrid;
    }

    public void setShowGrid(boolean showGrid)
    {
        this.showGrid = showGrid;
        repaint();
    }
}
