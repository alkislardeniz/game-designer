package expr;

/**
 * ArithOp
 * Represents arithmetic operations on numbers.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
enum ArithOp implements BinaryOp
{
    ADD ("+") {
        protected Number applyOp(double arg1, double arg2)
        {
            return arg1 + arg2;
        }
    },
    SUB ("-") {
        protected Number applyOp(double arg1, double arg2)
        {
            return arg1 - arg2;
        }
    },
    MUL ("*") {
        protected Number applyOp(double arg1, double arg2)
        {
            return arg1 * arg2;
        }
    },
    DIV ("/") {
        protected Number applyOp(double arg1, double arg2)
        {
            return arg1 / arg2;
        }
    },
    REM ("%") {
        protected Number applyOp(double arg1, double arg2)
        {
            return arg1 % arg2;
        }
    };

    String name;

    private ArithOp(String name) { this.name = name; }

    public String getName() { return name; }

    public boolean applicable(VariableSet vars, Expr arg1, Expr arg2)
    {
        return arg1.getType(vars).isA(ExprType.DOUBLE)
                && arg2.getType(vars).isA(ExprType.DOUBLE);
    }

    public ExprType getReturnType(ExprType arg1Type, ExprType arg2Type)
    {
        return arg1Type.max(arg2Type);
    }

    public ExprValue apply(ExprValue obj1, ExprValue obj2)
    {
        Number num1, num2, res;

        num1 = (Number) obj1.getValue();
        num2 = (Number) obj2.getValue();

        res = applyOp(num1.doubleValue(), num2.doubleValue());

        return new ExprValue(res, getReturnType(obj1.getType(), obj2.getType()));
    }

    protected Number applyOp(double arg1, double arg2) { return null; }
}