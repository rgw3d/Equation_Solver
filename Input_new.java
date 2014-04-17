/**
 * The new input class for this project!
 * it is using objects more now
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
        
        String leftSide = originalInput.substring(0, originalInput.indexOf('=') - 1);//get each side 
        String rightSide = originalInput.substring(originalInput.indexOf('=') + 1);
        System.out.println(leftSide+" Left Side of the equation");//displays the left
        System.out.println(rightSide+" Right Side of the equation");//displays the right 
        
        ArrayList left= parseObjects(leftSide);
        ArrayList right= parseObjects(rightSide);//returns an array list of all the objects
        
        
        
        
        
        
        
        
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
        return theList;
    }
}
