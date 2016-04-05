package gamemodel;

import java.util.List;
import java.io.Serializable;
import expr.*;

/**
 * Created by admin on 4/3/16.
 */
public class Game implements Serializable, VariableSet
{
    // possibly make the lists maps, so as to memoize screen names and speed up program
    List<Screen> screens; // search through screens by their names
    List<ScreenObject> sharedObjects; // searched by names, represent objects shared across screens
    List<Var> variables; // perhaps create new class for collection of variables
    Screen startScreen;

    // initialize empty collections, null startScreen
    public Game()
    {

    }

    // getters, setters
    // for lists, get() and remove() methods call objects by their names
    // return null if no objects of that name
    public boolean hasVariable(Var var)
    {
        return false;
    }

    // editor interface

    // create new screen, playable, asignment or cond
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
        return null;
    }
}