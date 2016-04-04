package expr;

/**
 * ArithOp
 * Represents arithmetic operations on numbers.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
// TODO
enum ArithOp implements BinaryOp
{
    ADD ("+") {
        public ExprValue apply(ExprValue obj1, ExprValue obj2)
        {
            double res = ((Number) obj1.getValue()).doubleValue() + ((Number) obj1.getValue()).doubleValue();

            // if
            return null;
        }
    },
    SUB ("-") {
        public ExprValue apply(ExprValue obj1, ExprValue obj2)
        {
            return null;
        }
    },
    MUL ("*") {
        public ExprValue apply(ExprValue obj1, ExprValue obj2)
        {
            return null;
        }
    },
    DIV ("/") {
        public ExprValue apply(ExprValue obj1, ExprValue obj2)
        {
            return null;
        }
    },
    REM ("%") {
        public ExprValue apply(ExprValue obj1, ExprValue obj2)
        {
            return null;
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
        return null;
    }
}