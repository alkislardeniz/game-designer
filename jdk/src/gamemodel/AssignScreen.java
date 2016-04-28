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
        // options.add(0, new Option("", null));
    }

    // getters, setters
    // parse newValue automatically within the setter, which takes a String

    public Var getVariable() { return variable; }

    public Expr getNewValue() { return newValue; }

    public String toString()
    {
        return variable + " := " + newValue;
    }

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
        if (valid() && variable.getType().equals(newValue.getType(parent)))
            player.addVariable(variable, newValue);
        return options.get(0); // first option
    }

    public boolean valid()
    {
        return newValue != null
            && newValue.valid(parent)
            && parent.hasVariable(variable);
    }

    public void accept(ScreenVisitor visitor)
    {
        visitor.visit(this);
    }
}