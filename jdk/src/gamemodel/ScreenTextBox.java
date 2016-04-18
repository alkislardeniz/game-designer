package gamemodel;

import expr.*;

/**
 * ScreenTextBox
 * Assigns inputted value to variable in a screen.
 * @author  Ata Deniz Aydin
 * @version 08/04/16
 */
public class ScreenTextBox extends ScreenComponent
{
    Var variable;
    Expr text;

    // perhaps with less options and setters
    public ScreenTextBox(PlayableScreen par, String nam)
    {
        super(par, nam);

        variable = null;
        text = null;
    }

    // getters, setters go here

    public boolean setVariable(String newVar)
    {
        Var temp = parent.parent.getVariable(newVar);

        if (temp == null)
            return false;

        variable = temp;
        return true;
    }

    // called from GUI, perhaps disable exiting from screen until a valid expression is added
    // one possible setback: dealing with literal text with quotes
    public ExprError setText(String newText)
    {
        Expr temp = Expr.parse(newText);

        if (temp == null)
            return ExprError.UNPARSED;

        if (!temp.valid(parent.parent))
            return ExprError.INVALID;

        text = temp;
        return ExprError.VALID;
    }

    // update value of variable to evaluation of text
    // if text is false, do nothing or perhaps disable exiting
    @Override
    public void leavingScreen(GamePlayer player)
    {
        if (valid() && text.valid(parent.parent) && text.getType(parent.parent) == variable.getType())
            player.addVariable(variable, text);
    }

    @Override
    public void accept(ComponentVisitor visitor)
    {
        visitor.visit(this);
    }

    public boolean valid()
    {
        return parent.parent.hasVariable(variable);
    }
}