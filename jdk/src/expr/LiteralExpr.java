package expr;

/**
 * LiteralExpr
 * Class representing literal expressions
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
class LiteralExpr extends Expr
{
    // return non-null LiteralExpr if it represents a literal object
    public static Expr parse(String parseString)
    {
        Object   val = null;
        ExprType typ = null;

        // check for boolean
        if (parseString.equals("true"))
        {
            val = Boolean.TRUE;
            typ = ExprType.BOOLEAN;
        }
        if (parseString.equals("false"))
        {
            val = Boolean.FALSE;
            typ = ExprType.BOOLEAN;
        }

        // check for integer/double
        try
        {
            val = Integer.valueOf(parseString);
            typ = ExprType.INTEGER;
        }
        catch (NumberFormatException e)
        {
            try
            {
                val = Double.valueOf(parseString);
                typ = ExprType.DOUBLE;
            } catch (NumberFormatException e1) {}
        }

        // check for string, i.e. starts and ends with quotation marks,
        // and doesn't contain any quotation mark inside
        if (parseString.matches("\"[^\"]*\""))
        {
            val = parseString.substring(1, parseString.length() - 1);
            typ = ExprType.STRING;
        }

        if (val != null && typ != null)
            return new LiteralExpr(new ExprValue(val, typ));

        return null;
    }

    ExprValue value;

    private LiteralExpr(ExprValue value) // memoize
    {
        this.value = value;
    }

    // equals()

    // possibly add random expressions

    public String toString()
    {
        return value.getValue().toString();
    }

    public boolean valid(VariableSet vars)
    {
        return true;
    }

    public ExprType getType(VariableSet vars)
    {
        return value.getType();
    }

    public ExprValue eval(VariableEnv env)
    {
        return value;
    }
}