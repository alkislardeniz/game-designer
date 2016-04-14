package gamemodel;

import java.io.Serializable;
import java.awt.Point;

/**
 * Created by admin on 4/3/16.
 */
public abstract class ScreenComponent implements Serializable
{
    String name;
    Point  position;
    PlayableScreen parent;

    // null everything
    public ScreenComponent()
    {

    }

    // point initially null, set by ScreenEditor
    public ScreenComponent(PlayableScreen par, String nam)
    {
        parent = par;
        name = nam;
        position = null;
    }

    public Point getPosition() { return position; }

    public PlayableScreen getParent() { return parent; }

    // returns whether other's position is compatible with this
    // can be overridden, but by default returns false if the positions are equal
    // change if other components contain information about their size
    // perhaps make abstract
    public boolean isCompatible(ScreenComponent other, int x, int y)
    {
        return position.getX() != x || position.getY() != y; // TODO
    }

    // what to do in leaving a screen, nothing by default
    public void leavingScreen(GamePlayer player)
    {

    }
}