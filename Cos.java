import java.util.Map;

/**
 * Cos class  extends UnaryExpression.
 */
public class Cos extends UnaryExpression {

    /**
     * instructor.
     *
     * @param e expression
     */
    public Cos(Expression e) {
        super(e);
    }

    /**
     * instructor.
     *
     * @param e double
     */
    public Cos(double e) {
        super(e);
    }

    /**
     * instructor.
     *
     * @param e string
     */
    public Cos(String e) {
        super(e);
    }

    /**
     * to string.
     *
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "cos(" + getExp().toString() + ")";
    }

    /**
     * Evaluate the expression using the variable values provided in the map.
     *
     * @param assignment the map
     * @return the result
     * @throws Exception if the expression
     *                   contains a variable which is not in the assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(Math.toRadians(getExp().evaluate(assignment)));
    }

    /**
     * assign.
     *
     * @param var        the epression to be replaced.
     * @param expression the expression we want to put instead
     * @return a new expression in which all occurrences of the variable
     * var are replaced with the provided expression
     */
    public Expression assign(String var, Expression expression) {

        Expression e = getExp().assign(var, expression);
        return new Cos(e);
    }

    /**
     * differentiate the expression.
     *
     * @param var differentiate according to this
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        Expression e = getExp().differentiate(var);
        Expression b = new Sin(getExp());
        return new Neg(new Mult(b, e));
    }

    /**
     * simplify.
     *
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        boolean num = isNnumber(getExp());
        if (num) {
            //its a number -calculate it !
            double angle = getnumber(getExp());
            return new Num(Math.cos(Math.toRadians(angle)));
        }
        //simplify the inner expression and cos it
        return new Cos(getExp().simplify());
    }

    /**
     * Simplify expression. -Bonus.
     *
     * @return the expression
     */
    public Expression simplifyB() {
        if (simplify() instanceof Cos) {
            Cos p = (Cos) simplify();
            //cos(-x) ==> cos(x)
            if (p.getExp().simplifyB() instanceof Neg) {
                Expression e = ((Neg) getExp().simplifyB()).getExp();
                return new Cos(e.simplifyB());
            }
            //simplify the inner expression and cos it
            return new Cos(p.getExp().simplifyB());
        }
        return simplify();
    }

    public Expression switchSinCos() {
        return new Sin(this.getExp().switchSinCos());
    }


}
