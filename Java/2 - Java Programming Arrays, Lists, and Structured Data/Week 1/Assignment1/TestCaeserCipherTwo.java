
/**
 * Write a description of TestCaeserCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class TestCaeserCipherTwo {
    public TestCaeserCipherTwo(){}
    
    public int indexOfMax(int [] values){
     
        int maxValue = values[0];
        int ixMax = 0;
        for(int i = 1; i<values.length; i++){
            if(values[i] > maxValue){
                ixMax = i;
                maxValue = values[i];
            }
        }

        return ixMax;
    }
    
    public int [] letterCounter(String message){
        int [] count = new int[26];
        
        String letters = "abcdefghijklmnopqrstuvwxyz"; 
        for(int i = 0; i < message.length(); i++){
            int ix = letters.indexOf(message.charAt(i));
            if(ix != -1){
                count[ix] ++;
            }
        }
        
        return count;
    }
    
    public int decryptKey(String message){
      
      int [] freq = letterCounter(message);
      int maxIx = indexOfMax(freq);
      int dkey = maxIx - 4;
      if (maxIx < 4){
         dkey = 26 -4 +maxIx; 
        }
      return dkey;
    }
    
    public String decryptTwoKeys(String message){
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        
        for(int i = 0; i<message.length(); i++){
            if(i%2 == 0){
                sb1.append(message.charAt(i));
            }
            else if(i%2 == 1){
                sb2.append(message.charAt(i));
            }
        }
        
        int decode1 = decryptKey(sb1.toString());
        int decode2 = decryptKey(sb2.toString());
        
        CaeserCipherTwo cc2 = new CaeserCipherTwo(decode1, decode2);
        String message2 = cc2.decrypt(message);
        
        return message2;
    }
    
    public void simpleTests(){
        String test = (new FileResource()).asString();
        CaeserCipherTwo cc3 = new CaeserCipherTwo(17,3);
        String code = cc3.encrypt(test);
        System.out.println(code);
        System.out.println(cc3.decrypt(code));
        
        System.out.println(decryptTwoKeys(code));
    }
}
