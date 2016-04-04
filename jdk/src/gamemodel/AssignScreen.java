package gamemodel;

import expr.*;

/**
 * Created by admin on 4/3/16.
 */
// make sure this is bound only to one screen
public class AssignScreen extends Screen
{
    String variable;
    Expr newValue;

    // initialize only one option, set optionLimit to 1
    public AssignScreen(Game parent, String name)
    {

    }

    // getters, setters
    // parse newValue automatically within the setter, which takes a String

    // calculates newValue in the context of player, sets value of variable in the
    // context of player, then calls toPlayer() with the default option
    public void fromPlayer(GamePlayer player)
    {
        String option = "";

        toPlayer(player, option);
    }
}