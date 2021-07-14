
/**
 * Write a description of TestCaeserCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class TestCaeserCipher {
    public TestCaeserCipher(){}
    
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
    
    public String breakCaeserCipher(String message){
      int [] freq = letterCounter(message);
      int maxIx = indexOfMax(freq);
      int dkey = maxIx - 4;
      if (maxIx < 4){
         dkey = 26 -4 +maxIx; 
        }
      CaeserCipher2 cc = new CaeserCipher2(dkey);
      return cc.decrypt(message);
    }
    
    public void simpleTests(){
        String content = (new FileResource()).asString();
        CaeserCipher2 cc1 = new CaeserCipher2(18);
        String encrypted = cc1.encrypt(content);
        String decrypted = breakCaeserCipher(encrypted);
        System.out.println(content);
        System.out.println(encrypted);
        System.out.println(decrypted);
        
    }
}
