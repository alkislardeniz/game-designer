package expr.tests;

import expr.ExprString;

/**
 * Created by admin on 4/25/16.
 */
public class ExprStringTest
{
    public static void main(String[] args)
    {
        ExprString expr = new ExprString();

        expr.setString("$\"$\"");

        System.out.println(expr.toString(null));
    }
}
