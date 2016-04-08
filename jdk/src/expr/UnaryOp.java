package expr;

/**
 * UnaryOp
 * Enumerated type for unary operations.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
enum UnaryOp
{
    NOT ("NOT", ExprType.BOOLEAN) {
        public ExprValue apply(ExprValue value)
        {
            Object  val;
            ExprType typ;

            val = ! (Boolean) value.getValue();
            typ = ExprType.BOOLEAN;

            return new ExprValue(val, typ);
        }
    },
    NEG ("-", ExprType.DOUBLE) {
        public ExprValue apply(ExprValue value)
        {
            Object  val  = null;
            ExprType typ = null;

            Number num = (Number) value.getValue();

            if (value.getType() == ExprType.INTEGER)
            {
                val = -num.intValue();
                typ = ExprType.INTEGER;
            }
            else if (value.getType() == ExprType.DOUBLE)
            {
                val = -num.doubleValue();
                typ = ExprType.DOUBLE;
            }

            if (val != null && typ != null)
                return new ExprValue(val, typ);

            return null;
        }
    };

    public static UnaryOp getOp(String opName)
    {
        for (UnaryOp op : values())
            if (op.name.equals(opName))
                return op;
        return null;
    }

    String   name;
    ExprType argType;

    UnaryOp(String name, ExprType argType)
    {
        this.name    = name;
        this.argType = argType;
    }

    public String getName() { return name; }

    public boolean applicable(VariableSet vars, Expr arg)
    {
        return arg.getType(vars).isA(argType);
    }

    public ExprType getReturnType(ExprType argType)
    {
        // neither NOT nor NEG change types of arguments
        return argType;
    }

    // assuming applicable() returns true
    public ExprValue apply(ExprValue value)
    {
        return null;
    }
}