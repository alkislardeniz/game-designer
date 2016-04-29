package expr;

import java.io.Serializable;

/**
 * RelationOp
 * Represents relational operations comparing two objects.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
enum RelationOp implements BinaryOp, Serializable
{
    EQ  ("=",  ExprType.ALL) {
        protected boolean objOp(Object obj1, Object obj2)
        {
            return obj1.equals(obj2);
        }
    },
    NEQ ("!=", ExprType.ALL) {
        protected boolean objOp(Object obj1, Object obj2)
        {
            return !obj1.equals(obj2);
        }
    },
    LT  ("<",  ExprType.DOUBLE) {
        protected boolean objOp(Object obj1, Object obj2)
        {
            // if (typ1.equals(ExprType.INTEGER) && typ2.equals(ExprType.INTEGER))
            //     return (Integer) obj1 < (Integer) obj2;
            // else
            //     return (Double) obj1 < (Double) obj2;
            return ((Number) obj1).doubleValue() <  ((Number) obj2).doubleValue();
        }
    },
    GT  (">",  ExprType.DOUBLE) {
        protected boolean objOp(Object obj1, Object obj2)
        {
            return ((Number) obj1).doubleValue() >  ((Number) obj2).doubleValue();
        }
    },
    LEQ ("<=", ExprType.DOUBLE) {
        protected boolean objOp(Object obj1, Object obj2)
        {
            return ((Number) obj1).doubleValue() <= ((Number) obj2).doubleValue();
        }
    },
    GEQ (">=", ExprType.DOUBLE) {
        protected boolean objOp(Object obj1, Object obj2)
        {
            return ((Number) obj1).doubleValue() >= ((Number) obj2).doubleValue();
        }
    };

    // perhaps get rid of repetitive code by putting the static method in BinaryOp
    public static BinaryOp getOp(String opName)
    {
        for (BinaryOp op : values())
            if (op.getName().equals(opName))
                return op;
        return null;
    }

    String   name;
    ExprType args;

    private RelationOp() {}

    private RelationOp(String name, ExprType args)
    {
        this.name = name;
        this.args = args;
    }

    public String getName() { return name; }

    public boolean applicable(VariableSet vars, Expr arg1, Expr arg2)
    {
        return arg1.getType(vars).isA(args)
                && arg2.getType(vars).isA(args);
    }

    public ExprType getReturnType(ExprType arg1Type, ExprType arg2Type)
    {
        return ExprType.BOOLEAN;
    }

    public ExprValue apply(ExprValue obj1, ExprValue obj2)
    {
        return new ExprValue(objOp(obj1.getValue(), obj2.getValue()), ExprType.BOOLEAN);
    }

    protected boolean objOp(Object obj1, Object obj2)
    {
        return true;
    }
}

