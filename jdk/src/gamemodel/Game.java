package gamemodel;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import expr.*;

/**
 * Created by admin on 4/3/16.
 */
public class Game implements Serializable, VariableSet
{
    // store dimensions of each screen of game
    // possibly make the lists maps, so as to memoize screen names and speed up program
    List<Screen> screens; // search through screens by their names
    List<ScreenObject> sharedObjects; // searched by names, represent objects shared across screens
    List<Var> variables; // perhaps store initial values paired with variables
    Screen startScreen;

    // initialize empty collections, null startScreen
    public Game()
    {
        variables = new ArrayList<Var>();
        // TODO
    }

    // getters, setters

    public Screen getScreen(String name)
    {
        // TODO
        return null;
    }

    // for lists, get() and remove() methods call objects by their names
    // return null if no objects of that name
    // change these to include initial values for variables, changing the type of the variable if necessary
    public boolean hasVariable(Var var)
    {
        return variables.contains(var);
    }
    public boolean addVariable(String varName)
    {
        return variables.add(new Var(varName));
    }

    public Var getVariable(String varName)
    {
        for (Var var : variables)
            if (var.getName().equals(varName))
                return var;

        return null;
    }

    // editor interface

    // create new screen, playable, asignment or cond
    // TODO
    public boolean newPlayableScreen(String name)
    {
        return false;
    }
    public boolean newAssignmentScreen(String name)
    {
        return false;
    }
    public boolean newCondScreen(String name)
    {
        return false;
    }

    // return ScreenEditor for a playable screen
    // called by controller in moving to a new panel to edit a screen
    public ScreenEditor getEditor(String name)
    {
        Screen screen = getScreen(name);

        if (screen == null || !(screen instanceof PlayableScreen))
            return null;

        return new ScreenEditor((PlayableScreen) screen);
    }
}