package gamemodel;

import java.io.Serializable;
import java.awt.Point;

/**
 * Created by admin on 4/3/16.
 */
public class ScreenComponent implements Serializable
{
    String name;
    Point  position;
    Screen parent;

    // null everything
    public ScreenComponent()
    {

    }

    // point initially null, set by ScreenEditor
    public ScreenComponent(Screen par, String nam)
    {
        parent = par;
        name = nam;
        position = null;
    }

    // in subclasses, GUI components might be inner classes
    // so that the parent screen's method can be called

    // returns whether other's position is compatible with this
    // can be overridden, but by default returns false if the positions are equal
    public boolean isCompatible(ScreenComponent other)
    {
        return false;
    }

    // what to do in leaving a screen, nothing by default
    public void leavingScreen(GamePlayer player)
    {

    }
}