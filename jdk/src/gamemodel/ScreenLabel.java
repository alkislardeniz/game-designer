package gamemodel;

import expr.*;

/**
 * ScreenLabel
 * Displays value of a given expression inside a screen.
 * @version 4/10/16
 */
public class ScreenLabel extends ScreenComponent
{
    ExprString expr; // expression whose value is to be displayed

    public ScreenLabel(PlayableScreen par, String nam, String exp)
    {
        super(par, nam);
        expr = new ExprString();
        setExpr(exp);
    }

    // getters, setters

    public ExprString getExpr() { return expr; }

    public boolean setExpr(String newText)
    {
        return expr.setString(newText);
    }

    public String getText(GamePlayer env)
    {
        return expr.toString(env);
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
