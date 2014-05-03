/**
 * This solves everything after it is all simplified.. yay
 */
import java.util.Scanner;
import java.util.ArrayList;
public class Solve
{
    public static void solve(ArrayList<Nomial> leftSide, ArrayList<Nomial> rightSide)//rectify both sides
    {
        //so I will be subtracting the right side from the left side
        //I need to replace all of the 
        ArrayList<Nomial> all= leftSide;
        
        for(int indx=0; indx<rightSide.size(); indx++)
        {
            Nomial tmp=(Nomial)rightSide.get(indx);
            tmp.makeNegative();
            all.add(tmp);
        }
        System.out.println(all);
        ArrayList allSimplified=Simplify.simplify(all);
        System.out.println(allSimplified);
       
        //time to figure out endgame strategy 
        double highestExponent=findHighestExponent(allSimplified);//get highest exponent
        System.out.println(highestExponent);
        //now time for the important if strucure 
        if(highestExponent<2)
        {
            //yay simple
        }
        else
        {
            if(highAndAlone(allSimplified))
            {
                solveWithLogs(allSimplified);
            }
            else if(highestExponent==2)
            {
                //quadratic
            }
            
        }
        //Ill have to account for just a random variable with nothing else
        
    }
    
    public static double findHighestExponent(ArrayList<Nomial> searchThrough)
    {
        double highestExponent=0;
        for(Nomial tmp: searchThrough)
        {
            if(tmp.getVarExponent()>highestExponent)
            {
                highestExponent=tmp.getVarExponent();
            }
        }
        return highestExponent;
    }
    public static boolean highAndAlone(ArrayList<Nomial> searchThrough)//to see if we can use logs to solve for x
    {
        if(searchThrough.size()==2)//has to be 2, one with an exponent and one without
        {
            Nomial tmp1= searchThrough.get(0);
            Nomial tmp2= searchThrough.get(1);//finding if either has a varExpoent of 0 -- no variable.
            if((tmp1.getVarExponent()==0)||(tmp2.getVarExponent()==0))
            {
                return true;
            }
        }
        return false;
    }
    
    
    public static void solveWithLogs(ArrayList<Nomial> toSolve)//must be array of length 2
    {
        Nomial variable;
        Nomial number;
        if(toSolve.get(0).getVarExponent()>toSolve.get(1).getVarExponent())
        {
            variable=toSolve.get(0);
            number=toSolve.get(1);
        }
        else
        {
            variable=toSolve.get(1);
            number=toSolve.get(0);
        }
        double answer=(Math.log10(number.getCount())/(Math.log10(variable.getVarExponent())));
        System.out.println("Worked! Solved logs!" + answer);
        
    }
}
