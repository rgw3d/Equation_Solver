/*
 * Here we are simplifying the input of the equations
 */
public class simplifyInput
{
    public static String simp(String original)
    {
        original=original.toLowerCase();//all lower case in the equation "X" to "x"
        if(!original.contains("="))
        {
            original=original+"=0";
            System.out.println(original);
        }
        original=original.replace("--"," + ");//Minus a negative, so addition.
        //System.out.println("1 "+original);
        original=original.replace("-"," + -");//Minus. Change it to addition of a negative number
        //System.out.println("2 "+original);
        original=original.replace(" ",""); //No more space.  Space kills this program
        //System.out.println("3 "+original);
        original=original.replace("++","+");//Sometiems this will occure within the code, so this fixes it
        //System.out.println("5 "+original);
        original=original.replace("(","*(");//Multiplied by a quantity.  Use the Multiplication sign 
        //System.out.println("6 "+original);
        original=original.replace("(*(","(1*(");//another error that is fixed
        //System.out.println("7 "+original);
        original=original.replace("+*(","+(");//Fixes any errors from above line
        original=original.replace("+)",")");
        //System.out.println("8 "+original);
        original=original.replace("**","*");//fixing errors
        //System.out.println("10 "+original);
        if((original.startsWith("*("))==true)
            original=original.replace("*(","(");//Fixing another error
        //System.out.println("11 "+original);
        original=original.replace("+-*(","+-1*(");//another error that occurs
        //System.out.println("12 "+original);
        original=original.replace("(+-","(0+-");//another thing that occurs that throws off the system
        //System.out.println("13 "+original);
        original=original.replace(")*(",")(");       
        
        //original=original.replace("--"," + ");
        return original;
    }
    
}
