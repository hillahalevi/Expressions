import java.util.Map;

/**
 * div class extends binary expression.
 */
public class Div extends BinaryExpression {
    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right expression
     */
    public Div(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right double
     */
    public Div(Expression e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right String
     */
    public Div(Expression e1, String e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 string
     * @param e2 right String
     */
    public Div(String e1, String e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right expression
     */
    public Div(double e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left string
     * @param e2 right expression
     */
    public Div(String e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right double
     */
    public Div(double e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left string
     * @param e2 right double
     */
    public Div(String e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right string
     */
    public Div(double e1, String e2) {
        super(e1, e2);
    }

    /**
     * to string.
     *
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "(" + super.getLeft().toString() + " / " + super.getRight().toString() + ")";
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
        if (getRight().evaluate(assignment) == 0) {
            throw new Exception("division of 0 is not allowed");
        }
        return super.getLeft().evaluate(assignment) / super.getRight().evaluate(assignment);
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
        return new Div(e1, e2);
    }

    /**
     * differentiate the expression.
     *
     * @param var differentiate according to this
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        Expression e = new Mult(getLeft().differentiate(var), getRight());
        Expression b = new Mult(getLeft(), getRight().differentiate(var));
        Expression up = new Minus(e, b);
        Expression bottom = new Pow(getRight(), 2);
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
        //x/0
        if (right && getnumber(righty) == 0) {
            return new Div(lefty,0);
        }
        if (left && getnumber(lefty) == 0) {
            return new Num(0);
        }
        //all numbers
        if (right && left) {
            return new Num(getnumber(lefty) / getnumber(righty));
        }
        //x/x
        if (isEquel(righty, lefty)) {
            return new Num(1);
        }
        //X/1
        if (((right) && (getnumber(righty) == 1))) {
            return lefty;
        }
        return new Div(lefty, righty);
    }

    /**
     * Simplify expression. -Bonus.
     *
     * @return the expression
     */
    public Expression simplifyB() {
        if (simplify() instanceof Div) {
            Div p = (Div) simplify();
            Expression lefty = p.getLeft().simplifyB();
            Expression righty = p.getRight().simplifyB();
            //xy/z
            if (lefty instanceof Mult) {
                if (isEquel(((Mult) lefty).getLeft(), righty)) {
                    return ((Mult) lefty).getRight();
                }
                if (isEquel(((Mult) lefty).getRight(), righty)) {
                    return ((Mult) lefty).getLeft();
                }
                //x*(y/z)
                Expression div = new Div(((Mult) lefty).getRight(), righty).simplifyB();
                Expression a = new Mult(((Mult) lefty).getLeft(), div);
                //y*(x/z)
                div = new Div(((Mult) lefty).getLeft(), righty).simplifyB();
                Expression b = new Mult(((Mult) lefty).getRight(), div);
                double lena = a.toString().length();
                double lenb = b.toString().length();
                if (lena < lenb) {
                    return a.simplifyB();
                }
                if (lena == lenb) {
                    return new Div(lefty, righty);
                }
                return b;
            }
            //x^z / y^t
            if (lefty instanceof Pow && righty instanceof Pow) {
                Expression e;
                if (isEquel(((Pow) lefty).getLeft(), ((Pow) righty).getLeft())) {
                    e = new Minus(((Pow) lefty).getRight(), ((Pow) righty).getRight());
                    return new Pow(((Pow) lefty).getLeft(), e).simplifyB();
                }
                if (isEquel(((Pow) lefty).getRight(), ((Pow) righty).getRight())) {
                    e = new Div(((Pow) lefty).getLeft(), ((Pow) righty).getLeft());
                    return new Pow(e, ((Pow) lefty).getRight()).simplifyB();
                }
            }
            //x^z /t
            if (lefty instanceof Pow) {
                if (isEquel(((Pow) lefty).getLeft(), righty)) {
                    return new Pow(((Pow) lefty).getLeft(), new Minus(((Pow) lefty).getRight(), 1)).simplifyB();
                }
            }
            //x/(y/z) ==> x*z/ y
            if (righty instanceof Div) {
                return new Div(new Mult(lefty, ((Div) righty).getRight()), ((Div) righty).getLeft()).simplifyB();
            }
            //x/z / y ==> x/y*z
            if (lefty instanceof Div) {
                Expression e = new Mult(((Div) lefty).getRight(), righty).simplifyB();
                return new Div(((Div) lefty).getLeft(), e);
            }
            return new Div(lefty, righty);

        }
        return simplify();
    }
}
