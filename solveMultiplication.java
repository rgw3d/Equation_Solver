import java.io.*;
import java.util.*;
/*
 * This is the class that will return the totels for the different variables 
 */
public class solveMultiplication
{
    public String simplify(String s)
    {
       Scanner count=new Scanner(s);//So first, we put the string into a scanner
        count.useDelimiter("\\s*\\+\\s*");//deliniate it by the "+" sign, and by the "(" and the ")".  
        int howMany=0;//Integer used to find how many arrays instances are necessary        
        while(count.hasNext())
        {
            count.next();
            howMany++;//counting how many times the string deliniated.  
        }         
        String rightArray[]=new String[howMany];//Here is the array, with the appropriate ammount of instances
        Scanner right=new Scanner(s);//using a different scanner because the otherone was used to tst the ammount of deliniations
        right.useDelimiter("\\s*\\+\\s*");//same deliniator as before... but it accounts for parenthesis.
        int whichArray=0;//variable used to control which array is being manipulated
        while(right.hasNext())
        {
            rightArray[whichArray]=right.next();//gives parts of the string to the array
            whichArray++;//gives which array its value
        } 
        whichArray-=1;// has to subtract one because the array indincies start at "0"
        final int finalArrayCount=whichArray;//setting a simmilar variable that will be used to restor whichArray back to its current value
        
        while(whichArray>=0)//Tests to find multiplication by cycling through the arrays
        {
            if(rightArray[whichArray].contains("x"))//if there are any variables
                    {
                        //System.out.println("why not this one????");
                        //System.out.println("right array"+rightArray[whichArray]);
                        Scanner sovlingMult=new Scanner(rightArray[whichArray]);
                        sovlingMult.useDelimiter("\\s*\\*\\s*");
                        int howManyMult=0;
                        while(sovlingMult.hasNext())
                        {
                            sovlingMult.next();
                            howManyMult++;
                        }
                        String Array[]=new String[howManyMult]; 
                        Scanner mult=new Scanner(rightArray[whichArray]);
                        mult.useDelimiter("\\s*\\*\\s*");
                        int whichArray1=0;
                        while(mult.hasNext())
                        {
                            Array[whichArray1]=mult.next();
                            whichArray1++;
                        }
                        whichArray1-=1;
                        int whichArrayFinal=whichArray1;
                        double numberTotal=1;
                        int variablePower=0;                       
                        while(whichArray1>=0)
                        {
                            Scanner convert=new Scanner(Array[whichArray1]);
                            String testThePower=convert.next();
                            if(testThePower.contains("x"))
                            {
                               if(testThePower.contains("x^2"))
                                {
                                    variablePower=variablePower+2;
                                    if(testThePower.compareTo("-x^2")==0)
                                    {
                                        Array[whichArray1]=Array[whichArray1].replace("-x^2","-1");
                                    }
                                    else if(testThePower.compareTo("x^2")==0)
                                    {
                                        Array[whichArray1]=Array[whichArray1].replace("x^2","1");
                                    }
                                    else
                                    {
                                     Array[whichArray1]=Array[whichArray1].replace("x^2","");
                                    }
                                }
                                else if(testThePower.contains("x^3"))
                                {
                                    variablePower=variablePower+3;
                                    if(testThePower.compareTo("-x^3")==0)
                                    {
                                        Array[whichArray1]=Array[whichArray1].replace("-x^3","-1");
                                    }
                                    else if(testThePower.compareTo("x^3")==0)
                                    {
                                        Array[whichArray1]=Array[whichArray1].replace("x^3","1");
                                    }
                                    else
                                    {
                                     Array[whichArray1]=Array[whichArray1].replace("x^3","");
                                    }   
                                }
                                else if(testThePower.contains("x^4"))
                                {
                                    variablePower=variablePower+4;
                                    if(testThePower.compareTo("-x^4")==0)
                                    {
                                        Array[whichArray1]=Array[whichArray1].replace("-x^4","-1");
                                    }
                                    else if(testThePower.compareTo("x^4")==0)
                                    {
                                        //System.out.println("does this run?");
                                        Array[whichArray1]=Array[whichArray1].replace("x^4","1");
                                    }
                                    else
                                    {
                                     Array[whichArray1]=Array[whichArray1].replace("x^4","");
                                    }   
                                }
                                else
                                {
                                    variablePower++;
                                    if(testThePower.compareTo("-x")==0)
                                    {
                                        Array[whichArray1]=Array[whichArray1].replace("-x","-1");
                                    }
                                    else if (testThePower.compareTo("x")==0)
                                    {
                                     Array[whichArray1]=Array[whichArray1].replace("x","1");
                                    }
                                    else
                                    {
                                        Array[whichArray1]=Array[whichArray1].replace("x","");
                                    }
                                }
                            }
                            whichArray1--;
                        }
                        whichArray1=whichArrayFinal;
                        //System.out.println(Arrays.toString(Array));
                        while(whichArray1>=0)
                        {
                            Scanner convert1=new Scanner(Array[whichArray1]);  
                            //System.out.println("right before it is converted "+Array[whichArray1]);
                            numberTotal=numberTotal*convert1.nextDouble();
                            whichArray1--;
                            
                        }
                        String variablePowerToAnswer="";
                        if(variablePower==0)
                        {
                            variablePowerToAnswer="";
                        }
                        else if(variablePower==1) 
                        {
                            variablePowerToAnswer="x";
                        }
                        else if(variablePower==2) 
                        {
                            variablePowerToAnswer="x^2";
                        }
                        else if(variablePower==3) 
                        {
                            variablePowerToAnswer="x^3";
                        }
                        else if(variablePower==4) 
                        {
                            variablePowerToAnswer="x^4";
                        }
                        String convertToString=String.valueOf(numberTotal);
                        rightArray[whichArray]=convertToString+variablePowerToAnswer;
                        //System.out.println("end????");
                    }
                    else//if there are just regular integers being multiplied
                    {//very similar to how the regular addition works and how the above works.  but in the end the answer is muliplicated
                        // and in the end the value is converted back to a string, and put back into the array simplified
                        //so the other methods can handle the addition. 
                        //System.out.println("why is this one looped???");
                        
                        String beingMultiplied=rightArray[whichArray];
                        //System.out.println(beingMultiplied);
                        beingMultiplied=beingMultiplied.replace(")","");
                        //System.out.println("array v"+rightArray[whichArray]);
                        /*Scanner sovlingMult=new Scanner(rightArray[whichArray]);
                        sovlingMult.useDelimiter("\\s*\\*\\s*");
                        int howManyMult=0;
                        while(sovlingMult.hasNext())
                        {
                            sovlingMult.next();
                            howManyMult++;
                        }
                        String Array[]=new String[howManyMult]; 
                        */
                        String Array[];
                        Array=beingMultiplied.split("\\s*\\*\\s*");
                        /*Scanner mult=new Scanner(rightArray[whichArray]);
                        mult.useDelimiter("\\s*\\*\\s*");
                        int whichArray1=0;
                        while(mult.hasNext())
                        {
                            Array[whichArray1]=mult.next();
                            whichArray1++;
                        }
                        whichArray1-=1;
                        */
                        double numberTotal=1;
                        
                        for(int dummy=Array.length;dummy>0;dummy--)
                        {
                            if(Array[dummy-1].equals("")||Array[dummy-1].contains(")"))
                            {
                            }
                            else
                            {
                                Scanner convert1=new Scanner(Array[dummy-1]);
                                //System.out.println("pringing????"+Array[dummy-1]);
                                numberTotal*=convert1.nextDouble();
                            }
                        }
                        
                        String convertToString=String.valueOf(numberTotal);
                        rightArray[whichArray]=convertToString;
                        
                    }     
                whichArray--;
        }
        
       whichArray=finalArrayCount;
       String toReturn="";
       while(whichArray>=0)//Need to return a string with the multiplied values
       {
           toReturn+=rightArray[whichArray]+"+";
           whichArray--;
        }      
        return toReturn;//returns the string for furture methods to handle
    }    
    public double fourX(String s1)
    {
        Scanner countRight=new Scanner(s1);
        countRight.useDelimiter("\\s*\\+\\s*");
        int howMany=0;
        while(countRight.hasNext())
        {
            countRight.next();
            howMany++;
        }        
        String rightArray[]=new String[howMany];
        Scanner right=new Scanner(s1);
        right.useDelimiter("\\s*\\+\\s*");
        int whichArray=0;
        while(right.hasNext())
        {
            rightArray[whichArray]=(right.next());
            whichArray++;
        } 
        whichArray-=1;
        int whichArrayFixed=whichArray;
        double variableDoubleTotal=0;
        double intTotal=0;
        while(whichArray>=0)
        {
            
                if(rightArray[whichArray].contains("x^4"))
                {
                     if(rightArray[whichArray].compareTo("x^4")==0)
                        {
                            rightArray[whichArray]=rightArray[whichArray].replace("x^4","1");
                        }
                    rightArray[whichArray]=rightArray[whichArray].replace("x^4","");
                    Scanner convert1=new Scanner(rightArray[whichArray]);                
                    variableDoubleTotal = variableDoubleTotal +convert1.nextDouble();
                }                
            whichArray--;
        }
        return variableDoubleTotal;
    }
    public double threeX(String s1)
    {
        Scanner countRight=new Scanner(s1);
        countRight.useDelimiter("\\s*\\+\\s*");
        int howMany=0;
        while(countRight.hasNext())
        {
            countRight.next();
            howMany++;
        }        
        String rightArray[]=new String[howMany];
        Scanner right=new Scanner(s1);
        right.useDelimiter("\\s*\\+\\s*");
        int whichArray=0;
        while(right.hasNext())
        {
            rightArray[whichArray]=(right.next());
            whichArray++;
        } 
        whichArray-=1;
        int whichArrayFixed=whichArray;
        double variableDoubleTotal=0;
        double intTotal=0;
        while(whichArray>=0)
        {
            
                if(rightArray[whichArray].contains("x^3"))
                {
                     if(rightArray[whichArray].compareTo("x^3")==0)
                        {
                            rightArray[whichArray]=rightArray[whichArray].replace("x^3","1");
                        }
                    rightArray[whichArray]=rightArray[whichArray].replace("x^3","");
                    Scanner convert1=new Scanner(rightArray[whichArray]);                
                    variableDoubleTotal = variableDoubleTotal +convert1.nextDouble();
                }                
            whichArray--;
        }
        return variableDoubleTotal;
    }
    public double doubleX(String s1)
    {
        Scanner countRight=new Scanner(s1);
        countRight.useDelimiter("\\s*\\+\\s*");
        int howMany=0;
        while(countRight.hasNext())
        {
            countRight.next();
            howMany++;
        }        
        String rightArray[]=new String[howMany];
        Scanner right=new Scanner(s1);
        right.useDelimiter("\\s*\\+\\s*");
        int whichArray=0;
        while(right.hasNext())
        {
            rightArray[whichArray]=(right.next());
            whichArray++;
        } 
        whichArray-=1;
        int whichArrayFixed=whichArray;
        double variableDoubleTotal=0;
        double intTotal=0;
        while(whichArray>=0)
        {
            
                if(rightArray[whichArray].contains("x^2"))
                {
                     if(rightArray[whichArray].compareTo("x^2")==0)
                        {
                            rightArray[whichArray]=rightArray[whichArray].replace("x^2","1");
                        }
                    rightArray[whichArray]=rightArray[whichArray].replace("x^2","");
                    Scanner convert1=new Scanner(rightArray[whichArray]);                
                    variableDoubleTotal = variableDoubleTotal +convert1.nextDouble();
                }                
            whichArray--;
        }
        return variableDoubleTotal;
    }
    public double singleX(String s2)
    {
         Scanner countRight=new Scanner(s2);
        countRight.useDelimiter("\\s*\\+\\s*");
        int howMany=0;
        while(countRight.hasNext())
        {
            countRight.next();
            howMany++;
        }        
        String rightArray[]=new String[howMany];
        Scanner right=new Scanner(s2);
        right.useDelimiter("\\s*\\+\\s*");
        int whichArray=0;
        while(right.hasNext())
        {
            rightArray[whichArray]=(right.next());
            whichArray++;
        } 
        whichArray-=1;
        int whichArrayFixed=whichArray;
        double variableTotal=0;
        double intTotal=0;
        while(whichArray>=0)
        {
            
                if(rightArray[whichArray].contains("x"))
                {
                    if(rightArray[whichArray].contains("^"))
                    {
                    }
                    else
                    {
                        if(rightArray[whichArray].compareTo("x")==0)
                        {
                            rightArray[whichArray]=rightArray[whichArray].replace("x","1");
                        }
                    rightArray[whichArray]=rightArray[whichArray].replace("x"," ");
                    Scanner convert1=new Scanner(rightArray[whichArray]);                
                    variableTotal = variableTotal +convert1.nextDouble();
                }
                }      
            whichArray--;
        }
        return variableTotal;
    }
    public double integer(String s3)
    {
         Scanner countRight=new Scanner(s3);
        countRight.useDelimiter("\\s*\\+\\s*");
        int howMany=0;
        while(countRight.hasNext())
        {
            countRight.next();
            howMany++;
        }        
        String rightArray[]=new String[howMany];
        Scanner right=new Scanner(s3);
        right.useDelimiter("\\s*\\+\\s*");
        int whichArray=0;
        while(right.hasNext())
        {
            rightArray[whichArray]=(right.next());
            whichArray++;
        } 
        whichArray-=1;
        int whichArrayFixed=whichArray;
        double variableTotal=0;
        double intTotal=0;
        while(whichArray>=0)
        {
            
                if(rightArray[whichArray].contains("x"))
                {}
                else
                {
                    Scanner convert2=new Scanner(rightArray[whichArray]);
                    intTotal=intTotal+convert2.nextDouble();
                    
                }
            
        
            whichArray--;
        }
        return intTotal;
    }   
    
}