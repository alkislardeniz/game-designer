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
        return null;
    } // TODO
    public List<ScreenComponent> getComponents()
    {
        return components;
    }
    public boolean addComponent(String name, ScreenComponent comp)
    {
        return false;
    } // TODO

    public ScreenComponent removeComponent(String name)
    {
        return null;
    } // TODO

    // return true if all components are compatible with comp
    // also check for boundaries
    // if comp is already in components and not on (x,y) and not movable, return false
    public boolean canPlaceComponent(ScreenComponent comp, int x, int y)
    {
        return false; // TODO
    }

    // alert each component
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