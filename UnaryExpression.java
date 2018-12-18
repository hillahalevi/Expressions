import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Unary expression class extends BaseExpression.
 */
abstract class UnaryExpression extends BaseExpression {
    private Expression exp;

    /**
     * instructor.
     *
     * @param e expression
     */
    protected UnaryExpression(Expression e) {
        exp = e;
    }

    /**
     * instructor.
     *
     * @param e double
     */
    protected UnaryExpression(double e) {
        exp = new Num(e);
    }


    /**
     * Instantiates a new Unary expression.
     *
     * @param e the e
     */
    protected UnaryExpression(String e) {
        exp = new Var(e);
    }


    /**
     * Gets exp.
     *
     * @return the exp
     */
    public Expression getExp() {
        return exp;
    }

    public Expression switchSinCos() {
       exp= exp.switchSinCos();
        return this;
    }
    /**
     * get variables.
     *
     * @return a sorted list of all existing variables in the expression.
     */
    public List<String> getVariables() {

        ArrayList<String> list = new ArrayList<String>();
        for (String s : exp.getVariables()) {
            //for each item of the expression
            if (!list.contains(s)) {
                //if its not on the list-add it!
                list.add(s);
            }
        }
        //sort it alphabetic
        Collections.sort(list);
        return list;
    }
}
