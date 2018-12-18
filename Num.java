import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * a Num class implements Expression.
 */
public class Num implements Expression {
    private double v;

    /**
     * constructor.
     *
     * @param val the variable
     */
    public Num(double val) {
        if (val == -0) {
            val = 0;
        }
        v = val;
    }

    /**
     * to string.
     *
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "" + v;
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
        return v;
    }

    /**
     * Evaluate the expression without provided assignment map.
     *
     * @return the evaluated expression
     * @throws Exception -doesent throw exeption.
     */
    public double evaluate() throws Exception {
        return v;
    }

    /**
     * get variables.
     *
     * @return an empty list .
     */
    public List<String> getVariables() {
        return new ArrayList<String>();
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
        return new Num(v);
    }

    /**
     * differentiate the expression.
     *
     * @param var differentiate according to this
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        return new Num(0);
    }

    /**
     * simplify.
     *
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        return this;
    }

    /**
     * Simplify expression. -Bonus.
     *
     * @return the expression
     */
    @Override
    public Expression simplifyB() {
        return this;
    }

    @Override
    public Expression switchSinCos() {
        return this;
    }

}
