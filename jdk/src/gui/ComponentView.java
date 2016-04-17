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
    // return the appropriate view for comp
    public static ComponentView getView(ScreenComponent comp, boolean editing)
    {
        // TODO
        return null;
    }

    ScreenComponent comp;
    boolean editing; // whether the component is editable or not
    int x, y;

    public ComponentView(ScreenComponent comp, boolean editing)
    {
        this.comp = comp;
        this.editing = editing;
        x = (int) comp.getPosition().getX();
        y = (int) comp.getPosition().getY();

        // GUI operations about the representation of the component
        // probably through subclasses for each type of component
    }

    public ScreenComponent getComponent() { return comp; }

    // draw component on screen
    public abstract void paintComponentOn(Graphics g, ScreenView scr);

    public int getX() { return x; }

    public int getY() { return y; }

    // change position of component if editing
    public void setX(int x)
    {
        if (editing)
            comp.getPosition().setLocation(x, getY());
        this.x = x;
    }

    public void setY(int y)
    {
        if (editing)
            comp.getPosition().setLocation(getX(), y);
        this.y = y;
    }
}
