/**
 * Just outlining a few things
 */
import java.util.ArrayList;
public class Parenthesis
{
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
    public ArrayList toArrayList()
    {
        return inside;
    }
    private ArrayList inside= new ArrayList();
    
    
}
