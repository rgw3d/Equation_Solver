/*
 * The new class that will solve the parenthesis in equations.  hopfully it is much simplier. and it actually works
 */
import java.io.*;
import java.util.*;
public class Parenthesis
{
    private String original;//two "original" variables because one is changed, while this one (original) is used for indexOf references.
    private String originalChanged;
    public String Paren(String s)
    {
        original=s;
        originalChanged=s;
        //Original getOriginal=new Original();
        /*if(storeOriginal==true)
        {
            getOriginal.setOriginal(original); 
        }
        */
        
        if(true)
        //original.contains("*(")&&original.contains("+"))//if there is a coefficeint and addition
        {
            //counting the number of open parenthesis 
            Scanner nOfOpen=new Scanner(original);
            nOfOpen.useDelimiter("");
            int howManyOpen=0;
            while(nOfOpen.hasNext())
            {
                if(nOfOpen.next().equals("("))
                    howManyOpen++;//counts the number of open parenthesis
            }
            int placeOfParenthesis[]=new int[howManyOpen*2];//setting the array to 2*howManyOpen.  this equals the total number of parenthesis
            index(placeOfParenthesis, original);
            //System.out.println(Arrays.toString(placeOfParenthesis));
            for(int loopInstance=0; loopInstance<howManyOpen; loopInstance++)//testing to see if there are any parenthesis inside parenthesis. 
            {
                System.out.println("what is used in the loop below "+original.substring(placeOfParenthesis[loopInstance]+1,placeOfParenthesis[loopInstance+howManyOpen]));
                //System.out.println("loopInstance and howManyOpen"+ loopInstance + howManyOpen);
                if((original.substring(placeOfParenthesis[loopInstance]+1,placeOfParenthesis[loopInstance+howManyOpen])).contains("("))//if there is an aditional pareninside
                {//if there is another paren inside
                    for(int test=loopInstance+howManyOpen+1; test<placeOfParenthesis.length; test++)//finding a whole parenthesis (even if it has another one inside)  
                    {//the test variable is equal +1 to the second part of the above if statement.  Esentailly, it is the next instance in the array.
                        
                        //System.out.println("This is the original "+original);
                        //System.out.println("after the loop and what will be used in the code below "+(original.substring(placeOfParenthesis[loopInstance]+1,placeOfParenthesis[test])));  
                        String findTotals=(original.substring(placeOfParenthesis[loopInstance]+1,placeOfParenthesis[test]));  
                        //then start solving
                        Scanner fT=new Scanner(findTotals);
                        fT.useDelimiter("");//so it looks at every character
                        int numOpen=0;
                        int numClosed=0;
                        while(fT.hasNext())//used for counting the total number of open and closed parenthesis
                        {
                            String temp=fT.next();
                            if(temp.equals("("))
                                numOpen++;//counts the number of open parenthesis
                            else if (temp.equals(")"))
                                numClosed++;                                
                        }
                        //System.out.println("the num open "+numOpen+" the num closed "+numClosed);
                        String returned;
                        if(numOpen==numClosed)//finding if there are the same ammount of open and closed parenthesis
                        {//if there are, then it can be solved, if not, the loop needs to continue
                            System.out.println("was this reached?");
                            Parenthesis reRun=new Parenthesis();
                            System.out.println(original.substring(placeOfParenthesis[loopInstance]+1,placeOfParenthesis[test]));
                            returned=reRun.Paren(original.substring(placeOfParenthesis[loopInstance]+1,placeOfParenthesis[test]));
                            //System.out.println(returned+" worK??/ and the orginal sent "+ original.substring(placeOfParenthesis[loopInstance]+1,placeOfParenthesis[test]));
                            originalChanged=originalChanged.replace(original.substring(placeOfParenthesis[loopInstance]+1,placeOfParenthesis[test]),returned); 
                            System.out.println(originalChanged);
                            //replacing the unsoved portion with the solve version
                            //System.out.println(howManyOpen+" how many open");
                            //System.out.println(test+" test");
                            loopInstance=test-howManyOpen-1;//this way, it sets the other for loop control variable to the number of times taht this for loop looped. 
                            //(accounts for multiple paren)
                            test=test+placeOfParenthesis.length;//so we can exit this loop with the correct thing solved
                            
                        }
                    }
                }
                else
                {//Make it so that it handes regular addition within parenthesis without coeffeicientes 
                    System.out.println("is this one run");
                    String oneParenthesis="";
                    oneParenthesis=oneParen(placeOfParenthesis, loopInstance, howManyOpen, original);
                    //System.out.println("this is what is returned"+oneParenthesis);
                    //System.out.println("the original string before :"+originalChanged);
                    originalChanged=solveCof(oneParenthesis, originalChanged);
                    //System.out.println("the original string after :"+originalChanged);
                }
                //System.out.println(Arrays.toString(placeOfParenthesis));
            }
        }       
        else if((original.contains("*")||original.contains("("))&&!original.contains("+"))//if there is only multiplication this is run.  multiplication is communative.
        {
            originalChanged=originalChanged.replace(")(","*");
            originalChanged=originalChanged.replace(")","");
            originalChanged=originalChanged.replace("(","");
            //System.out.println(originalChanged);
        }
        else if((original.contains(")(")&&original.contains("+")))
        {
            //System.out.println("does it get called?");
            /*
            Scanner nOfOpen=new Scanner(original);
            nOfOpen.useDelimiter("");
            int howManyOpen=0;
            while(nOfOpen.hasNext())
            {
                if(nOfOpen.next().equals("("))
                    howManyOpen++;//counts the number of open parenthesis
            }
            int placeOfParenthesis[]=new int[howManyOpen*2];//setting the array to 2*howManyOpen.  this equals the total number of parenthesis
            index(placeOfParenthesis, original);
            for(int loopInstance=0; loopInstance<howManyOpen; loopInstance++)//testing to see if there are any parenthesis inside parenthesis. 
            {
                String oneParenthesis=oneParen(placeOfParenthesis, loopInstance, howManyOpen, original);
                System.out.println("this is what is returned"+oneParenthesis);
                if(loopInstance>0)
                {
                    String secondParenthesis=oneParen(placeOfParenthesis, loopInstance-1, howManyOpen);
                    String foiledParen=solveMultipleCof(oneParenthesis, secondParenthesis);
                    //make a combieed string so that it can be used to replace the originalChanged variable with the foildParen (the answer vaiable)  
                        //actullay the current method will not work as it will not account for mulitple ()()()    it wont work.  damn
                }
                String foiledParen=solveMultipleCof(oneParenthesis, "blah");
            }
            */
            
        }
        else //if there is only addition or multiplication not related to the parenthesis 
        {
            originalChanged=originalChanged.replace(")","");
            originalChanged=originalChanged.replace("(","");
            //System.out.println(originalChanged);
            
        }

        if(originalChanged.contains(")")||originalChanged.contains("("))
        {
            Parenthesis remove=new Parenthesis();
            originalChanged=remove.Paren(originalChanged);//solves for any more parens that may have been left on (happens when there are nested parenthesis
        }
        
        return originalChanged;
    }
    
    
    
    
    public static void index(int placeOfParenthesis[], String original)
    {
        int parenIndex=0;
        int loopNum=0;
        while(loopNum<(placeOfParenthesis.length/2))
        {
            placeOfParenthesis[loopNum]=original.indexOf("(",parenIndex);
            parenIndex=original.indexOf("(",parenIndex)+1;
            loopNum++;
        }
        for(parenIndex=0; loopNum<placeOfParenthesis.length; loopNum++)// same as above while loop, but it resets parenIndex to 0, because it needs to start 
        {//searching from the beginning of the string, and then it continues looping the array until it is filled            
            placeOfParenthesis[loopNum]=original.indexOf(")",parenIndex);
            parenIndex=original.indexOf(")",parenIndex)+1;
        }
        //At this point, half the array is filled with the indexs of the starting parenthesis, and the other half has all the closing. 
        //these are always seperated by the number of open parenthesis [2, 10, 6, 14] -- total of two open.  2 and 6 are the first set of parenthesis, 
        //separated by two indexes in the array--the same number that there are open parenthesis.
        
        //if there is something like "1*(1*(2*3)+1)+2*(2+2)"   the output is [2, 5, 16, 9, 12, 20].  does not follow above rules
        //                                                      should be 2,12  5,9  16,20 
    }
    
    
    public static String oneParen(int placeOfParenthesis[], int loopInstance, int howManyOpen, String original)
    {
        int indexL=placeOfParenthesis[loopInstance];//finding the left side of the parenthesis 
        //System.out.println("INDEXL before changes "+indexL);
        int indexR=placeOfParenthesis[loopInstance+howManyOpen]+1;//by default this is the index for the right parenthesis, or closing paren 
        if(indexL!=0&&original.charAt(indexL-1)!='+'&&original.charAt(indexL-1)!=')')//if index does not equal zero(there is nothing to the left) AND the character to 
        {//the left of the Parenthesis is not a '+'
                indexL=original.lastIndexOf("+",indexL); //takes an addition sign and the numbers before it to find the coeficient of the parenthesis
                //System.out.println("indexL 1 possible change "+indexL);
                if(indexL!=-1)
                {
                    indexL++;//because by doing this, we do not include the "+"
                    //System.out.println("indexL 2 possible change "+indexL);
                }
                //System.out.println("indexL first possible change "+indexL);
                if(indexL==-1)//if the search is unsucessful-- so we need to change the value to something that can be used.
                {

                    indexL=0;
                    //System.out.println("indexL 3 possible change "+indexL);
                }
            }
            //System.out.println(original.substring(indexL,indexR));
        String oneParenthesis="";//stuff for one parenthesis
         if(indexR==original.length())//if the substring value is the end of the string, then using a 2 substring system will not work, as 
         //it always pulls back one for the last variable/substring input                    
            oneParenthesis=original.substring(indexL);                    
        else                    
            oneParenthesis=original.substring(indexL,indexR+1);
        return oneParenthesis;
    }
    
