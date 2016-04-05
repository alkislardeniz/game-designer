package gamemodel;

import expr.*;

/**
 * AssignScreen
 * Performs assignment on variable.
 * @author  Ata Deniz Aydin
 * @version 05/04/16
 */
// make sure this is bound only to one screen
public class AssignScreen extends NonPlayableScreen
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
    @Override
    public String getOption(GamePlayer player)
    {
        player.addVariable(variable, newValue);
        return ""; // first option
    }
}