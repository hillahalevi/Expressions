import java.util.Map;

/**
 * a pow class extends BinaryExpression.
 */
public class Pow extends BinaryExpression {


    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right expression
     */
    public Pow(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right double
     */
    public Pow(Expression e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right String
     */
    public Pow(Expression e1, String e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 string
     * @param e2 right String
     */
    public Pow(String e1, String e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right expression
     */
    public Pow(double e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left string
     * @param e2 right expression
     */
    public Pow(String e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right double
     */
    public Pow(double e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left string
     * @param e2 right double
     */
    public Pow(String e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right string
     */
    public Pow(double e1, String e2) {
        super(e1, e2);
    }

    /**
     * to string.
     *
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "(" + super.getLeft().toString() + "^" + super.getRight().toString() + ")";
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

        if (getLeft().evaluate(assignment) < 0 && getRight().evaluate(assignment) < 1 && getRight().evaluate() > 0) {
            throw new Exception("negative base");
        }
        return Math.pow(super.getLeft().evaluate(assignment), super.getRight().evaluate(assignment));
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
        return new Pow(e1, e2);
    }

    /**
     * differentiate the expression.
     *
     * @param var differentiate according to this
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        Expression b = new Div(getRight(), getLeft());
        Expression right = new Mult(getLeft().differentiate(var), b);
        Expression left = new Mult(getRight().differentiate(var), new Log("e", getLeft()));
        Expression almost = new Plus(right, left);
        return new Mult(new Pow(getLeft(), getRight()), almost);
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
        //x<0
        if (((left) && (getnumber(lefty) < 0))) {
            //y<1 y>0
            if ((((right) && (getnumber(righty) > 0) && (getnumber(righty) < 1)))) {
                new Pow(lefty, righty);
            }
        }
        //all numbers
        if (right && left) {
            return new Num(Math.pow(getnumber(lefty), getnumber(righty)));
        }

        return new Pow(lefty, righty);
    }

    /**
     * Simplify expression. -Bonus.
     *
     * @return the expression
     */
    public Expression simplifyB() {
        if (simplify() instanceof Pow) {
            Pow p = (Pow) simplify();
            Expression lefty = p.getLeft().simplifyB();
            Expression righty = p.getRight().simplifyB();
            boolean right = isNnumber(righty);
            boolean left = isNnumber(lefty);
            //x^(-y)==> 1/x^y
            if (((right) && (getnumber(righty) < 0))) {
                return new Div(1, new Pow(lefty, -1 * getnumber(righty))).simplifyB();
            }
            //0^x ==> 0
            if (((left) && (getnumber(lefty) == 0))) {
                return new Num(0);
            }
            //x^0 || 1^x ==> 1
            if (((right) && (getnumber(righty) == 0)) || (left) && (getnumber(lefty) == 1)) {
                return new Num(1);
            }
            //x^1 ==> x
            if (((right) && (getnumber(righty) == 1))) {
                return lefty;
            }
            //(x^y)^z ==> x^(y*z)
            if (lefty instanceof Pow) {
                Expression power = new Mult(((Pow) lefty).getRight().simplifyB(), righty);
                Expression base = ((Pow) lefty).getLeft();
                return new Pow(base.simplifyB(), power.simplifyB()).simplifyB();

            }
            return new Pow(lefty, righty);
        }
        return simplify();
    }
}
