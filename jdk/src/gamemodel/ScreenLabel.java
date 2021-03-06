package gamemodel;

import expr.*;

/**
 * ScreenLabel
 * Displays value of a given expression inside a screen.
 * @author  Ata Deniz Aydin
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

    public ScreenLabel(ScreenLabel other)
    {
        super(other);
        expr = other.expr;
    }

    // getters, setters

    public ScreenComponent copy()
    {
        return new ScreenLabel(this);
    }

    public ExprString getExpr() { return expr; }

    public boolean setExpr(String newText)
    {
        return expr.setString(newText);
    }

    public String getText(GamePlayer env)
    {
        if (env == null)
            return expr.toString();
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
