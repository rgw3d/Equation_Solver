/**
 * This class will contain the operators 
 * I may want to use Enums for this
 */
public enum Operator
{
    MULT("*"),
    ADD("+"),
    DIV("-");
    
    Operator(String s)
    {
        theOperator=s;
    }
    private final String theOperator;
    
    public String getOperator()
    {
        return theOperator;
    }
}
