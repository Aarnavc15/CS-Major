
/**
 * Write a description of CaeserCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaeserCipher {
    public CaeserCipher(){
        
    }
    
    public String encrypt(String phrase, int key){
        if(key >= 26 || key <= 0){
            return phrase;
        }
        
        StringBuilder sb = new StringBuilder(phrase);
        
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String encrypted = alphabet.substring(key) + alphabet.substring(0,key);
        
        for(int i = 0; i < phrase.length(); i++){
            char ch = Character.toLowerCase(sb.charAt(i));
            int ix = alphabet.indexOf(ch);
            
            if(ix != -1){
              if(Character.isLowerCase(sb.charAt(i))){
                  sb.setCharAt(i, encrypted.charAt(ix));
              }
              
              else{
                  sb.setCharAt(i, encrypted.toUpperCase().charAt(ix));
              }
            }
        }
        
        return sb.toString();
    }
    
    public String encryptTwoKeys(String phrase, int key1, int key2){
        if(key1 >= 26 && key1 <= 0 && key2 >= 26 && key2 <= 0){
            return phrase;
        }
        
        StringBuilder sb = new StringBuilder(phrase);
        
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String encrypted1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String encrypted2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        
        for(int i = 0; i < phrase.length(); i++){
            char ch = Character.toLowerCase(sb.charAt(i));
            int ix = alphabet.indexOf(ch);
            
            if(ix != -1){
              if(i%2 == 0){
                  if(Character.isLowerCase(sb.charAt(i))){
                  sb.setCharAt(i, encrypted1.charAt(ix));
                  }
              
                  else{
                      sb.setCharAt(i, encrypted1.toUpperCase().charAt(ix));
                  }
              }
              
              else if (i%2 == 1){
                  if(Character.isLowerCase(sb.charAt(i))){
                  sb.setCharAt(i, encrypted2.charAt(ix));
                   }
              
                   else{
                       sb.setCharAt(i, encrypted2.toUpperCase().charAt(ix));
                  }    
              }
            }
        }
        
        return sb.toString();
    }
    
    public void tests(){
     
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }
}
