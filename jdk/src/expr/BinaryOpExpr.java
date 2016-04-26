package expr;

import java.util.regex.Pattern;

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
        final String opRegex = "\\s*" + Pattern.quote(op.getName()) + "\\s*";

        Expr arg1, arg2;
        String[] tokens;

        // else, locate the two arguments of op
        tokens = splitOp(opRegex, parseString);

        // System.out.println(parseString + " , " + op + " : " + java.util.Arrays.toString(tokens));

        // if parseString doesn't contain op return null
        if (tokens.length < 2 || tokens[0].isEmpty())
            return null;

        // try to parse the 2nd argument
        arg1 = Expr.parse(tokens[0]);
        arg2 = tryOp(op, tokens[1]);

        if (arg2 == null)
            arg2 = Expr.parse(tokens[1]);

        if (arg1 == null || arg2 == null)
            return null;

        // otherwise, form application of op
        return new BinaryOpExpr(op, arg1, arg2);
    }

    // split parseString based on regex, taking care of parentheses
    public static String[] splitOp(String regex, String parseString)
    {
        int i = 0;
        String start = "";
        char c;

        // do not match at first
        do
        {
            c = parseString.charAt(0);

            if (c == '(')
                i++;
            else if (c == ')')
                i--;

            start = start + c;
            parseString = parseString.substring(1);
        } while (!parseString.isEmpty() && (i != 0 || !parseString.matches(regex + ".*")));

        // if parentheses unmatched
        if (i != 0)
            return null;

        if (parseString.isEmpty())
            return new String[] {start};

        return new String[] {start, parseString.replaceFirst(regex, "")};
    }

    BinaryOp op;
    Expr arg1, arg2;

    protected BinaryOpExpr(BinaryOp op, Expr arg1, Expr arg2)
    {
        this.op   = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public String toString()
    {
        return "(" + arg1 + " " + op.getName() + " " + arg2 + ")";
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