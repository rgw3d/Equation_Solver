import java.util.ArrayList;
import java.util.ListIterator;
/**
 * This is the new version of the Simplify method
 * @author rgw3d
 * @version 5-24-14
 * This Class will contain all the methods that allow all the operations to be simplified
 */
public class Simplify_new
{
    /**
     * The main method of this class that is called from the outside
     * it is given an arraylist and then decides what needs to be done
     * For example, it figures out if multiplication is present, and then calls
     * the appropriate method to solve it
     */
    public static ArrayList<Equation> simplify_control(ArrayList<Equation> toSimplify)
    {
        ArrayList<Equation> toReturn = new ArrayList<Equation>();
        if(haveMultiplication(toSimplify))//if there is multiplication or division
        {
            toReturn=solveMultiplicaiton(toSimplify);
        }
        else
        {
            toReturn=solveAddition(toSimplify);
        }
        return toReturn;
    }
    /**
     * Tests for a Parenthesis object present in the arrayList
     * @return int number of parenthesis present in a given arrayList
     */
    public static int haveParenthesis(ArrayList<Equation> search)
    {
        int count = 0;
        for(Equation test: search)
        {
            if(test instanceof Parenthesis_new)
                count++;
        }
        return count;
    }
     /**
     * Tests for a Operator object that returns a multiplication or division sign
     */
    public static boolean haveMultiplication(ArrayList<Equation> search)
    {
        for(Equation test: search)
        {
            if(test instanceof Operator)
            {
                String theSign=test.toString();
                if(theSign.equals("*")||theSign.equals("/"))
                    return true;
            }
        }
        return false;
    }
    /**
     * solves addition within the ArrayList
     */
    public static ArrayList<Equation> solveAddition(ArrayList<Equation> toSimplify)
    {
        ArrayList<Equation> noOperators=removeOperators(toSimplify);
        
        ArrayList <Equation>simplified = new ArrayList<Equation>();//will be the one that is returned
        decompressParenthesis(noOperators);//reoves parenthesis if possible
        ListIterator <Equation>cycleAll = noOperators.listIterator();//so noOperators can be cycled through
        while(cycleAll.hasNext())
        {   
            Nomial_new compare1;
            if(cycleAll.next() instanceof Parenthesis_new)
                continue;
            else
            {
                compare1= (Nomial_new)cycleAll.previous();
                cycleAll.next();
                while(cycleAll.hasNext())
                {
                    Nomial_new compare2;
                    if(cycleAll.next() instanceof Parenthesis_new)
                        continue;
                    else
                    {
                        compare2= (Nomial_new)cycleAll.previous();
                        cycleAll.next();
                        if(compare1.compareTo(compare2)==0)
                        {
                            compare1.changeCount(compare2.getCount());
                            cycleAll.remove();//okay so i removed compare 2
                        }
                    }
                    
                }
            }
            simplified.add(compare1);//always add this object
            //simplified.add(new Operator()); 
            removeFirst(cycleAll);//remove the first object in the iterator as well
        }
        
        simpleFixes(simplified);//removes zeros, exponent errors
        if(haveParenthesis(simplified)>1)//if there are 2 parenthesis left, they are tested to see if they can be added together. 
        {
            ArrayList<Parenthesis_new> allParen = new ArrayList<Parenthesis_new>();
            ArrayList<Parenthesis_new> simplifiedParen = new ArrayList<Parenthesis_new>();
            for(int indx = 0; indx< simplified.size(); indx++)//gets all Parenthesis and removes them from the list
            {   //regardless, all parenthesis are added back on weather or not they are added together.
                if(simplified.get(indx) instanceof Parenthesis_new)
                {
                    allParen.add((Parenthesis_new)simplified.get(indx));
                    simplified.remove(indx);
                    indx--;
                }
            }
            ListIterator <Parenthesis_new>cycleParen = allParen.listIterator();//so noOperators can be cycled through
            while(cycleAll.hasNext())
            {   
                Parenthesis_new compare1=cycleParen.next();         
                while(cycleAll.hasNext())
                {
                    Parenthesis_new compare2=cycleParen.next();
                    if(compare1.compareTo(compare2)==0)
                    {
                        compare1.addition(compare2);
                        cycleParen.remove();//okay so i removed compare 2
                    }
                }
                simplifiedParen.add(compare1);//always add this object
                //simplified.add(new Operator()); 
                removeFirst(cycleAll);//remove the first object in the iterator as well
            }
            //then use the same method to add them together as is done above then add the arraylist to the other one
            
        }
        
        return simplified;
    }
    public static void decompressParenthesis(ArrayList<Equation> toSimplify)
    {   //I need to make a thing that deals with the "extra" bit of parenthesis
        for(int indx = 0; indx<toSimplify.size(); indx++)
        {
            if(toSimplify.get(indx) instanceof Parenthesis_new)
            {
                Parenthesis_new decomp = (Parenthesis_new) toSimplify.get(indx);
                ArrayList<Equation> bottom = decomp.getPart(1);
                if(bottom.size()==1)
                {
                    Nomial_new bottomNomial= (Nomial_new)bottom.get(0);
                    if(bottomNomial.getCount()==1 && bottomNomial.getVarExponent()==0)
                    {
                        ArrayList<Equation> top = removeOperators(decomp.getPart(0));
                        toSimplify.remove(indx);
                        for(Equation toAdd: top)
                        {
                            toSimplify.add(indx, toAdd);
                        }
                        indx--;
                    }
                }
                ArrayList<Equation> extra = decomp.getPart(3);
                if(!(extra.size()==0))//if there in an extra part to the parenthesis
                {
                    decomp.resetExtra();
                    for(Equation toAdd: extra)
                    {
                        toSimplify.add(toAdd);
                    }
                }
            }
        }
    }
    public static ArrayList<Equation> removeOperators(ArrayList<Equation> toSimplify)
    {
        ArrayList<Equation> noOperators= new ArrayList<Equation>();
        for(Equation test: toSimplify)
        {
            if(test instanceof Nomial_new|| test instanceof Parenthesis_new)
                noOperators.add(test);
        }
        return noOperators;
    }
    /**
     * part of the above solveAddition() method, this removes the first object in the 
     * listIterator and then prepares the iterator for the next cycle through it
     * Essentially, it moves the position in the ListIterator back to the beginning.  
     * 
     */
    public static void removeFirst(ListIterator<Equation> toRemove)
    {   //removes the first object in the listIterator and prepares it for the next cyle
        while(toRemove.hasPrevious())
        {
            toRemove.previous();
        }
        toRemove.remove();
    }
    /**
     * some of the common errors that occure are fixed ehre
     * Sometimes, 0 is raised to a power and that is fixed
     * SOmetimes, there are multiple zeros that are present, and they are all removed
     * Sometimes the size of the ArrayList is zero -- there is nothing in it-- and 
     * one zero is added back onto the array.  
     */
    public static void simpleFixes(ArrayList<Equation> toSimplify)
    {
        for(int indx =0; indx<toSimplify.size(); indx++)
        {
            if(toSimplify.get(indx) instanceof Nomial_new)
            {
                Nomial_new nom=(Nomial_new)toSimplify.get(indx);
                nom.fixExponents();
                if(nom.getCount()==0)
                {
                    toSimplify.remove(indx);
                    indx--;
                }
            }
        }
        if(toSimplify.size()==0)//if it has removed every zero, so there is nothing there
        {
            toSimplify.add(new Nomial_new());
        }
        //sortDecending(toSimplify);
    }
    /**
     * method that could be implemented for a neat output
     */
    public static void sortDecending(ArrayList<Equation> toSimplify)
    {
        Nomial_new itemToInsert;
        int j;
        boolean keepGoing;
        
        for(int k=1; k<toSimplify.size(); k++)
        {
            itemToInsert=(Nomial_new)toSimplify.get(k);
            j=k-1;
            keepGoing=true;
            while((j>=0)&&keepGoing)
            {
                Nomial_new tmp = (Nomial_new) toSimplify.get(j);
                if(itemToInsert.getVarExponent()<tmp.getVarExponent())
                {
                    toSimplify.set(j+1,toSimplify.get(j));
                    j--;
                    if(j==-1)
                        toSimplify.set(0,itemToInsert);
                        
                }
                else
                {
                    keepGoing=false;
                    toSimplify.set(j+1,itemToInsert);
                }
            }
        }
    }
    public static ArrayList<Equation> solveMultiplicaiton(ArrayList<Equation> toSimplify)
    {
        ListIterator<Equation> cycleAll = toSimplify.listIterator();
        ArrayList<Equation> simplified = new ArrayList<Equation>();//has no operators
        
        while(cycleAll.hasNext())
        {
            ArrayList<Equation> toMultiply = new ArrayList<Equation>();//temporary.  
            Equation tmp1 = cycleAll.next();
            boolean addFirst= true;
            while(cycleAll.hasNext())
            {
                Equation tmp2 = cycleAll.next();
                if(tmp2 instanceof Operator && (tmp2.toString().equals("*")||tmp2.toString().equals("/")))
                {
                    if(addFirst)
                    {
                        toMultiply.add(tmp1);
                        addFirst=false;
                    }
                    toMultiply.add(tmp2);
                    toMultiply.add(cycleAll.next());
                }
                else
                {
                    if(addFirst)//so if first has been added already
                    {
                        simplified.add(tmp1);
                    }
                    if(toMultiply.size()>0)
                    {
                    simplified.add(multiply(toMultiply));
                }
                    break;
                }
            }
        }
        
        
        return simplified;
    }
    public static Equation multiply(ArrayList<Equation> toMultiply)
    {
        return new Nomial_new();
    }
}

