package expr;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ExprString
 * String containing expressions.
 * @author  Ata Deniz Aydin
 * @version 25/04/16
 */
public class ExprString implements Serializable
{
    public ArrayList<Expr> exprs;
    String exprString;

    public ExprString()
    {
        setString("");
    }

    public ExprString(String exprStr)
    {
        setString(exprStr);
    }

    // parse exprStr into list of exprs, to be printed in order
    // an expression is preceded by a $, encapsulated by parentheses if necessary
    public boolean setString(String exprStr)
    {
        Expr expr;

        exprString = exprStr;
        exprs = new ArrayList<Expr>();

        // find first instance of $ in exprStr
        // if no instance, convert rest of expression into literal expression and add to exprs
        // else, split exprStr at first $,
        // convert expression before $ into literal expression,
        // split the rest based on first instance of whitespace (or end) not inside parentheses
        // if the splitting returns array of one element, expression is malformed, set exprs to null
        // else add literal expression, parsed expression and rest into list

        return setStringAux(exprStr);
    }

    private boolean setStringAux(String exprStr)
    {
        String[] split;
        int startIndex;
        Expr expr;

        // find nearest $ sign
        startIndex = exprStr.indexOf("$");

        // no $ sign
        if (startIndex < 0)
        {
            exprs.add(new LiteralExpr(new ExprValue(exprStr, ExprType.STRING)));
            return true;
        }

        // $ sign escaped by \
        if (startIndex > 0 && exprStr.charAt(startIndex - 1) == '\\')
        {
            exprs.add(new LiteralExpr(new ExprValue(exprStr.substring(0, startIndex - 1) + "$", ExprType.STRING)));
            return setStringAux(exprStr.substring(startIndex + 1));
        }

        // $ sign at end
        if (startIndex == exprStr.length() - 1)
        {
            exprs = null;
            return false;
        }

        // add string until $ sign to list
        exprs.add(new LiteralExpr(new ExprValue(exprStr.substring(0, startIndex), ExprType.STRING)));

        // find whitespace or end of string outside parentheses after $ sign
        split = BinaryOpExpr.splitOp("[\\s$]", exprStr.substring(startIndex + 1));

        // malformed expression
        if (split == null)
        {
            exprs = null;
            return false;
        }

        expr = Expr.parse(split[0]);

        if (expr == null)
        {
            exprs = null;
            return false;
        }
        exprs.add(expr);

        return split.length == 1 || setStringAux(split[1]);
    }

    public boolean valid(VariableSet set)
    {
        if (exprs == null)
            return false;

        for (Expr expr : exprs)
            if (!expr.valid(set))
                return false;

        return true;
    }

    // return non-evaluated string
    public String toString()
    {
        return exprString;
    }

    // return string after evaluating expressions
    public String toString(VariableEnv env)
    {
        String res = "";

        if (exprs == null)
            return "";

        for (Expr expr : exprs)
            res = res + expr.eval(env);

        return res;
    }
}
