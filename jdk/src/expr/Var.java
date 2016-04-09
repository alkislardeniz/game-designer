package expr;

/**
 * Var
 * A variable with a type.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
// static type checking
public class Var
{
    String name;
    ExprType type;

    // TODO check for valid name
    public Var(String name)
    {
        this.name = name;
        this.type = ExprType.NONE;
    }

    public String   getName() { return name; }
    public ExprType getType() { return type; }

    // called after addition of assignment screen
    public boolean setType(ExprType newType)
    {
        if (type.isA(newType))
        {
            type = newType;
            return true;
        }
        return false;
    }

    public boolean equals(Object other)
    {
        return other.toString().equals(toString());
    }

    public String toString()
    {
        return name;
    }

    // whether this can be coerced to newType
    public boolean hasType(ExprType newType)
    {
        return type.isA(newType);
    }
}