/**
 * Equation solver.  
 * read the Readme if you must
 * nothing special
 */
import java.util.Scanner;
public class Input
{
    public static void main(String args[])
    {            
        System.out.println("Input your equation");
        
        String originalInput = new Scanner(System.in).nextLine(); //Get input
        
        //Checking if input is an equation
        if(!isEquation(originalInput)){
            System.out.println("Please enter an equation");
            return;
        }
        
        originalInput = simplifyInput(originalInput);
        
        //Scanner to_delim=new Scanner(originalInput).useDelimiter("=");// set delimiter to be a =.  to get both sides  
        String leftSide = originalInput.substring(0, originalInput.indexOf('=') - 1);    
        String rightSide = originalInput.substring(originalInput.indexOf('=') + 1);
        
        System.out.println(leftSide+" Left Side of the equation");//displays the left
        System.out.println(rightSide+" Right Side of the equation");//displays the right 
        
        //will work its way down, I only need to call parethesis() because it has calls to the other methods
        
        leftSide=Functions.parenthesis(leftSide);//call parenthesis even if there are no parentheses
        //it has calls to the other methods.
        
        rightSide=Functions.parenthesis(rightSide);//same as above
        
        resolve(leftSide, rightSide); //now its simplified and good to go
        
        
        
    }
    public static boolean isEquation(String equation){
        if(!(equation.contains("=")))//Not enough = signs!
            return false;
        
        if(equation.indexOf('=', equation.indexOf('=') != -1)//Too many = signs!
            return false;
        
    }
    
    public static String simplifyInput(String fix)
    {
        /*That's bad practice! Sanitize your input!
        if(!(fix.contains("=")))//it needs to have a equals sign
            fix=fix+"=0";
        */
        
        fix=fix.replace(" ","");//Geting rid of spaces
        
        fix=fix.replace("--","+"); //minus a minus is addition.  make it simple
        
        fix=fix.replace("-","+-");  //replace a negative with just plus a minus.  makes it easier
        
        //this will be updated later as I fix all the syntax errors that come with exponents and parentheses
        
        
        return fix;
        
    }
    public static void resolve(String leftSide, String rightSide)
    {
        double leftSideInt=Functions.countNum(leftSide);//solves all the integer addition, ignores all the other addition
        double leftSideX=Functions.countX(leftSide);//solves all the "x" addition, ignores all the other addition
        double leftSide2X=Functions.countX2(leftSide);//solves all the "x^2" addition, ignores all the other addition
        double leftSide3X=Functions.countX3(leftSide);//solves all the "x^3" addition, ignores all the other addition
        double leftSide4X=Functions.countX4(leftSide);//solves all the "x^4" addition, ignores all the other addition
              //Its all addition because the negatives were replaced  
        double rightSideInt=Functions.countNum(rightSide);//Same thing as above but for the other side of the equation
        double rightSideX=Functions.countX(rightSide);
        double rightSide2X=Functions.countX2(rightSide);               
        double rightSide3X=Functions.countX3(rightSide);               
        double rightSide4X=Functions.countX4(rightSide);
        
        System.out.println(leftSideInt+" =Left number total\t"+rightSideInt+" =Right number total");
        System.out.println(leftSideX+" =Left X total\t"+rightSideX+" =Right X total");
        System.out.println(leftSide2X+" =Left X^2 total\t"+rightSide2X+" =Right X^2 total");  
        System.out.println(leftSide3X+" =Left X^3 total\t"+rightSide3X+" =Right X^3 total"); 
        System.out.println(leftSide4X+" =Left X^4 total\t"+rightSide4X+" =Right X^4 total");
        
        
        
        //dont have the methods for the X-nomials yet
        
       /* if((leftSide4X-rightSide4X)!=0)//now we solve for x
        {   
            Scanner result=new Scanner(trinomialsNew.quad((leftSide4X-rightSide4X),(leftSide3X-rightSide3X),(leftSide2X-rightSide2X),(leftSideX-rightSideX),(leftSideInt-rightSideInt)));//same thing as solving for x^4 but a little bit more simple
            while(result.hasNext())
            {
                System.out.println("x= "+result.next());
            }
        }
        else if((leftSide3X-rightSide3X)!=0)
        {            
            Scanner result=new Scanner(trinomialsNew.tri((leftSide3X-rightSide3X),(leftSide2X-rightSide2X),(leftSideX-rightSideX),(leftSideInt-rightSideInt)));//same thing as solving for x^4 but a little bit more simple
            while(result.hasNext())
            {
                System.out.println("x= "+result.next());
            }
        }
        else */ if((leftSide2X-rightSide2X)!=0)//quadratic formula
                {
                    double a=leftSide2X-rightSide2X;
                    double b=leftSideX-rightSideX;
                    double c=leftSideInt-rightSideInt;
                    System.out.println("x= "+(-b+Math.sqrt(Math.pow(b,2)+(-4*a*c)))/(2*a));
                    System.out.println("x= "+(-b-Math.sqrt(Math.pow(b,2)+(-4*a*c)))/(2*a));
                }
                else
                {//simple subtraction and then division.
                    System.out.print("x= "+(leftSideInt-rightSideInt)/(rightSideX-leftSideX));
                }        
    }
}
