package expr;

import java.io.Serializable;

/**
 * LogicOp
 * Represents logical operations.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
enum LogicOp implements BinaryOp, Serializable
{
    OR  ("OR",  ExprType.BOOLEAN) {
        protected boolean boolOp(boolean a, boolean b) { return a || b; }
    },
    AND ("AND", ExprType.BOOLEAN) {
        protected boolean boolOp(boolean a, boolean b) { return a && b; }
    };

    public static BinaryOp getOp(String opName)
    {
        for (BinaryOp op : values())
            if (op.getName().equals(opName))
                return op;
        return null;
    }

    private String name;
    private ExprType argType;

    private LogicOp() {}

    private LogicOp(String name, ExprType argType)
    {
        this.name    = name;
        this.argType = argType;
    }

    public String getName() { return name; }

    public boolean applicable(VariableSet vars, Expr arg1, Expr arg2)
    {
        return arg1.getType(vars).isA(argType)
                && arg2.getType(vars).isA(argType);
    }

    public ExprType getReturnType(ExprType arg1Type, ExprType arg2Type)
    {
        // assuming arguments are valid, i.e. their types are both argType
        return argType;
    }

    public ExprValue apply(ExprValue obj1, ExprValue obj2)
    {
        boolean arg1 = (Boolean) obj1.getValue();
        boolean arg2 = (Boolean) obj2.getValue();
        boolean res;

        res = boolOp(arg1, arg2);

        return new ExprValue(res, ExprType.BOOLEAN);
    }

    protected boolean boolOp(boolean a, boolean b) { return false; }
}

