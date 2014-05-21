/**
 *This Class will contain all the methods that allow all the operations to be simplified
 */
import java.util.ArrayList;
import java.util.ListIterator;
public class Simplify
{
    /**
     * The main method of this class that is called from the outside
     * it is given an arraylist and then decides what needs to be done
     * For example, it figures out if multiplication is present, and then calls
     * the appropriate method to solve it
     */
    public static ArrayList simplify(ArrayList toSimplify)
    {
        //so lets have it check to see if there are Parenthesis
        ArrayList toReturn = new ArrayList();
        if(haveParenthesis(toSimplify))
        {
            //time to solve parenthesis 
            //only one method call, it then calls the other sub-methods that solve division and addition
            //toReturn=solveParenthesis(toSimplify);
            toReturn=solveAddition(solveMultiplication(solveParenthesis(toSimplify)));
        }
        else if(haveMultiplication(toSimplify))//if there is multiplication or division
        {
            //System.out.println("Test\n "+solveMultiplication(toSimplify)+"\n ");
            toReturn=solveAddition(solveMultiplication(toSimplify));
            //run the multiplication method that solves division as well
        }
        else
        {
            toReturn=solveAddition(toSimplify);
        }
        return toReturn;
    }
    /**
     * Tests for a Parenthesis object present in the arrayList
     */
    public static boolean haveParenthesis(ArrayList toSimplify)
    {
        for(int indx=0; indx<toSimplify.size(); indx++)
        {
            if(toSimplify.get(indx) instanceof Parenthesis)
                return true;
        }
        return false;
    }
    /**
     * Tests for a Operator object that returns a multiplication or division sign
     */
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
    /**
     * solves addition within the ArrayList
     */
    public static ArrayList solveAddition(ArrayList toSimplify)
    {
        //just solving addition, there are no other signs so operators in general can be ignored
        //first thing to do is to remove all the operators
        ArrayList<Nomial> noOperators=new ArrayList<Nomial>();
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
        simpleFixes(simplified);//removes zeros, exponent errors, and sorts in decending order
        
        return simplified;
    }
    /**
     * part of the above solveAddition() method, this removes the first object in the 
     * listIterator and then prepares the iterator for the next cycle through it
     * Essentially, it moves the position in the ListIterator back to the beginning.  
     * 
     */
    public static void removeFirst(ListIterator toRemove)
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
    public static void simpleFixes(ArrayList<Nomial> toSimplify)
    {
        for(int indx=0; indx< toSimplify.size();indx++)
        {
            Nomial nom=toSimplify.get(indx);
            nom.fixExponents();
            if(nom.getCount()==0)
            {
                toSimplify.remove(indx);
                indx--;
            }
        }
        if(toSimplify.size()==0)//if it has removed every zero, so there is nothing there
        {
            toSimplify.add(new Nomial(0,0));
        }
        //sortDecending(toSimplify);
    }
    /**
     * Solves multiplication and division. simplifies the arrayList and returns a new ArrayList
     * Basic outline: advance Nomial by Nomial, checking to see if the operator is multiplication sign or division
     * if it is multiplication or division, it gets the next Nomial following and adds all of them to toMultiply ArrayList
     * this loop continues until it finds an Operator that is not multiplication or division, at which point it breaks the innter while loop and sends
     * everything that it had stored in toMultiply to the methods that returns one Nomial of everything
     * This returned nomial is added to the ArrayList simplified.  Any nomial that does not have multiplication is added onto the ArrayList simplified as well
     * there are no Operator objects in the simplifed ArrayList because it is assumed that everything is added
     */
    public static ArrayList solveMultiplication(ArrayList toSimplify)
    {
        //okay so the strategy is to find the objects in a group that have multiplication
        //and then they are multiplied together and the thing moves on
        ListIterator cycleAll = toSimplify.listIterator();
        ArrayList simplified = new ArrayList();
        while(cycleAll.hasNext())//use a list iterator and loop.  yay
        {
            ArrayList toMultiply = new ArrayList();//this is used below and sent to be simplifed in the multiplication methods
            Nomial nTmp=(Nomial)cycleAll.next();//The first object in the list is a <Nomial>. even after it loops, its a <Nomial>
            boolean addFirst=true;// special case where the 
            if(cycleAll.hasNext())
            {
                while(cycleAll.hasNext())
                {
                    if(cycleAll.hasNext() && cycleAll.next() instanceof Operator) 
                    {
                        Operator opTmp= (Operator)cycleAll.previous();
                        cycleAll.next();//move it up
                        if(cycleAll.hasNext() && (opTmp.toString().equals("*")|| opTmp.toString().equals("/")))
                        {
                            Nomial nTmp2=(Nomial)cycleAll.next();
                            if(addFirst)
                            {
                                toMultiply.add(nTmp);
                                addFirst=false;
                            }
                            toMultiply.add(opTmp);
                            toMultiply.add(nTmp2);
                        }
                        else 
                        {
                            if(addFirst)
                                simplified.add(nTmp);
                            break;
                        }
                    }
                    else
                    {
                        if(addFirst)
                            simplified.add(nTmp);
                        break;
                    }
                    addFirst=false;
                }
            }
            else
            {
                toMultiply.add(nTmp);
            }
            //System.out.println(toMultiply);
            simplified.add(doMultiplicationOrDivision(toMultiply));
        }
        return simplified;
    }
    public static Nomial doMultiplicationOrDivision(ArrayList toMultiply)
    {
        Nomial runningTotal;
        if(toMultiply.size()==0)
        {
            return new Nomial(0,0);
        }
        else 
        {
            runningTotal = multiply(new Nomial(0,1),(Nomial)toMultiply.get(0));
            for(int indx=1; indx<toMultiply.size(); indx+=2)
            {
                Operator tmp= (Operator)toMultiply.get(indx);
                if(tmp.toString().equals("*"))
                {
                    runningTotal= multiply(runningTotal,(Nomial)toMultiply.get(indx+1));
                }
                else if(tmp.toString().equals("/"))
                {
                    runningTotal= divide(runningTotal, (Nomial)toMultiply.get(indx+1));
                }
                else
                {
                    System.out.println("This should not happen.  Something went wrong");
                }
            }
        }
        System.out.println(runningTotal);
        return runningTotal;
    }
    public static Nomial multiply(Nomial x, Nomial y)
    {
        return new Nomial(x.getVarExponent()+y.getVarExponent(), x.getCount()*y.getCount());
    }
    public static Nomial divide(Nomial x, Nomial y)
    {
        return new Nomial(x.getVarExponent()-y.getVarExponent(), x.getCount()/y.getCount());
    }
    /**
     * Solve parenthesis.  lots of recursion up in here.  
     */
    public static ArrayList solveParenthesis(ArrayList toSimplify)
    {
        ListIterator<Parenthesis> paren= findParenthesis(toSimplify);
        while(paren.hasNext())
        {
            Parenthesis x =paren.next();
             simplifyIndividualParenthesis(x);
        }
        
        ArrayList toReturn = new ArrayList();
        for(int indx=0; indx<toSimplify.size(); indx++)
        {
            if(toSimplify.get(indx) instanceof Parenthesis)
            {
                toReturn.add(simplifyIndividualParenthesis((Parenthesis)toSimplify.get(indx)));
            }
            else
            {
                toReturn.add(toSimplify.get(indx));
            }
        }
        /*
        ArrayList toReturn= new ArrayList();
        
        for(Parenthesis obj: findParenthesis(toSimplify))//find all the inside/stacked parentheses
        {
            solveParenthesis(obj.toArrayList());
        }
        toReturn=simplify(toSimplify);
        */
       return toReturn;
    }
    public static Parenthesis simplifyIndividualParenthesis(Parenthesis original)
    {
        return new Parenthesis(simplify(original.toArrayList()));        
    }
    public static ListIterator<Parenthesis> findParenthesis(ArrayList toSimplify)
    {
        
        ArrayList<Parenthesis> toReturn = new ArrayList<Parenthesis>();
        for(int indx=0; indx<toSimplify.size(); indx++)
        {
            if(toSimplify.get(indx) instanceof Parenthesis)
            {
                Parenthesis x = (Parenthesis)toSimplify.get(indx);
                toReturn.add(x);
            }
        }
        ListIterator<Parenthesis> paren = toReturn.listIterator();
        return paren;
    }
    /**
     * Un used method that could be implemented for a neat output
     */
    public static void sortDecending(ArrayList<Nomial> toSimplify)
    {
        Nomial itemToInsert;
        int j;
        boolean keepGoing;
        
        for(int k=1; k<toSimplify.size(); k++)
        {
            itemToInsert=toSimplify.get(k);
            j=k-1;
            keepGoing=true;
            while((j>=0)&&keepGoing)
            {
                if(itemToInsert.getVarExponent()<toSimplify.get(j).getVarExponent())
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
}

