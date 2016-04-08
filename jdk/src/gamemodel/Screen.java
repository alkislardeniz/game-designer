package gamemodel;

import java.util.List;
import java.io.Serializable;

/**
 * Screen
 * Abstract class for screens.
 * @author  Ata Deniz Aydin
 * @version 05/04/16
 */
// class codifying a screen, visible or not
public abstract class Screen implements Serializable
{
    // name already defined inside game
    String name;
    String description;
    Game parent; // why?
    List<Option> options; // searched by name
    int optionLimit = -1; // affects add, changed for AssignScreen and CondScreen
    boolean playable; // true for PlayableScreen, used to determine type w/o calling instanceof

    // for serialization, set everything to null
    public Screen()
    {

    }

    // set other properties to default values, to be changed by edit description screen
    public Screen(Game parent, String name)
    {

    }

    // called from player, either responds directly or waits for input from GUI interface
    public abstract void fromPlayer(GamePlayer player);

    // calls player after updating its state, sets new screen of player to option,
    // then calls the player's call() method
    // can be called either from fromPlayer or from GUI action listeners
    public void toPlayer(GamePlayer player, Option option)
    {

    }

    // getOptions() to be called from editor
    protected List<Option> getOptions()
    {
        return null;
    }

    public boolean addOption(String name, Screen screen)
    {
        return false;
    }

    public Screen getScreenWithOption(String name)
    {
        return null;
    }

    public boolean removeOption(String name)
    {
        return false;
    }

    // getter, setter for playable etc.
}