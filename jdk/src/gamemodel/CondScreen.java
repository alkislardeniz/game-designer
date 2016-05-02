package gamemodel;

import expr.*;
import java.lang.Boolean;

/**
 * CondScreen
 * Nonplayable screen for a conditional
 * @author  Ata Deniz Aydin
 * @version 08/04/16
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
        pred = null; // should disallow user from running game before defining a predicate
    }

    public Screen copy(Game parent)
    {
        CondScreen screen = new CondScreen(parent, name);
        screen.pred = pred;

        return screen;
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

    public Expr getPred() { return pred; }

    public String getText()
    {
        return pred.toString();
    }

    // evaluates pred in the current state of the game, then calls toPlayer() based on
    // the result of the evaluation
    @Override
    public Option getOption(GamePlayer player)
    {
        // System.out.println(pred + " = " + pred.eval(player));
        
        if (pred.eval(player).getValue() == Boolean.TRUE)
            return options.get(0);
        return options.get(1);
    }

    // whether pred is a valid predicate
    // possibly throw error or alert parent otherwise
    // checked automatically by editor
    public boolean valid()
    {
        return pred != null
            && pred.valid(parent)
            && pred.getType(parent) == ExprType.BOOLEAN;
    }

    public void accept(ScreenVisitor visitor)
    {
        visitor.visit(this);
    }
}