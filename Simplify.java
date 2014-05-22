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
            toReturn=solveAddition(simplifyMultiplication(solveParenthesis(toSimplify)));
        }
        else if(haveMultiplication(toSimplify))//if there is multiplication or division
        {
            //System.out.println("Test\n "+solveMultiplication(toSimplify)+"\n ");
            toReturn=solveAddition(simplifyMultiplication(toSimplify));
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
    public static ArrayList simplifyMultiplication(ArrayList toSimplify)
    {
        ListIterator cycleAll = toSimplify.listIterator();
        ArrayList simplified = new ArrayList();
        while(cycleAll.hasNext())
        {
            ArrayList toMultiply = new ArrayList();//this is used below and sent to be simplifed in the multiplication methods
            Object nTmp;
            if(cycleAll.next() instanceof Parenthesis)
            {
                Parenthesis tmpParen= (Parenthesis)cycleAll.previous();
                nTmp = tmpParen.toArrayList();
            }
            else
            {
                nTmp=(Nomial)cycleAll.previous();
            }
            cycleAll.next();//move it up
            boolean addFirst=true;// special case
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
                            Object nTmp2;
                            if(cycleAll.next() instanceof Parenthesis)
                            {
                                Parenthesis tmpParen= (Parenthesis)cycleAll.previous();
                                nTmp2 = tmpParen.toArrayList();
                            }
                            else
                            {
                                nTmp2=(Nomial)cycleAll.previous();
                            }
                            if(addFirst)//special case where the first object, outside this innerloop, is added on.  can only be added once, so thats why there the boolean.  
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
    public static Parenthesis doMultiplicationOrDivision(ArrayList toMultiply)
    {
        Parenthesis runningTotal;
        if(toMultiply.size()==0)
        {
            return new Parenthesis( new Nomial(0,0));
        }
        else 
        {
            runningTotal = multiply(new Parenthesis(new Nomial(0,1)),new Parenthesis((Nomial)toMultiply.get(0)));
            for(int indx=1; indx<toMultiply.size(); indx+=2)
            {
                Operator tmp= (Operator)toMultiply.get(indx);
                if(tmp.toString().equals("*"))
                {
                    runningTotal= multiply(runningTotal, new Parenthesis((Nomial)toMultiply.get(indx+1)));
                }
                else if(tmp.toString().equals("/"))
                {
                    runningTotal= divide(runningTotal, new Parenthesis((Nomial)toMultiply.get(indx+1)));
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
    public static Parenthesis multiply(Parenthesis x, Parenthesis y)
    {
        ArrayList runningTotal= new ArrayList();
        boolean firstPass=true;
        for(Nomial nom1: x.toArrayListWithoutOperators())
        {  
            for(Nomial nom2: y.toArrayListWithoutOperators())
            {
                runningTotal.add(new Nomial(nom1.getVarExponent()+nom2.getVarExponent(), nom1.getCount()*nom2.getCount()));
                
            }
        }
        //runningTotal.remove(runningTotal.size()-1);
        return new Parenthesis(runningTotal);
    }
    public static Parenthesis divide(Parenthesis x, Parenthesis y)
    {   
        //check to see if I can even divide.
        x= new Parenthesis(solveAddition(solveMultiplication(x.toArrayList())));
        y= new Parenthesis(solveAddition(solveMultiplication(y.toArrayList())));
        int xSize=x.toArrayListWithoutOperators().size();
        int ySize=y.toArrayListWithoutOperators().size();
        if(xSize==1&& ySize==1)
        {
            return new Parenthesis( new Nomial(x.toArrayListWithoutOperators().get(0).getVarExponent()-y.toArrayListWithoutOperators().get(0).getVarExponent(), x.toArrayListWithoutOperators().get(0).getCount()/y.toArrayListWithoutOperators().get(0).getCount()));
        }
        else //Numerator is only one digit
        {
            ArrayList<Integer> xConstantFactors=findConstantFactors(x.toArrayListWithoutOperators());
            ArrayList<Integer> yConstantFactors=findConstantFactors(y.toArrayListWithoutOperators());
            double xVarMax=findVarFactor(x.toArrayListWithoutOperators());
            double yVarMax=findVarFactor(y.toArrayListWithoutOperators());
            double highestVar=xVarMax;
            if(xVarMax>yVarMax)
                highestVar=yVarMax;
            else
                highestVar=xVarMax;
            int bestConstantFactor=findBestContantFactors(xConstantFactors, yConstantFactors);
            Parenthesis top = reduceByDivision(x,highestVar,bestConstantFactor);
            Parenthesis bottom = reduceByDivision(y, highestVar, bestConstantFactor);
        }
        /*
        else if(xSize>0 && ySize==1)//Denominator is only one digit
        {
            ArrayList<Integer> xConstantFactors=findConstantFactors(x.toArrayListWithoutOperators());
            ArrayList<Integer> yConstantFactors=findConstantFactors(y.toArrayListWithoutOperators());
            double xVarMax=findVarFactor(x.toArrayListWithoutOperators());
            double yVarMax=findVarFactor(y.toArrayListWithoutOperators());   
        }
        else if(xSize>1 && ySize>1)//both Numerator and Denominator have multiple digits
        {
            ArrayList<Integer> xConstantFactors=findConstantFactors(x.toArrayListWithoutOperators());
            ArrayList<Integer> yConstantFactors=findConstantFactors(y.toArrayListWithoutOperators());
            double xVarMax=findVarFactor(x.toArrayListWithoutOperators());
            double yVarMax=findVarFactor(y.toArrayListWithoutOperators());            
        }
        */
        return new Parenthesis( new Nomial(0,0));
    }
    public static Parenthesis reduceByDivision(Parenthesis toDivide, double var, int num)
    {
        Parenthesis toReturn;
        ArrayList changes= new ArrayList();
        for(Nomial toChange: toDivide.toArrayListWithoutOperators())
        {
            changes.add(new Nomial(toChange.getVarExponent()-var,toChange.getCount()-num));
        }
        toReturn = new Parenthesis(changes);
        return toReturn;
    }
    public static int findBestContantFactors(ArrayList<Integer> top, ArrayList<Integer> bottom)
    {
        int highestFactor=1;
        for(int t: top)
        {
            for(int b: bottom)
            {
                if(t==b&&t>highestFactor)
                    highestFactor=t;
            }
        }
        return highestFactor;
    }
    public static ArrayList<Integer> findConstantFactors(ArrayList toSearch)
    {
        ArrayList<Integer> factors= new ArrayList<Integer>();
        Nomial tmp = (Nomial)toSearch.get(0);
        double count=Math.abs(tmp.getCount());
        
        for(int indx=0; indx<Math.sqrt(count); indx++)//find factors for the first one
        {
            if(count%indx==0)
            {
                factors.add(indx);
                factors.add((int)count/indx);
            }
        }
        for(int indx=0; indx<factors.size(); indx++)//remove any that dont work for everysingle other number
        {
            for(int indx2=1; indx2<toSearch.size(); indx2++)
            {
                Nomial toCompare = (Nomial)toSearch.get(indx2);
                if(!(toCompare.getCount()%factors.get(indx)==0))
                {
                    factors.remove(indx);
                    indx--;
                }
            }
        }
        return factors;
    }
    public static double findVarFactor(ArrayList toSearch)
    {
        double varMin=10; 
        for(int indx=0; indx<toSearch.size(); indx++)
        {
            Nomial tmp=(Nomial)toSearch.get(indx);
            if(tmp.getVarExponent()<varMin)
                varMin=(tmp.getVarExponent());
        }
        return varMin;
    }
    /**
     * Solve parenthesis.  lots of recursion up in here.  
     */
    public static ArrayList solveParenthesis(ArrayList toSimplify)
    {
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

