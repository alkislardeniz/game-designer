package gamemodel;

import expr.*;
import java.lang.Boolean;

/**
 * CondScreen
 * Nonplayable screen for a conditional
 * @author  Ata Deniz Aydin
 * @version 04/08/16
 */
// and this only to two options
public class CondScreen extends NonPlayableScreen
{
    Expr pred;

    // initialize two options, set optionLimit to 2
    // one representing true, the other false
    public CondScreen(Game parent, String name)
    {
        super(parent, name);
        optionLimit = 2;
    }

    public ExprError setPred(String newText)
    {
        Expr temp = Expr.parse(newText);

        if (temp == null)
            return ExprError.UNPARSED;

        if (!temp.valid(parent))
            return ExprError.INVALID;

        pred = temp;
        return ExprError.VALID;
    }

    // getters, setters
    // parse pred automatically within the setter, which takes a String

    // evaluates pred in the current state of the game, then calls toPlayer() based on
    // the result of the evaluation
    @Override
    public Option getOption(GamePlayer player)
    {
        if (pred.eval(player).getValue() == Boolean.TRUE)
            return options.get(0);
        return options.get(1);
    }

    // whether pred is a valid predicate
    // possibly throw error or alert parent otherwise
    // checked automatically by editor
    public boolean validExpr()
    {
        return pred.valid(parent);
    }
}