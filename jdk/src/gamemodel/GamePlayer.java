package gamemodel;

import java.util.Observable;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import expr.*;

/**
 * GamePlayer
 * A particular instance of playing through a game.
 * @author Ata Deniz Aydin
 * @author Demir Topaktas
 * @version
 */
// class executing a game
public class GamePlayer extends Observable implements Serializable, VariableEnv
{
    Game game;
    Screen currentScreen;
    List<Binding> varBinds;

    // for serialization, instantiate everything as null
    public GamePlayer()
    {

    }

    // only method called from outside
    public GamePlayer(Game game)
    {
        this.game = game;
        currentScreen = game.startScreen;
        varBinds = new ArrayList<Binding>(game.variables);

        // call();
    }

    // called from the constructor and individual screens
    // calls the current screen in the state
    // returns false if newScreen null, i.e. there are no screens left to execute
    // automatically sets shown to false
    public boolean call()
    {
        if (currentScreen == null)
            return false;

        currentScreen.fromPlayer(this);
        setChanged();
        notifyObservers();
        return true;
    }

    public Game getGame()
    {
        return game;
    }

    // perhaps make this only return playable screens
    public Screen getCurrentScreen()
    {
        return currentScreen;
    }

    // perhaps make this only return playable screens
    public void setCurrentScreen(Screen newScreen)
    {
        currentScreen = newScreen;
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

        return val  != null
            && bind != null
            && bind.setValue(val);
    }
}