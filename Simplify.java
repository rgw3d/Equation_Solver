/**
 *This Class will contain all the methods that allow all the operations to be simplified
 */
import java.util.ArrayList;
import java.util.ListIterator;
public class Simplify
{
     public static ArrayList simplify(ArrayList toSimplify)
    {
        //so lets have it check to see if there are Parenthesis
        ArrayList toReturn = new ArrayList();
        if(haveParenthesis(toSimplify))
        {
            //time to solve parenthesis 
            //only one method call, it then calls the other sub-methods that solve division and addition
            
        }
        else if(haveMultiplication(toSimplify))//if there is multiplication or division
        {
            //run the multiplication method that solves division as well
        }
        else
        {
            toReturn=solveAddition(toSimplify);
        }
        return toReturn;
    }
    public static boolean haveParenthesis(ArrayList toSimplify)
    {
        for(int indx=0; indx<toSimplify.size(); indx++)
        {
            if(toSimplify.get(indx) instanceof Parenthesis)
                return true;
        }
        return false;
    }
    public static boolean haveMultiplication(ArrayList toSimplify)
    {
        for(int indx=0; indx<toSimplify.size(); indx++)
        {
            if(toSimplify.get(indx) instanceof Operator)
            {
                Operator testSign= (Operator)toSimplify.get(indx);
                String theSign=testSign.toString();
                if(theSign.equals("*")||theSign.equals("/"))
                    return true;
            }
        }
        return false;
    }
    public static ArrayList solveAddition(ArrayList toSimplify)
    {
        //just solving addition, there are no other signs so operators in general can be ignored
        //first thing to do is to remove all the operators
        ArrayList<Nomial> noOperators=new <Nomial>ArrayList();
        for(int indx=0; indx<toSimplify.size(); indx++)
        {
            if(toSimplify.get(indx) instanceof Nomial)
            {
                noOperators.add((Nomial)toSimplify.get(indx));//add it to the array
            }
        }
        ArrayList <Nomial>simplified = new ArrayList();//will be the one that is returned
        ListIterator <Nomial>cycleAll = noOperators.listIterator();//so noOperators can be cycled through
        while(cycleAll.hasNext())
        {   //compare the first indx to all others.  compare second to all others, and so on
            Nomial compare1=cycleAll.next();
            while(cycleAll.hasNext())
            {
                Nomial compare2=cycleAll.next();
                System.out.println(compare2.getVarExponent()+ "    " + compare2);
                if(compare1.compareTo(compare2)==0)
                {
                    compare1.changeCount(compare2.getCount());
                    cycleAll.remove();//okay so i removed compare 2
                }
            }
            simplified.add(compare1);//always add this object
            //simplified.add(new Operator()); 
            removeFirst(cycleAll);//remove the first object in the iterator as well
        }
        //if(simplified.size()>1)
           // simplified.remove(simplified.size()-1);
        return simplified;
    }
    public static void removeFirst(ListIterator toRemove)
    {
        while(toRemove.hasPrevious())
        {
            toRemove.previous();
        }
        toRemove.remove();
    }
}

