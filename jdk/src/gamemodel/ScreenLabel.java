package gamemodel;

import expr.*;

/**
 * ScreenLabel
 * Displays value of a given expression inside a screen.
 * @version 4/10/16
 */
public class ScreenLabel extends ScreenComponent
{
    Expr expr; // expression whose value is to be displayed

    public ScreenLabel(PlayableScreen par, String nam, String exp)
    {
        super(par, nam);
        expr = Expr.parse(exp); // problem if this isn't valid
    }

    // getters, setters

    public Expr getExpr() { return expr; }

    public ExprError setExpr(String newText)
    {
        Expr temp = Expr.parse(newText);

        if (temp == null)
            return ExprError.UNPARSED;

        if (!temp.valid(parent.parent))
            return ExprError.INVALID;

        expr = temp;
        return ExprError.VALID;
    }

    public String getText(GamePlayer env)
    {
        return expr.eval(env).toString();
    }

    @Override
    public boolean isCompatible(ScreenComponent other, int x, int y)
    {
        return true;
    }

    @Override
    public void accept(ComponentVisitor visitor)
    {
        visitor.visit(this);
    }

    public boolean valid()
    {
        return expr != null
            && expr.valid(parent.parent);
    }
}
