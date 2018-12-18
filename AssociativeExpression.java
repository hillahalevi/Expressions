import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Associative expression.
 */
public class AssociativeExpression extends BinaryExpression {
    /**
     * instructor.
     *
     * @param e1 left expression
     * @param e2 right expression
     */
    public AssociativeExpression(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * instructor.
     *
     * @param e1 left expression
     * @param e2 right double
     */
    public AssociativeExpression(Expression e1, double e2) {
        super(e1, e2);
    }

    /**
     * instructor.
     *
     * @param e1 left expression
     * @param e2 right String
     */
    public AssociativeExpression(Expression e1, String e2) {
        super(e1, e2);
    }

    /**
     * instructor.
     *
     * @param e1 string
     * @param e2 right String
     */
    public AssociativeExpression(String e1, String e2) {
        super(e1, e2);
    }

    /**
     * instructor.
     *
     * @param e1 left double
     * @param e2 right expression
     */
    public AssociativeExpression(double e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * instructor.
     *
     * @param e1 left string
     * @param e2 right expression
     */
    public AssociativeExpression(String e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * instructor.
     *
     * @param e1 left double
     * @param e2 right double
     */
    public AssociativeExpression(double e1, double e2) {
        super(e1, e2);
    }

    /**
     * instructor.
     *
     * @param e1 left string
     * @param e2 right double
     */
    public AssociativeExpression(String e1, double e2) {
        super(e1, e2);
    }

    /**
     * instructor.
     *
     * @param e1 left double
     * @param e2 right string
     */
    public AssociativeExpression(double e1, String e2) {
        super(e1, e2);
    }

    /**
     * Evaluate the expression using the variable values provided in the map.
     *
     * @param assignment the map
     * @return the result
     * @throws Exception if the expression
     *                   contains a variable which is not in the assignment
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return 0;
    }

    /**
     * assign.
     *
     * @param var        the epression to be replaced.
     * @param expression the expression we want to put instead
     * @return a new expression in which all occurrences of the variable
     * var are replaced with the provided expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return null;
    }

    /**
     * differentiate the expression.
     *
     * @param var differentiate according to this
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    @Override
    public Expression differentiate(String var) {
        return null;
    }

    /**
     * simplify.
     *
     * @return a simplified version of the current expression.
     */

    public Expression simplify() {
        return null;
    }

    /**
     * Simplify expression. -Bonus.
     *
     * @return the expression
     */
    public Expression simplifyB() {
        return null;
    }


    /**
     * switches the two sides of the expression.
     */
    public void switchExp() {
        //save the right side
        Expression expression = getRight();
        //set it to the unary Expression
        setRight(getLeft());
        //put what we saved on the left
        setLeft(expression);

    }

    /**
     * sorts the expression so the variables goes alphabtic ,numbers first,and complex
     * expression last.
     */
    public void sortedexp() {
        setLeft(getLeft().simplify());
        setRight(getRight().simplify());
        //cos/sin/neg+ something
        if (getLeft() instanceof UnaryExpression) {
            switchExp();
            return;


        }
        //y+x=x+y
        if (!isNnumber(getRight()) && !isNnumber(getLeft())) {

            //x+z +y = y + x+z
            if (getLeft() instanceof BinaryExpression && getRight() instanceof Var) {
                switchExp();
                return;

            }
            //single variables
            if (getRight() instanceof Var && getLeft() instanceof Var) {
                List<String> list = getVariables();
                //x+x
                if (list.size() == 1) {
                    return;
                }
                //place them alphabetic
                setLeft(new Var(list.get(0)));
                setRight(new Var(list.get(1)));

            }
        }

        //x+num=num+x
        if (!isNnumber(getLeft()) && isNnumber(getRight())) {
            switchExp();

        }

    }

    /**
     * sorts the expression so the variables goes alphabtic ,numbers first,and complex
     * expression last.
     */
    public void sortedexpB() {
        setLeft(getLeft().simplifyB());
        setRight(getRight().simplifyB());
        sortedexp();


    }
    /**
     * Combine fam Bonus ==> set the expression in the most convenient way.
     */
    public void combineFamB() {
    }
}
