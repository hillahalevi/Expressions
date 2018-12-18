import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Binary expression class extends BaseExpression.
 */
abstract class BinaryExpression extends BaseExpression {

    private Expression left;
    private Expression right;

    /**
     * instructor.
     *
     * @param e1 left expression
     * @param e2 right expression
     */
    protected BinaryExpression(Expression e1, Expression e2) {
        left = e1;
        right = e2;
    }

    /**
     * instructor.
     *
     * @param e1 left expression
     * @param e2 right double
     */
    protected BinaryExpression(Expression e1, double e2) {
        left = e1;
        right = new Num(e2);
    }

    /**
     * instructor.
     *
     * @param e1 left expression
     * @param e2 right String
     */
    protected BinaryExpression(Expression e1, String e2) {
        left = e1;
        right = new Var(e2);
    }

    /**
     * instructor.
     *
     * @param e1 string
     * @param e2 right String
     */
    protected BinaryExpression(String e1, String e2) {
        left = (new Var((e1)));
        right = (new Var(e2));
    }

    /**
     * instructor.
     *
     * @param e1 left double
     * @param e2 right expression
     */
    protected BinaryExpression(double e1, Expression e2) {
        left = new Num(e1);
        right = e2;
    }

    /**
     * instructor.
     *
     * @param e1 left string
     * @param e2 right expression
     */
    protected BinaryExpression(String e1, Expression e2) {
        left = new Var(e1);
        right = e2;
    }

    /**
     * instructor.
     *
     * @param e1 left double
     * @param e2 right double
     */
    protected BinaryExpression(double e1, double e2) {
        left = new Num(e1);
        right = new Num(e2);
    }

    /**
     * instructor.
     *
     * @param e1 left string
     * @param e2 right double
     */
    protected BinaryExpression(String e1, double e2) {
        left = new Var(e1);
        right = new Num(e2);
    }

    /**
     * instructor.
     *
     * @param e1 left double
     * @param e2 right string
     */
    protected BinaryExpression(double e1, String e2) {
        left = new Num(e1);
        right = new Var(e2);
    }

    /**
     * geters.
     *
     * @return the left expression.
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * Sets left.
     *
     * @param le the le
     */
    protected void setLeft(Expression le) {
        this.left = le;
    }

    /**
     * geters.
     *
     * @return the right expression.
     */
    public Expression getRight() {
        return right;
    }

    /**
     * Sets right.
     *
     * @param ri the ri
     */
    protected void setRight(Expression ri) {
        this.right = ri;
    }

    /**
     * get variables.
     *
     * @return a sorted list of all existing variables in the expression.
     */
    public List<String> getVariables() {
        //create a list and adds the variables on the lest side.
        ArrayList<String> list = new ArrayList<String>(left.getVariables());
        //adds the right side
        list.addAll(right.getVariables());
        //new empty list
        ArrayList<String> finalist = new ArrayList<String>();
        for (String s : list) {
            //for each var of the expression
            if (!finalist.contains(s)) {
                //if its not on the list-add it!
                finalist.add(s);
            }
        }
        //sort it alphabetic
        Collections.sort(finalist);
        return finalist;
    }

    public Expression switchSinCos() {
        left = left.switchSinCos();
        right = right.switchSinCos();
        return this;

    }


}
