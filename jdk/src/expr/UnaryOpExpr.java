package expr;

/**
 * UnaryOpExpr
 * Represents expressions formed by the application of unary operations.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
class UnaryOpExpr extends Expr
{
    // go through each UnaryOp in order
    public static Expr parse(String parseString)
    {
        Expr temp;

        // return first parsing that works
        for (UnaryOp op : UnaryOp.values())
        {
            temp = tryOp(op, parseString);
            if (temp != null)
                return temp;
        }

        return null;
    }

    // try to parse parseString with op
    // try to parse the same unary operation from left as far as possible,
    // then call Expr.parse() on rightmost string
    public static Expr tryOp(UnaryOp op, String parseString)
    {
        final String opRegex = " *" + op.getName() + " *";

        Expr arg;
        String argString;

        // if parseString doesn't start with op return null
        if (!parseString.matches("^" + opRegex))
            return null;

        // else, locate the argument of op
        argString = parseString.replaceFirst(opRegex, "");

        // check if the argument is also an application of op
        arg = tryOp(op, argString);

        // if it isn't, try to parse it as a general expression
        if (arg == null)
            arg = Expr.parse(argString);

        // if it isn't a valid general expression, neither is parseString
        if (arg == null)
            return null;

        // otherwise, form application of op
        return new UnaryOpExpr(op, arg);
    }

    UnaryOp op;
    Expr    arg;

    protected UnaryOpExpr(UnaryOp op, Expr arg)
    {
        this.op  = op;
        this.arg = arg;
    }

    public boolean valid(VariableSet vars)
    {
        return arg.valid(vars)
            && op.applicable(vars, arg);
    }

    @Override
    public ExprType getType(VariableSet vars)
    {
        if (type == null)
            type = op.getReturnType(arg.getType(vars));
        return type;
    }

    public ExprValue eval(VariableEnv env)
    {
        return op.apply(arg.eval(env));
    }


}