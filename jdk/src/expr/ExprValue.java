package expr;

import java.io.Serializable;

/**
 * ExprValue
 * Value bound to a type.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
public class ExprValue implements Serializable
{
    Serializable value;
    ExprType type;

    public ExprValue(Serializable value, ExprType type)
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

    //
    public String toString()
    {
        return type.toString(value);
    }

    // whether this can be coerced to newType
    public boolean hasType(ExprType newType)
    {
        return type.isA(newType);
    }
}