/**
 * New nomial class
 * 
 * @author (your name) 
 * @version second Nomial class
 */
import java.util.ArrayList;
import java.util.Scanner;
public class Nomial_new implements Equation
{
    private double constantCount = 0;
    private double varExponent = 0;
    private String input;
    
    private double numExponent=1;
    private boolean containedCarrot=false;//Carrot variables are used in one class below
    private int varCarrot=-1;
    private int numCarrot=-1;
    
    
    
    /**
     * default constructor that initializes a Nomial of 0 value
     */
    public Nomial_new()
    {
    }
    /**
     * constructor that allows the initialization of variables
     * @param tmpCount is the non-variable Constant sent 
     * @param tmpVarEx, if something other than a 0, confirms a variable and sets its exponent
     */
    public Nomial_new(double tmpCount, double tmpVarEx)
    {
        constantCount=tmpCount;
        varExponent=tmpVarEx;
    }
    /**
     * constructor that allows the initialization from a String
     * @param tmpInput is the input in string form of one Nomial
     */
    public Nomial_new(String tmpInput)
    {
        input=tmpInput;
        parseInput();
    }
    /**
     * @return String form of the nomial
     */
    public String toString()
    {
        String toReturn = constantCount+"";
        if(varExponent!=0) 
        {
            if(varExponent!=1)
            {
                toReturn+="x^"+varExponent;
            }
            else
            {
                toReturn+="x";
            }
        }
        return toReturn;
    }
    /**
     * method for parsing the input if the input is in a string form
     */
    private void parseInput()
    {
        if(input.contains("x"))//if there is a x in it
        {
            varExponent=1;
        }
        if(input.contains("^"))//if there is a power
        {
            findCarrots();
            removeCarrots();
            if(hasVariable())
                extractCount();
            else
                constantCount=Double.parseDouble(input);
        }
        else
        {
            if(input.equals("x")||input.equals("-x"))// if it just a "x"
                input=input.replace("x","1x");
            if(input.equals("-"))//another special case where it sends just a negative
                input=input.replace("-","-1");
            input=input.replace("x","");
            constantCount=Double.parseDouble(input);
        }
        
        constantCount=Math.pow(constantCount,numExponent);//raises the count to the number exponent.  no effect if there is a var exponent as well
    }
    /**
     * part of the parseInput() method, finds where the number is raised to a power
     */
    private void findCarrots()
    {
        if(input.contains("x^"))
        {
            if(input.contains("x^-"))
            {
                varCarrot=input.indexOf("x^-");
                varExponent=Integer.parseInt(input.substring(varCarrot+2,varCarrot+4));
            }
            else
            {
                varCarrot=input.indexOf("x^");
                varExponent=Integer.parseInt(input.charAt(varCarrot+2)+"");
            }
            if(input.lastIndexOf("^")!=input.indexOf("^"))
            {
                if(input.lastIndexOf("^")>varCarrot+1)
                    numCarrot=input.lastIndexOf("^");
                else
                    numCarrot=input.indexOf("^");
                numExponent=Integer.parseInt(input.charAt(numCarrot+1)+"");                    
            }
        }
        else
        {
            if(input.contains("^-"))
            {
                numCarrot=input.indexOf("^-");
                numExponent=Integer.parseInt(input.substring(numCarrot+1,numCarrot+3));
            }
            else
            {
                numCarrot=input.indexOf("^");
                numExponent=Integer.parseInt(input.charAt(numCarrot+1)+"");
            }
        }            
        containedCarrot=true;
    }    
    /**
     * removes all carrots after the raised powers are found.
     */
    private void removeCarrots()
    {
        input=input.replace("^"+(int)varExponent," ");
        input=input.replace("^"+(int)numExponent," ");
        //System.out.println(numExponent);
        input=input.trim();
        //System.out.println(input);
    }
    /**
     * pulls the constantCount from a string
     */
    private void extractCount()
    {
        Scanner findVar=new Scanner(input);
        while(findVar.hasNext())
        {
            String temp=findVar.next();
            if(temp.contains("x"))
            {
                if(temp.equals("x")||temp.equals("-x"))// if it just a "x"
                    temp=temp.replace("x","1x");
                temp=temp.replace("x","");
                constantCount=Double.parseDouble(temp);
            }
            else
            {
                constantCount=Double.parseDouble(temp);
            }
        }
    }
    /**
     * @return ArrayList<Equation> with the Nomial inside into ArrayList form
     */
    public ArrayList<Equation> toArrayList()
    {
        ArrayList<Equation> toReturn = new ArrayList<Equation>();
        toReturn.add(new Nomial_new(constantCount, varExponent));
        return toReturn;
    }    
    /**
     * @return double value of exponent for the variable (if there is one)
     */
    public double getVarExponent()
    {
        return varExponent;
    }
    /**
     * @return double value of the count of the constants in this nomial
     */
    public double getCount()
    {
        return constantCount;
    }
    /**
     * @return true if there is a variable
     */
    public boolean hasVariable()
    {
        if(varExponent!=0)
            return true;
        return false;
    }
    public void addToCount(double toAdd)
    {
        constantCount+=toAdd;
    }
    public void multiplyToCount(double toMultiply)
    {
        constantCount*=toMultiply;
    }
    public void addToVarExponent(double toAdd)
    {
        varExponent+=toAdd;
    }
    public int compareTo(Equation x)//returns 0 if they are equal
    {
        Nomial_new second=(Nomial_new)x;
        int isEqual=-1;        
        double firstVarValue=getVarExponent();
        double secondVarValue=second.getVarExponent();
        if(firstVarValue==secondVarValue)
            isEqual=0;
        else
            isEqual=(int)(firstVarValue-secondVarValue);
        return isEqual;
    }
    public void fixExponents()//used if there is an varExponent and the count is zero.  changes varExponent to zero
    {
        if(constantCount==0&&(varExponent>0||varExponent<0))
        {
            varExponent=0;
        }
    }
    
    
    
    
    
    
    
}
