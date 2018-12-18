import java.util.HashMap;
import java.util.Map;

/**
 * Basic expression class.
 */
abstract class BaseExpression implements Expression {

    /**
     * Evaluate the expression without provided assignment map.
     *
     * @return the evaluated expression
     * @throws Exception .
     */
    public double evaluate() throws Exception {
        //creates an empty map
        Map<String, Double> hillasMap = new HashMap<String, Double>();
        return evaluate(hillasMap);
    }

    /**
     * checks if an expression is a number or a complicated expression.
     *
     * @param expression the expression to be checked.
     * @return true(its a number) else false
     */
    public boolean isNnumber(Expression expression) {
        return expression.getVariables().isEmpty();
    }

    /**
     * if the expression is a number -return the number.
     * this metohod will only be activated if the expression is a number.
     *
     * @param expression the expression to be evluated.
     * @return the evaluates expression.
     */
    public double getnumber(Expression expression) {
        try {
            return expression.evaluate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * check if two simplifyed expressions are equel.
     *
     * @param left  expression.
     * @param right expression.
     * @return true or false
     */
    public boolean isEquel(Expression left, Expression right) {
        return right.toString().equals(left.toString());
    }

    public Expression switchSinCos() {
        return this;
    }


}
