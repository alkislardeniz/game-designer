package gamemodel;

import expr.Expr;

/**
 * ScreenLabel
 * Displays value of a given expression inside a screen.
 * @version 4/10/16
 */
public class ScreenLabel extends ScreenComponent
{
    Expr expr; // expression whose value is to be displayed

    public ScreenLabel(Screen par, String nam, String exp)
    {
        parent = par;
        name = nam;
        expr = Expr.parse(exp);
    }

    // getters, setters

    public String getText(GamePlayer env)
    {
        return expr.eval(env).toString();
    }
}
