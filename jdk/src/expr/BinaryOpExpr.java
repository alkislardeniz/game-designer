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

    /**
     * BinaryOp
     * Represents binary operations.
     * @author  Ata Deniz Aydin
     * @version 03/04/16
     */
    interface BinaryOp
    {
        // public static BinaryOp getOp(String opName);
        public String getName();

        public boolean applicable(VariableSet vars, Expr arg1, Expr arg2);

        public ExprType getReturnType(ExprType arg1Type, ExprType arg2Type);

        public ExprValue apply(ExprValue obj1, ExprValue obj2);
    }

    /**
     * LogicOp
     * Represents logical operations.
     * @author  Ata Deniz Aydin
     * @version 03/04/16
     */
    enum LogicOp implements BinaryOp
    {
        OR  ("OR",  ExprType.BOOLEAN) {
            private boolean boolOp(boolean a, boolean b) { return a || b; }
        },
        AND ("AND", ExprType.BOOLEAN) {
            private boolean boolOp(boolean a, boolean b) { return a && b; }
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

        private boolean boolOp(boolean a, boolean b) { return false; }
    }

    /**
     * RelationOp
     * Represents relational operations comparing two objects.
     * @author  Ata Deniz Aydin
     * @version 03/04/16
     */
    enum RelationOp implements BinaryOp
    {
        EQ  ("=",  ExprType.ALL) {
            private boolean objOp(Object obj1, Object obj2)
            {
                return obj1.equals(obj2);
            }
        },
        NEQ ("!=", ExprType.ALL) {
            private boolean objOp(Object obj1, Object obj2)
            {
                return !obj1.equals(obj2);
            }
        },
        LT  ("<",  ExprType.DOUBLE) {
            private boolean objOp(Object obj1, Object obj2)
            {
                // if (typ1.equals(ExprType.INTEGER) && typ2.equals(ExprType.INTEGER))
                //     return (Integer) obj1 < (Integer) obj2;
                // else
                //     return (Double) obj1 < (Double) obj2;
                return ((Number) obj1).doubleValue() <  ((Number) obj2).doubleValue();
            }
        },
        GT  (">",  ExprType.DOUBLE) {
            private boolean objOp(Object obj1, Object obj2)
            {
                return ((Number) obj1).doubleValue() >  ((Number) obj2).doubleValue();
            }
        },
        LEQ ("<=", ExprType.DOUBLE) {
            private boolean objOp(Object obj1, Object obj2)
            {
                return ((Number) obj1).doubleValue() <= ((Number) obj2).doubleValue();
            }
        },
        GEQ (">=", ExprType.DOUBLE) {
            private boolean objOp(Object obj1, Object obj2)
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

        private boolean objOp(Object obj1, Object obj2)
        {
            return false;
        }
    }

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
}