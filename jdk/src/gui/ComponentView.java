package gui;

import gamemodel.*;
import javax.swing.*;
import java.awt.*;

/**
 * Represents screen component graphically
 * Created by admin on 4/12/16.
 */
public abstract class ComponentView // extends JComponent
{
    ScreenComponent comp;

    public ComponentView(ScreenComponent comp)
    {
        this.comp = comp;

        // GUI operations about the representation of the component
        // probably through subclasses for each type of component

        // TODO find a way to move objects inside the screen without changing their starting position in the game
    }

    public ScreenComponent getComponent() { return comp; }

    // called from constructor of screenview
    public abstract void addComponent(ScreenView scr);

    // draw component on screen
    public abstract void paintComponentOn(Graphics g, ScreenView scr);

    public int getX() { return (int) comp.getPosition().getX(); }

    public int getY() { return (int) comp.getPosition().getY(); }

    public void setX(int x) { comp.getPosition().setLocation(x, getY()); }

    public void setY(int y) { comp.getPosition().setLocation(getX(), y); }
}
