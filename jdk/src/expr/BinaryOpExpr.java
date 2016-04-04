package expr;

/**
 * BinaryOpExpr
 * Represents expressions formed by binary operations.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
class BinaryOpExpr extends Expr
{
    // TODO
    public static Expr parse(String parseString)
    {
        // locate first binary operation on parseString,
        // pursue it from the left as far as possible,
        // then call Expr.parse() on each branch discovered
        //
        return null;
    }

    BinaryOp op;
    Expr arg1, arg2;

    private BinaryOpExpr(BinaryOp op, Expr arg1, Expr arg2)
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