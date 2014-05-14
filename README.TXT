This program is able to solve equations that contain a variable.  If you are ever in algebra II and need to 
solve an equation, you should just go to wolframAlpha.com.  If you are feeling risque, you could try my buggy
version that doenst do 1% of what wolframAlpha is capable of.  

Regardless, this program has more or less taught me how to effectivly (or uneffectivly, if you look at the code)
manage and work with objects, arrayLists and iterators.  Here are some of the code capabilities.

Input:
    This program can accept an equation - must have an equals sign - that uses "x" as a variable.  Any other variable 
    and the computer will blow up.  The syntax for input is so: "x^2+1-x+20x^9*(1x-x)=30x^4+2.0^9;  

Capabilities:
    Can solve addition, subtraction, and can solve single-power variable equations, quadratics, and x^x power 
    equations if that is the only variable ( x^9=45 for example)

Limitations:
    *Can accept only up to the 9th power for both variables and regular numbers.  Additionally, you can type an input
    string of: "x^23^3" and the simplifed version is: "27x^2"
    *Mulitplicaiton and Division currently dont work, as I have not written the code.  Parenthesis are parsed 
    correctly but any arithmatic with them currently does not work.  
    
    
    