package expr;

import java.io.Serializable;
import java.lang.Math;

/**
 * UnaryOp
 * Enumerated type for unary operations.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
enum UnaryOp implements Serializable
{
    NOT ("NOT", ExprType.BOOLEAN) {
        public ExprValue apply(ExprValue value)
        {
            Serializable val;
            ExprType typ;

            val = ! (Boolean) value.getValue();
            typ = ExprType.BOOLEAN;

            return new ExprValue(val, typ);
        }
    },
    NEG ("-", ExprType.DOUBLE) {
        public ExprValue apply(ExprValue value)
        {
            Serializable val = null;
            ExprType typ = null;

            Number num = (Number) value.getValue();

            val = -num.doubleValue();
            typ = value.getType();

            return new ExprValue(val, typ);
        }
    },
    SQRT ("sqrt", ExprType.DOUBLE) {
        public ExprValue apply(ExprValue value)
        {
            Serializable val = null;
            ExprType typ = null;

            Number num = (Number) value.getValue();

            val = Math.sqrt(num.doubleValue());
            typ = ExprType.DOUBLE;

            return new ExprValue(val, typ);
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