package gamemodel;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import expr.*;

/**
 * Created by admin on 4/3/16.
 */
public class Game extends Observable implements Serializable, VariableSet
{
    // store dimensions of each screen of game
    // possibly make the lists maps, so as to memoize screen names and speed up program
    List<Screen> screens; // search through screens by their names
    List<ScreenObject> sharedObjects; // searched by names, represent objects shared across screens
    List<Binding> variables;
    int height = 21, width = 21; // dimensions of grid
    Screen startScreen;

    // initialize empty collections, null startScreen
    public Game()
    {
        screens = new ArrayList<Screen>();
        sharedObjects = new ArrayList<ScreenObject>();
        variables = new ArrayList<Binding>();
        // TODO

    }

    // TODO getters, setters

    public void setStartScreen(Screen startScreen) { this.startScreen = startScreen; }

    public Screen getScreen(String name)
    {
        for (Screen screen : screens)
            if (screen.name.equals(name))
                return screen;

        return null;
    }

    public boolean addScreen(Screen screen)
    {
        return screens.add(screen);
    }

    public Screen removeScreen(String name)
    {
        for (Screen screen : screens)
            if (screen.name.equals(name))
            {
                screens.remove(screen);
                return screen;
            }

        return null;
    }



    // for lists, get() and remove() methods call objects by their names
    // return null if no objects of that name
    // change these to include initial values for variables, changing the type of the variable if necessary
    public boolean hasVariable(Var var)
    {
        return variables.contains(new Binding(var));
    }

    public boolean addVariable(String varName)
    {
        return variables.add(new Binding(new Var(varName)));
    }

    public Var getVariable(String varName)
    {
        for (Binding bind : variables)
            if (bind.getVar().getName().equals(varName))
                return bind.getVar();

        return null;
    }

    public Binding getBinding(String varName)
    {
        for (Binding bind : variables)
            if (bind.getVar().getName().equals(varName))
                return bind;

        return null;
    }

    public boolean setVariable(String varName, String initValue)
    {
        Expr expr = Expr.parse(initValue);
        ExprValue val;
        Binding bind = getBinding(varName);

        if (bind == null || expr == null || !expr.valid(this))
            return false;

        val = expr.eval(null); // should I consider the game itself an environment?

        if (val == null)
            return false;

        bind.setValue(val);

        return true;
    }

    // return whether the game is set up correctly
    public boolean valid()
    {
        if ( startScreen == null)
        return false;
        for ( Screen screen : screens )
            if ( !screen.valid() )
            {
                return false;
            }

    }
}