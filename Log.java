import java.util.Map;

/**
 * a log class extends BinaryExpression.
 */
public class Log extends BinaryExpression {

    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right expression
     */
    public Log(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right double
     */
    public Log(Expression e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right String
     */
    public Log(Expression e1, String e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 string
     * @param e2 right String
     */
    public Log(String e1, String e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right expression
     */
    public Log(double e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left string
     * @param e2 right expression
     */
    public Log(String e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right double
     */
    public Log(double e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left string
     * @param e2 right double
     */
    public Log(String e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right string
     */
    public Log(double e1, String e2) {
        super(e1, e2);
    }

    /**
     * to string.
     *
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "log(" + super.getLeft().toString() + ", " + super.getRight().toString() + ")";
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
        if (getLeft().evaluate(assignment) == 1) {
            throw new Exception("illegal log operation");
        }
        if (getLeft().evaluate(assignment) <= 0) {
            throw new Exception("illegal log operation");
        }
        if (getRight().evaluate(assignment) <= 0) {
            throw new Exception("reaching infinity");
        }
        return Math.log(super.getRight().evaluate(assignment)) / Math.log(super.getLeft().evaluate(assignment));
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
        return new Log(e1, e2);
    }

    /**
     * differentiate the expression.
     *
     * @param var differentiate according to this
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        Expression ln = new Log("e", getLeft()).simplify();
        Expression up = getRight().differentiate(var);
        Expression bottom = new Mult(getRight(), ln).simplify();
        return new Div(up, bottom);

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
        //careful !
        if (((left) && (getnumber(lefty) == 1))) {
            return new Log(lefty, righty);
        }
        if (((left) && (getnumber(lefty) <= 0))) {
            return new Log(lefty, righty);
        }
        if (((right) && (getnumber(righty) <= 0))) {
            return new Log(lefty, righty);
        }
        //all numbers
        if (right && left) {
            double num = (Math.log(getnumber(righty)) / (Math.log(getnumber(lefty))));
            return new Num(num);
        }
        //log(x,x) ==> 1
        if (isEquel(righty, lefty)) {
            return new Num(1);
        }


        return new Log(lefty, righty);
    }

    /**
     * Simplify expression. -Bonus.
     *
     * @return the expression
     */
    public Expression simplifyB() {
        if (simplify() instanceof Log) {
            Log p = (Log) simplify();
            Expression lefty = p.getLeft().simplifyB();
            Expression righty = p.getRight().simplifyB();
            //log(x,y^2) ==> 2*log(x,y)
            if (righty instanceof Pow) {
                return new Mult(((Pow) righty).getRight(), new Log(lefty, ((Pow) righty).getLeft())).simplifyB();
            }
            return new Log(lefty, righty);

        }
        return simplify();
    }
}
