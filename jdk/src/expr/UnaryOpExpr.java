package expr;

/**
 * UnaryOpExpr
 * Represents expressions formed by the application of unary operations.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
class UnaryOpExpr extends Expr
{
    // TODO
    public static Expr parse(String parseString)
    {
        // try to parse the same unary operation from left as far as possible,
        // then call Expr.parse() on rightmost string

        return null;
    }

    UnaryOp op;
    Expr    arg;

    private UnaryOpExpr(UnaryOp op, Expr arg)
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