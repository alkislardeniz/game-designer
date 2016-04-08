package expr;

/**
 * BinaryOp
 * Represents binary operations.
 * @author  Ata Deniz Aydin
 * @version 03/04/16
 */
interface BinaryOp
{
    public String getName();

    public boolean applicable(VariableSet vars, Expr arg1, Expr arg2);

    // assumes applicable returns true
    public ExprType getReturnType(ExprType arg1Type, ExprType arg2Type);

    // assumes applicable returns true
    public ExprValue apply(ExprValue obj1, ExprValue obj2);
}