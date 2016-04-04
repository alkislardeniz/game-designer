package gamemodel;

import expr.*;

/**
 * Created by admin on 4/3/16.
 */
// and this only to two options
public class CondScreen extends Screen
{
    Expr pred;

    // initialize two options, set optionLimit to 2
    // one representing true, the other false
    public CondScreen(Game parent, String name)
    {

    }

    // getters, setters
    // parse pred automatically within the setter, which takes a String

    // evaluates pred in the current state of the game, then calls toPlayer() based on
    // the result of the evaluation
    public void fromPlayer(GamePlayer player)
    {
        String option = "";

        toPlayer(player, option);
    }

    // whether pred is a valid predicate
    // possibly throw error or alert parent otherwise
    public boolean validExpr()
    {
        return false;
    }
}