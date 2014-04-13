/**
 * all the important stuff is in here
 * yay
 */
import java.util.Scanner;
public class Functions
{
    public static String parenthesis(String s)
    {
        return s;
    }
    public static String exponents(String s)
    {
        return s;
    }
    public static String multiplication(String s)
    {
        return s;
    }
    public static String addition(String s)
    {
        return s;
    }
    
    
    public static double noX(String s)
    {
        //we are finding single numbers without variables
        
        String equation[]= s.split("\\s*\\+\\s*");
        double toReturn=0;
        for(String varName: equation)//yay enhanced for loop
        {
            if(!(varName.contains("x"))) //if it does not contain an x
            {
                toReturn=toReturn+new Scanner(varName).nextDouble();
            }
        }
        
        return toReturn;
    }
    public static double oneX(String s)
    {
        //finding equations that have x to the first power
        String equation[]= s.split("\\s*\\+\\s*");
        double toReturn=0;
        for(String varName: equation)//yay enhanced for loop
        {
            if(!(varName.contains("x"))&&!(varName.contains("^"))) //if it does not contain an x
            {
                if(varName.equals("x")||varName.equals("-x"))                
                    toReturn=toReturn+new Scanner(varName.replace("x","1")).nextDouble();                
                else
                    toReturn=toReturn+new Scanner(varName.replace("x","")).nextDouble();
            }
        }
        
        return toReturn;
    }
    public static double twoX(String s)
    {
        String equation[]= s.split("\\s*\\+\\s*");
        double toReturn=0;
        for(String varName: equation)//yay enhanced for loop
        {
            if(!(varName.contains("x"))) //if it does not contain an x
            {
                toReturn=toReturn+new Scanner(varName).nextDouble();
            }
        }
        
        return 0;
    }
    public static double threeX(String s)
    {
        String equation[]= s.split("\\s*\\+\\s*");
        double toReturn=0;
        for(String varName: equation)//yay enhanced for loop
        {
            if(!(varName.contains("x"))) //if it does not contain an x
            {
                toReturn=toReturn+new Scanner(varName).nextDouble();
            }
        }
        
        return 0;
    }
    public static double fourX(String s)
    {
        String equation[]= s.split("\\s*\\+\\s*");
        double toReturn=0;
        for(String varName: equation)//yay enhanced for loop
        {
            if(!(varName.contains("x"))) //if it does not contain an x
            {
                toReturn=toReturn+new Scanner(varName).nextDouble();
            }
        }
        
        return 0;
    }
    
    
}
