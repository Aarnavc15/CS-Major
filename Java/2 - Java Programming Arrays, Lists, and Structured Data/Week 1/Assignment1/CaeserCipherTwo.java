
/**
 * Write a description of CaeserCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;

public class CaeserCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaeserCipherTwo(int key1, int key2){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        if (key1 < 0 || key1 > 26){
            shiftedAlphabet1 = alphabet;
            mainKey1 = 0;
        }
        
        else{
            shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
            mainKey1 = key1;
        }
        
        if (key2 < 0 || key2 > 26){
            shiftedAlphabet2 = alphabet;
            mainKey2 = 0;
        }
        
        else{
            shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
            mainKey2 = key2;
        }
    }
    
    public String encrypt(String phrase){
        
        StringBuilder sb = new StringBuilder(phrase);
        
        for(int i = 0; i < phrase.length(); i++){
            char ch = Character.toLowerCase(sb.charAt(i));
            int ix = alphabet.indexOf(ch);
            
            if(ix != -1){
              if(i%2 == 0){
                  if(Character.isLowerCase(sb.charAt(i))){
                  sb.setCharAt(i, shiftedAlphabet1.charAt(ix));
                  }
              
                  else{
                      sb.setCharAt(i, shiftedAlphabet1.toUpperCase().charAt(ix));
                  }
              }
              
              else if (i%2 == 1){
                  if(Character.isLowerCase(sb.charAt(i))){
                  sb.setCharAt(i, shiftedAlphabet2.charAt(ix));
                   }
              
                   else{
                       sb.setCharAt(i, shiftedAlphabet2.toUpperCase().charAt(ix));
                  }    
              }
            }
        }
        
        return sb.toString();
    }
    
    public String decrypt(String code){
        CaeserCipherTwo cc = new CaeserCipherTwo(26-mainKey1, 26-mainKey2);
        return (cc.encrypt(code));
    
    }
}
