/**
 * all the important stuff is in here
 * yay
 */


import java.util.Scanner;
import java.util.Arrays;
public class Functions
{
    /**
     * This block of functions is used to simplify their respective names (parenthesis solves parenthesis, multiplication solves multiplication...) 
     * At the end of each method, there is a call to the method below.  it is not recursion, but something similar as it allows the Input class to call
     * just the parenthesis() method and the rest of the classes will be called and the correct String will be returned 
     */
    public static String parenthesis(String s)
    {
        if( s.contains("("))
        {
            
            
        }
        s=exponents(s);//feeds into the one below
        
        return s;
    }
    public static String exponents(String s)
    {
        if( s.contains("^"))
        {
            
            
        }
        s=multiplication(s);//feeds into multiplication
        
        return s;
    }
    public static String multiplication(String s)
    {   //solves multiplication, and I will try to explain what I am doing inside this method
        if(s.contains("*"))
        {
            String multArray[]=s.split("\\s*\\+\\s*");//deliniate it using the "+" sign 
            
            for(int indx=0; indx<multArray.length; indx++)
            {
                if(multArray[indx].contains("x")&&multArray[indx].contains("*")) //if there are variables AND multiplicaton. 
                {   //needs multiplication as well because it could just be "4x"  or something of the like
                    String tempMultArray[]=multArray[indx].split("\\s*\\*\\s*"); //now divide the part that has multiplication
                    //into seperate parts.  it wont have addition because thats deliniated out before
                    int variablePower=0;//we need to find the power if we are multiplying X variables together, used in next loop
                    for(int indx1=0; indx1<tempMultArray.length; indx1++)//lets loop this array that has parts multiplied together
                    {   //the only difference is that it has to test the power of each x variable.  
                        //additionally, this loop finds the total power of the variables, and also removes the variables for multiplication later
                        String testThePower=tempMultArray[indx1];
                        if(testThePower.contains("x")) //if it has an x, it could be a regular number
                        {
                            if(testThePower.contains("x^2"))
                            {
                                variablePower=variablePower+2;
                                if(testThePower.equals("x^2")||testThePower.equals("-x^2"))//if there is no number preceding x^2
                                     tempMultArray[indx1]=tempMultArray[indx1].replace("x^2","1");
                                tempMultArray[indx1]=tempMultArray[indx1].replace("x^2","");//get rid of the x^2
                            }
                            else if(testThePower.contains("x^3"))
                            {
                                variablePower=variablePower+3;
                                if(testThePower.equals("x^3")||testThePower.equals("-x^3"))//if there is no number preceding x^3
                                     tempMultArray[indx1]=tempMultArray[indx1].replace("x^3","1");
                                tempMultArray[indx1]=tempMultArray[indx1].replace("x^3","");//get rid of the x^3
                            }
                            else if(testThePower.contains("x^4"))
                            {
                                variablePower=variablePower+4;
                                if(testThePower.equals("x^4")||testThePower.equals("-x^4"))//if there is no number preceding x^4
                                     tempMultArray[indx1]=tempMultArray[indx1].replace("x^4","1");
                                tempMultArray[indx1]=tempMultArray[indx1].replace("x^4","");//get rid of the x^4
                            }
                            else//well it has to just be a regular old x by this point
                            {
                                variablePower++;
                                if(testThePower.equals("x")||testThePower.equals("-x"))//if there is no number preceding x
                                     tempMultArray[indx1]=tempMultArray[indx1].replace("x","1");
                                tempMultArray[indx1]=tempMultArray[indx1].replace("x^4","");//get rid of the x^4
                            }
                        }
                    }
                    //so the above loop finishes, but we havent actually done any multipliation yet
                    //we need to do that                    
                    double product=1;//total of the multiplied parts, lets set that up
                    
                    for(String toMultiply: tempMultArray)//used enhanced for loop and go through the array
                    {
                        product=product*(new Scanner(toMultiply).nextDouble()); //get the double and multiply it!
                    }//yay now just to add the power back on to it
                    
                    String variablePowerString;
                    if(variablePower==0)
                        variablePowerString="";
                    else if(variablePower==1)
                        variablePowerString="x";
                    else 
                        variablePowerString="x^";                        
                    multArray[indx]=product+variablePowerString+variablePower;
                    
                }
                else // if there is just regular multiplication
                {
                    //very similar to how it would work if there were variables, but no powers so its simplererer 
                    String tempMultArray[]=multArray[indx].split("\\s*\\*\\s*"); 
                    
                    double product=1;
                    for(String toMultiply: tempMultArray)//used enhanced for loop and go through the array
                    {
                        product=product*(new Scanner(toMultiply).nextDouble()); //get the double and multiply it!
                    }
                    
                    multArray[indx]=product+"";//turn it into a string
                }
            }
            
            
             s="";//set the original back to nothing
            for(String cat: multArray)
            {
                s+=cat+"+";//turn the array back into a string
            }
        }
        
       
        return s;
    }
    
    
    
    
    /**
     * This next block is used to count the number of each type of number
     * It is used in the Input class resolve() method to be added together and to figure out what the final equation is.  
     * so it finds that the regular numbers add to 5, the single-nominal x variables add to 3, and so on.  
     * This information is found for both sides of the equation, subtracted, and simplified, and then x can be solved
     */
    public static double countNum(String s) 
    { //This method is responsible adding together all of the integers.  it is only called after multiplication is done
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
    public static double countX(String s)
    {// This method is responsible for counting all of the single-nominal x variables.   it is only called after multiplication is done
        //finding equations that have x to the first power
        String equation[]= s.split("\\s*\\+\\s*");
        double toReturn=0;
        for(String varName: equation)//yay enhanced for loop
        {
            if((varName.contains("x"))&&!(varName.contains("^"))) //if it does not contain an x
            {
                if(varName.equals("x")||varName.equals("-x"))                
                    toReturn=toReturn+new Scanner(varName.replace("x","1")).nextDouble();                
                else
                    toReturn=toReturn+new Scanner(varName.replace("x","")).nextDouble();
            }
        }
        return toReturn;
    }
    public static double countX2(String s)
    {// This finds all of the double-nominal x variables.  only called after multiplication is done
        String equation[]= s.split("\\s*\\+\\s*");
        double toReturn=0;
        for(String varName: equation)//yay enhanced for loop
        {
            if((varName.contains("x^2"))) //if it does not contain an x
            {
                 if(varName.equals("x^2")||varName.equals("-x^2"))                
                    toReturn=toReturn+new Scanner(varName.replace("x^2","1")).nextDouble();                
                else
                    toReturn=toReturn+new Scanner(varName.replace("x^2","")).nextDouble();
            }
        }        
        return toReturn;
    }
    public static double countX3(String s)
    {// do you really need more descriptions?  This finds all of the trinominal x variables.  called after multiplication
        String equation[]= s.split("\\s*\\+\\s*");
        double toReturn=0;
        for(String varName: equation)//yay enhanced for loop
        {
            if((varName.contains("x^3"))) //if it does not contain an x
            {
                 if(varName.equals("x^3")||varName.equals("-x^3"))                
                    toReturn=toReturn+new Scanner(varName.replace("x^3","1")).nextDouble();                
                else
                    toReturn=toReturn+new Scanner(varName.replace("x^3","")).nextDouble();
            }
        }
        return toReturn;
    }
    public static double countX4(String s)
    {// finds all of the quadnominal x variables.  called after multiplication
        String equation[]= s.split("\\s*\\+\\s*");
        double toReturn=0;
        for(String varName: equation)//yay enhanced for loop
        {
           if((varName.contains("x^4"))) //if it does not contain an x
            {
                 if(varName.equals("x^4")||varName.equals("-x^4"))                
                    toReturn=toReturn+new Scanner(varName.replace("x^4","1")).nextDouble();                
                else
                    toReturn=toReturn+new Scanner(varName.replace("x^4","")).nextDouble();
            }
        }
        return toReturn;
    }
    
    
}
