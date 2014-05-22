/**
 * Top level class
 * 
 * @author rgw3d 
 * @version (5-21-14
 */
import java.util.ArrayList;
public abstract class Number implements Equation
{
    /**
     * Returns a String representation of the data
     * @return String representation of the data
     */
    public abstract String toString();
    /**
     * Tests to see if the inside variables can be easily counted.  
     * 
     * @return  boolean true if the count/var variable can be returned.
     */
    public abstract boolean canCountInside();
    /**
     * Finds the inside value and returns it
     * 
     * @return  double value of the count 
     */
    public abstract double getCount();
    /**
     * Finds the inside value of the variable Exponent and returns it
     * 
     * @return double value of the variable 
     */
    public abstract double getVarExponent();
    /**
     * Returns an ArrayList representation of the the data
     * 
     * @return  ArrayList with all the data in objects
     */
    public abstract ArrayList<Equation> toArrayList();
    
    
    
    
}
