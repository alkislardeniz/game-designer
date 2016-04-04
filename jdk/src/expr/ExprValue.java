package expr;

/**
 * ExprValue
 * Value bound to a type.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
public class ExprValue
{
    Object   value;
    ExprType type;

    public ExprValue(Object value, ExprType type)
    {
        this.value = value;
        this.type  = type;
    }

    public Object getValue() { return value; }
    public ExprType getType() { return type; }

    // for coercion of arguments
    public boolean setType(ExprType newType)
    {
        if (type.isA(newType))
        {
            type = newType;
            return true;
        }
        return false;
    }

    // whether this can be coerced to newType
    public boolean hasType(ExprType newType)
    {
        return type.isA(newType);
    }
}