
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class WordPlay {

    public WordPlay(){
        
    }
    
    public boolean isVowel(char ch){
        char chMod = Character.toLowerCase(ch);
        String vowels = "aeiou";
        if (vowels.indexOf(chMod) != -1){
            return true;
        }
        
        else{
            return false;
        }
    }
    
    public String replaceVowels (String phrase, char ch){
        
        StringBuilder sb = new StringBuilder(phrase);
        
        for(int i = 0; i < phrase.length(); i++){
         
            if(isVowel(sb.charAt(i))){
                sb.setCharAt(i, ch);
            }
            
        }
        
        return sb.toString();
    }
    
    public String emphasize(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        
        for(int i = 0; i < phrase.length(); i++){
         
            if(Character.toLowerCase(sb.charAt(i)) == Character.toLowerCase(ch)){
                if(i%2 == 1){
                    sb.setCharAt(i, '+');
                }
                
                else if(i%2 == 0){
                    sb.setCharAt(i, '*');
                }
            }
            
        }
        
        return sb.toString();
    }
    
    public void tests(){
     
        System.out.println(isVowel('u'));
        System.out.println(replaceVowels("Hello World", '*'));
        System.out.println(emphasize("dna ctgaaactga", 'a'));
    }
}
