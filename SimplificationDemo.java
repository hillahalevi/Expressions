/**
 * Created by shimon on 5/12/2017.
 */
public class SimplificationDemo {

    /**
     * @param args the mains arguments.
     * @throws Exception a new Exception.
     */
    public static void main(String[] args) throws Exception {
        Expression e;
        e = new Pow(0, "x");
        System.out.println(e); //0^x
        System.out.println(e.simplifyB()); //0
        e = new Pow("x", 0);
        System.out.println(e); //x^0
        System.out.println(e.simplifyB()); //1
        e = new Pow(1, "x");
        System.out.println(e); //1^x
        System.out.println(e.simplifyB()); //1
        e = new Pow("x", 1);
        e=new Div(0,0);
        System.out.println(e); //x^1
        System.out.println(e.simplifyB()); //x
        e = new Pow(new Pow("x", "y"), new Var("z"));
        System.out.println(e); //(x^y)^z
        System.out.println(e.simplifyB()); //x^(y*z)

        e = new Plus(new Mult(2, "x"), new Mult(4, "x"));
        System.out.println(e); //2x+4x
        System.out.println(e.simplifyB()); //6x
        e = new Plus(new Mult(2, "x"), new Plus(2, new Plus(new Mult(4, "x"), 1)));
        System.out.println(e); //2x + (2 + (4x + 1))
        System.out.println(e.simplifyB().toString()); //3 + 6x
        e = new Plus(new Plus(2, new Plus(new Mult(4, "x"), 1)), new Mult(2, "x"));
        System.out.println(e); //2x + (2 + (4x + 1))
        System.out.println(e.simplifyB()); //3 + 6x
        e = new Plus(new Mult(2, "x"), new Plus(new Mult(4, "x"), new Plus("y", 1)));
        System.out.println(e); //((2.0 * x) + ((4.0 * x) + (y + 1.0)))
        System.out.println(e.simplifyB()); //((6.0 * x) + (y + 1.0))
        e = new Div(new Plus("y", new Plus(6, new Plus("x", 3))), new Plus("x",
                new Plus(3, new Plus("y", 6))));
        System.out.println(e); //(y+6+x+3)/(x+3+y+6)
        System.out.println(e.simplifyB()); //1
        e = new Div(new Plus("x", 3), new Plus(1, new Plus("x", 2)));
        System.out.println(e);  //(x+3)/(1+(x+2))
        System.out.println(e.simplifyB()); //1
        e = new Div(new Mult(new Plus("x", 32), "y"), new Mult("y", new Plus(32, "x"))); //not working
        System.out.println(e); //(((x + 32) * y) / ((32 + x) * y))
        System.out.println(e.simplifyB()); //1
        e = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2)).differentiate("x");
        System.out.println(e); //(((x + y)^2.0) * (((1.0 + 0.0) * (2.0 / (x + y))) + (0.0 * log(e, (x + y)))))
        System.out.println(e.simplifyB()); //(2.0 * (x + y))
        e = new Log(new Plus("x", "y"), new Plus("y", "x"));
        System.out.println(e); //Log((x + y), (y + x))
        System.out.println(e.simplifyB()); //1
        e = new Mult(new Plus("x", "y"), new Plus("y", "x"));
        System.out.println(e); //((x + y) * (y + x))
        System.out.println(e.simplifyB()); //((x + y)^2)
        e = new Pow("x", "x");
        System.out.println(e); //x^x
        System.out.println(e.differentiate("x").simplifyB()); //(x^x) * (1+ Log(e, x) )
        e = new Log("e", new Pow("x", 4));
        System.out.println(e); //Log(e, (x^4))
        System.out.println(e.simplifyB()); //(4 * Log(e, x))
        System.out.println(e.differentiate("x").simplifyB()); //((4.0 / x)
        e = new Minus(4, new Neg("x"));
        System.out.println(e); //4 - (-x)
        System.out.println(e.simplifyB()); //4 + x
        e = new Sin(new Neg("x"));
        System.out.println(e); //Sin(-x)
        System.out.println(e.simplifyB()); //-Sin(x)
        e = new Cos(new Neg("x"));
        System.out.println(e); //Cos(-x)
        System.out.println(e.simplifyB()); //Cos(x)
        e = new Mult(new Div("x", "y"), new Div("y", "x"));
        System.out.println(e); //((x / y) * (y / x))
        System.out.println(e.simplifyB()); //1.0
        e = new Plus(new Div("x", "y"), new Div("y", "x"));
        System.out.println(e); //((x / y) + (y / x))
        System.out.println(e.simplifyB()); //(((x^2.0) + (y^2.0)) / (y * x))
        e = new Minus(new Mult(new Cos(new Neg("x")), new Div(new Pow("y", 4), "y")),
                new Neg(new Minus(new Neg(1), 0)));
        System.out.println(e); //((Cos((-x)) * ((y^4.0) / y)) - (-((-1.0) - 0.0)))
        System.out.println(e.simplifyB()); //((Cos(x) * (y^3.0)) -1.0))
        e = new Plus(new Neg(5), "x");
        System.out.println(e); //(-5 + x)
        System.out.println(e.simplifyB()); //x - 5
        e = new Div("x", new Div("y", 5));
        System.out.println(e); //(x / (y / 5.0))
        System.out.println(e.simplifyB()); //((5.0 * x) / y)
        e = new Div(new Mult(new Pow("x", 4), 7), new Pow("x", 5));
        System.out.println(e); //(((x^4.0) * 7.0) / (x^5.0))
        System.out.println(e.simplifyB()); //(7.0 / x)
        e = new Div(new Mult(7, "x"), 7);
        System.out.println(e); //(((x * 7.0) / 7))
        System.out.println(e.simplifyB()); //x
        e = new Plus(new Plus("y", "x"), new Plus("x", "y"));
        System.out.println(e); //(y + x) + (x + y)
        System.out.println(e.simplifyB()); //2 * (y + x)
        e = new Minus(new Plus("y", "x"), new Plus("x", "y"));
        System.out.println(e); //(y + x) - (x + y)
        System.out.println(e.simplifyB()); //0
        e = new Minus(1, new Pow(new Cos("x"), 2));
        System.out.println(e); //1 - Cos(x)
        System.out.println(e.simplifyB()); //Sin(x)^2
        e = new Minus(1, new Pow(new Sin("x"), 2));
        System.out.println(e); //1 - Sin(x)
        System.out.println(e.simplifyB()); //Cos(x)^2
        e = new Plus(new Pow(new Cos("x"), 2), new Pow(new Sin("x"), 2));
        System.out.println(e); //((Cos(x)^2.0) + (Sin(x)^2.0))
        System.out.println(e.simplifyB()); //1
    }
}
