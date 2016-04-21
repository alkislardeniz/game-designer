package expr;

/**
 * Binding
 * Binds a variable to a value.
 * @author  Ata Deniz Aydin
 * @version 15/04/16
 */
public class Binding
{
    Var var;
    ExprValue val;

    public Binding(Var var)
    {
        this.var = var;
        val = new ExprValue(var.getType().getInitialValue(), var.getType());
    }

    public Binding(Var var, ExprValue val)
    {
        this.var = var;
        this.val = val;
    }

    public String toString()
    {
        return var.toString();
    }

    public boolean equals(Object other)
    {
        return other != null
            && other instanceof Binding
            && ((Binding) other).var.equals(var);
    }

    public Var getVar() { return var; }

    public ExprValue getValue() { return val; }

    public boolean setValue(ExprValue newValue)
    {
        if (var.setType(newValue.getType()))
        {
            val = newValue;
            return true;
        }

        return false;
    }

    public boolean valid()
    {
        return var != null
            && val != null
            && val.getType().isA(var.getType());
    }
}
