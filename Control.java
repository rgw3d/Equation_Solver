import java.util.Scanner;
import java.util.ArrayList;
import java.util.ListIterator;
/**
 * New version of the input method
 * 
 * @author rgw3d
 * @version 5-23-14
 */
public class Control
{
    public static void main(String args[])
    {
        System.out.println("Type in your equation");
        String input = new Scanner(System.in).nextLine();
        
        if(!isEquation(input))
        {
            System.out.println("\n\tPlease enter an equation next time");
            return;
        }
        
        input = handSanitizer(input);
        
        String leftSide = input.substring(0, input.indexOf('='));//get each side 
        String rightSide = input.substring(input.indexOf('=') + 1);
        
        if((!properSyntax(leftSide))||(!(properSyntax(rightSide))))
        {
            System.out.println("\n\tImproper Syntax!");
            return;
        }
        
        ArrayList<Equation> left = parseEquation(leftSide);
        System.out.println(left);
        ArrayList<Equation> right = parseEquation(rightSide);
        
        left = Simplify_new.simplify_control(left);
        right = Simplify_new.simplify_control (right);
        System.out.println(left);
        
        
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
    public static ArrayList<Equation> parseEquation(String input)
    {
        ArrayList<Equation> parsedEquation = new ArrayList<Equation>();
        
        ArrayList<String> useWithIterator = new ArrayList<String>();
        for(int indx=0; indx<input.length(); indx++)
        {
            useWithIterator.add(input.charAt(indx)+"");
        }
        ListIterator<String> charByChar= useWithIterator.listIterator();
        //Scanner charByChar = new Scanner(input);
        //charByChar.useDelimiter("");
        String temp="";
        String stringToChange="";
        while(charByChar.hasNext())
        {
            temp=charByChar.next();
            if(isOperator(temp))//if it is one of the operators
            {
                if(!stringToChange.equals(""))//if the stringToChange string is not empty
                {
                    parsedEquation.add(new Nomial_new(stringToChange));//add nomial
                    stringToChange="";//set it back to nothing
                }
                
                parsedEquation.add(new Operator(temp));//add the operator
                continue;
            }
            else if(temp.equals("("))//if it is the start of a parenthesis
            {
                String oneParenthesis="(";
                int otherOpen=0;
                int otherClosed=0;
                String parenTemp="";
                while(charByChar.hasNext())
                {
                    parenTemp= charByChar.next();
                    if(parenTemp.equals("("))
                        otherOpen++;
                    else if(parenTemp.equals(")"))
                        otherClosed++;
                    oneParenthesis+=parenTemp;
                    if(otherClosed==otherOpen+1)//this means that they are equal number
                    {
                        if(charByChar.hasNext())
                        {
                            if(charByChar.next().equals("^"))
                            {
                                if(charByChar.next().equals("-"))
                                {
                                    parsedEquation.add(new Parenthesis_new(oneParenthesis,-1*Double.parseDouble(charByChar.next())));
                                    break;
                                }
                                else
                                {
                                    parsedEquation.add(new Parenthesis_new(oneParenthesis,Double.parseDouble(charByChar.previous())));
                                    break;
                                }
                            }
                            else
                                charByChar.previous();
                        }
                        parsedEquation.add(new Parenthesis_new(oneParenthesis));
                        break;
                    }
                }
            }
            else
                stringToChange+=temp;
        }
        if(!stringToChange.equals(""))//if on the last loop it does not add the last object
        {
            parsedEquation.add(new Nomial_new(stringToChange));
            stringToChange="";
        }
        return parsedEquation;
    }
    public static boolean isOperator(String temp)
    {
        if(temp.equals("+")||temp.equals("/")||temp.equals("*"))//||temp.equals("(")||temp.equals(")")
            return true;
        else
            return false;
    }
}
