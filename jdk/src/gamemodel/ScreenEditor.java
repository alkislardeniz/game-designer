package gamemodel;

import java.awt.Point;

/**
 * Created by admin on 4/3/16.
 */
// encapsulates methods to edit a screen
// Is this class necessary? Perhaps move to gui.ScreenEditor
public class ScreenEditor
{
    PlayableScreen screen;

    public ScreenEditor(PlayableScreen screen)
    {

    }

    // editor methods
    // TODO

    // place component on screen, adjusting its position
    // called by GUI editor
    // calls canAddComponent()
    public boolean placeComponent(String name, Point point)
    {
        return false;
    }

    // find component with name/location and return their editor
    public ComponentEditor findComponent(String name)
    {
        return null;
    }

    public ComponentEditor findComponent(Point point)
    {
        return null;
    }

    // change location of component
    // calls canAddComponent()
    public boolean moveComponent(String name, Point newPoint)
    {
        return false;
    }
}