/**
 * The new input class for this project!
 * it is using objects now
 * yay
 * 
 */

import java.util.Scanner;
import java.util.ArrayList;
public class Input_new
{
    public static void main(String args[])
    {
        System.out.println("Type in your equation");
        String originalInput= new Scanner(System.in).nextLine();
        
        if(!isEquation(originalInput))
        {
            System.out.println("Please enter an equation next time");
            return;
        }        
        
        originalInput = handSanitizer(originalInput);
        
        String leftSide = originalInput.substring(0, originalInput.indexOf('='));//get each side 
        String rightSide = originalInput.substring(originalInput.indexOf('=') + 1);
        if((!properSyntax(leftSide))||(!(properSyntax(rightSide))))
        {
            System.out.println("Improper Syntax!");
            return;
        }
        /*System.out.println(leftSide+" Left Side of the equation");//displays the left
        System.out.println(rightSide+" Right Side of the equation");//displays the right */
        
        ArrayList left= parseObjects(leftSide);
        ArrayList right= parseObjects(rightSide);//returns an array list of all the objects
        
        //Now we have the list.  Time to simplify
        
        ArrayList<Nomial> leftFinal=Simplify.simplify(left);
        ArrayList<Nomial> rightFinal=Simplify.simplify(right);
        
        /*System.out.println("left side:  "+leftFinal);
        System.out.println("right side: "+rightFinal);*/
        
        Solve.solve(leftFinal,rightFinal);
        
        
        
        
    }
    public static boolean isEquation(String equation)
    {
        if(!(equation.contains("=")))//Not enough = signs!
            return false;
        
        if(equation.indexOf('=')!= equation.lastIndexOf('='))//Too many = signs!
            return false;
        return true;
    }
    public static boolean properSyntax(String equation)
    {
        String endOfEq=equation.substring(equation.length()-1);
        if(endOfEq.equals("+")||endOfEq.equals("-")||endOfEq.equals("*")||endOfEq.equals("/"))
            return false;
        return true;
    }
    public static String handSanitizer(String fix)
    {
        fix=fix.replace(" ","");//Geting rid of spaces
        
        fix=fix.replace("--","+"); //minus a minus is addition.  make it simple
        
        fix=fix.replace("-","+-");  //replace a negative with just plus a minus. 
        
        fix=fix.replace("X","x");
        
        fix=fix.replace("^+-","^-"); //common error that happens after one of the above methods run. negative exponents
        
        //this will be updated later as I fix all the syntax errors that come with exponents and parentheses
                
        return fix;
    }
    public static ArrayList parseObjects(String startingPoint)
    {
        ArrayList theList = new ArrayList();
        //time to parse
        Scanner charBychar = new Scanner(startingPoint);
        charBychar.useDelimiter("");
        String temp="";
        String stringToChange="";
        while(charBychar.hasNext())
        {
            temp=charBychar.next();
            if(isOperator(temp))//if it is one of the operators
            {
                if(!stringToChange.equals(""))//if the stringToChange string is not empty
                {
                    theList.add(new Nomial(stringToChange));//add nomial
                    stringToChange="";//set it back to nothing
                }
                /*if(temp.equals("(")||temp.equals(")"))
                    theList.add(new Parenthesis(temp));//add the object
                else*/
                    theList.add(new Operator(temp));//add the object
                continue;
            }
            else if(temp.equals("("))//if it is the start of a parenthesis
            {
                String oneParenthesis="(";
                int otherOpen=0;
                int otherClosed=0;
                String parenTemp="";
                while(charBychar.hasNext())
                {
                    parenTemp= charBychar.next();
                    if(parenTemp.equals("("))
                        otherOpen++;
                    else if(parenTemp.equals(")"))
                        otherClosed++;
                    oneParenthesis+=parenTemp;
                    if(otherClosed==otherOpen+1)//this means that they are equal number
                    {
                        theList.add(new Parenthesis(oneParenthesis));
                        break;
                    }
                }
            }
            else
            {
                stringToChange+=temp;
            }
        }
        if(!stringToChange.equals(""))
        {
            theList.add(new Nomial(stringToChange));
            stringToChange="";
        }
        return theList;
    }
    public static boolean isOperator(String temp)
    {
        if(temp.equals("+")||temp.equals("/")||temp.equals("*"))//||temp.equals("(")||temp.equals(")")
            return true;
        else
            return false;
    }
    
    
    
   
}
