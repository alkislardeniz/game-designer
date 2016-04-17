package gamemodel;

import java.util.List;
import java.util.ArrayList;
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
    List<Binding> varBinds;
    boolean shown;

    // for serialization, instantiate everything as null
    public GamePlayer()
    {
        varBinds = new ArrayList<Binding>();
    }

    // only method called from outside
    public GamePlayer(Game game)
    {
        varBinds = new ArrayList<Binding>(game.variables);
    }

    // called from the constructor and individual screens
    // calls the current screen in the state
    // returns false if newScreen null, i.e. there are no screens left to execute
    // automatically sets shown to false
    public boolean call(Screen newScreen)
    {
        return false;
    }

    // perhaps make this only return playable screens
    public Screen getCurrentScreen() { return currentScreen; }

    // return shown, called from GUI
    public boolean getShown()
    {
        return false;
    }

    public ExprValue getVariable(Var var)
    {
        Binding bind = getBinding(var);

        if (bind == null)
            return null;

        return bind.getValue();
    }

    public Binding getBinding(Var var)
    {
        for (Binding bind : varBinds)
            if (bind.getVar().equals(var))
                return bind;

        return null;
    }

    // evaluates value before adding it to map
    // should check for null
    public boolean addVariable(Var var, Expr value)
    {
        ExprValue val = value.eval(this);
        Binding bind = getBinding(var);

        return val != null
            && bind != null
            && bind.setValue(val);
    }
}