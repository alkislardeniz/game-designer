package gamemodel;

import java.util.List;

/**
 * Created by admin on 4/3/16.
 */
public class PlayableScreen extends Screen
{
    List<ScreenComponent> components; // container?
    ScreenComponent movableComponent;
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
        playable = true;

    }

    public String getBackgroundName() { return backgroundName; }

    public ScreenComponent getComponent(String name)
    {
        for (ScreenComponent comp : components)
            if (comp.name.equals(name))
                return comp;
        return null;
    }

    public List<ScreenComponent> getComponents()
    {
        return components;
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

    // return true if all components are compatible with comp
    // also check for boundaries
    // if comp is already in components and not on (x,y) and not movable, return false
    // try to place comp on (x,y) on the screen
    public boolean placeComponent(ScreenComponent newComp, int x, int y)
    {
        // check for boundaries
        if (x < 0 || y < 0 || x + newComp.width > parent.width || y + newComp.height > parent.height)
            return false;

        // check for compatibility
        for (ScreenComponent comp : components)
            if (!comp.isCompatible(newComp, x, y))
                return false;

        // assign newComp to its position on the screen
        newComp.getPosition().setLocation(x, y);

        // add it to screen if not already in screen
        if (!components.contains(newComp))
            components.add(newComp);

        return true;
    }

    public ScreenComponent getMovable()
    {
        return movableComponent;
    }

    public void setMovable(ScreenComponent movable)
    {
        movableComponent = movable;
    }

    // alert each component (is that necessary?)
    @Override
    public void fromPlayer(GamePlayer player)
    {
        // TODO
    }

    // call components in leaving screen
    @Override
    public void toPlayer(GamePlayer player, Option option)
    {
        // TODO
    }
}