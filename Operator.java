/**
 * Im not sure If I will use this or another class
 */
public class Operator implements Equation 
{
    public Operator(String s)
    {
       theOperator=s;
    }
    public String toString()
    {
        return theOperator;
    }
    public Operator()
    {
        theOperator="+";
    }
    public int compareTo(Object x)
    {
        Operator other = (Operator)x;
        if(other.toString().equals(toString()))
            return 0;
        else
            return -1;
    }
    private String theOperator="";
}
