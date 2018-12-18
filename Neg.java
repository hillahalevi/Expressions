
import java.util.Map;

/**
 * neg class  extends UnaryExpression.
 */
public class Neg extends UnaryExpression {
    /**
     * instructor.
     *
     * @param e expression
     */
    public Neg(Expression e) {
        super(e);
    }

    /**
     * instructor.
     *
     * @param e double
     */
    public Neg(double e) {
        super(e);
    }

    /**
     * instructor.
     *
     * @param e string
     */
    public Neg(String e) {
        super(e);
    }

    /**
     * to string.
     *
     * @return a nice string representation of the expression.
     */
    public String toString() {

        return "(-" + getExp().toString() + ")";
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

        return -getExp().evaluate(assignment);
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
        return new Neg(e);
    }

    /**
     * differentiate the expression.
     *
     * @param var differentiate according to this
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        return new Neg(getExp().differentiate(var));

    }


    /**
     * simplify.
     *
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        //simplify inner expression.
        Expression e = getExp().simplify();
        return new Neg(e);
    }

    /**
     * Simplify expression. -Bonus.
     *
     * @return the expression
     */
    public Expression simplifyB() {
        Expression e = getExp().simplifyB();
        //avoiding --x
        if (isNnumber(e)) {
            return new Num(getnumber(e) * -1);
        }
        //avoiding --x
        if (isEquel(this, (new Neg(new Neg(e))))) {
            return e;
        }
        return simplify();
    }
}
