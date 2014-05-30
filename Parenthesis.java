/**
 * extends the Nomial class because it has numbers in it
 */
import java.util.ArrayList;
public class Parenthesis
{
    private ArrayList inside= new ArrayList();
    private ArrayList<Nomial> insideNoOperators = new ArrayList<Nomial>();
    public Parenthesis(String insideValue)
    {
        //System.out.println("yay"+insideValue);
        inside= Input_new.parseObjects(insideValue.substring(1,insideValue.length()-1));//send it without the surrounding parenthesis
        //System.out.println(inside);
    }
    public Parenthesis(ArrayList insideValue)
    {
        inside=insideValue;
    }
    public Parenthesis(Nomial insideValue)
    {
        insideNoOperators.add(insideValue);
    }
    public ArrayList toArrayList()
    {
        return inside;
    }
    public ArrayList<Nomial> toArrayListWithoutOperators()
    {
        return insideNoOperators;
    }
    public double getVarExponent()
    {
        return 1;
    }
    public double getCount()
    {
        return 1;
    }
    public boolean canCountInside()
    {
        return true;
    }
    public String toString()
    {
        return "s";
    }
    
    
}
