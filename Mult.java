
import java.util.Map;

/**
 * a mult class extends AssociativeExpression.
 */
public class Mult extends AssociativeExpression {


    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right expression
     */
    public Mult(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right double
     */
    public Mult(Expression e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right String
     */
    public Mult(Expression e1, String e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 string
     * @param e2 right String
     */
    public Mult(String e1, String e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right expression
     */
    public Mult(double e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left string
     * @param e2 right expression
     */
    public Mult(String e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right double
     */
    public Mult(double e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left string
     * @param e2 right double
     */
    public Mult(String e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right string
     */
    public Mult(double e1, String e2) {
        super(e1, e2);
    }

    /**
     * to string.
     *
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "(" + super.getLeft().toString() + " * " + super.getRight().toString() + ")";
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
        return super.getLeft().evaluate(assignment) * super.getRight().evaluate(assignment);
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
        return new Mult(e1, e2);
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
        return new Plus(e, b);
    }

    /**
     * Combine fam ==> set the expression in the most convenient way.
     */
    public void combineFam() {
        sortedexp();
        //pv*cx
        if ((getRight() instanceof Mult) && getLeft() instanceof Mult) {
            //p*c
            Expression v1 = new Mult(((Mult) getLeft()).getLeft(), ((Mult) getRight()).getLeft()).simplify();
            //v*x
            Expression v2 = new Mult(((Mult) getLeft()).getRight(), ((Mult) getRight()).getRight()).simplify();
            //pc *vx
            Expression fin = new Mult(v1, v2).simplify();
            //if pvcx is longer than pcvx ==> set the expression
            if (this.toString().length() > fin.toString().length()) {
                setLeft(v1);
                setRight(v2);
            }


        }


    }

    /**
     * simplify.
     *
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        Mult m = new Mult(getLeft().simplify(), getRight().simplify());
        m.combineFam();
        m.sortedexp();
        Expression lefty = m.getLeft();
        Expression righty = m.getRight();
        boolean right = isNnumber(righty);
        boolean left = isNnumber(lefty);
        //all numbers calculate it .
        if (right && left) {
            return new Num(getnumber(righty) * getnumber(lefty));
        }
        //0*x=0
        if ((left) && (getnumber(lefty) == 0)) {
            return new Num(0);
        }
        //1*x=x
        if ((left) && (getnumber(lefty) == 1)) {
            return righty;
        }
        return m;

    }


    /**
     * Combine fam Bonus ==> set the expression in the most convenient way.
     */
    public void combineFamB() {
        sortedexpB();
        //pv*cx
        if ((getRight() instanceof Mult) && getLeft() instanceof Mult) {
            //p*c
            Expression v1 = new Mult(((Mult) getLeft()).getLeft(), ((Mult) getRight()).getLeft()).simplifyB();
            //v*x
            Expression v2 = new Mult(((Mult) getLeft()).getRight(), ((Mult) getRight()).getRight()).simplifyB();
            //pc *vx
            Expression fin = new Mult(v1, v2).simplifyB();
            //if pvcx is longer than pcvx ==> set the expression
            if (this.toString().length() > fin.toString().length()) {
                setLeft(v1);
                setRight(v2);
            }


        }


    }

    /**
     * Simplify expression. -Bonus.
     *
     * @return the expression
     */
    public Expression simplifyB() {
        if (simplify() instanceof Mult) {
            Mult p = (Mult) simplify();
            p.combineFamB();
            p.sortedexpB();
            Expression lefty = p.getLeft().simplifyB();
            Expression righty = p.getRight().simplifyB();
            //BONUS! x*x=x^2
            if (isEquel(righty, lefty)) {
                return new Pow(lefty, 2).simplifyB();
            }
            //BONUS! x*(y/x) ==> y
            if (righty instanceof Div && isEquel(((Div) righty).getRight(), lefty)) {
                return ((Div) righty).getLeft().simplifyB();
            }
            //BONUS! x*(x/y) ==> x^2/y
            if (righty instanceof Div && isEquel(((Div) righty).getLeft(), lefty)) {
                return new Div(new Pow(lefty, 2), ((Div) righty).getRight()).simplifyB();
            }
            //BONUS! x*(y/z) ==> xy/z
            if (righty instanceof Div) {
                Expression c = new Mult(lefty, ((Div) righty).getLeft()).simplifyB();
                return new Div(c, ((Div) righty).getRight()).simplifyB();
            }
            //BONUS! x^z * x^y ==> x^(y+z) ||x^y * z^y ==> (xz)^y
            if (righty instanceof Pow && lefty instanceof Pow) {
                Expression e;
                // x^z * x^y ==> x^(y+z)
                if (isEquel(((Pow) righty).getLeft(), ((Pow) lefty).getLeft())) {
                    e = new Plus(((Pow) righty).getRight(), ((Pow) lefty).getRight());
                    return new Pow(((Pow) righty).getLeft(), e).simplifyB();
                }
                //x^y * z^y ==> (xz)^y
                if (isEquel(((Pow) righty).getRight(), ((Pow) lefty).getRight())) {
                    e = new Mult(((Pow) righty).getLeft(), ((Pow) lefty).getLeft());
                    e = new Pow(e, ((Pow) lefty).getRight()).simplifyB();
                    return e;
                }
            }
            return new Mult(lefty, righty);

        }
        return simplify();
    }
}
