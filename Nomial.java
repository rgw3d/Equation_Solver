/**
 * 
 */
import java.util.Scanner;
public class Nomial implements Comparable
{
    private String rawInput;
    private double varExponent=0;
    private double numExponent=1;
    private double count=1;
    private boolean var=false;
    private boolean containedCarrot=false;
    private int varCarrot=-1;
    private int numCarrot=-1;
    public Nomial(double varEx, double countTemp)//seondary constructor
    {
        varExponent=varEx;
        count=countTemp;
        if(varExponent!=0)
            var=true;
    }
    public Nomial(String s)
    {     
        rawInput=s;
        if(rawInput.contains("x"))//if there is a x in it
        {
            varExponent=1;
            var=true;
        }
        //System.out.println(rawInput+"\t"+ varExponent+"\t"+numExponent+"\t"+count+"\t"+var+"\t"+containedCarrot+"\t"+varCarrot+"\t"+numCarrot);
        if(rawInput.contains("^"))//if there is a power
        {
            //System.out.println("1"+rawInput+"\t"+ varExponent+"\t"+numExponent+"\t"+count+"\t"+var+"\t"+containedCarrot+"\t"+varCarrot+"\t"+numCarrot);
            findCarrots();
            //System.out.println("2"+rawInput+"\t"+ varExponent+"\t"+numExponent+"\t"+count+"\t"+var+"\t"+containedCarrot+"\t"+varCarrot+"\t"+numCarrot);
            removeCarrots();
            //System.out.println("3"+rawInput+"\t"+ varExponent+"\t"+numExponent+"\t"+count+"\t"+var+"\t"+containedCarrot+"\t"+varCarrot+"\t"+numCarrot);
            if(var)
                {
                //System.out.println("4"+rawInput+"\t"+ varExponent+"\t"+numExponent+"\t"+count+"\t"+var+"\t"+containedCarrot+"\t"+varCarrot+"\t"+numCarrot);
                extractCount();
            }
            else
                count=Double.parseDouble(rawInput);
        }
        else
        {
            //System.out.println(rawInput+"\t"+ varExponent+"\t"+numExponent+"\t"+count+"\t"+var+"\t"+containedCarrot+"\t"+varCarrot+"\t"+numCarrot);
            if(rawInput.equals("x")||rawInput.equals("-x"))// if it just a "x"
                rawInput=rawInput.replace("x","1x");
            if(rawInput.equals("-"))//another special case where it sends just a negative
                rawInput=rawInput.replace("-","-1");
            rawInput=rawInput.replace("x","");
            count=Double.parseDouble(rawInput);
        }
        
        count=Math.pow(count,numExponent);//raises the count to the number exponent.  no effect if there is a var exponent as well
            
        //System.out.println("Variable Exponent"+varExponent+ "   "+"number Count"+count);
    }
    private void findCarrots()
    {
        if(rawInput.contains("x^"))
        {
            if(rawInput.contains("x^-"))
            {
                varCarrot=rawInput.indexOf("x^-");
                varExponent=Integer.parseInt(rawInput.substring(varCarrot+2,varCarrot+4));
            }
            else
            {
                varCarrot=rawInput.indexOf("x^");
                varExponent=Integer.parseInt(rawInput.charAt(varCarrot+2)+"");
            }
            if(rawInput.lastIndexOf("^")!=rawInput.indexOf("^"))
            {
                if(rawInput.lastIndexOf("^")>varCarrot)
                    numCarrot=rawInput.lastIndexOf("^");
                else
                    numCarrot=rawInput.indexOf("^");
                numExponent=Integer.parseInt(rawInput.charAt(numCarrot+1)+"");                    
            }
        }
        else
        {
            if(rawInput.contains("^-"))
            {
                numCarrot=rawInput.indexOf("^-");
                numExponent=Integer.parseInt(rawInput.substring(numCarrot+1,numCarrot+3));
            }
            else
            {
                numCarrot=rawInput.indexOf("^");
                numExponent=Integer.parseInt(rawInput.charAt(numCarrot+1)+"");
            }
        }            
        containedCarrot=true;
    }
    private void removeCarrots()
    {
        rawInput=rawInput.replace("^"+(int)varExponent," ");
        rawInput=rawInput.replace("^"+(int)numExponent," ");
        System.out.println(numExponent);
        rawInput.trim();
        System.out.println(rawInput);
        // if(varCarrot>-1)
            //leaves the x and any preceding numbers
        //if(numCarrot>-1)
            //leaves any preceding numbers.  
        //3^3x^2=0
    }
    private void extractCount()
    {
        Scanner findVar=new Scanner(rawInput);
        while(findVar.hasNext())
        {
            String temp=findVar.next();
            if(temp.contains("x"))
            {
                if(temp.equals("x")||temp.equals("-x"))// if it just a "x"
                    temp=temp.replace("x","1x");
                temp=temp.replace("x","");
                count*=Double.parseDouble(temp);
            }
            else
            {
                count*=Double.parseDouble(temp);
            }
        }
    }
    
    public double getVarExponent()
    {
        return varExponent;
    }
    public double getCount()
    {
        return count;
    }
    public boolean hasVar()
    {
        return var;
    }
    public void changeCount(double toAdd)
    {
        count+=toAdd;
    }
    public void makeNegative()
    {
        count*=-1;
    }
    
    public int compareTo(Object x)//returns 0 if they are equal
    {
        Nomial second=(Nomial)x;
        int isEqual=-1;        
        double firstVarValue=getVarExponent();
        double secondVarValue=second.getVarExponent();
        if(firstVarValue==secondVarValue)
            isEqual=0;
        else
            isEqual=(int)(firstVarValue-secondVarValue);
        return isEqual;
    }
    
    public String toString()
    {
        String toReturn="count: "+count +" varExponent: "+varExponent;
        return toReturn;
    }
    
    public void fixExponents()//used if there is an varExponent and the count is zero.  changes varExponent to zero
    {
        if(count==0&&(varExponent>0||varExponent<0))
        {
            varExponent=0;
        }
    }
    
    
}
/*
 might be needed
            if(changedInput.contains("x^"))//if there is an x with an exponent
            {
                int varCarrot=changedInput.indexOf("x^");
                varPower=Integer.parseInt(changedInput.charAt(varCarrot+2)+"");//only single digit exponents
                if(changedInput.lastIndexOf("^")!=changedInput.indexOf("^"))
                {   //now we find if there are 2 varCarrots, and if therer are we find the other exponent (of the number)
                    int numCarrot;
                    if(changedInput.lastIndexOf("^")>varCarrot)
                        numCarrot=changedInput.lastIndexOf("^");
                    else
                        numCarrot=changedInput.indexOf("^");
                    numPower=Integer.parseInt(changedInput.charAt(numCarrot+1)+"");
                }
            }
            else// if there is just a number with an exponent
            {
                int varCarrot=changedInput.indexOf("^");
                numPower=Integer.parseInt(changedInput.charAt(varCarrot+1)+"");
            }
 */
