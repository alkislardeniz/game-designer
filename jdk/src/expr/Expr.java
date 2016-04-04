package expr;

/**
 * Expr
 * Parses and evaluates expressions.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
// parse string expression into tree, evaluate its value
public abstract class Expr
{
    // TODO
    // return null if parseString not a valid Expr, a non-null Expr otherwise
    public static Expr parse(String parseString)
    {
        // go through each subclass in order, seeing if they are not null
        return null;
    }

    ExprType type;

    public Expr()
    {
        type = null;
    }

    // return whether the constructed expression is valid within the game
    // use for error checking while editing, possibly incorporate under parse()
    public abstract boolean valid(VariableSet vars);

    // assuming expression is valid
    public ExprType getType(VariableSet vars)
    {
        return type;
    }

    // return the result of evaluating the parse tree in the current environment
    // assuming valid() is already true
    public abstract ExprValue eval(VariableEnv env);
}
