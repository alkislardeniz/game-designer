package expr;

/**
 * VariableEnv
 * Stores bindings for variables.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
public interface VariableEnv
{
    public ExprValue getVariable(String name);
    public void addVariable(String name, Expr value);
}
