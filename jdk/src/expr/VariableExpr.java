package expr;

import gamemodel.*;

/**
 * VariableExpr
 * Class representing variables
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
class VariableExpr extends Expr
{
    // account for static types, or make it dynamically typed somehow
    public static Expr parse(String parseString)
    {
        // check if valid symbol without spaces
        if (parseString.matches("\\p{Alpha}[\\p{Alnum}_]*"))
            return new VariableExpr(parseString);

        return null;
    }

    Var var;

    private VariableExpr(String name) // memoize
    {
        var = new Var(name);
    }

    // equals()

    // whether variable is defined within game
    public boolean valid(VariableSet vars)
    {
        return vars.hasVariable(var);
    }

    public ExprType getType(VariableSet vars)
    {
        return var.getType();
    }

    // assuming variable is already defined
    public ExprValue eval(VariableEnv env)
    {
        return env.getVariable(var);
    }
}