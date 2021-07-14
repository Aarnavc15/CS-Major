
/**
 * Write a description of CaeserBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class CaeserBreaker {
    public CaeserBreaker(){
    }
    
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
    
    
    public String decrypt(String message){
      CaeserCipher cc = new CaeserCipher();
      int [] freq = letterCounter(message);
      int maxIx = indexOfMax(freq);
      int dkey = maxIx - 4;
      if (maxIx < 4){
         dkey = 26 -4 +maxIx; 
        }
      
      return cc.encrypt(message, 26 -dkey);
    }
    
    public String decryptTwoKeys(String message){
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        
        for(int i = 0; i<message.length(); i++){
            if(i%2 == 0){
                sb1.append(message.charAt(i));
            }
            else if(i%2 == 1){
                sb2.append(message.charAt(i));
            }
        }
        
        String decode1 = decrypt(sb1.toString());
        String decode2 = decrypt(sb2.toString());
        
        for(int i = 0; i<message.length(); i++){
            if(i%2 == 0){
                sb3.append(decode1.charAt(i/2));
            }
            else if(i%2 == 1){
                sb3.append(decode2.charAt((i-1)/2));
            }
        }
        
        return sb3.toString();
    }
    
    public void tests(){
        String test1 = "Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu";
        System.out.println(decryptTwoKeys(test1));
    }
}
