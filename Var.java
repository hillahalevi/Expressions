import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * var class implements Expression.
 */
public class Var implements Expression {
    private String str;

    /**
     * constructor.
     *
     * @param val the variable
     */
    public Var(String val) {
        str = val;
    }

    /**
     * to string.
     *
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return str;
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
        //check if there is a match
        if (assignment.containsKey(this.str)) {
            return assignment.get(this.str);

        }
        //else, throw excepton!
        throw new Exception("no matching key");

    }

    /**
     * Evaluate the expression without provided assignment map.
     *
     * @return the evaluated expression
     * @throws Exception .
     */
    public double evaluate() throws Exception {
        throw new Exception("no matching key");
    }

    /**
     * get variables.
     *
     * @return a list of contains the var value.
     */
    public List<String> getVariables() {
        List<String> list = new ArrayList<String>();
        list.add(this.str);
        return list;
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
        if (this.str.equals(var)) {
            return expression;

        }

        return new Var(this.str);
    }


    /**
     * differentiate the expression.
     *
     * @param var differentiate according to this
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        if (this.str.equals(var)) {
            return new Num(1);

        }
        //treating it like a const.
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
    public Expression simplifyB() {
        return this;
    }

    @Override
    public Expression switchSinCos() {
        return this;
    }


}
