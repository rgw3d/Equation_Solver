import java.util.ArrayList;
/**
 * New Parenthesis class.
 * the format for Parenthesis is: (top/bottom+extra)
 * 
 * @author rgw3d
 * @version 5-22-14
 */
public class Parenthesis_new implements Equation
{
    private ArrayList<Equation> simplified = new ArrayList<Equation>();
    private double power = 1;
    public Parenthesis_new()
    {
    }
    public Parenthesis_new(String insideValue, double tmpPower)
    {
        power=tmpPower;
        simplified = Simplify_new.simplify_control(Control.parseEquation(insideValue.substring(1,insideValue.length()-1)));
    }
    public Parenthesis_new(String insideValue)   
    {
        simplified = Simplify_new.simplify_control(Control.parseEquation(insideValue.substring(1,insideValue.length()-1)));
    }
    public ArrayList<Equation> toArrayList()
    {
        return simplified;
    }
    public String toString()
    {
        String toReturn = "(";
        for(Equation tmp: simplified)
        {
            toReturn+=tmp.toString()+" ";
        }
        toReturn+=")";
        return toReturn;
    }
    public int compareTo(Equation x)
    {
        /*Parenthesis_new other = (Parenthesis_new)x;
        
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
            */
           return 1;
    }
    public void addition(Parenthesis_new toAdd)
    {
        for(Equation tmp: toAdd.toArrayList())
        {
            simplified.add(tmp);
        }
    }
    public void multiplyToCount(Nomial_new x)
    {
        for(int indx = 0; indx<simplified.size(); indx++)
        {
            if(simplified.get(indx) instanceof Nomial_new)
            {
                Nomial_new set= (Nomial_new) simplified.get(indx);
                set.multiplyToCount(x.getCount());
                set.addToVarExponent(x.getVarExponent());
                simplified.remove(indx);
                simplified.add(indx,set);
            }
            else if(simplified.get(indx) instanceof Operator)//ignore it
            {
            }
            else if(simplified.get(indx) instanceof Parenthesis_new)
            {
                if(simplified.get(indx) instanceof Parenthesis_fraction)
                {
                    Parenthesis_fraction set=(Parenthesis_fraction) simplified.get(indx);
                    set.multiplyToCount(x);
                    simplified.remove(indx);
                    simplified.add(indx,set);
                }
                else
                {
                    Parenthesis_new set= (Parenthesis_new)simplified.get(indx);
                    set.multiplyToCount(x);
                    simplified.remove(indx);
                    simplified.add(indx,set);
                }
            }
        }
    }
}
