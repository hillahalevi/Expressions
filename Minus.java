import java.util.Map;

/**
 * a minus class extends BinaryExpression.
 */
public class Minus extends BinaryExpression {

    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right expression
     */
    public Minus(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right double
     */
    public Minus(Expression e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right String
     */
    public Minus(Expression e1, String e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 string
     * @param e2 right String
     */
    public Minus(String e1, String e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right expression
     */
    public Minus(double e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left string
     * @param e2 right expression
     */
    public Minus(String e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right double
     */
    public Minus(double e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left string
     * @param e2 right double
     */
    public Minus(String e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right string
     */
    public Minus(double e1, String e2) {
        super(e1, e2);
    }

    /**
     * to string.
     *
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "(" + super.getLeft().toString() + " - " + super.getRight().toString() + ")";
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
        return super.getLeft().evaluate(assignment) - super.getRight().evaluate(assignment);
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
        Expression e1 = getLeft().assign(var, expression);
        Expression e2 = getRight().assign(var, expression);
        return new Minus(e1, e2);
    }

    /**
     * differentiate the expression.
     *
     * @param var differentiate according to this
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        Expression e1 = getLeft().differentiate(var);
        Expression e2 = getRight().differentiate(var);
        return new Minus(e1, e2);

    }

    /**
     * simplify.
     *
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        Expression lefty = getLeft().simplify();
        Expression righty = getRight().simplify();
        boolean right = isNnumber(righty);
        boolean left = isNnumber(lefty);
        //all numbers
        if (right && left) {
            return new Num(getnumber(lefty) - getnumber(righty));
        }
        //X-X=0
        if (isEquel(righty, lefty)) {
            return new Num(0);
        }
        //X-0=x
        if (((right) && (getnumber(righty) == 0))) {
            return lefty;
        }
        //0-x=x
        if ((left) && (getnumber(lefty) == 0)) {
            return new Neg(righty).simplify();
        }


        return new Minus(lefty, righty);

    }

    /**
     * Simplify expression. -Bonus.
     *
     * @return the expression
     */
    @Override
    public Expression simplifyB() {
        if (simplify() instanceof Minus) {
            Minus p = (Minus) simplify();
            Expression lefty = p.getLeft().simplifyB();
            Expression righty = p.getRight().simplifyB();
            boolean left = isNnumber(lefty);
            //X - (-k) ==> x+k
            if (righty instanceof Neg) {
                return new Plus(lefty, ((Neg) righty).getExp()).simplifyB();
            }
            if (righty instanceof Num && getnumber(righty) < 0) {
                return new Plus(lefty, getnumber(righty) * -1).simplifyB();
            }
            //1-sin^2 ==> cos^2 || 1-cos^2 ==>sin^2
            if ((left) && (getnumber(lefty) == 1) && righty instanceof Pow) {
                if (isNnumber(((Pow) righty).getRight()) && getnumber(((Pow) righty).getRight()) == 2) {
                    if (((Pow) righty).getLeft() instanceof Sin) {
                        return new Pow(new Cos(((Sin) ((Pow) righty).getLeft()).getExp()), 2);
                    }
                    if (((Pow) righty).getLeft() instanceof Cos) {
                        return new Pow(new Sin(((Cos) ((Pow) righty).getLeft()).getExp()), 2);
                    }
                }
            }
            return new Minus(lefty, righty);
        }
        return simplify();
    }
}
