package expr;

/**
 * Created by admin on 4/3/16.
 */
public interface VariableEnv
{
    public ExprValue getVariable(String name);
    public void addVariable(String name, String valueString);
}
