import java.util.Map;

/**
 * sin class  extends UnaryExpression.
 */
public class Sin extends UnaryExpression {

    /**
     * instructor.
     *
     * @param e expression
     */
    public Sin(Expression e) {
        super(e);
    }

    /**
     * instructor.
     *
     * @param e double
     */
    public Sin(double e) {
        super(e);
    }

    /**
     * instructor.
     *
     * @param e string
     */
    public Sin(String e) {
        super(e);
    }

    /**
     * to string.
     *
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "sin(" + getExp().toString() + ")";
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
        double angle = Math.toRadians(getExp().evaluate(assignment));
        return Math.sin(angle);
    }

    /**
     * assign.
     *
     * @param var        the epression to be replaced
     * @param expression the expression we want to put instead
     * @return a new expression in which all occurrences of the variable
     * var are replaced with the provided expression
     */
    public Expression assign(String var, Expression expression) {

        Expression e = getExp().assign(var, expression);
        return new Sin(e);
    }

    /**
     * differentiate the expression.
     *
     * @param var differentiate according to this
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        Expression e = getExp().differentiate(var).simplify();
        Expression b = new Cos(getExp()).simplify();
        return new Mult(e, b);
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
            return new Num(Math.sin(Math.toRadians(angle)));
        }
        //simplify the inner expression and sin it
        return new Sin(getExp().simplify());
    }

    /**
     * Simplify expression. -Bonus.
     *
     * @return the expression
     */
    public Expression simplifyB() {
        if (simplify() instanceof Sin) {
            Sin p = (Sin) simplify();
            //sin(-x) ==> -sin(x)
            if (p.getExp().simplifyB() instanceof Neg) {
                Expression e = ((Neg) getExp().simplifyB()).getExp();
                return new Neg(new Sin(e.simplifyB()));
            }
            return new Sin(p.getExp().simplifyB());
        }
        //simplify the inner expression and sin it
        return simplify();
    }

    public Expression switchSinCos() {
        return new Cos(this.getExp().switchSinCos());
    }
}