    public static String solveCof(String oneParenthesis, String originalChanged)
    {
        System.out.println(oneParenthesis);
        solveMultiplication objP=new solveMultiplication();//obj for the multiplication class
        Scanner openParenthesis=new Scanner(oneParenthesis);//to divide it by the one opening parenthesis
        openParenthesis.useDelimiter("\\(");//the delimiter '('
        String left=openParenthesis.next();//left side (the number)
        String right=openParenthesis.next();//right side (the other numbers
        left=objP.simplify(left);
        left=left.replace("+","");//replaces the "+" that is often left at the end of left variable.  needs it to be removed
        
        right=right.replace(")","");
        
        right=objP.simplify(right);//simplifies it if possible
        Scanner insideParenthesis=new Scanner(right);
        insideParenthesis.useDelimiter("\\+");
        
        int howMany=0;  //used in the following loop to count the number of iterations                         
        while(insideParenthesis.hasNext())
        {
            insideParenthesis.next();
            howMany++;//counting how many times the string deliniated.  
        }         
        String partsOfInsideParenthesisArray[]=new String[howMany];//Here is the array, with the appropriate ammount of instances
        Scanner partsOfInsideParenthesisScanner=new Scanner(right);//parts of inside parenthesis... we need to create another scanner because the other one was used up with the "next" methods.
        partsOfInsideParenthesisScanner.useDelimiter("\\+");//same deliniator as before... but it accounts for parenthesis.
        int whichArray=0;//variable used to control which array is being manipulated
        while(partsOfInsideParenthesisScanner.hasNext())
        {
            partsOfInsideParenthesisArray[whichArray]=left+"*"+partsOfInsideParenthesisScanner.next();//gives parts of the string to the array times the coeficient.  
            whichArray++;//gives which array its value
        }
        //now to combine all of the Array strings together into one string that can be sent to the sovleMultipication class.  
        int loopThroughParenthesisArray=0;
        String simplified="";            
        loopThroughParenthesisArray=0;
        while(loopThroughParenthesisArray<howMany)//set it to loop the number of times that there are instances in the Array
        {
            simplified=simplified+partsOfInsideParenthesisArray[loopThroughParenthesisArray]+"+";
            loopThroughParenthesisArray++;
        }            
        //System.out.println("this is what is the simplifeid string"+simplified);            
        simplified=objP.simplify(simplified);//finally we are running the final simplification part.
        
        originalChanged=originalChanged.replace(oneParenthesis,simplified);//replace the oneParenthesis with its simplified version.  
        originalChanged=originalChanged.replace("++","+");//an error that occurs 
        return originalChanged;
        
    }
    
}

