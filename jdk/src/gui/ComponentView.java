package gui;

import gamemodel.*;
import java.awt.*;

/**
 * Represents screen component graphically
 * Created by admin on 4/12/16.
 */
public abstract class ComponentView
{
    ScreenView parent;
    ScreenComponent comp;
    boolean editing; // whether the component is editable or not
    int x, y;

    public ComponentView(ScreenView parent, ScreenComponent comp, boolean editing)
    {
        this.parent = parent;
        this.comp = comp;
        this.editing = editing;

        x = (int) comp.getPosition().getX();
        y = (int) comp.getPosition().getY();
    }

    public ScreenComponent getComponent() { return comp; }

    // draw component on screen
    public abstract void paintComponentOn(Graphics g);

    public int getX() { return x; }

    public int getY() { return y; }

    // change position of component if editing
    public void setX(int x)
    {
        comp.getPosition().setLocation(x, getY());
        this.x = x;
    }

    public void setY(int y)
    {
        comp.getPosition().setLocation(getX(), y);
        this.y = y;
    }
}
