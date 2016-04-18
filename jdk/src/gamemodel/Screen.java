package gamemodel;

import java.util.List;
import java.util.ArrayList;
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
        this.parent = parent;
        this.name = name;
        options = new ArrayList<Option>();
    }

    public boolean getPlayable() { return playable; }

    public Game getParent() { return parent; }

    // called from player, either responds directly or waits for input from GUI interface
    public abstract void fromPlayer(GamePlayer player);

    // calls player after updating its state, sets new screen of player to option,
    // then calls the player's call() method
    // can be called either from fromPlayer or from GUI action listeners
    public void toPlayer(GamePlayer player, Option option)
    {
        player.setCurrentScreen(option.getScreen());
        player.call();
        // TODO
    }

    // getOptions() to be called from editor
    public List<Option> getOptions()
    {
        return options;
    }

    public boolean addOption(String name, Screen screen)
    {
        return options.add(new Option(name, screen));
    }

    public Option getOption(String name)
    {
        for (Option op : options)
            if (op.getName().equals(name))
                return op;
        return null;
    }

    public boolean hasOption(String name)
    {
        return getOption(name) != null;
    }

    public Screen getScreenWithOption(String name)
    {
        for (Option op : options)
            if (op.getName().equals(name))
                return op.getScreen();
        return null;
    }

    public boolean removeOption(String name)
    {
        for (Option op : options)
        {
            if (op.getName().equals(name))
            {
                options.remove(op);
                return true;
            }
        }
        return false;
    }

    // getter, setter for playable etc.

    public abstract boolean valid();
}