import java.util.ArrayList;
/**
 * Hopefully, if this works out, I will be able to deal with Fraction 
 * Parenthesis.  
 * 
 */
public class Parenthesis_fraction extends Parenthesis_new
{
    private ArrayList<Equation> top = new ArrayList<Equation>();
    private ArrayList<Equation> bottom = new ArrayList<Equation>();
    public Parenthesis_fraction(ArrayList<Equation> topTmp, ArrayList<Equation> bottomTmp)
    {
        top = topTmp;
        bottom = bottomTmp;
    }
    public boolean canCountInside()
    {
        if(top.size()==1&& bottom.size()==1)
            return true;
        return false;
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
    public ArrayList<Equation> getPart(boolean top)
    {   //0 =top,   1=bottom, 2 = extra
        if(top)
            return this.top;
        else 
            return bottom;
    }
    public int compareTo(Equation x)
    {
        return 1;
    }
    public void multiplyToCount(Nomial_new x)
    {
        for(int indx = 0; indx<top.size(); indx++)
        {
            if(top.get(indx) instanceof Nomial_new)
            {
                Nomial_new set= (Nomial_new) top.get(indx);
                set.multiplyToCount(x.getCount());
                set.addToVarExponent(x.getVarExponent());
                top.remove(indx);
                top.add(indx,set);
            }
            else if(top.get(indx) instanceof Operator)//ignore it
            {
            }
            else if(top.get(indx) instanceof Parenthesis_new)
            {
                if(top.get(indx) instanceof Parenthesis_fraction)
                {
                    Parenthesis_fraction set=(Parenthesis_fraction) top.get(indx);
                    set.multiplyToCount(x);
                    top.remove(indx);
                    top.add(indx,set);
                }
                else
                {
                    Parenthesis_new set= (Parenthesis_new)top.get(indx);
                    set.multiplyToCount(x);
                    top.remove(indx);
                    top.add(indx,set);
                }
            }
        }
    }
    public void multiplyToCount(Parenthesis_new x)
    {
        if(x instanceof Parenthesis_fraction)
        {
            Parenthesis_fraction otherParen= (Parenthesis_fraction)x;
            for(int indx = 0; indx<top.size(); indx++)
            {
                for( int indx2 = 0; indx2<y.getPart(true).size(); indx++)
                {
                    
                    if(top.get(indx) instanceof Nomial_new)
                    {
                        
                        
                        
                        Nomial_new set= (Nomial_new) top.get(indx);
                        set.multiplyToCount(x.getCount());
                        set.addToVarExponent(x.getVarExponent());
                        top.remove(indx);
                        top.add(indx,set);
                    }
                    else if(top.get(indx) instanceof Operator)//ignore it
                    {
                    }
                    else if(top.get(indx) instanceof Parenthesis_new)
                    {
                        if(top.get(indx) instanceof Parenthesis_fraction)
                        {
                            Parenthesis_fraction set=(Parenthesis_fraction) top.get(indx);
                            set.multiplyToCount(x);
                            top.remove(indx);
                            top.add(indx,set);
                        }
                        else
                        {
                            Parenthesis_new set= (Parenthesis_new)top.get(indx);
                            set.multiplyToCount(x);
                            top.remove(indx);
                            top.add(indx,set);
                        }
                    }
                }
            }
        }
    }
}
