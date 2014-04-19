/**
 * The new input class for this project!
 * it is using objects now
 * yay
 * 
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.EnumSet;
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
        System.out.println(leftSide+" Left Side of the equation");//displays the left
        System.out.println(rightSide+" Right Side of the equation");//displays the right 
        
        ArrayList left= parseObjects(leftSide);
        ArrayList right= parseObjects(rightSide);//returns an array list of all the objects
        System.out.println(left);
        
        //Now we have the list.  Time to simplify
        
        simplify(left);
        simplify(right);
        
        
        
        
        
        
    }
    public static boolean isEquation(String equation)
    {
        if(!(equation.contains("=")))//Not enough = signs!
            return false;
        
        if(equation.indexOf('=')!= equation.lastIndexOf('='))//Too many = signs!
            return false;
        return true;
    }
    public static String handSanitizer(String fix)
    {
        fix=fix.replace(" ","");//Geting rid of spaces
        
        fix=fix.replace("--","+"); //minus a minus is addition.  make it simple
        
        fix=fix.replace("-","+-");  //replace a negative with just plus a minus. 
        
        fix=fix.replace("X","x");
        
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
    public static void simplify(ArrayList toSimplify)
    {
        
        ArrayList<Integer>parenIndx=findParenthesis(toSimplify);//get the indexs of everthing
        if(!parenIndx.isEmpty())//if it is not empty
            solveParenthesis(parenIndx, toSimplify);
        
        
            
        
    
        
        //findParenthesis(toSimplify);
    }
    public static ArrayList<Integer> findParenthesis(ArrayList tempToSimplify)
    {
        ArrayList<Integer>tempParenIndx= new ArrayList<Integer>();
        for(int indx=0; indx<tempToSimplify.size(); indx++)
        {
            if(tempToSimplify.get(indx) instanceof Parenthesis)
            {
                tempParenIndx.add(indx);
            }
        }
        return tempParenIndx;
    }
    public static void solveParenthesis(ArrayList<Integer> parenIndx, ArrayList toSimplify )
    {
        //what it needs to do is find one paenthesis (maybe just return a coords? n
        
        /*int left=(int)toSimplify.get(leftSide);
        
        int right=0;
        for(int indx=left; indx)
        {
            Parenthesis tempParen = (Parenthesis)toSimplify.get(indx);
            if(tempParen.getValue())
            {
                
            }
        }
        */
    }
}
