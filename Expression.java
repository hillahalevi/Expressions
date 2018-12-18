import java.util.List;
import java.util.Map;

/**
 * an Expression interface.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided in the map.
     *
     * @param assignment the map
     * @return the result
     * @throws Exception if the expression                   contains a variable which is not in the assignment
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Evaluate the expression without provided assignment map.
     *
     * @return the evaluated expression
     * @throws Exception .
     */
    double evaluate() throws Exception;

    /**
     * get variables.
     *
     * @return a sorted list of all existing variables in the expression.
     */
    List<String> getVariables();

    /**
     * to string.
     *
     * @return a nice string representation of the expression.
     */
    String toString();

    /**
     * assign.
     *
     * @param var        the epression to be replaced.
     * @param expression the expression we want to put instead
     * @return a new expression in which all occurrences of the variable var are replaced with the provided expression
     */
    Expression assign(String var, Expression expression);

    /**
     * differentiate the expression.
     *
     * @param var differentiate according to this
     * @return the expression tree resulting from differentiating the current expression relative to variable `var`.
     */
    Expression differentiate(String var);

    /**
     * simplify.
     *
     * @return a simplified version of the current expression.
     */
    Expression simplify();

    /**
     * Simplify expression. -Bonus.
     *
     * @return the expression
     */
    Expression simplifyB();

    Expression switchSinCos();


}