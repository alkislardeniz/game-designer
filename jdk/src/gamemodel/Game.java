package gamemodel;

import java.util.ArrayList;
import java.io.Serializable;
import expr.*;

/**
 * Game
 * Object for a game.
 * @author Ata Deniz Aydin
 * @author Demir Topaktas
 * @version 30/04/16
 */
public class Game implements Serializable, VariableSet
{
    // store dimensions of each screen of game
    // possibly make the lists maps, so as to memoize screen names and speed up program
    ArrayList<Screen> screens; // search through screens by their names
    ArrayList<Binding> variables;
    int height = 21, width = 21; // dimensions of grid
    PlayableScreen startScreen;

    // initialize empty collections, null startScreen
    public Game()
    {
        screens = new ArrayList<>();
        variables = new ArrayList<>();

        startScreen = new PlayableScreen(this, "Start");
        screens.add(startScreen);
    }

    public Game(Game other)
    {
        screens = new ArrayList<>();
        variables = new ArrayList<>();

        for (Screen screen : other.screens)
            screens.add(screen.copy(this));

        for (Binding variable : other.variables)
            variables.add(new Binding(variable));

        startScreen = (PlayableScreen) other.startScreen.copy(this);
    }

    // used for GUI elements

    public ArrayList<Binding> getVariables()
    {
        return new ArrayList<>(variables);
    }

    public ArrayList<Screen> getScreens()
    {
        return new ArrayList<>(screens);
    }

    // returns list of names of screens along with special name for end screen, which is null
    public ArrayList<String> getScreenNames()
    {
        ArrayList<String> res = new ArrayList<>();

        for (Screen screen : screens)
            res.add(screen.getName());

        res.add("End");

        return res;
    }

    // return name of screen, null or not
    public String getScreenName(Screen screen)
    {
        if (screen == null)
            return "End";

        return screen.getName();
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public PlayableScreen getStartScreen() { return startScreen; }

    public void setStartScreen(PlayableScreen startScreen) { this.startScreen = startScreen; }

    public Screen getScreen(String name)
    {
        if (name == null || name.equals("End"))
            return null;

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

                // remove screen from options of other screens
                for (Screen other : screens)
                    for (Option op : new ArrayList<>(other.getOptions()))
                        if (op.getScreen().equals(screen))
                        {
                            other.removeOption(op.getName());
                            if (other.getPlayable())
                                for (ScreenComponent comp : new ArrayList<>(((PlayableScreen) other).components))
                                    if (comp instanceof ScreenButton && ((ScreenButton) comp).getOption().equals(op.getName()))
                                        ((PlayableScreen) other).removeComponent(comp);
                        }

                return screen;
            }

        return null;
    }



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
        Binding bind = getBinding(varName);
        ExprValue val;

        if (bind == null || expr == null || !expr.valid(this))
            return false;

        val = expr.eval(null); // should I consider the game itself an environment?

        if (val == null)
            return false;

        bind.setValue(val);

        return true;
    }

    public boolean removeVariable(String varName)
    {
        if (!variables.remove(getBinding(varName)))
            return false;

        // remove references to it in components
        for (Screen screen : screens)
            if (screen.getPlayable())
                for (ScreenComponent comp : new ArrayList<>(((PlayableScreen) screen).components))
                    if (comp instanceof ScreenTextBox && ((ScreenTextBox) comp).variable.getName().equals(varName))
                        ((PlayableScreen) screen).removeComponent(comp);

        return true;
    }

    public boolean hasBinding(String varName)
    {
        return getBinding(varName) != null;
    }

    // return whether the game is set up correctly
    public boolean valid()
    {
        if (startScreen == null)
            return false;

        for (Screen screen : screens)
            if (!screen.valid())
                return false;

        for (Binding bind : variables)
            if (!bind.valid())
                return false;

        return true;
    }
}