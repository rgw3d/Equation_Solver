/*
 * new attempt at solving tri-nomials
 */
import java.util.*;
public class trinomialsNew

{
    public static String tri(double one, double two, double three, double four)
    {
         //first, find the factors in "four" and in "one" and then store them in arrays
        double a=Math.abs(one);
        double d=Math.abs(four); 
        int loopCount=0;
        while(d>0)//count the number of times that there are factors
        {
            if(four%d==0)            
                loopCount+=1;            
            d--;
        }
        d=Math.abs(four);
        double foff[]=new double[loopCount];//(factors of four) thats the array name
        loopCount--; 
        while(d>=0)//give the array instances a value
        {
            if(four%d==0){
                foff[loopCount]=(four/d);
                loopCount--;
            }
                
            d--;
        }
        System.out.println(Arrays.toString(foff));
        System.out.println("d"+d);
        
        loopCount=0;
        while(a>0)//count the number of times that there are factors
        {
            if(one%a==0)            
                loopCount+=1;            
            a--;
        }
        a=Math.abs(one);
        double fofo[]=new double[loopCount];//(factors of one) thats the array name
        loopCount--;        
        while(a>=0)//give the array instances a value
        {
            if(one%a==0){
               fofo[loopCount]=one/a;
               loopCount--;
            }
            a--;
        }        
        System.out.println(Arrays.toString(fofo));
        //Now we have tow arrays that have all the factors of both p and q
        String send="";
        for(double dummyFour: foff)
        {
            for(double dummyOne: fofo)
            {
                double x= dummyFour/dummyOne;
                if(((((((one*x)+two)*x)+three)*x)+four)==0)
                    send=send+x+" ";
                x=-dummyFour/dummyOne;
                if(((((((one*x)+two)*x)+three)*x)+four)==0)
                    send=send+x+" ";
            }
        }
        
        return send;         
    }
    public static String quad(double one, double two, double three, double four, double five)
    {
         //first, find the factors in "four" and in "one" and then store them in arrays
        double a=Math.abs(one);
        double e=Math.abs(five);
        int loopCount=0;
        while(e>0)//count the number of times that there are factors
        {
            if(five%e==0)            
                loopCount+=1;            
            e--;
        }
        e=Math.abs(five);
        double foff[]=new double[loopCount];//(factors of five) thats the array name
        loopCount--; 
        while(e>=0)//give the array instances a value
        {
            if(five%e==0){
                foff[loopCount]=(five/e);
                loopCount--;
            }
                
            e--;
        }
        System.out.println(Arrays.toString(foff));

        
        loopCount=0;
        while(a>0)//count the number of times that there are factors
        {
            if(one%a==0)            
                loopCount+=1;            
            a--;
        }
        a=Math.abs(one);
        double fofo[]=new double[loopCount];//(factors of one) thats the array name
        loopCount--;        
        while(a>=0)//give the array instances a value
        {
            if(one%a==0){
               fofo[loopCount]=one/a;
               loopCount--;
            }
            a--;
        }        
        System.out.println(Arrays.toString(fofo));
        //Now we have tow arrays that have all the factors of both p and q
        String send="";
        for(double dummyFive: foff)
        {
            for(double dummyOne: fofo)
            {
                double x= dummyFive/dummyOne;//just so i dont have to keep dividing it
                if(((((((((one*x)+two)*x)+three)*x)+four)*x)+five)==0)
                    send=send+x+" ";
                x=-dummyFive/dummyOne;
                if(((((((((one*x)+two)*x)+three)*x)+four)*x)+five)==0)
                    send=send+x+" ";
            }
        }
        
        return send;         
    }
}