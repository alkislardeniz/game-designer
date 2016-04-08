package expr;

/**
 * VariableEnv
 * Stores bindings for variables.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
public interface VariableEnv
{
    public ExprValue getVariable(Var var);
    public void addVariable(Var var, Expr value);
}
