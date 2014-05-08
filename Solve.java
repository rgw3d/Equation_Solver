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
        System.out.println("Everything on one side:   "+all);
        ArrayList allSimplified=Simplify.simplify(all);
        System.out.println("Everything Simplified:   "+allSimplified);
        
        //time to figure out endgame strategy 
        double highestExponent=findHighestExponent(allSimplified);//get highest exponent
        System.out.println(highestExponent);
        //now time for the important if strucure 
        if(highestExponent==1)
        {
            solveFirstPower(allSimplified);
        }
        else if(highestExponent==0)
        {
            solveNoPower(allSimplified);
        }
        else
        {
            if(highAndAlone(allSimplified))
            {
                solveWithLogs(allSimplified);
            }
            else if(highestExponent==2)
            {
                solveQuadratic(allSimplified);
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
        if(searchThrough.size()==1)//if it is just the variable 
        {
            searchThrough.add(new Nomial(0,0));//so its just the variable, but 0 is added back on to the arrayList so that solving it can be done easily.  
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
        number.makeNegative();
        double answer=(Math.log10(number.getCount())/(Math.log10(variable.getVarExponent())));
        System.out.println("Worked! Solved logs!    " + answer);
        
    }
    public static void solveFirstPower(ArrayList<Nomial> toSolve)
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
        number.makeNegative();
        double answer=number.getCount()/variable.getCount();
        System.out.println("Worked! Solved simple equations!    " + answer);
        
    }
    public static void solveQuadratic(ArrayList<Nomial> toSolve)
    {
        Nomial a=findValue(toSolve, 2);
        Nomial b=findValue(toSolve, 1);
        Nomial c=findValue(toSolve,0);
        
        System.out.println("Final Equation: "+a.getCount()+"+x^2 + "+b.getCount()+"x + "+c.getCount());        
        System.out.println("x= "+(-b.getCount()+Math.sqrt(Math.pow(b.getCount(),2)+(-4*a.getCount()*c.getCount())))/(2*a.getCount()));
        System.out.println("x= "+(-b.getCount()-Math.sqrt(Math.pow(b.getCount(),2)+(-4*a.getCount()*c.getCount())))/(2*a.getCount()));
    }
    public static void solveNoPower(ArrayList<Nomial> toSolve)
    {
        System.out.println("No x value, cant solve, so here is what everything added up to: "+toSolve.get(0).getCount());
    }
    public static Nomial findValue(ArrayList<Nomial> search, int match)
    {
        for(Nomial nom: search)
        {
            if(nom.getVarExponent()==match)
                return nom;
        }
        return new Nomial(0,0);
    }
    
}
