
/**
 * Write a description of CaeserCipher2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaeserCipher2 {
    private String alphabet;
    private String shiftedAlphabet; 
    private int mainKey;
    
    public CaeserCipher2(int key){
        mainKey = key;
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        if(key < 0 || key >26){
            shiftedAlphabet = alphabet;
        }
        
        else{
        shiftedAlphabet = alphabet.substring(key) 
        + alphabet.substring(0,key);
        }
    }
    
    public String encrypt(String phrase){
        
        StringBuilder sb = new StringBuilder(phrase);
        
        for(int i = 0; i < phrase.length(); i++){
            char ch = Character.toLowerCase(sb.charAt(i));
            int ix = alphabet.indexOf(ch);
            
            if(ix != -1){
              if(Character.isLowerCase(sb.charAt(i))){
                  sb.setCharAt(i, shiftedAlphabet.charAt(ix));
              }
              
              else{
                  sb.setCharAt(i, shiftedAlphabet.toUpperCase().charAt(ix));
              }
            }
        }
        
        return sb.toString();
    }
    
    public String decrypt(String code){
        CaeserCipher2 cc = new CaeserCipher2(26-mainKey);
        return (cc.encrypt(code));
    }
    
    
}
