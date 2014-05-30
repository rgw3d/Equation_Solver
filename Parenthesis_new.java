import java.util.ArrayList;
import java.util.Scanner;
/**
 * New Parenthesis class.
 * the format for Parenthesis is: (top/bottom+extra)
 * 
 * @author rgw3d
 * @version 5-22-14
 */
public class Parenthesis_new implements Equation
{
    private ArrayList<Equation> unSimplified = new ArrayList<Equation>();
    private ArrayList<Equation> top = new ArrayList<Equation>();
    private ArrayList<Equation> bottom = new ArrayList<Equation>();
    private ArrayList<Equation> extra = new ArrayList<Equation>();
    private ArrayList<Equation> simplified = new ArrayList<Equation>();
    private double power = 1;
    
    public Parenthesis_new(String insideValue, double tmpPower)
    {
        power=tmpPower;
        unSimplified = Control.parseEquation(insideValue.substring(1,insideValue.length()-1));
        setPrivateValues();
    }
    public Parenthesis_new(String insideValue)   
    {
        unSimplified = Control.parseEquation(insideValue.substring(1,insideValue.length()-1));
        
        setPrivateValues();
        
        System.out.println(top);
        System.out.println(bottom);
        System.out.println(extra);
    }
    public Parenthesis_new(ArrayList<Equation> insideValue)
    {
        // idk if this is necessary.  
        top = insideValue;
        bottom = new Nomial_new(1,0).toArrayList();
    }
    public Parenthesis_new(ArrayList<Equation> tmpTop, ArrayList<Equation> tmpBottom, ArrayList<Equation> tmpExtra, double tmpPower)
    {   
        top = tmpTop;
        bottom = tmpBottom;
        extra = tmpExtra;
        power = tmpPower;
    }
    public void setPrivateValues()
    {
        simplified = Simplify_new.simplify_control(unSimplified);
        top = simplified;
        bottom = new Nomial_new(1,0).toArrayList();
        extra = new Nomial_new().toArrayList();
        //first the string must be simplifed
        /*
        if(simplified.size()==0|| power==0)
        {
            top= new Nomial_new().toArrayList();
            bottom = new Nomial_new(1,0).toArrayList();
            extra = new Nomial_new().toArrayList();
        }
        else 
        {
            
            if(simplified.get(0) instanceof Parenthesis_new)
            {
                Parenthesis_new x= (Parenthesis_new)simplified.get(0);
                System.out.println(x.toString());
                //removeExtraParenthesis(simplified.get(0));
            }
            else if(simplified.get(0) instanceof Nomial_new)
            {
                Nomial_new tmp = (Nomial_new)simplified.get(0);
                top = tmp.toArrayList();
                bottom = new Nomial_new(1,0).toArrayList();
                extra = new Nomial_new().toArrayList();
            }
            else
            {
                top = new Nomial_new(1,0).toArrayList();
                bottom = new Nomial_new(1,0).toArrayList();
                extra = new Nomial_new().toArrayList();
            }      
            
            //Nomial_new tmp = (Nomial_new)simplified.get(0);
            
        }
        */
    }
    /*
    public String removeExtraParenthesis(String toFix)
    {
        
        ArrayList<String> useWithIterator = new ArrayList<String>();
        for(int indx=0; indx<input.length(); indx++)
        {
            useWithIterator.add(input.charAt(indx)+"");
        }
        ListIterator<String> charByChar= useWithIterator.listIterator();
        
       
        Scanner charByChar=new Scanner(toFix);
        charByChar.useDelimiter("");
        
        String toReturn="";
        while(charByChar.hasNext())
        {
            String tmp= charByChar.next();
            if(tmp.equals("("))//if it is the start of a parenthesis
            {
                String oneParenthesis="(";
                int otherOpen=0;
                int otherClosed=0;
                String parenTemp="";
                while(charByChar.hasNext())
                {
                    parenTemp= charByChar.next();
                    if(parenTemp.equals("("))
                        otherOpen++;
                    else if(parenTemp.equals(")"))
                        otherClosed++;
                    oneParenthesis+=parenTemp;
                    if(otherClosed==otherOpen+1)//this means that they are equal number
                    {
                        toReturn+=findSmallestNeeded(oneParenthesis);
                        break;
                    
                    }
                }
            }
            else
                toReturn+=tmp;
        }        
    }
    */
    //1+1+1+1*(2*2(333+(((((3)))))))+ za((((((((2+3))))))))
    //1+1+1+1*(2*2(333+(3))) za(2+3)
    //(1(1(1(1(1)))))
    
    
    //((((2)2)2)2)
    //(2(2(2(2(2)))))
    //(2(2(2+(2+2)+(2+2)2)2)2)
    
