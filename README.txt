#hilla halevi
#208953083

In this file I would like to explain some of my actions in this project .
I will start with the boring staff and get to the cool staff.
~~~~~~~~~Good To Know ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
In each advanced simplification I called the regular simplification and worked on it.
Help function called isEquel check if two expressions are equel by checking their strings.
~~~~~~~~~Lest Go ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Unary Expression ; check if the inner expression is neg ant act on it
#sin(-x) ==> -sin(x) ~~ TRIGO_RULES
#cos(-x) ==> cos(x)  ~~ TRIGO_RULES
# --x    ==> x       ~~ ALGEBRA
# --num  ==> num     ~~ ALGEBRA

Binary Expression
#Log: log(x,y^2) ==> 2*log(x,y) ~~  check if the right side is pow and use  Mult LOG_RULES

#Pow: x^(-y) ==> 1/x^y ~~ check if the right side is negetive and use Div
    : 0^x    ==> 0     ~~ ALGEBRA
    : x^0    ==> 1     ~~ ALGEBRA
    : 1^x    ==> 1     ~~ ALGEBRA
    : x^1    ==> x     ~~ ALGEBRA
    :(x^y)^z ==> x^(y*z)  ~~   check if the right side is pow and use  Mult

#Minus: x - (-k)  ==>  x+k   ~~ ALGEBRA
      : x - -num  ==>  x+num ~~ ALGEBRA
      : 1 - sin^2 ==> cos^2  ~~ TRIGO_RULES
      : 1 - cos^2 ==> sin^2  ~~ TRIGO_RULES

#Div: xy/x  ==> y   ~~ check if the left side of the MULT equels to the right side of the Div
    : yx/x  ==> y   ~~ check if the right side of the MULT equels to the right side of the Div
    : xz/y  ==> x*z/y || z*x/y ~~ create this two options ,simplify them and then choose the shortest option
                                  using to string.length method - and there you have the most simplified version.
    : x^y / x^z ==> x^(y-z)    ~~ check if left and right are pow  and have same base than call pow(x,y-z).
    : y^x / z^x ==> (y/z)^x    ~~ check if left and right are pow  and have same power than call pow(y/z,x).
    : x/(y/z)  ==>  x*z/ y     ~~ check if the right side is Div  then MULT the left && Div.right and Div it on Div.left.
    : x/z / y  ==> x/y*z       ~~ check if the left side is Div  than Div(left.left,Mult(left.right,right))

Plus and Mult are different because they are associative - it doesnt matter what goes first.
To use this incredible gift i created a new class called AssociativeExpression that Plus and Mult inherent from.
In this class i created a sorting method using Switch method that will help me apply this order :
#before means - on the left.
num goes before var
var goes before var in alphabetic order. ~~ to accomplish that i sorted my getVariable list alphabeticly.
var goes before binary expression.
binary expression before unary expressions.
on top of this nice order i applyed a combineFam mathod that its goal is to combine similar Expressions together.
lets start with Plus :
# p + (x+y)  this method will generate two expression  (p+x)+y && (p+y)+x ,simplify them and then choose the shortest
 Expression using to string.length method ,set it - and there you have the most simplified version.
#(x+y) + p  ~~ same as above just reverse.
after using this method we have our expression modified we sort it and start to work.

#Plus: x + (-k)  ==>  x - k.
     : x + -num ==> x - num
     :-1 + x  ==>  x - 1
     :x + px  ==> (p+1)x  ~~ using isEquel. (var+mult)
     :cx+py   ==>  c=p -> (x+y)c || c=y -> (x+p)c || x=p -> (c+y)x || x=y -> (c+p)x  ~~ using isEquel.(mult+mult)
     :x/y +z/t ==> (xt+yz)/yt Div + Div  creating common ground using Mult Plus and Div  ~~ ALGEBRA
     :sin^2 +cos^2 ==> 1   ~~ TRIGO_RULES

lets see Mult - combineFam is a bit different  here:
Mult is also an associative class so we can sort it .
in case of Mult mult Mult we can combine it any wey we want.
#pv*cx  this method will generate new expression p*c * v*x simplify it and compare it with our original expression
using to string.length method ,set it - and there you have the most simplified version.
after using this method we have our expression modified we sort it and start to work.

#Mult: x * x == > x^2 ~~ using isEquel && Pow ~~ ALGEBRA.
     : x * (y/x) ==> y ~~ using isEquel ~~ ALGEBRA.
     : x * (x/y) ==> x^2/y ~~ using isEquel && Pow && Div ~~ ALGEBRA.
     : x * (y/z) ==> xy/z  ~~ using Mult && Div ~~ ALGEBRA.
     : x^z * x^y ==> x^(y+z) ~~ using isEquel && Pow && Plus ~~ ALGEBRA.
     : x^y * z^y ==> (xz)^y ~~ using isEquel && Pow && Mult ~~ ALGEBRA.

with the help of all of  this i was able to create beautiful simplified Expressions.
thank you !






