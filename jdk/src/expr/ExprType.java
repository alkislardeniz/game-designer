package expr;

/**
 * ExprType
 * Enumerated type for expressions.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
public enum ExprType
{
    BOOLEAN (false),
    INTEGER (0) {
        public String toString(Object obj)
        {
            return ((Number) obj).intValue() + "";
        }
    },
    DOUBLE (0),
    STRING (""),
    ALL (null),
    NONE (null);

    Object initialValue;

    ExprType(Object init)
    {
        initialValue = init;
    }

    public Object getInitialValue()
    {
        return initialValue;
    }

    // whether newType can be substituted as a member of this
    public boolean isA(ExprType newType)
    {
        return equals(NONE)
            || newType.equals(ALL)
            || equals(newType)
            || (equals(INTEGER) && newType.equals(DOUBLE));
    }

    public ExprType max(ExprType other)
    {
        if (isA(other))
            return other;
        if (other.isA(this))
            return this;
        return null;
    }

    // called from toString() of ExprValue
    public String toString(Object obj)
    {
        return obj.toString();
    }
}