    //((2+2))
    //public String
    /*
    public String findSmallestNeeded(String oneParenthesis)
    {
        int howManyParenNeeded=numberOfParenNeeded(oneParenthesis); 
        
        
        
        
        oneParenthesis=oneParenthesis.replace("1(","(");
        Scanner charByChar= new Scanner(oneParenthesis);
        charByChar.useDelimiter("");
        String smallestNeededParen="(";
        //find it if one parenthesis contains just another parenthesis
        ArrayList<Integer> parenIndx = new ArrayList<Integer>();
        
        
        while(charByChar.hasNext())
        {
            String tmp=charByChar.next();
            if(tmp.equals("("))
            {
                while(charByChar.hasNext())
                {
                    
                }
            }
        }
        
    }
    */
    
    
    public boolean canCountInside()
    {
        if(top.size()==1&& bottom.size()==1)
            return true;
        return false;
    }
    public ArrayList<Equation> toArrayList()
    {
        ArrayList<Equation> toReturn = new ArrayList<Equation>();
        toReturn.add(new Parenthesis_new(top));
        toReturn.add(new Operator("/"));
        toReturn.add(new Parenthesis_new(bottom));
        return toReturn;
    }
    public double getVarExponent()
    {
        Nomial_new tmp = (Nomial_new)top.get(0);
        return tmp.getVarExponent();
    }
    public double getCount()
    {
        double toReturn = 0;
        for(int indx = 0; indx<top.size(); indx++)
        {
            if( top.get(indx) instanceof Nomial_new)
            {
                Nomial_new tmp = (Nomial_new)top.get(indx);
                toReturn+=tmp.getCount();
            }
        }   
        return toReturn;
    }
    public String toString()
    {
        String toReturn = "(";
        for(Equation tmp: top)
        {
            toReturn+=tmp.toString()+" ";
        }
        toReturn+=")/(";
        for(Equation tmp: bottom)
        {
            toReturn+=tmp.toString()+" ";
        }
        toReturn+=")";
        return toReturn;
    }
    public ArrayList<Equation> getPart(int x)
    {   //0 =top,   1=bottom, 2 = extra
        if(x==0)
            return top;
        else if(x==1)
            return bottom;
        else
            return extra;
    }
    public int compareTo(Object x)
    {
        Parenthesis_new other = (Parenthesis_new)x;
        
        ArrayList<Equation> bottom = getPart(1);
        ArrayList<Equation> bottomOther = other.getPart(1);
        
        Simplify_new.sortDecending(Simplify_new.removeOperators(bottom));
        Simplify_new.sortDecending(Simplify_new.removeOperators(bottomOther));
        
        String bottomString="";
        String bottomOtherString="";
        for(Equation toAdd: bottom)
        {
            Nomial_new tmp= (Nomial_new)toAdd;
            bottomString+=tmp.toString();
        }
        for(Equation toAdd: bottomOther)
        {
            Nomial_new tmp= (Nomial_new)toAdd;
            bottomOtherString+=tmp.toString();
        }
        if(bottomString.equals(bottomOtherString))
        {
            return 0;
        }
        else
            return -1;
    }
    public void addition(Parenthesis_new toAdd)
    {
        for(Equation tmp: toAdd.getPart(0))
        {
            top.add(tmp);
        }
    }
    public void resetExtra()
    {
        extra=null;
    }
}
