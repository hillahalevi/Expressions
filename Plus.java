import java.util.Map;

/**
 * a plus class extends AssociativeExpression.
 */
public class Plus extends AssociativeExpression {


    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right expression
     */
    public Plus(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right double
     */
    public Plus(Expression e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left expression
     * @param e2 right String
     */
    public Plus(Expression e1, String e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 string
     * @param e2 right String
     */
    public Plus(String e1, String e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right expression
     */
    public Plus(double e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left string
     * @param e2 right expression
     */
    public Plus(String e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right double
     */
    public Plus(double e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left string
     * @param e2 right double
     */
    public Plus(String e1, double e2) {
        super(e1, e2);
    }

    /**
     * constructor.
     *
     * @param e1 left double
     * @param e2 right string
     */
    public Plus(double e1, String e2) {
        super(e1, e2);
    }

    /**
     * to string.
     *
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "(" + super.getLeft().toString() + " + " + super.getRight().toString() + ")";
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
        return super.getLeft().evaluate(assignment) + super.getRight().evaluate(assignment);
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
        return new Plus(e1, e2);

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
        return new Plus(e1, e2);

    }

    /**
     * Combine fam ==> set the expression in the most convenient way.
     */
    public void combineFam() {
        sortedexp();
        if (isEquel(getRight(), getLeft())) {
            return;
        }
        if ((getRight() instanceof Plus)) {
            Expression v1 = new Plus(getLeft(), ((Plus) getRight()).getLeft()).simplify();
            double len1 = v1.toString().length();
            Expression v2 = new Plus(getLeft(), ((Plus) getRight()).getRight()).simplify();
            double len2 = v2.toString().length();
            if (len1 > len2) {
                setLeft(((Plus) getRight()).getLeft().simplify());
                setRight(v2);
            } else {
                setLeft(((Plus) getRight()).getRight().simplify());
                setRight(v1);

            }


        }
        //x+y + y+z

        if (getLeft() instanceof Plus) {
            Expression e1 = new Plus(((Plus) getLeft()).getLeft(), getRight()).simplify();
            double size1 = e1.toString().length();
            Expression e2 = new Plus(((Plus) getLeft()).getRight(), getRight()).simplify();
            double size2 = e2.toString().length();
            if (size1 > size2) {
                setLeft(((Plus) getLeft()).getLeft().simplify());
                setRight(e2);
            } else {
                setLeft(((Plus) getLeft()).getRight().simplify());
                setRight(e1);
            }
        }

    }

    /**
     * Combine fam Bonus ==> set the expression in the most convenient way.
     */
    public void combineFamB() {
        sortedexpB();
        if (isEquel(getRight(), getLeft())) {
            return;
        }
        if ((getRight() instanceof Plus)) {
            Expression v1 = new Plus(getLeft(), ((Plus) getRight()).getLeft()).simplifyB();
            double len1 = v1.toString().length();
            Expression v2 = new Plus(getLeft(), ((Plus) getRight()).getRight()).simplifyB();
            double len2 = v2.toString().length();
            if (len1 > len2) {
                setLeft(((Plus) getRight()).getLeft().simplifyB());
                setRight(v2);
            } else {
                setLeft(((Plus) getRight()).getRight().simplifyB());
                setRight(v1);

            }


        }
        //x+y + y+z

        if (getLeft() instanceof Plus) {
            Expression e1 = new Plus(((Plus) getLeft()).getLeft(), getRight()).simplifyB();
            double size1 = e1.toString().length();
            Expression e2 = new Plus(((Plus) getLeft()).getRight(), getRight()).simplifyB();
            double size2 = e2.toString().length();
            if (size1 > size2) {
                setLeft(((Plus) getLeft()).getLeft().simplifyB());
                setRight(e2);
            } else {
                setLeft(((Plus) getLeft()).getRight().simplifyB());
                setRight(e1);
            }
        }

    }

    /**
     * simplify.
     *
     * @return a simplified version of the current expression.
     */
    public Expression simplify() {
        Plus p = new Plus(getLeft().simplify(), getRight().simplify());
        p.combineFam();
        p.sortedexp();
        Expression lefty = p.getLeft();
        Expression righty = p.getRight();
        boolean right = isNnumber(righty);
        boolean left = isNnumber(lefty);
        Expression e;
        //all numbers
        if (right && left) {
            return new Num(getnumber(righty) + getnumber(lefty));
        }

        //0+x=x
        if ((left) && (getnumber(lefty) == 0)) {
            return righty;
        }
        //X+X
        if (isEquel(righty, lefty)) {
            e = new Mult(lefty, 2);
            return e.simplify();
        }
        //x+ -x
        if (lefty == new Neg(righty)) {
            return new Num(0);
        }

        return new Plus(lefty, righty);
    }

    /**
     * Simplify expression. -Bonus.
     *
     * @return the expression
     */
    public Expression simplifyB() {
        if (simplify() instanceof Plus) {
            Plus p = (Plus) simplify();
            p.combineFamB();
            p.sortedexpB();
            Expression lefty = p.getLeft().simplifyB();
            Expression righty = p.getRight().simplifyB();
            Expression e;
            //x + (-k) ==> x-k
            if (righty instanceof Neg) {
                return new Minus(lefty, ((Neg) righty).getExp()).simplifyB();
            }
            //x + -1 ==> x-1
            if (righty instanceof Num && getnumber(righty) < 0) {
                return new Minus(lefty, getnumber(righty)).simplifyB();
            }
            //-1+x ==> x-1
            if (lefty instanceof Num && getnumber(lefty) < 0) {
                return new Minus(righty, getnumber(lefty) * -1).simplifyB();
            }
            //x+px
            if (lefty instanceof Var && righty instanceof Mult) {
                if (isEquel(lefty, ((Mult) righty).getLeft())) {
                    e = new Mult(new Plus(((Mult) righty).getRight(), 1), lefty);
                    return e.simplifyB();
                }
                if (isEquel(lefty, ((Mult) righty).getRight())) {
                    e = new Mult(new Plus(((Mult) righty).getLeft(), 1), lefty);
                    return e.simplifyB();
                }

            }
            //cx+py
            if (lefty instanceof Mult && righty instanceof Mult) {
                Expression b;
                if (isEquel(((Mult) lefty).getLeft(), ((Mult) righty).getLeft())) {
                    //c=p...(x+y)c
                    b = new Plus(((Mult) righty).getRight(), ((Mult) lefty).getRight());
                    e = new Mult(b, ((Mult) lefty).getLeft());
                    return e.simplifyB();
                }
                if (isEquel(((Mult) lefty).getLeft(), ((Mult) righty).getRight())) {
                    //c=y...(x+p)c
                    b = new Plus(((Mult) righty).getLeft(), ((Mult) lefty).getRight());
                    e = new Mult(b, ((Mult) lefty).getLeft());
                    return e.simplifyB();
                }
                if (isEquel(((Mult) lefty).getRight(), ((Mult) righty).getLeft())) {
                    //x=p...(c+y)x
                    b = new Plus(((Mult) righty).getRight(), ((Mult) lefty).getLeft());
                    e = new Mult(b, ((Mult) lefty).getRight());
                    return e.simplifyB();
                }
                if (isEquel(((Mult) lefty).getRight(), ((Mult) righty).getRight())) {
                    //x=y...(c+p)x
                    b = new Plus(((Mult) righty).getLeft(), ((Mult) lefty).getLeft());
                    e = new Mult(b, ((Mult) lefty).getRight());
                    return e.simplifyB();
                }


            }
            //x/y +z/t ==> (xt+yz)/yt
            if (lefty instanceof Div && righty instanceof Div) {
                Expression a = new Mult(((Div) lefty).getLeft(), ((Div) righty).getRight()).simplify();
                Expression b = new Mult(((Div) lefty).getRight(), ((Div) righty).getLeft()).simplify();
                Expression c = new Mult(((Div) lefty).getRight(), ((Div) righty).getRight()).simplify();
                e = new Div(new Plus(a, b), c);
                return e.simplifyB();
            }
            //sin^2 +cos^2 ==> 1
            if (lefty instanceof Pow && righty instanceof Pow) {
                boolean a = isNnumber(((Pow) righty).getRight()) && getnumber(((Pow) righty).getRight()) == 2;
                boolean b = isNnumber(((Pow) lefty).getRight()) && getnumber(((Pow) lefty).getRight()) == 2;
                boolean c = false;
                if (a && b && ((Pow) lefty).getLeft() instanceof Sin && ((Pow) righty).getLeft() instanceof Cos) {
                    c = isEquel(((Sin) ((Pow) lefty).getLeft()).getExp(), ((Cos) ((Pow) righty).getLeft()).getExp());
                }
                if (a && b && ((Pow) lefty).getLeft() instanceof Cos && ((Pow) righty).getLeft() instanceof Sin) {
                    c = isEquel(((Cos) ((Pow) lefty).getLeft()).getExp(), ((Sin) ((Pow) righty).getLeft()).getExp());
                }
                if (c) {
                    return new Num(1);
                }
            }
            return new Plus(lefty, righty);
        }
        return simplify();
    }

}
