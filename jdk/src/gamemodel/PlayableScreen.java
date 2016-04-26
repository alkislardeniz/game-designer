package gamemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 4/3/16.
 */
public class PlayableScreen extends Screen
{
    List<ScreenComponent> components; // container?
    ScreenObject movable;
    String backgroundName;
    // store screen dimensions

    public PlayableScreen()
    {
        playable = true;
    }

    // empty components other than shared objects
    // playable set to true
    public PlayableScreen(Game parent, String name)
    {
        super(parent, name);
        playable = true;
        components = new ArrayList<ScreenComponent>();
    }

    public String getBackgroundName() { return backgroundName; }

    public ScreenComponent getComponent(String name)
    {
        for (ScreenComponent comp : components)
            if (comp.name.equals(name))
                return comp;
        return null;
    }

    public int getWidth() { return parent.width; }

    public int getHeight() { return parent.height; }

    public List<ScreenComponent> getComponents()
    {
        return new ArrayList<ScreenComponent>(components);
    }

    public boolean addComponent(ScreenComponent comp)
    {
        return components.add(comp);
    }

    public ScreenComponent removeComponent(String name)
    {
        for (ScreenComponent comp : components)
            if (comp.name.equals(name))
            {
                components.remove(comp);
                return comp;
            }
        return null;
    }

    public boolean removeComponent(ScreenComponent comp)
    {
        return components.remove(comp);
    }

    // try to place comp on (x,y) on the screen
    public boolean placeComponent(ScreenComponent newComp, int x, int y)
    {
        if (!canPlaceComponent(newComp, x, y))
            return false;

        // assign newComp to its position on the screen
        newComp.getPosition().setLocation(x, y);

        // add it to screen if not already in screen
        if (!components.contains(newComp))
            components.add(newComp);

        return true;
    }

    // return true if all components are compatible with comp
    // also check for boundaries
    // if comp is already in components and not on (x,y) and not movable, return false
    public boolean canPlaceComponent(ScreenComponent newComp, int x, int y)
    {
        // check for boundaries
        if (x < 0 || y < 0 || x + newComp.width > parent.width || y + newComp.height > parent.height)
            return false;

        // check for compatibility
        for (ScreenComponent comp : components)
            if (comp != newComp && !comp.isCompatible(newComp, x, y))
                return false;

        return true;
    }

    public ScreenComponent findComponentAt(int x, int y)
    {
        for (ScreenComponent comp : components)
            if (comp.contains(x, y))
                return comp;
        return null;
    }

    public ScreenObject getMovable()
    {
        return movable;
    }

    public void setMovable(ScreenObject movable)
    {
        if (movable.isMovable())
            this.movable = movable;
    }

    // call components in leaving screen
    @Override
    public void toPlayer(GamePlayer player, Option option)
    {
        for (ScreenComponent comp : components)
            comp.leavingScreen(player);
        super.toPlayer(player, option);
    }

    public boolean valid()
    {
        for (ScreenComponent comp : components)
            if (!comp.valid())
                return false;
        return super.valid();
    }
}