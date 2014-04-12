/**
 * its 4-12-14 and I am writing this poor code
 * its fun time
 * 11:00 PM
 * rgw3d
 */
import java.util.Scanner;
public class InputThree
{
    public static void main(String args[])
    {            
        System.out.println("Input your equation");
        String originalInput=simplifyInput(new Scanner(System.in).nextLine());//get and simplify the input
        
        Scanner to_delim=new Scanner(originalInput).useDelimiter("=");// set delimiter to be a =.  to get both sides  
        String leftSide=to_delim.next();        
        String rightSide=to_delim.next();
        
        System.out.println(leftSide+" Left Side of the equation");//displays the left
        System.out.println(rightSide+" Right Side of the equation");//displays the right 
        
        //so i need to set up a pemdas system.  the parenthesis can call the rest of it 
        //and so on.  like recurson 
        //yay
        //fuck
        
        /*leftSide=Functions.parenthesis(leftSide);
        leftSide=Functions.exponent(leftSide);
        leftSide=Functions.multiplication(leftSide);
        leftSide=Functions.addition(leftSide);
        
        //I think this part is self explanatory 
        
        rightSide=Functions.parenthesis(rightSide);
        rightSide=Functions.exponent(rightSide);
        rightSide=Functions.multiplication(rightSide);
        rightSide=Functions.addition(rightSide);
        
        */
        resolve(leftSide, rightSide);
        
        
        
    }
    public static String simplifyInput(String fix)
    {
        if(!(fix.contains("=")))//it needs to have a equals sign
            fix=fix+"=0";
            
        fix=fix.replace(" ","");//space is bad
        
        fix=fix.replace("--","+"); //minus a minus is addition.  make it simple
        
        fix=fix.replace("-","+-");  //replace a negative with just plus a minus.  makes it easier
        
        //this will be updated later as I fix all the syntax errors that come with exponents and parentheses
        
        
        return fix;
        
    }
    public static void resolve(String leftSide, String rightSide)
    {
        double leftSideInt=Functions.noX(leftSide);//solves all the integer addition, ignores all the other addition
        System.out.println(leftSideInt+" =Left integer total");
        double leftSideX=Functions.oneX(leftSide);//solves all the "x" addition, ignores all the other addition
        System.out.println(leftSideX+" =Left X total");
        double leftSide2X=Functions.twoX(leftSide);//solves all the "x^2" addition, ignores all the other addition
        System.out.println(leftSide2X+" =Left X^2 total");        
        double leftSide3X=Functions.threeX(leftSide);//solves all the "x^3" addition, ignores all the other addition
        System.out.println(leftSide3X+" =Left X^3 total");                
        double leftSide4X=Functions.fourX(leftSide);//solves all the "x^4" addition, ignores all the other addition
        System.out.println(leftSide4X+" =Left X^4 total");      //Its all addition because the negatives were replaced  
        
        
        double rightSideInt=Functions.noX(rightSide);//Same thing as above but for the other side of the equation
        System.out.println(rightSideInt+" =Right integer total");
        double rightSideX=Functions.oneX(rightSide);
        System.out.println(rightSideX+" =Right X total");
        double rightSide2X=Functions.twoX(rightSide);
        System.out.println(rightSide2X+" =Right X^2 total");                
        double rightSide3X=Functions.threeX(rightSide);
        System.out.println(rightSide3X+" =Right X^3 total");                
        double rightSide4X=Functions.fourX(rightSide);
        System.out.println(rightSide4X+" =Right X^4 total"); 
        
        
        if((leftSide4X-rightSide4X)!=0)//now we solve for x
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
        else if((leftSide2X-rightSide2X)!=0)//quadratic formula
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
