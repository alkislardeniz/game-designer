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
    final int IMAGE_HEIGHT = 24;
    final int IMAGE_WIDTH  = 24;

    ScreenController parent;
    PlayableScreen screen;
    ArrayList<ComponentView> comps;
    ObjectView movable, bg;
    boolean editing;
    boolean showGrid;

    public ScreenView(ScreenController parent, PlayableScreen screen)
    {
        final int WIDTH = screen.getWidth() * IMAGE_WIDTH;
        final int HEIGHT = screen.getHeight() * IMAGE_HEIGHT;

        showGrid = true;

        // initialize fields
        this.parent = parent;
        this.screen = screen;
        editing = parent.getPlayer() == null;
        comps = new ArrayList<>();

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

        comps = new ArrayList<>();

        for (ScreenComponent comp : screen.getComponents())
            comp.accept(this);

        revalidate();
        repaint();
    }

    public boolean removeComponent(ScreenComponent comp)
    {
        screen.removeComponent(comp);

        if (comp == screen.getMovable())
        {
            screen.setMovable(null);
            movable = null;
        }

        for (ComponentView view : new ArrayList<>(comps))
            if (view.comp == comp)
                return comps.remove(view);

        return false;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // g.drawImage(bg.getImage(), 0, 0, null); // make this the screen's property

        // first paint grid
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

        // then paint each component

        bg.paintComponentOn(g);
        for (ComponentView comp : comps)
        {
            comp.paintComponentOn(g);
        }
    }

    public void setMovable(ObjectIcon icon)
    {
        if (screen.getMovable() != null)
        {
            screen.getMovable().setIcon(icon);
            movable.setIcon(ObjectIconView.getIcon(icon));
        }
        else
        {
            ScreenObject object = new ScreenObject(screen, icon.toString(), icon);
            screen.addComponent(object);
            screen.setMovable(object);
            object.accept(this);
        }
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

    // visitor methods to add components

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

        if (comp.equals(screen.getBackground()))
        {
            bg = view;
        }
        else
        {
            if (comp.equals(screen.getMovable()))
                movable = view;

            comps.add(view);
        }

        if (editing)
            screen.addComponent(comp);
    }

    public void visit(ScreenTextBox comp)
    {
        comps.add(new TextBoxView(this, comp, editing));

        if (editing)
            screen.addComponent(comp);
    }

    // inner classes

    class DirectionListener implements KeyListener
    {
        public void keyPressed(KeyEvent event)
        {
            if (movable != null)
                movable.move(event.getKeyCode());

            repaint();
        }

        public void keyReleased(KeyEvent event)
        {
            if (movable != null)
                movable.stopMoving();

            repaint();
        }

        public void keyTyped (KeyEvent event) {}
    }
}
