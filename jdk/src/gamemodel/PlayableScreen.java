package gamemodel;

import java.util.List;

/**
 * Created by admin on 4/3/16.
 */
public class PlayableScreen extends Screen
{
    List<ScreenComponent> components; // container?
    ScreenComponent movableComponent;

    public PlayableScreen()
    {

    }

    // empty components other than shared objects
    // playable set to true
    public PlayableScreen(Game parent, String name)
    {

    }

    public ScreenComponent getComponent(String name)
    {
        return null;
    }
    public boolean addComponent(String name, ScreenComponent comp)
    {
        return false;
    }

    public ScreenComponent removeComponent(String name)
    {
        return null;
    }

    // return true if all components are compatible with comp
    public boolean canAddComponent(ScreenComponent comp)
    {
        return false;
    }

    @Override
    public void fromPlayer(GamePlayer player)
    {

    }

    // call components in leaving screen
    @Override
    public void toPlayer(GamePlayer player, String option)
    {

    }
}