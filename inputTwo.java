/*
 * Richard Wendel
 * This is the second attempt at a "Main" class and the second "input" class
 */
import java.io.*;
import java.util.*;
public class inputTwo
{
    public static void main()
    {
        
        Scanner kb=new Scanner(System.in);
        System.out.println("Input an equation like 5x^2+4-5+1x=3+(2x-1) To solve for X");
        System.out.println("\t/ is an invalid Character.");
        String original=kb.nextLine();//okay so this gets the equation
        solveMultiplication objM=new solveMultiplication();// just an object that is being declared
        Parenthesis objP=new Parenthesis();// just an object that is being declared
        original=simplifyInput.simp(original);//simplifies it for the rest of the code
        
        Scanner original_delim=new Scanner(original);//make the "changed" string into a Scanner object
        original_delim.useDelimiter("=");//uses the equals sign to deliniate 
        String part1=original_delim.next();//left side
        String part2=original_delim.next();//right side
        System.out.println(part1+" Left Side of the equation");//displays the left
        System.out.println(part2+" Right Side of the equation");//displays the right
        
        String simplifyLeftP=objP.Paren(part1);//simplifies parentheis
        String simplifyRightP=objP.Paren(part2);//simplifies pare
        //simplifyLeftP=objP.removeParenthesis(simplifyLeftP);
        //simplifyRightP=objP.removeParenthesis(simplifyRightP);
        String simplifyLeft=objM.simplify(simplifyLeftP);//runs the left side of the equation throught the multiplication simplifyer
        String simplifyRight=objM.simplify(simplifyRightP);//runs the right side of the equation throught the multiplication simplifyer
        String newpart1=simplifyLeft;//setting them equal to a string
        String newpart2=simplifyRight;//setting them equal to a string
        
        double leftSideInt=objM.integer(newpart1);//solves all the integer addition, ignores all the other addition
        System.out.println(leftSideInt+" =Left integer total");
        double leftSideX=objM.singleX(newpart1);//solves all the "x" addition, ignores all the other addition
        System.out.println(leftSideX+" =Left X total");
        double leftSide2X=objM.doubleX(newpart1);//solves all the "x^2" addition, ignores all the other addition
        System.out.println(leftSide2X+" =Left X^2 total");        
        double leftSide3X=objM.threeX(newpart1);//solves all the "x^3" addition, ignores all the other addition
        System.out.println(leftSide3X+" =Left X^3 total");                
        double leftSide4X=objM.fourX(newpart1);//solves all the "x^4" addition, ignores all the other addition
        System.out.println(leftSide4X+" =Left X^4 total");      //Its all addition because the negatives were replaced  
        
        
        double rightSideInt=objM.integer(newpart2);//Same thing as above but for the other side of the equation
        System.out.println(rightSideInt+" =Right integer total");
        double rightSideX=objM.singleX(newpart2);
        System.out.println(rightSideX+" =Right X total");
        double rightSide2X=objM.doubleX(newpart2);
        System.out.println(rightSide2X+" =Right X^2 total");                
        double rightSide3X=objM.threeX(newpart2);
        System.out.println(rightSide3X+" =Right X^3 total");                
        double rightSide4X=objM.fourX(newpart2);
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
