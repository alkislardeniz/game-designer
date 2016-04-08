package expr;

/**
 * BinaryOpExpr
 * Represents expressions formed by binary operations.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
class BinaryOpExpr extends Expr
{
    // WIP
    public static Expr parse(String parseString)
    {
        Expr temp;

        // first go through LogicOp, then RelationOp, then ArithOp
        temp = tryOps(LogicOp.values(), parseString);
        if (temp == null)
            temp = tryOps(RelationOp.values(), parseString);
        if (temp == null)
            temp = tryOps(ArithOp.values(), parseString);

        return temp;
    }

    private static Expr tryOps(BinaryOp[] values, String parseString)
    {
        Expr temp;

        // return first parsing that works
        for (BinaryOp op : values)
        {
            temp = tryOp(op, parseString);
            if (temp != null)
                return temp;
        }

        return null;
    }

    // parse parseString left-associatively
    public static Expr tryOp(BinaryOp op, String parseString)
    {
        final String opRegex = " *" + op.getName() + " *";

        Expr temp;
        String[] tokens;

        // if parseString doesn't contain op return null
        if (!parseString.matches(opRegex))
            return null;

        // else, locate the two arguments of op
        tokens = parseString.split(opRegex, 2);

        // try to parse the 2nd argument
        temp = tryOp(op, tokens[1]);

        if (temp == null)
            temp = Expr.parse(tokens[1]);

        if (temp == null)
            return null;

        // otherwise, form application of op
        return new BinaryOpExpr(op, Expr.parse(tokens[0]), temp);
    }

    BinaryOp op;
    Expr arg1, arg2;

    protected BinaryOpExpr(BinaryOp op, Expr arg1, Expr arg2)
    {
        this.op   = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public boolean valid(VariableSet vars)
    {
        return arg1.valid(vars)
                && arg2.valid(vars)
                && op.applicable(vars, arg1, arg2);
    }

    public ExprType getType(VariableSet vars)
    {
        if (type == null)
            type = op.getReturnType(arg1.getType(vars), arg2.getType(vars));
        return type;
    }

    public ExprValue eval(VariableEnv env)
    {
        return op.apply(arg1.eval(env), arg2.eval(env));
    }
}