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
    Var  variable;
    Expr newValue;

    // initialize only one option, set optionLimit to 1
    public AssignScreen(Game parent, String name)
    {
        super(parent, name);
        optionLimit = 1;
    }

    // getters, setters
    // parse newValue automatically within the setter, which takes a String

    public boolean setVariable(String var)
    {
        Var temp = parent.getVariable(var);

        if (temp == null)
            return false;

        variable = temp;
        return true;
    }

    // possibly abstract this in the Expr class
    public ExprError setNewValue(String newText)
    {
        Expr temp = Expr.parse(newText);

        if (temp == null)
            return ExprError.UNPARSED;

        if (!temp.valid(parent))
            return ExprError.INVALID;

        newValue = temp;
        return ExprError.VALID;
    }

    // calculates newValue in the context of player, sets value of variable in the
    // context of player, then calls toPlayer() with the default option
    @Override
    public Option getOption(GamePlayer player)
    {
        player.addVariable(variable, newValue);
        return options.get(0); // first option
    }
}