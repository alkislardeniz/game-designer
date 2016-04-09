package gamemodel;

import java.util.Hashtable;
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
    Map<String,ExprValue> varBinds;
    boolean shown;

    // for serialization, instantiate everything as null
    public GamePlayer()
    {
        varBinds = new Hashtable<String, ExprValue>();
    }

    // only method called from outside
    public GamePlayer(Game game)
    {
        varBinds = new Hashtable<String, ExprValue>();
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

    public ExprValue getVariable(Var var)
    {
        return varBinds.get(var.getName());
    }

    // evaluates value before adding it to map
    // should check for null
    public void addVariable(Var var, Expr value)
    {
        varBinds.put(var.getName(), value.eval(this));
    }
}