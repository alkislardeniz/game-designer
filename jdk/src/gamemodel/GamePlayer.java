package gamemodel;

import java.util.Map;
import java.io.Serializable;
import expr.*;

/**
 * Created by admin on 4/3/16.
 */
// class executing a game
public class GamePlayer implements Serializable, VariableEnv
{
    Game game;
    Screen currentScreen;
    Map<Var,ExprValue> varBinds;
    boolean shown;

    // for serialization, instantiate everything as null
    public GamePlayer()
    {

    }

    // only method called from outside
    public GamePlayer(Game game)
    {

    }

    // called from the constructor and individual screens
    // calls the current screen in the state
    // returns false if newScreen null, i.e. there are no screens left to execute
    // automatically sets shown to false
    public boolean call(Screen newScreen)
    {
        return false;
    }

    // called from playable screen's fromPlayer() method
    // set shown to true
    public void showCurrentScreen()
    {

    }

    // return shown, called from GUI
    public boolean getShown()
    {
        return false;
    }

    public ExprValue getVariable(String name)
    {
        return null;
    }

    public void addVariable(String name, Expr value) // parses and evaluates valueString
    {

    }
}